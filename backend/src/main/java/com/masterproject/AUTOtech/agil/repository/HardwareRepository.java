package com.masterproject.AUTOtech.agil.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masterproject.AUTOtech.agil.entity.Hardware;

public interface HardwareRepository extends JpaRepository<Hardware, Long>{
	List<Hardware> findByArchitectureId(Long id);

}
