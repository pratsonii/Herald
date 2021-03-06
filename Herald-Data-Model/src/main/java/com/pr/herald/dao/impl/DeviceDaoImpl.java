package com.pr.herald.dao.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.geo.Circle;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.mongodb.client.MongoDatabase;
import com.pr.herald.models.Devices;

@Component
public class DeviceDaoImpl 
{

	
	@Autowired
	MongoTemplate template;
	
	@Autowired
	@Qualifier("db")
	MongoDatabase db;
	
	public List<Devices> findDevicesToNotify(Double lng, Double lat, Long distance, Set<String> favCategory)
	{
		Point point = new Point(lng, lat);
		Distance d = new Distance(distance, Metrics.KILOMETERS);
		Circle c = new Circle(point, d);

		return
			    template.find(new Query(Criteria.where("location").withinSphere(c))
			    				 .addCriteria(Criteria.where("favCategories").in(favCategory)), 
			    				 Devices.class);
		
	}
}
