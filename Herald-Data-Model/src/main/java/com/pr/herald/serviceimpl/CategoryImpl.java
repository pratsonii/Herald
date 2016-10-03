package com.pr.herald.serviceimpl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.pr.herald.base.BaseException;
import com.pr.herald.contants.Constants.EventCategories;
import com.pr.herald.dao.CategoryDao;
import com.pr.herald.models.Categories;
import com.pr.herald.service.CategoryServ;

@Service
@org.springframework.transaction.annotation.Transactional
public class CategoryImpl implements CategoryServ 
{
	Logger log  = Logger.getLogger(this.getClass());
	
	@Autowired
	CategoryDao categoryDao;
	
	@Override
	public void addCategory(Categories c) throws BaseException 
	{	
		try
		{
			c.setCreatedDate(new Date());
			c.setUpdatedDate(new Date());
			categoryDao.insert(c);
		}
		catch(DuplicateKeyException e)
		{
			log.info("---"+e.getMessage()+"---");
			throw new BaseException("Category '"+c.getCategoryName()+"' already exists!");
		}
	}
	


	@Override
	public void updateCategory(Categories c)
	{	
		c.setUpdatedDate(new Date());
		categoryDao.save(c);
	}


	@Override
	public List<Categories> findAllCategory() 
	{	
		return categoryDao.findByOrderByPriority();
	}


	@Override
	public List<Categories> findCategoryExceptFeatured() 
	{	
		List<Categories> categories = categoryDao.findByOrderByPriority();
		for(int i = 0; i < categories.size(); i++)
		{
			if(StringUtils.equals(categories.get(i).getCategoryName(), EventCategories.featured))
			{
				categories.remove(i);
			}
		}
		return categories;
	}
}
