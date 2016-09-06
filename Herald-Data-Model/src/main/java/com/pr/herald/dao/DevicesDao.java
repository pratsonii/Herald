package com.pr.herald.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pr.herald.models.Devices;

public interface DevicesDao extends JpaRepository<Devices, Long> {

}

