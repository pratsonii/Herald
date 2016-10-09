package com.pr.herald.utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class FileReaderUtility 
{
	private static final String textExt = ".txt";
	private static final String semicolon = ";";
	private static final String classPath = "/Scripts/";
	
	Logger log = Logger.getLogger(this.getClass());
	
	public <T> List<T> getJsonObjectsFromFile(String fileName, Class<T> cls) throws FileNotFoundException
	{
//		File file = fetchClassPathFile(fileName, textExt);
		InputStream stream = fetchClassPathFile(fileName, textExt);
		List<String> stringJsonList = getScannedStrings(stream, semicolon);
		return stringsToObjects(cls, stringJsonList);		
	}
	
	private InputStream fetchClassPathFile(String fileName, String fileExtension) 
	{
		String fileLocation = null;
		String filePath = null;
		InputStream stream = null;
		try {
			fileLocation = classPath + fileName + fileExtension;
			log.info("file location:" + fileLocation);
//			filePath = URLDecoder.decode(classLoader.getResource(fileLocation).getFile(),"UTF-8");
			log.info("-----Testing -------");
			stream = this.getClass().getResourceAsStream(fileLocation);
		} catch (Exception e) 
		{
			log.info("---"+e.getMessage()+"---");
		}
		return  stream;
	}
	/*
	private File fetchClassPathFile(String fileName, String fileExtension) 
	{
		ClassLoader classLoader = null;
		String fileLocation = null;
		String filePath = null;
		try {
			classLoader = getClass().getClassLoader();
			fileLocation = classPath + fileName + fileExtension;
			log.info("file location:" + fileLocation);
			filePath = URLDecoder.decode(classLoader.getResource(fileLocation).getFile(),"UTF-8");
		} catch (Exception e) 
		{
			log.info("---"+e.getMessage()+"---");
		}
		log.info("decoder path:" + filePath);
		return new File(filePath);
	}*/
	/*
	private List<String> getScannedStrings(File file, String delimiter) throws FileNotFoundException
	{
		List<String> scannedStrings = new ArrayList<>();
		Scanner scanner = new Scanner(file).useDelimiter(delimiter);

		while(scanner.hasNext()) 
		{
			String s = scanner.next();
			scannedStrings.add(s);
		}

		scanner.close();
		
		return scannedStrings;
	}*/
	
	private List<String> getScannedStrings(InputStream stream, String delimiter) throws FileNotFoundException
	{
		List<String> scannedStrings = new ArrayList<>();
		Scanner scanner = new Scanner(stream).useDelimiter(delimiter);

		while(scanner.hasNext()) 
		{
			String s = scanner.next();
			scannedStrings.add(s);
		}

		scanner.close();
		
		return scannedStrings;
	}
	
	private <T> List<T> stringsToObjects(Class<T> cls, List<String> strings)
	{
		List<T> objList = new ArrayList<>();
		for(String s : strings)
		{
			ObjectMapper mapper = new ObjectMapper();
			
			try 
			{
				objList.add(mapper.readValue(s, cls));
			} 
			catch (IOException e) {
				log.info("---"+e.getMessage()+"---");
			}
		}
		
		return objList;
	}
}
