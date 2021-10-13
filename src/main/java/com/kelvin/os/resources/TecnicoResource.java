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

import com.kelvin.os.domain.Tecnico;
import com.kelvin.os.dtos.TecnicoDTO;
import com.kelvin.os.services.TecnicoService;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/tecnicos") //endpoins // localhost:8080/tecnicos
public class TecnicoResource {
	
	@Autowired
	private TecnicoService services;
	
	@GetMapping(value = "/{id}") // vou receber uma variavel de path
	public ResponseEntity<TecnicoDTO> findById(@PathVariable Integer id){
		Tecnico obj = services.findById(id);
		TecnicoDTO objDTO = new TecnicoDTO(obj);
		return ResponseEntity.ok().body(objDTO);
	}
	
	@GetMapping
	public ResponseEntity<List<TecnicoDTO>> findAll(){
		List<Tecnico> list = services.findAll();
		List<TecnicoDTO> listDTO = new ArrayList<>();
		
		for(Tecnico obj : list) {
			listDTO.add(new TecnicoDTO(obj));
		}
		
		return ResponseEntity.ok().body(listDTO);
	}
	
	/*
	 * Cria um novo tecnico
	 */
	@PostMapping
	public ResponseEntity<TecnicoDTO> create(@Valid @RequestBody TecnicoDTO objDTO){
		
		Tecnico newObj = services.create(objDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(newObj.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
		
	}
	/*
	 * Atualiza informções do tecnico
	 */
	@PutMapping(value = "/{id}")
	public ResponseEntity<TecnicoDTO> update(@PathVariable Integer id, 
			@Valid @RequestBody TecnicoDTO objDTO){
		
		TecnicoDTO newOBJ = new TecnicoDTO(services.update(id, objDTO));
		
		return ResponseEntity.ok().body(newOBJ);
		
	}
	
	/*
	 * Deleta um tecnico
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		services.delete(id);
		return ResponseEntity.noContent().build();
	}

}
