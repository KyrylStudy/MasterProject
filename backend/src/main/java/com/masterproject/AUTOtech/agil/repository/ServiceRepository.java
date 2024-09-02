package com.masterproject.AUTOtech.agil.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masterproject.AUTOtech.agil.entity.ServiceEntity;


public interface ServiceRepository extends JpaRepository<ServiceEntity, Long>{
	List<ServiceEntity> findByHardwareId(Long id);

}

