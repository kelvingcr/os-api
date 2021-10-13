package com.kelvin.os.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.br.CPF;

import com.kelvin.os.domain.Tecnico;

/*
 * DTO USADO PARA FAZER TRANSFERENCIA DE DADOS | Nunca use a classe original
 */

//uso correta para na resquisição puxar essa classe
public class TecnicoDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message = "O campo NOME é requirido")
	private String nome;
	
	@CPF
	@NotEmpty(message = "O campo CPF é requirido")
	private String cpf;
	
	@NotEmpty(message = "O campo TELEFONE é requirido")
	private String telefone;
	
	public TecnicoDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TecnicoDTO(Tecnico obj) {
		super();
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.cpf = obj.getCpf();
		this.telefone = obj.getTelefone();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	
	

}
