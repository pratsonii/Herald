package com.pr.herald.dto;

import com.pr.herald.models.Categories;

public class CategoryRequestDto 
{
	private String categoryName;
	private String parentCategoryName;
	
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getParentCategoryName() {
		return parentCategoryName;
	}
	public void setParentCategoryName(String parentCategoryName) {
		this.parentCategoryName = parentCategoryName;
	}
	
	public CategoryRequestDto() {
		super();
	}
	public CategoryRequestDto(String categoryName, String parentCategoryName) {
		super();
		this.categoryName = categoryName;
		this.parentCategoryName = parentCategoryName;
	}
	
	public Categories convertToModels()
	{
		Categories model = new Categories();
		
		model.setCategoryName(this.categoryName);
		model.setParentCategoryName(parentCategoryName);
		
		return model;
	}
	
	public CategoryRequestDto convertToDto(Categories model)
	{
		this.categoryName = model.getCategoryName();
		this.parentCategoryName = model.getParentCategoryName();
		
		return this;
	}
}
