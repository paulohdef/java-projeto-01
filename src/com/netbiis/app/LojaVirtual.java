package com.netbiis.app;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.netbiis.dao.ClienteDAO;
import com.netbiis.dao.CursoDAO;
import com.netbiis.dao.PagamentoDAO;
import com.netbiis.factory.ConnectionFactory;
import com.netbiis.model.cliente.Cliente;
import com.netbiis.model.curso.Curso;
import com.netbiis.model.pagamento.Pagamento;

public class LojaVirtual {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		try(Connection conexao = new ConnectionFactory().conectar()) {
			PagamentoDAO pagamentoDao = new PagamentoDAO(conexao);
			ClienteDAO clienteDao = new ClienteDAO(conexao);
			CursoDAO cursoDao = new CursoDAO(conexao);
			
// === LISTAR
//			List<Pagamento> listaPagamentos = pagamentoDao.listar();
//			
//			listaPagamentos.stream().forEach(pagamento -> System.out.println(pagamento));
			
// === INSERIR
					
//			Cliente cliente = new Cliente("PEREBA", "PEREBINHA@gmail.com");
//			Integer idCliente = clienteDao.inserir(cliente);
//			cliente.setId(idCliente);
//			
//			Curso curso = new Curso("CURSO DE COZINHA", 50, "URLCOZINHA");
//			Integer idCurso = cursoDao.inserir(curso);
//			curso.setId(idCurso);
//			
//			Pagamento pagamento = new Pagamento(cliente,curso);
//			Integer idPagamento = pagamentoDao.inserir(pagamento);
//			pagamento.setId(idPagamento);
			
			
// === EDITAR
			
//			Cliente clienteNovo = new Cliente("OIII", "PEREBINHA@gmail.com");
//			Integer idClienteNovo = clienteDao.inserir(clienteNovo);
//			clienteNovo.setId(idClienteNovo);
//			
//			
//			pagamento.setCliente(clienteNovo);
//			
//			pagamentoDao.editar(pagamento);
			
			
// === LISTAR UM
			
//			Pagamento pagamentoListado = pagamentoDao.listarUm(30);
//			
//			System.out.println(pagamentoListado);
			
// === DELETAR
			
//			pagamentoDao.deletar(30);
			
			
		}
	}
}
