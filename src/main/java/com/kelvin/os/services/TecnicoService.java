package com.kelvin.os.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kelvin.os.domain.Pessoa;
import com.kelvin.os.domain.Tecnico;
import com.kelvin.os.dtos.TecnicoDTO;
import com.kelvin.os.repositories.PessoaRepository;
import com.kelvin.os.repositories.TecnicoRepository;
import com.kelvin.os.services.exceptions.DataIntegratyViolationException;
import com.kelvin.os.services.exceptions.ObjectNotFoundException;

@Service
public class TecnicoService {
	
	@Autowired //spring no controle || instancia
	private TecnicoRepository tecnicoRepository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	public Tecnico findById(Integer id) {
		Optional<Tecnico> obj = tecnicoRepository.findById(id); //optional ele pode encontrar na nossa db ou não
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Tecnico.class.getName()))	;
	}

	public List<Tecnico> findAll() {
		return tecnicoRepository.findAll();

	}
	
	public Tecnico create(TecnicoDTO objDTO) {
		if(findByCPF(objDTO) != null) {
			throw new DataIntegratyViolationException("CPF já cadastrado na base de dados.");
		}
		Tecnico newObj = new Tecnico(null, objDTO.getNome(), objDTO.getCpf(), objDTO.getTelefone());
		return tecnicoRepository.save(newObj);
	}
	
	public Tecnico update(Integer id, @Valid TecnicoDTO objDTO) {
		Tecnico oldOBJ = findById(id);
		
		if(findByCPF(objDTO) != null && findByCPF(objDTO).getId() != id) {
			throw new DataIntegratyViolationException("CPF já cadastrado na base de dados.");
		}
		
		oldOBJ.setNome(objDTO.getNome());
		oldOBJ.setCpf(objDTO.getCpf());
		oldOBJ.setTelefone(objDTO.getTelefone());
		
		return tecnicoRepository.save(oldOBJ);
	}
	
	public void delete(Integer id) {
		Tecnico obj = findById(id);
		if(obj.getList().size() > 0) {
			throw new DataIntegratyViolationException("Técnico possui Ordens de Serviço, "
					+ "não pode ser deletado.");
		}
		tecnicoRepository.deleteById(id);
		
	}
	
	private Pessoa findByCPF(TecnicoDTO objDTO) {
		Pessoa obj = pessoaRepository.findByCPF(objDTO.getCpf());
		return ((obj != null)  ?  obj : null);
	}



}
