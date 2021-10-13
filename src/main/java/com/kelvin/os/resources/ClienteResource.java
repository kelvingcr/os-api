package com.kelvin.os.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.kelvin.os.domain.Cliente;
import com.kelvin.os.domain.Pessoa;
import com.kelvin.os.dtos.ClienteDTO;
import com.kelvin.os.dtos.TecnicoDTO;
import com.kelvin.os.repositories.PessoaRepository;
import com.kelvin.os.services.ClienteService;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {
	
	@Autowired
	private ClienteService clienteService;
	
	
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ClienteDTO> findById(@PathVariable Integer id){
		Cliente obj = clienteService.findById(id);
		ClienteDTO objDTO = new ClienteDTO(obj);
		return ResponseEntity.ok().body(objDTO);
	}
	
	@GetMapping
	public ResponseEntity<List<ClienteDTO>> findAll(){
		
		List<Cliente> list = clienteService.findAll();
		List<ClienteDTO> listDTO = new ArrayList<>();
		
		for(Cliente x : list) {
			listDTO.add(new ClienteDTO(x));
		}
		return ResponseEntity.ok().body(listDTO);
		
	}
	@PostMapping
	public ResponseEntity<ClienteDTO>create(@Valid @RequestBody ClienteDTO objDTO){
		Cliente newOBJ = clienteService.create(objDTO);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(newOBJ.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
		
	}
	
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<ClienteDTO> update(@PathVariable Integer id, 
			@Valid @RequestBody ClienteDTO objDTO){
		
		ClienteDTO newOBJ = new ClienteDTO(clienteService.update(id, objDTO));
		
		return ResponseEntity.ok().body(newOBJ);
		
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		clienteService.delete(id);
		return ResponseEntity.noContent().build();
	}
	

}
