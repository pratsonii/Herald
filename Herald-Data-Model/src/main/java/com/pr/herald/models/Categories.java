package com.pr.herald.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T_H_Categories")
public class Categories {
	
	private String categoryName;
	private String parentCategoryName;
	private String masterCategoryFlag;
	

	@Id
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	
	public String getMasterCategoryFlag() {
		return masterCategoryFlag;
	}
	public void setMasterCategoryFlag(String masterCategoryFlag) {
		this.masterCategoryFlag = masterCategoryFlag;
	}
	
	public String getParentCategoryName() {
		return parentCategoryName;
	}
	public void setParentCategoryName(String parentCategoryName) {
		this.parentCategoryName = parentCategoryName;
	}
		
	
}
