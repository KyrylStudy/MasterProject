package com.masterproject.AUTOtech.agil.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masterproject.AUTOtech.agil.entity.ServiceProperty;

import java.util.List;


public interface ServicePropertyRepository extends JpaRepository<ServiceProperty, Long>{
	List<ServiceProperty> findByServiceEntityId(Long id);
} 
