package com.pr.herald.serviceImpl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pr.herald.contants.Constants.EventReaction;
import com.pr.herald.contants.Constants.EventStatus;
import com.pr.herald.dao.EventsDao;
import com.pr.herald.dao.PlansDao;
import com.pr.herald.dao.impl.EventDaoImpl;
import com.pr.herald.dto.EventReactionDto;
import com.pr.herald.dto.EventRequestDto;
import com.pr.herald.models.Events;
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
	
	@Override
	public Events addEvent(Events e) 
	{	
		e.setCreatedDate(new Date());
		e.setUpdatedDate(new Date());
		e.setStatus(EventStatus.active);
		e.setLife(planDao.findOne(e.getPlanId()).getLife());
		
		return dao.insert(e);
	}

	@Override
	public void updateEvent(EventRequestDto dto) 
	{
		Events e = dao.findOne(dto.getId());
		
		e = dto.convertToModel(e);
		e.setUpdatedDate(new Date());
		
		dao.save(e);
	}

	@Override
	public void updateReaction(EventReactionDto dto) 
	{
		Events e = dao.findOne(dto.getEventId());
		
		if(StringUtils.equalsIgnoreCase(dto.getReaction(), EventReaction.like))
		{
			e.getLikedByDevice().add(dto.getDeviceToken());
			e.setLikes((long) e.getLikedByDevice().size());
		}
		else if(StringUtils.equalsIgnoreCase(dto.getReaction(), EventReaction.dislike))
		{
			e.getDislikedByDevice().add(dto.getDeviceToken());
			e.setDislikes((long) e.getDislikedByDevice().size() );
		}
		
		dao.save(e);
	}

	@Override
	public List<Events> getNearByEvents(Double lng,
									    Double lat, 
									    String category, 
									    Long distance) 
	{
		return daoImpl.findEventsNearPoint(lng, lat, distance, category, EventStatus.active);
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
	public List<Events> upgradeToFeatured() 
	{
		daoImpl.upgradeToFeatured();
		
		return null;
	}
	


	@Override
	public List<Events> deActivateDislikedEvents() 
	{
		daoImpl.deActivateDislikedEvents();
		
		return null;
	}

}
