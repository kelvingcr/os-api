package com.kelvin.os.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.kelvin.os.domain.Cliente;
import com.kelvin.os.domain.OS;
import com.kelvin.os.domain.Tecnico;
import com.kelvin.os.dtos.OSDTO;
import com.kelvin.os.enuns.Prioridade;
import com.kelvin.os.enuns.Status;
import com.kelvin.os.repositories.OSRepository;
import com.kelvin.os.services.exceptions.DataIntegratyViolationException;
import com.kelvin.os.services.exceptions.ObjectNotFoundException;

@Service
public class OsService {
	
	@Autowired
	private OSRepository repository;
	
	@Autowired
	private TecnicoService tecnicoService;
	
	@Autowired
	private ClienteService clienteService;
	
	public OS findById(Integer id) {
		Optional<OS> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + OS.class.getName()));
	}
	
	
	@GetMapping
	public List<OS> findAll(){
		return repository.findAll();
	}


	public OS create(@Valid OSDTO obj) {
		return fromDTO(obj);
	}
	
	public OS update(@Valid OSDTO obj) {
		findById(obj.getId());
		
		return fromDTO(obj);
	}
	
	public void delete(Integer id) {
		repository.deleteById(id);
	}
	
	private OS fromDTO(OSDTO obj) {
		OS newObj = new OS();
		newObj.setId(obj.getId());
		newObj.setObservacoes(obj.getObservacoes());
		newObj.setPrioridade(Prioridade.toEnum(obj.getPrioridade().getCod()));
		newObj.setStatus(Status.toEnum(obj.getStatus().getCod()));
		
		Tecnico tec = tecnicoService.findById(obj.getTecnico());
		Cliente cli = clienteService.findById(obj.getCliente());
		
		newObj.setTecnico(tec);
		newObj.setCliente(cli);
		
		if(newObj.getStatus().getCod().equals(2)) {
			newObj.setDatafechamento(LocalDateTime.now());
		}
		
		return repository.save(newObj);
		
	}




}
