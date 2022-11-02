package com.netbiis.app;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.netbiis.dao.ClienteDAO;
import com.netbiis.factory.ConnectionFactory;
import com.netbiis.model.cliente.Cliente;

public class ClientApp {

	public static void main(String[] args) {
	
		try(Connection connection = new ConnectionFactory().conectar()){
			
			ClienteDAO clienteDao = new ClienteDAO(connection);
			
//			List<Cliente> listaClientes = clienteDao.listar();
//			
//			listaClientes.stream().forEach(cliente -> System.out.println(cliente.getNome()));
//			
//			Cliente clienteCriado = new Cliente("Jurema", "Jurandinr@gmail.com");
//			
//			Integer idClienteCriado = clienteDao.inserir(clienteCriado);
//			
//			System.out.println("idClienteCriado: "+idClienteCriado);
			
//			Cliente clienteTestado = clienteDao.listarUm(23);
//			
//			System.out.println(clienteTestado);
//			
//			clienteTestado.setNome("XURIMBA");
//			
//			clienteDao.editar(clienteTestado);
			
//			clienteDao.deletar(23);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
