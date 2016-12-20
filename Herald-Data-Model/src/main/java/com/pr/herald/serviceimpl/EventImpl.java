package com.pr.herald.serviceimpl;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pr.herald.base.BaseException;
import com.pr.herald.contants.Constants.EventCategories;
import com.pr.herald.contants.Constants.EventReaction;
import com.pr.herald.contants.Constants.EventStatus;
import com.pr.herald.dao.EventsDao;
import com.pr.herald.dao.PlansDao;
import com.pr.herald.dao.impl.EventDaoImpl;
import com.pr.herald.dto.AppStartResponseDto;
import com.pr.herald.dto.CategoryResponseDto;
import com.pr.herald.dto.EventReactionDto;
import com.pr.herald.dto.EventRequestDto;
import com.pr.herald.dto.EventResponseDto;
import com.pr.herald.dto.PlanResponseDto;
import com.pr.herald.models.Categories;
import com.pr.herald.models.Devices;
import com.pr.herald.models.Events;
import com.pr.herald.models.Plans;
import com.pr.herald.service.CategoryServ;
import com.pr.herald.service.EventServ;

@Service
@Transactional
public class EventImpl implements EventServ 
{	
	@Autowired
	EventsDao dao;
	
	@Autowired
	EventDaoImpl daoImpl;
	
	@Autowired
	PlansDao planDao;

	@Autowired
	CategoryServ categoryServ;
	
	@Override
	public Events addEvent(Events e) throws BaseException 
	{	
		eventChecks(e);
		e.setCreatedDate(new Date());
		e.setUpdatedDate(new Date());
		e.setStatus(EventStatus.active);
		e.setLife(planDao.findOne(e.getPlanId()).getLife());
		
		return dao.insert(e);
	}
	
	private void eventChecks(Events e) throws BaseException
	{
		for(String c : e.getCategoryName())
		{
			if(StringUtils.equals(c, EventCategories.featured))
				throw new BaseException("Event of category 'Featured' cant be created!");
		}
		
			
	}

	@Override
	public void updateEvent(EventRequestDto dto) 
	{
		Events e = dao.findOne(dto.getId());
		e.getCategoryName().clear();
		e = dto.convertToModel(e);
		e.setUpdatedDate(new Date());
		
		dao.save(e);
	}

	@Override
	public void reactivateEvent(String eventId) throws BaseException 
	{
		Events e = dao.findOne(eventId);
		if(StringUtils.equals(e.getStatus(), EventStatus.inActive))
		{
			Long relive = planDao.findOne(e.getPlanId()).getRelive();
			Date reliveDate = java.sql.Date.valueOf( LocalDate.now().minusDays(relive) );
			Date inactiveDate = e.getUpdatedDate();
			int days = daysBetween(reliveDate, inactiveDate);
			
			if( days >= 0)
			{
				e.setCreatedDate(new Date());
				e.setUpdatedDate(new Date());
				e.setStatus(EventStatus.active);
				dao.save(e);
			}
			else
			{
				days = days - (days*2);
				throw new BaseException("Wait For "+days+" day(s) to reactivate! ");
			}
		}
		else
		{
			throw new BaseException(" Event is Already Active!");
		}
	}

	@Override
	public Events updateReaction(EventReactionDto dto) throws BaseException 
	{
		Events e = dao.findOne(dto.getEventId());
		
		boolean reacted = false;
		
		if(StringUtils.equalsIgnoreCase(dto.getReaction(), EventReaction.like))
		{
			reacted = e.getLikedByDevice().add(dto.getImei());
			e.setLikes((long) e.getLikedByDevice().size());
		}
		else if(StringUtils.equalsIgnoreCase(dto.getReaction(), EventReaction.dislike))
		{
			reacted = e.getDislikedByDevice().add(dto.getImei());
			e.setDislikes((long) e.getDislikedByDevice().size() );
		}
		
		if(!reacted)
		{
			throw new BaseException(" You have already reacted!");
		}
		return dao.save(e);
	}

	@Override
	public List<Events> getNearByEvents(Double lng,
									    Double lat, 
									    String category, 
									    Double distance) 
	{
		return daoImpl.findEventsNearPoint(lng, lat, distance, category, EventStatus.active);
	}

	@Override
	public List<Events> getEventsWithinBox(Double lng1, 
		    							   Double lat1, 
		    							   Double lng2, 
		    							   Double lat2, 
		    							   String category) 
	{
		return daoImpl.findEventsWithinBox(lng1, lat1, lng2, lat2, category, EventStatus.active);
	}

	@Override
	public List<Events> getUserEvents(String mailId) 
	{
		return dao.findByUserMailIdOrderByStatusAscCreatedDateDesc(mailId);
	}
	
	public void deactivateCompletedEvents()
	{
		
	}

	@Override
	public void upgradeToFeatured() 
	{
		daoImpl.upgradeToFeatured();
	}
	


	@Override
	public void deActivateDislikedEvents() 
	{
		daoImpl.deActivateDislikedEvents();
	}
	
	@Override
	public void deleteEvent(String eventId) 
	{
		dao.delete(eventId);
	}

	@Override
	public AppStartResponseDto startUpEvent(Devices d, 
											Double lng1, 
											Double lat1, 
											Double lng2, 
											Double lat2) 
	{
		List<Events> events = getNearByEvents(d.getLocation().getX(), 
											  d.getLocation().getY(), 
											  EventCategories.featured, 
											  lng1);
		
//		List<Events> events = getEventsWithinBox(lng1, lat1, lng2, lat2, 
//				  EventCategories.featured);
		
		List<EventResponseDto> eventList = new EventResponseDto().convetToDto(events);
		AppStartResponseDto dto = new AppStartResponseDto();
		dto.setEvents(eventList);
		
		List<Categories> categories = categoryServ.findAllCategory();
		List<CategoryResponseDto> categoryDto = categories.stream().map(  c -> 
												new CategoryResponseDto().convertToDto(c, d.getFavCategories())
												).collect(Collectors.toList());
		dto.setCategories(categoryDto);
		return dto;
	}

	@Override
	public List<Events> searchNearByEvents(Double lng, Double lat, String searchString, Double distance) 
	{
		return daoImpl.searchEvents(searchString, lng, lat, distance, EventStatus.active);
	}

	public static int daysBetween(Date d1, Date d2)
	{
		return (int)( (d1.getTime() - d2.getTime()) / (1000 * 60 * 60 * 24));
	}

	@Override
	public List<EventResponseDto> addPlanToDto(List<EventResponseDto> dtos) 
	{
		for(EventResponseDto dto : dtos)
		{
			Plans p = planDao.findOne(dto.getPlanId());
			dto.setPlan(new PlanResponseDto().convetToDto(p));
		}
		return dtos;
	}

	@Override
	public Events getEventByID(String eventId) 
	{
		return dao.findOne(eventId);
	}

}
