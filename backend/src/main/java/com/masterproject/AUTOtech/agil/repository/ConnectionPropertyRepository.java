package com.masterproject.AUTOtech.agil.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masterproject.AUTOtech.agil.entity.ConnectionProperty;
import java.util.List;


public interface ConnectionPropertyRepository extends JpaRepository<ConnectionProperty, Long>{
	List<ConnectionProperty> findByConnectionId(Long id);
} 

