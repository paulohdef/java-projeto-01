package com.netbiis.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.netbiis.factory.ConnectionFactory;
import com.netbiis.model.curso.Curso;
import com.netbiis.model.curso.ICurso;

public class CursoDAO{
	
	private Connection conexao;
	
	public CursoDAO(Connection conexao) {
		this.conexao = conexao;
	}
	
	public Integer inserir(ICurso curso) {
		try {
			String sql = "INSERT INTO formacaoJava.cursos VALUES (DEFAULT, ?,?,?)";
			
			try (PreparedStatement stm = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
				
				stm.setString(1, curso.getNome());
				stm.setDouble(2, curso.getPreco());
				stm.setString(3, curso.getUrl());
				
				
				int isRowsUpdated = stm.executeUpdate();
				
				conexao.commit();
				
				if (isRowsUpdated > 0) {
					System.out.println("Curso adicionado com sucesso !");
				}
				
				try(ResultSet rst = stm.getGeneratedKeys()){
					
					Integer id = null;
					
					while(rst.next()) {
						id  = rst.getInt(1);
						System.out.println("O id criado foi: "+id);
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
		
		
	}

	public void listarUm(long idCurso){		
		try {
			String sql = "SELECT * FROM formacaoJava.cursos WHERE idcurso=?";
			
			try(PreparedStatement stm = conexao.prepareStatement(sql)){
				
				stm.setLong(1, idCurso);
				
				try(ResultSet rowsUpdated = stm.executeQuery()){
					
					while(rowsUpdated.next())
					{
						System.out.println("cpf:"+rowsUpdated.getInt(1)+
								" nome:"+ rowsUpdated.getString(2)+ " email"+ rowsUpdated.getString(3));
					}	
				}
			}	
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Curso> listar() {	
		try {
			String sql = "SELECT * FROM formacaoJava.cursos";
			
			try(PreparedStatement stm = conexao.prepareStatement(sql)){
				
				try(ResultSet rs = stm.executeQuery()){
					
					List<Curso> cursos = new ArrayList<>();
					
					while(rs.next())
					{
						Curso curso = new Curso(rs.getLong(1), rs.getString(2), rs.getDouble(3), rs.getString(4));
						cursos.add(curso);
					}
					
					return cursos;		
				}
			}	
			
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}		
	}

	public void editar(ICurso curso) {	
		
		try {
			String sql = "UPDATE formacaoJava.cursos set nome=?, valor=?, url=? WHERE idcurso=?" ;
			
			try(PreparedStatement statementPrepared = conexao.prepareStatement(sql)) {
				statementPrepared.setString(1, curso.getNome());
				statementPrepared.setDouble(2, curso.getPreco());
				statementPrepared.setString(3, curso.getUrl());
				statementPrepared.setLong(4, curso.getId());
				
				int rowsUpdated = statementPrepared.executeUpdate();
				
				if (rowsUpdated > 0) {
					System.out.println("Curso adicionado com sucesso !");
				}
				
				conexao.commit();		
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void deletar(Long idCurso) {
		try {
			String sql = "DELETE FROM formacaoJava.cursos WHERE idcurso=?";
			
			try(PreparedStatement pstm = conexao.prepareStatement(sql)) {
				pstm.setLong(1, idCurso);
				
				int rowsUpdated = pstm.executeUpdate();
				
				System.out.println("Foram excluídos "+rowsUpdated+" registros da tabela !");
				
				conexao.commit();	
			}	
			
		} catch (SQLException e){
			throw new RuntimeException(e);
		}

	}
	
	public void inserirSP(ICurso curso) throws SQLException{
		
		CallableStatement cstmt = conexao.prepareCall("CALL formacaoJava.SP_INSERIR_CURSO(?,?,?,?)");
		
		cstmt.setLong(1, curso.getId());
		cstmt.setString(2, curso.getNome());
		cstmt.setDouble(3, curso.getPreco());
		cstmt.setString(4, curso.getUrl());

		cstmt.execute();
		
		conexao.commit();
	}	
}
