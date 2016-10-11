package com.pr.herald.service;

import java.io.IOException;

import org.json.simple.parser.ParseException;

public interface NotificationServ 
{
	public void notifyDevices(String eventId) throws IOException, ParseException;
}
