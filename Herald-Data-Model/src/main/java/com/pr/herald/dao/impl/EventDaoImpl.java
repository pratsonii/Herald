package com.pr.herald.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Circle;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
//import org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
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
	
	public void find(String status, Date date)
	{
		AggregationOperation  match = Aggregation.match( Criteria.where("status").is(status));
//		AggregationOperation project1 = Aggregation.project().andExpression("add(createdDate, ( multiply(life, 1000 * 60 * 60 * 24)))")
		AggregationOperation project1 = Aggregation.project("createdDate","life").andExpression("life").
				multiply(1000*60*60*24).as("life1");
		AggregationOperation project2 = Aggregation.project("createdDate","life1")
				.andExpression("life1").plus("createdDate")
				.as("createdDate");
//		AggregationOperation  match1 = Aggregation.match( Criteria.where("diff").lt(date));
		
		Aggregation agg = Aggregation.newAggregation(match, project1, project2);//, match1);
		
		AggregationResults<Events> r = template.aggregate(agg, Events.class, Events.class
				);
		List<Events> result = r.getMappedResults();
		
//		List<Events> events = template.find(new Query( Criteria.where("status").is(status).and(key)), 
//				Events.class);
	}
	
	
	
//	db.getCollection('T_H_Events').aggregate([
//	                                          { "$project": { "diff" : { $add:["$createdDate", {$multiply:["$life", 1000 * 60 * 60 * 24]}]}}},
//	                                          {"$match": {"diff" :{"$lt": new Date()}}}
//	                                          
//	                                          ])

}
