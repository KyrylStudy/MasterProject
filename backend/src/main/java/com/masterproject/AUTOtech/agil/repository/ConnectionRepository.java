package com.masterproject.AUTOtech.agil.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masterproject.AUTOtech.agil.entity.Connection;

public interface ConnectionRepository extends JpaRepository<Connection, Long>{
	List<Connection> findByArchitectureId(Long id);
}
