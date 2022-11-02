package com.netbiis.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.netbiis.factory.ConnectionFactory;
import com.netbiis.model.cliente.Cliente;

public class ClienteDAO {
	
	private Connection conexao;

	public ClienteDAO(Connection conexao) {
		this.conexao = conexao;
	}
	
	public List<Cliente> listar() {
		try {
			List<Cliente> clientes = new ArrayList<>();
			String sql = "SELECT * FROM clientes";
			
			try(PreparedStatement pstm = conexao.prepareStatement(sql)){
				pstm.execute();
				
				try(ResultSet rst = pstm.getResultSet()){
					while(rst.next()) {
						Cliente cliente = new Cliente(rst.getInt(1), rst.getString(2),rst.getString(3));
						
						clientes.add(cliente);
					}
				}
			}
			return clientes;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Integer inserir(Cliente cliente){
		try {
			String sql = "INSERT INTO clientes VALUES (DEFAULT, ?,?)";
			
			try(PreparedStatement pstm = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
				pstm.setString(1,cliente.getNome());
				pstm.setString(2, cliente.getEmail());
				
				int rowsUpdated = pstm.executeUpdate();
				
				conexao.commit();
				
				if(rowsUpdated > 0) {
					System.out.println("Cliente adicionado com sucesso !");
				}
				
				try(ResultSet rst = pstm.getGeneratedKeys()){
					Integer id = null;
					while(rst.next()) {
						id  = rst.getInt(1);
						System.out.println("O id do cliente criado foi: "+id);
					}
					return id;
				} catch(SQLException e) {
					e.printStackTrace();
					conexao.rollback();
				}
				return null;
			}	
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Cliente listarUm(Integer idCliente) throws SQLException {

		
		String sql = "SELECT * FROM clientes WHERE idcliente=?";
		
		PreparedStatement stm = conexao.prepareStatement(sql);
		
		stm.setLong(1, idCliente);

		ResultSet rst = stm.executeQuery();
		
		while(rst.next())
		{
		Cliente cliente = new Cliente(rst.getInt(1), rst.getString(2), rst.getString(3));
		
		System.out.println("id:"+rst.getInt(1)+
		" nome:"+ rst.getString(2)+ " email"+ rst.getString(3));
		
		return cliente;
		}	
		return null;
	}
	
	public void editar(Cliente cliente){
		try {
			String sql = "UPDATE clientes set nome=?, email=? WHERE idcliente=?" ;
			
			try(PreparedStatement statementPrepared = conexao.prepareStatement(sql)) {
				
				statementPrepared.setString(1, cliente.getNome());
				statementPrepared.setString(2, cliente.getEmail());
				statementPrepared.setLong(3, cliente.getId());
				
				
				int rowsUpdated = statementPrepared.executeUpdate();
				
				if (rowsUpdated > 0) {
					System.out.println("Cliente editado com sucesso !");
				}
				
				conexao.commit();	
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void deletar(Long idCliente){
		try {
			String sql = "DELETE FROM clientes WHERE idcliente=?";
			
			try(PreparedStatement stm = conexao.prepareStatement(sql)) {
				
				stm.setLong(1, idCliente);
				
				int rowsUpdated = stm.executeUpdate();
				
				System.out.println("Foram excluídos "+rowsUpdated+" registros da tabela !");
				
				conexao.commit();			
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
