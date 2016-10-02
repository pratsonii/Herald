package com.pr.herald.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "config")
public class Config 
{
	private String id;
	private String defaultDataFlag;
	
	@Id
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getDefaultDataFlag() {
		return defaultDataFlag;
	}
	public void setDefaultDataFlag(String defaultDataFlag) {
		this.defaultDataFlag = defaultDataFlag;
	}
}
