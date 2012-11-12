package com.lsy.vehicle.web;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lsy.vehicle.controller.spi.DBFixture;

@Service
public class InitDatabase {
	
	@Autowired
	private DBFixture dbFixture;
	
	@PostConstruct
	public void postConstruct() {
		dbFixture.createDefaultDataInDatabase();
	}

}
