package com.netbiis.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.netbiis.model.cliente.Cliente;
import com.netbiis.model.curso.Curso;
import com.netbiis.model.pagamento.Pagamento;

public class PagamentoDAO {
	private Connection conexao;

	public PagamentoDAO(Connection conexao) {
		this.conexao = conexao;
	}
	
	public List<Pagamento> listar(){
		try {
			String sql = "SELECT * FROM pagamentos P INNER JOIN clientes CL ON P.idcliente = CL.idcliente INNER JOIN cursos C ON P.idcurso = C.idcurso";
			List<Pagamento> pagamentos = new ArrayList<>();
			try(PreparedStatement pstm = conexao.prepareStatement(sql)){
				
				try(ResultSet rst = pstm.executeQuery()){
					while(rst.next()) {			
						Cliente cliente = new Cliente(rst.getLong(5),rst.getString(6),rst.getString(7));
						Curso curso = new Curso(rst.getLong(8), rst.getString(9),rst.getDouble(10), rst.getString(11));
						Pagamento pagamento = new Pagamento(rst.getLong(1), cliente, curso, rst.getString(4));
						pagamentos.add(pagamento);
					}
				}
			}
			return pagamentos;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Integer inserir(Pagamento pagamento){
		try {
			String sql = "INSERT INTO pagamentos VALUES (DEFAULT, ?,?,?)";
			
			try (PreparedStatement stm = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
				
				stm.setLong(1, pagamento.getCliente().getId());
				stm.setLong(2, pagamento.getCurso().getId());
				stm.setString(3, pagamento.getDataInscricao());
				
				
				int isRowsUpdated = stm.executeUpdate();
				
				conexao.commit();
				
				if (isRowsUpdated > 0) {
					System.out.println("Pagamento adicionado com sucesso !");
				}
				
				try(ResultSet rst = stm.getGeneratedKeys()){
					
					Integer id = null;
					
					while(rst.next()) {
						id  = rst.getInt(1);
						System.out.println("O id do pagamento criado foi: "+id);
					}			
					return id;
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("ROLLBACK");
				conexao.rollback();
			}
			
			return null;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}// ENVOLVER TUDO COM TRY WUTG RESOURCES
	public void editar(Pagamento pagamento) {
		try {
			String sql = "UPDATE pagamentos set idcliente=?, idcurso=?, datainscricao=? WHERE idpagamento=?" ;
			
			try(PreparedStatement statementPrepared = conexao.prepareStatement(sql)){
				statementPrepared.setLong(1, pagamento.getCliente().getId());
				statementPrepared.setLong(2, pagamento.getCurso().getId());
				statementPrepared.setString(3, pagamento.getDataInscricao());
				statementPrepared.setLong(4, pagamento.getId());
				
				
				int rowsUpdated = statementPrepared.executeUpdate();
				
				conexao.commit();	
				
				if (rowsUpdated > 0) {
					System.out.println("Pagamento alterado com sucesso !");
				}	
			}	
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Pagamento listarUm(int idPagamento) throws SQLException {
		String sql = "SELECT * FROM pagamentos P INNER JOIN clientes CL ON P.idcliente = CL.idcliente INNER JOIN cursos C ON P.idcurso = C.idcurso WHERE idpagamento=?";
		try(PreparedStatement pstm = conexao.prepareStatement(sql)){
			pstm.setInt(1, idPagamento);
			try(ResultSet rst = pstm.executeQuery()){
				while(rst.next()) {			
					Cliente cliente = new Cliente(rst.getLong(5),rst.getString(6),rst.getString(7));
					Curso curso = new Curso(rst.getLong(8), rst.getString(9),rst.getDouble(10), rst.getString(11));
					Pagamento pagamento = new Pagamento(rst.getLong(1), cliente, curso, rst.getString(4));
					return pagamento;
				}
			}
		}
		return null;
		// MELHORAR TRATATIVA DE ERRO
	}

	public void deletar(Long idPagamento) {
		try {
			String sql = "DELETE FROM pagamentos WHERE idpagamento=?";
			
			try(PreparedStatement stm = conexao.prepareStatement(sql)){
				stm.setLong(1, idPagamento);
				
				int rowsUpdated = stm.executeUpdate();
				
				System.out.println("Foram excluídos "+rowsUpdated+" registros da tabela !");
				
				conexao.commit();			
			}	
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
