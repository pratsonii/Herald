package com.pr.herald.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pr.herald.base.BaseException;
import com.pr.herald.base.RespEntity;
import com.pr.herald.contants.Constants;
import com.pr.herald.dto.CategoryRequestDto;
import com.pr.herald.models.Categories;
import com.pr.herald.service.CategoryServ;

@RestController
@RequestMapping("/category")
public class CategoryController 
{
	@Autowired
	CategoryServ category;
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity addCategory(@RequestBody CategoryRequestDto dto) throws BaseException
	{
		category.addCategory(dto.convertToModels());
		return new ResponseEntity<String>(Constants.addSuccess, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/findAll", method = RequestMethod.GET)
	public ResponseEntity<RespEntity<List<Categories>>> findAllCategory()
	{
		return new ResponseEntity<RespEntity<List<Categories>>>
		
		((new RespEntity<List<Categories>>(category.findAllCategory(),"Success")), HttpStatus.OK
				);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ResponseEntity updateCategory(@RequestBody CategoryRequestDto dto)
	{
		category.updateCategory(dto.convertToModels());
		return new ResponseEntity<String>(Constants.updatSuccess, HttpStatus.OK);
	}
}
