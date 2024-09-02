package com.masterproject.AUTOtech.agil.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masterproject.AUTOtech.agil.entity.HardwareProperty;
import java.util.List;

//@Repository
public interface HardwarePropertyRepository extends JpaRepository<HardwareProperty, Long>{
	List<HardwareProperty> findByHardwareId(Long id);
} 


