package com.netbiis.model.pagamento;

import java.sql.Date;

import com.netbiis.model.cliente.Cliente;
import com.netbiis.model.curso.Curso;

public class Pagamento {
	private long id;
	private Cliente cliente;
	private Curso curso;
	private String dataInscricao;
	
	//BANCO
	public Pagamento(long id, Cliente cliente, Curso curso, String dataInscricao) {
		this.id = id;
		this.cliente = cliente;
		this.curso = curso;
		this.dataInscricao = dataInscricao;
	}
	// FRONT
	public Pagamento(Cliente cliente, Curso curso) {
		this.cliente = cliente;
		this.curso = curso;
		this.dataInscricao = "HOJE"; //ATUALIZAR PARA DATE()
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Curso getCurso() {
		return curso;
	}
	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	public String getDataInscricao() {
		return dataInscricao;
	}
	public void setDataInscricao(String dataInscricao) {
		this.dataInscricao = dataInscricao;
	}
	@Override
	public String toString() {
		return "Pagamento [id=" + id + ", cliente=" + cliente + ", curso=" + curso + ", dataInscricao=" + dataInscricao
				+ "]";
	}
	
}
