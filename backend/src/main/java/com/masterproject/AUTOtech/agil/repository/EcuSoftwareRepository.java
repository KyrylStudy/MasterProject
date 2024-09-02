package com.masterproject.AUTOtech.agil.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masterproject.AUTOtech.agil.entity.EcuSoftware;
import java.util.List;


public interface EcuSoftwareRepository extends JpaRepository<EcuSoftware, Long>{
	List<EcuSoftware> findByHardwareId(Long id);
} 

