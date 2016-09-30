package com.pr.herald.service;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;

public interface StartUpServ 
{
	void loadInitialData() throws FileNotFoundException, 
									NoSuchMethodException, 
									SecurityException, 
									IllegalAccessException, 
									IllegalArgumentException, 
									InvocationTargetException;
}
