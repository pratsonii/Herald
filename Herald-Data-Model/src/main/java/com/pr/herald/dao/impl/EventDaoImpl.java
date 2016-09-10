package com.pr.herald.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Circle;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.pr.herald.models.Events;

@Component
public class EventDaoImpl  
{
	@Autowired
	MongoTemplate template;
	
	public List<Events> findEventsNearPoint(Double lng, Double lat, Long distance, String category, String status)
	{
		Point point = new Point(lng, lat);
		Distance d = new Distance(distance, Metrics.KILOMETERS);
		Circle c = new Circle(point, d);

		List<Events> events =
			    template.find(new Query(Criteria.where("location").withinSphere(c))
			    				 .addCriteria(Criteria.where("categoryName").is(category))
			    				 .addCriteria(Criteria.where("status").is(status)), 
			    		     Events.class);
		
		return events;
	}
}
