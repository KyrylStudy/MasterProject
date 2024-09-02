package com.masterproject.AUTOtech.agil;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.masterproject.AUTOtech.agil.repository.HardwareRepository;
import com.masterproject.AUTOtech.agil.repository.HardwarePropertyRepository;
import com.masterproject.AUTOtech.agil.service.HardwarePropertyService;
import com.masterproject.AUTOtech.agil.service.impl.HardwarePropertyServiceImpl;

@SpringBootApplication
@ComponentScan(basePackages = {"com.masterproject.AUTOtech.agil"})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
