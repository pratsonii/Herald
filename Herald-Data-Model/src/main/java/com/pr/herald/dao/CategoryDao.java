package com.pr.herald.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pr.herald.models.Categories;

public interface CategoryDao extends JpaRepository<Categories, Long> {

}
