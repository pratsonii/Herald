package com.pr.herald.dto;

import java.util.Set;

import com.pr.herald.contants.Constants;
import com.pr.herald.models.Categories;

public class CategoryResponseDto 
{
	private String category;
	private String favorite;
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getFavorite() {
		return favorite;
	}
	public void setFavorite(String favorite) {
		this.favorite = favorite;
	}
	
	public CategoryResponseDto convertToDto(Categories c, Set<String> favorites)
	{
		this.setCategory(c.getCategoryName());
		if( favorites.contains(c.getCategoryName()) )
			this.setFavorite(Constants.trueValue);
		else
			this.setFavorite(Constants.falseValue);
		return this;
	}
	
}
