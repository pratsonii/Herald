package com.pr.herald.contants;

import org.springframework.stereotype.Component;

@Component
public class Constants 
{
	public static final String addSuccess = "Added successfully";
	public static final String retriveSuccess = "Retirved successfully";
	public static final String registerSuccess = "Registered successfully";
	public static final String updatSuccess = "Updated successfully";
	public static final String noData = "No data!";

	public static class EventStatus
	{
		public static final String active = "Active";
		public static final String inActive = "In Active";
	}

	public static class EventCategories
	{
		public static final String featured = "Featured";
	}
	
	public static class EventReaction
	{
		public static final String like = "Like";
		public static final String dislike = "dislike";
	}
	
	public static class LocationType
	{
		public static final String point = "Point";
	}
}
