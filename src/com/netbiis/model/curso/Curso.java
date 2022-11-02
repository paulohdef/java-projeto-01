package com.netbiis.model.curso;

public class Curso implements ICurso{
	
	private long id;
	private String nome;
	private double preco;
	private String getUrl;
	
	
	public Curso(String nome, double preco, String getUrl) {
		super();
		this.nome = nome;
		this.preco = preco;
		this.getUrl = getUrl;
	}
	
	public Curso(long id, String nome, double preco, String getUrl) {
		super();
		this.id = id;
		this.nome = nome;
		this.preco = preco;
		this.getUrl = getUrl;
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
	public double getPreco() {
		// TODO Auto-generated method stub
		return this.preco;
	}
	@Override
	public String getUrl() {
		// TODO Auto-generated method stub
		return this.getUrl;
	}
	
	
	public String getGetUrl() {
		return getUrl;
	}

	public void setGetUrl(String getUrl) {
		this.getUrl = getUrl;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("%d, %s, %f, %s", this.getId(), this.getNome(), this.getPreco(), this.getUrl());
	}

	

}
