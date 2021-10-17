package com.kelvin.os.dtos;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kelvin.os.domain.OS;
import com.kelvin.os.enuns.Prioridade;
import com.kelvin.os.enuns.Status;

/*
 * DTO USADO PARA FAZER TRANSFERENCIA DE DADOS | Nunca use a classe original
 */

public class OSDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	
	private Integer id;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime dataabertura;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime datafechamento;
	
	private Integer prioridade;
	
	@NotEmpty(message = "O campo OBSERVAÇÕES é requirido")
	private String observacoes;
	
	private Integer status;
	private Integer tecnico;
	private Integer cliente;

	public OSDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OSDTO(OS obj) {
		super();
		this.id = obj.getId();
		this.dataabertura = obj.getDataabertura();
		this.datafechamento = obj.getDatafechamento();
		this.prioridade = obj.getPrioridade().getCod();
		this.observacoes = obj.getObservacoes();
		this.status = obj.getStatus().getCod();
		this.tecnico = obj.getTecnico().getId();
		this.cliente = obj.getCliente().getId();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getDataabertura() {
		return dataabertura;
	}

	public void setDataabertura(LocalDateTime dataabertura) {
		this.dataabertura = dataabertura;
	}

	public LocalDateTime getDatafechamento() {
		return datafechamento;
	}

	public void setDatafechamento(LocalDateTime datafechamento) {
		this.datafechamento = datafechamento;
	}

	public Prioridade getPrioridade() {
		return Prioridade.toEnum(this.prioridade);
	}

	public void setPrioridade(Integer prioridade) {
		this.prioridade = prioridade;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public Status getStatus() {
		return Status.toEnum(this.status);
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getTecnico() {
		return tecnico;
	}

	public void setTecnico(Integer tecnico) {
		this.tecnico = tecnico;
	}

	public Integer getCliente() {
		return cliente;
	}

	public void setCliente(Integer cliente) {
		this.cliente = cliente;
	}
	
	
	
	

}
