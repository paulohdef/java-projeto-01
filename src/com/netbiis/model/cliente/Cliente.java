package com.netbiis.model.cliente;

public class Cliente implements ICliente {
	
	private long id;
	private String nome;
	private String email;
	
	
	public Cliente(String nome, String email) {
		super();
		this.nome = nome;
		this.email = email;
	}
	
	public Cliente(long id, String nome, String email) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
	}
	
	@Override
	public long getId() {
		// TODO Auto-generated method stub
		return this.id;
	}
	@Override
	public String getNome() {
		// TODO Auto-generated method stub
		return this.nome;
	}

	@Override
	public String getEmail() {
		// TODO Auto-generated method stub
		return this.email;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("%d, %s, %s", this.getId(), this.getNome(), this.getEmail());
	}


}
