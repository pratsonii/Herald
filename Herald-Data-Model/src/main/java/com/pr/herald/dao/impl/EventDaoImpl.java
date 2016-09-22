package com.pr.herald.dao.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.geo.Circle;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.pr.herald.contants.Constants.EventCategories;
import com.pr.herald.contants.Constants.EventStatus;
import com.pr.herald.models.Events;

@Component
public class EventDaoImpl  
{
	@Value("${events.max.likes}")
	private Long maxLikes;

	@Value("${events.max.dislikes}")
	private Long maxDisLikes;
	
	@Autowired
	MongoTemplate template;
	
	@Autowired
	@Qualifier("db")
	MongoDatabase db;
	
	public void updateOverDueEvents(String eventId, String eventStatus)
	{
		template.updateFirst(new Query(Criteria.where("_id").is(eventId)), Update.update("status", eventStatus), Events.class);
	}
	
	public void upgradeToFeatured()
	{
		Query q = new Query(Criteria.where("status").is(EventStatus.active) 
			    									.and("categoryName").ne(EventCategories.featured)
			    									.and("likes").gt(maxLikes));
		
		
		template.updateMulti(q, new Update().addToSet("categoryName", EventCategories.featured), Events.class);
	}
	
	public void deActivateDislikedEvents()
	{

		Query q = new Query(Criteria.where("status").is(EventStatus.active) 
			    									.and("categoryName").ne(EventCategories.featured)
			    									.and("dislikes").gt(maxDisLikes));
		
		template.updateMulti(q, new Update().set("status", EventStatus.inActive), Events.class);
	}
	
	public List<Events> findEventsNearPoint(Double lng, Double lat, Long distance, String category, String status)
	{
		Point point = new Point(lng, lat);
		Distance d = new Distance(distance, Metrics.KILOMETERS);
		Circle c = new Circle(point, d);

		return
			    template.find(new Query(Criteria.where("location").withinSphere(c))
			    				 .addCriteria(Criteria.where("categoryName").is(category))
			    				 .addCriteria(Criteria.where("status").is(status)), 
			    		     Events.class);
		
	}
	
	public List<Document> findOverdueEvents(String status, Date date)
	{
		MongoCollection<Document> col = db.getCollection("T_H_Events");
		
		BasicDBList multiply  = new BasicDBList();
		multiply.add("$life");
		multiply.add( 1000 * 60 * 60 * 24);
		
		
		BasicDBObject multiplyObj = new BasicDBObject("$multiply", multiply);

		BasicDBList add  = new BasicDBList();
		add.add("$createdDate");
		add.add(multiplyObj);
		
		BasicDBObject addObj = new BasicDBObject("$add", add);
		
		Document diff = new Document("diff", addObj);
		
		List<Document> pipeline = Arrays.asList(new Document("$match", 
				  											  new Document("status", status)),
												new Document("$project", diff ),
												new Document("$match", 
															  new Document("diff", new Document("$lt", date))));
		
		return col.aggregate(pipeline).into(new ArrayList<>());
		
	}
	
	
	
//	db.getCollection('T_H_Events').aggregate([
//	                                          { "$project": { "diff" : { $add:["$createdDate", {$multiply:["$life", 1000 * 60 * 60 * 24]}]}}},
//	                                          {"$match": {"diff" :{"$lt": new Date()}}}
//	                                          
//	                                          ])

//	AggregationOperation  match = Aggregation.project("diff").andExpression(expression, params).
//	AggregationOperation  match = Aggregation.match( Criteria.where("status").is(status));
//	AggregationOperation project = Aggregation.project("life").andExpression("life").multiply(1000*60*60*24).as("life1");
//	AggregationOperation project1 = Aggregation.project("createdDate","life1").andExpression("createdDate",project).
//			plus("life1").as("createdDate");
////	AggregationOperation project2 = Aggregation.project("createdDate");//,"life1")
////			.andExpression("life1").plus("createdDate")
////			.as("createdDate");7
////	AggregationOperation  match1 = Aggregation.match( Criteria.where("diff").lt(date));
//	
//	Aggregation agg = Aggregation.newAggregation(match, project1);//, project2);//, match1);
//	
//	AggregationResults<Events> r = template.aggregate(agg, Events.class, Events.class
//			);
//	List<Events> result = r.getMappedResults();
	
//	List<Events> events = template.find(new Query( Criteria.where("status").is(status).and(key)), 
//			Events.class);
	
	
}
