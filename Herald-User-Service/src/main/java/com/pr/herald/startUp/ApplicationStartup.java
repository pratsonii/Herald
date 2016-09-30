package com.pr.herald.startUp;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.pr.herald.service.StartUpServ;

@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> 
{
	@Autowired
	StartUpServ startUp;
	
	@Override
	public void onApplicationEvent(final ApplicationReadyEvent event) 
	{
		System.out.println("On App Start");
		
		try {
			startUp.loadInitialData();
		} catch (FileNotFoundException | NoSuchMethodException | SecurityException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
