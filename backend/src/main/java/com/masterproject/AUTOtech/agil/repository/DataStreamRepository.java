package com.masterproject.AUTOtech.agil.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masterproject.AUTOtech.agil.entity.DataStream;

public interface DataStreamRepository extends JpaRepository<DataStream, Long>{
	List<DataStream> findByArchitectureId(Long id);
}

