package com.pr.herald.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pr.herald.models.Events;


public interface EventsDao extends JpaRepository<Events, Long> {

}
