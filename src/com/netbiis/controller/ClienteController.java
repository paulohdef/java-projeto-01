package com.netbiis.controller;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.netbiis.dao.ClienteDAO;
import com.netbiis.factory.ConnectionFactory;
import com.netbiis.model.cliente.Cliente;

public class ClienteController {
	
	ClienteDAO clienteDao;
	
	public ClienteController() {
		
		Connection conexao = new ConnectionFactory().conectar();
		this.clienteDao = new ClienteDAO(conexao);
	}

	public List<Cliente> listar() {		
		return this.clienteDao.listar();
	}

	public Integer salvar(Cliente cliente) {
		return this.clienteDao.inserir(cliente);
	}

	public void deletar(Long idCliente) {
		this.clienteDao.deletar(idCliente);
	}

	public void alterar(Cliente cliente) {
		this.clienteDao.editar(cliente);
	}

}
