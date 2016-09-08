package com.pr.herald.serviceImpl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pr.herald.dao.CategoryDao;
import com.pr.herald.models.Categories;
import com.pr.herald.service.Category;

@Service
@Transactional
public class CategoryImpl implements Category 
{
	@Autowired
	CategoryDao categoryDao;
	
	@Override
	public void addCategory(Categories c) 
	{	
		categoryDao.save(c);
	}

}
