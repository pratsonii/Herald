package com.pr.herald.utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class FileReaderUtility 
{
	private final String textExt = ".txt";
	private final String semicolon = ";";
	private final String classPath = "Scripts/";
	
	Logger log = Logger.getLogger(this.getClass());
	
	public <T> List<T> getJsonObjectsFromFile(String fileName, Class<T> cls) throws FileNotFoundException
	{
		File file = fetchClassPathFile(fileName, textExt);
		List<String> stringJsonList = getScannedStrings(file, semicolon);
		return stringsToObjects(cls, stringJsonList);		
	}
	
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info("decoder path:" + filePath);
		return new File(filePath);
	}
	
	private List<String> getScannedStrings(File file, String delimiter) throws FileNotFoundException
	{
		List<String> scannedStrings = new ArrayList<>();
		Scanner scanner = new Scanner(file).useDelimiter(delimiter);

		while(scanner.hasNext()) 
		{			// Get statement 
			String s = scanner.next();
//			log.info("Statement: " +s);
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return objList;
	}
}
