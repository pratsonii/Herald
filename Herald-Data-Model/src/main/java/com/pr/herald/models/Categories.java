package com.pr.herald.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T_H_Categories")
public class Categories {
	
	private Long id;
	private String categoryName;
	private Long parentId;
	private String masterCategoryFlag;
	

	@Id
	@GeneratedValue(strategy= GenerationType.SEQUENCE)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "category_name",length = 100)
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	@Column(name = "parent_id")
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	
	@Column(name = "master_category_flag",length = 10)
	public String getMasterCategoryFlag() {
		return masterCategoryFlag;
	}
	public void setMasterCategoryFlag(String masterCategoryFlag) {
		this.masterCategoryFlag = masterCategoryFlag;
	}
		
}
