package com.masterproject.AUTOtech.agil.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masterproject.AUTOtech.agil.entity.DataStreamProperty;
import java.util.List;


public interface DataStreamPropertyRepository extends JpaRepository<DataStreamProperty, Long>{
	List<DataStreamProperty> findByDataStreamId(Long id);
} 


