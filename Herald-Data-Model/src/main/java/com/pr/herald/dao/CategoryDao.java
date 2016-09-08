package com.pr.herald.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.pr.herald.models.Categories;

public interface CategoryDao extends MongoRepository<Categories, Long> {

}
