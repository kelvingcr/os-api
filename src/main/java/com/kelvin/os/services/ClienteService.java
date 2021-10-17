package com.kelvin.os.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import com.kelvin.os.domain.Cliente;
import com.kelvin.os.domain.Pessoa;
import com.kelvin.os.domain.Tecnico;
import com.kelvin.os.dtos.ClienteDTO;
import com.kelvin.os.dtos.TecnicoDTO;
import com.kelvin.os.repositories.ClienteRepository;
import com.kelvin.os.repositories.PessoaRepository;
import com.kelvin.os.services.exceptions.DataIntegratyViolationException;
import com.kelvin.os.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	
	public Cliente findById(Integer id) {
		Optional<Cliente> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}


	public List<Cliente> findAll() {
		return repository.findAll();
	}


	public Cliente create(@Valid ClienteDTO objDTO) {
		
		if(findByCPF(objDTO) != null) {
			throw new DataIntegratyViolationException("CPF já cadastrado na base de dados.");
		}
		
		Cliente newObj = new Cliente(null, objDTO.getNome(), objDTO.getCpf(), objDTO.getTelefone());
		return repository.save(newObj);
	}
	
	public Cliente update(Integer id, @Valid ClienteDTO objDTO) {
		
		Cliente oldOBJ = findById(id);
		
		if(findByCPF(objDTO) != null && findByCPF(objDTO).getId() != id) {
			throw new DataIntegratyViolationException("CPF já cadastrado na base de dados.");
		}
		
		oldOBJ.setNome(objDTO.getNome());
		oldOBJ.setCpf(objDTO.getCpf());
		oldOBJ.setTelefone(objDTO.getTelefone());
		
		return repository.save(oldOBJ);
	}
	
	private Pessoa findByCPF(ClienteDTO objDTO) {
		Pessoa obj = pessoaRepository.findByCPF(objDTO.getCpf());
		return ((obj != null)  ?  obj : null);
	}
	
	public void delete(Integer id) {
		Cliente obj = findById(id);
		if(obj.getList().size() > 0) {
			throw new DataIntegratyViolationException("Cliente possui Ordens de Serviço, "
					+ "não pode ser deletado.");
		}
		repository.deleteById(id);
		
	}


	

}
