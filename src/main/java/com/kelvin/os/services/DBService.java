package com.kelvin.os.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kelvin.os.domain.Cliente;
import com.kelvin.os.domain.OS;
import com.kelvin.os.domain.Tecnico;
import com.kelvin.os.enuns.Prioridade;
import com.kelvin.os.enuns.Status;
import com.kelvin.os.repositories.ClienteRepository;
import com.kelvin.os.repositories.OSRepository;
import com.kelvin.os.repositories.TecnicoRepository;

@Service
public class DBService {
	
	@Autowired
	private TecnicoRepository tecnicoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private OSRepository osRepository;

	public void instanciaDB() {
		Tecnico t1 = new Tecnico(null, "Kelvin Rodrigues", "948.734.240-03", "(13) 98884-1491");
		Tecnico t2 = new Tecnico(null, "Elias Rodrigues", "324.218.218-94", "(13) 988117729");
		Cliente c1 = new Cliente(null, "Leandro Luiz", "072.641.120-55", "(13) 98811-7726");
		Cliente c2 = new Cliente(null, "Caroline Rodrigues", "214.743.820-02", "(13) 98811-7726");
		OS os1 = new OS(null, Prioridade.ALTA, "Teste create OS", Status.ANDAMENTO, t1, c1);
		
		t1.getList().add(os1);
		c1.getList().add(os1);
		
		tecnicoRepository.saveAll(Arrays.asList(t1, t2));
		clienteRepository.saveAll(Arrays.asList(c1, c2));
		osRepository.saveAll(Arrays.asList(os1));
	}
	
}
