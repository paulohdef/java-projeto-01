package com.netbiis.controller;

import java.sql.Connection;
import java.util.List;

import com.netbiis.dao.PagamentoDAO;
import com.netbiis.factory.ConnectionFactory;
import com.netbiis.model.pagamento.Pagamento;

public class PagamentoController {
	
	private PagamentoDAO pagamentoDao;

	public PagamentoController() {
		Connection conexao = new ConnectionFactory().conectar();
		this.pagamentoDao = new PagamentoDAO(conexao);
	}

	public List<Pagamento> listar() {
		return this.pagamentoDao.listar();
	}

	public Integer salvar(Pagamento pagamento) {
		return this.pagamentoDao.inserir(pagamento);	
	}

	public void deletar(Long idPagamento) {
		this.pagamentoDao.deletar(idPagamento);
	}
	

}
