package com.kelvin.os.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.kelvin.os.services.DBService;

@Configuration
@Profile("dev")
public class DevConfig {

	@Autowired
	private DBService dbService;
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String dd1;
	
	@Bean //Chamador de metado quando a classe for instanciada
	public boolean instanciaDB() {
		if(dd1.equals("create")) {
			this.dbService.instanciaDB();
		}
		
		return false;
	}

}
