package com.pr.herald.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.pr.herald.models.Categories;

public interface CategoryDao extends MongoRepository<Categories, Long> 
{
	List<Categories> findByOrderByPriority();
}
