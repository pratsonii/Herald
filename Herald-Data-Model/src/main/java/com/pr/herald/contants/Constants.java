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
	public static final String trueValue = "Y";
	public static final String falseValue = "N";
	public static final String defaultData = "Default Data";
	public static final String invalidEmail = "Invalid mail address";
	public static final String invalidUserPass = "Invalid Username/Password";
	public static final String cantEmpty = "Cannot be empty!";

	public static class EventStatus
	{
		public static final String active = "Active";
		public static final String inActive = "In Active";
	}

	public static class PlanStatus
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
	
	public static class UserRoles
	{
		public static final String user = "ROLE_USER";
		public static final String admin = "ROLE_ADMIN";
	}
}
