package com.kelvin.os.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kelvin.os.domain.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{
	
	

}
