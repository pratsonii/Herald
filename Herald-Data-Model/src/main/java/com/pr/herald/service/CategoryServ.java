package com.pr.herald.service;

import java.util.List;

import com.pr.herald.base.BaseException;
import com.pr.herald.models.Categories;

public interface CategoryServ 
{
	void addCategory(Categories c) throws BaseException;
	public void updateCategory(Categories c);
	public List<Categories> findAllCategory();
}
