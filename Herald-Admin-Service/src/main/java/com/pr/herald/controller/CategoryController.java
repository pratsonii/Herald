package com.pr.herald.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pr.herald.contants.Constants;
import com.pr.herald.dto.CategoryRequestDto;
import com.pr.herald.service.Category;

@RestController
@RequestMapping("/category")
public class CategoryController 
{
	@Autowired
	Category category;
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity addCategory(@RequestBody CategoryRequestDto dto)
	{
		ResponseEntity re = null;
		category.addCategory(dto.convertToModels());
		re.ok(Constants.addedSuccess);
		return re;
	}
}
