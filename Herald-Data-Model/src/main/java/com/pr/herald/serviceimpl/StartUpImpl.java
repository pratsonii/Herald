package com.pr.herald.serviceimpl;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pr.herald.contants.Constants;
import com.pr.herald.dao.CategoryDao;
import com.pr.herald.dao.ConfigDao;
import com.pr.herald.dao.PlansDao;
import com.pr.herald.models.Categories;
import com.pr.herald.models.Config;
import com.pr.herald.models.Plans;
import com.pr.herald.service.StartUpServ;
import com.pr.herald.utility.FileReaderUtility;

@Service
@Transactional
public class StartUpImpl implements StartUpServ 
{
	@Autowired
	FileReaderUtility reader;
	
	@Autowired
	PlansDao planDao;
	
	@Autowired
	CategoryDao categoryDao;
	
	@Autowired
	ConfigDao configDao;

	@Override
	public void loadInitialData() throws FileNotFoundException, 
										 NoSuchMethodException, 
										 SecurityException, 
										 IllegalAccessException, 
										 IllegalArgumentException, 
										 InvocationTargetException 
	{
		
		loadDefaultData();
	}

	private void loadDefaultData()
			throws FileNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException 
	{
		if(checkConfig(Constants.defaultData))
		{
			loadPlan();
			loadCategory();
			
			setConfigFalse(Constants.defaultData);
		}
	}

	private void loadPlan()
			throws FileNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException 
	{
		List<Plans> plans = reader.getJsonObjectsFromFile("plan", Plans.class);
		setDefaultDates(plans);
		planDao.save(plans);
	}
	
	private void loadCategory()
			throws FileNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException 
	{
		List<Categories> c = reader.getJsonObjectsFromFile("categories", Categories.class);
		setDefaultDates(c);
		categoryDao.save(c);
	}

	private <T> List<T> setDefaultDates(List<T> models) throws NoSuchMethodException, 
															   SecurityException, 
															   IllegalAccessException, 
															   IllegalArgumentException, 
															   InvocationTargetException
	{
		for(T m : models)
		{
			Method setCreatedDate = m.getClass().getMethod("setCreatedDate", Date.class);
			Method setUpdatedDate = m.getClass().getMethod("setUpdatedDate", Date.class);
			
			setCreatedDate.invoke(m, new Date());
			setUpdatedDate.invoke(m, new Date());
		}
		
		return models;
	}
	
	private boolean checkConfig(String name)
	{
		Config c = configDao.findOne(name);
		if(c == null)
		{
			c = new Config();
			c.setId(name);
			c.setDefaultDataFlag(Constants.trueValue);
			configDao.save(c);
		}
		
		return StringUtils.equals(c.getDefaultDataFlag(), Constants.trueValue);
	}
	
	private void setConfigFalse(String name)
	{
		Config c = new Config();
		c.setId(name);
		c.setDefaultDataFlag(Constants.falseValue);
		configDao.save(c);
	}
	
}
