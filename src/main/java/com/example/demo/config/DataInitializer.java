package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.entity.AreaEntity;
import com.example.demo.repository.AreaRepository;


@Configuration
public class DataInitializer {
	

		@Autowired
	    private AreaRepository areaRepository;

		@Bean
	    public CommandLineRunner loadData() {
	    	return args -> {
		        if (areaRepository.count() == 0) {
		        	areaRepository.save(new AreaEntity(1, "RRHH"));
		        	areaRepository.save(new AreaEntity(2, "Finanzas"));
		        }
	    	};
	    }  
	    	
}
