package com.pr.herald.startup;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.pr.herald.service.StartUpServ;

@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> 
{
	Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	StartUpServ startUp;
	
	@Override
	public void onApplicationEvent(final ApplicationReadyEvent event) 
	{
		log.info("On App Start");
		
		try {
			startUp.loadInitialData();
		} catch (FileNotFoundException | NoSuchMethodException | SecurityException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e) {
			log.info(e.getMessage());
		}
		
	}

}
