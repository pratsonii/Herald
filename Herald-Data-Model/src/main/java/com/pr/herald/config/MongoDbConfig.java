package com.pr.herald.config;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

@Configuration
public class MongoDbConfig 
{
	Logger log = Logger.getLogger(this.getClass());
	
	@Bean(name = "db")
	public MongoDatabase mongo()
	{
		log.info("-- Mongo Db bean ---");
		MongoClient client = new MongoClient();
		MongoDatabase db = client.getDatabase("herald");
		return db;
	}
}
