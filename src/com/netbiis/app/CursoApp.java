package com.netbiis.app;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.netbiis.dao.CursoDAO;
import com.netbiis.factory.ConnectionFactory;
import com.netbiis.model.curso.Curso;
import com.netbiis.model.curso.ICurso;

public class CursoApp {

	public static void main(String[] args) {
		
		try(Connection conexao = new ConnectionFactory().conectar()) {	
			
			CursoDAO cursoDao = new CursoDAO(conexao);
			
//			ICurso curso = new Curso("Chulapa", 100, "urlCurso");
			
//			cursoDao.inserir(curso);
						
//			cursoDao.editarCurso(curso);
			
//			cursoDao.listarUm(23);
			
			List<Curso> cursos = cursoDao.listar();
			
			cursos.forEach(curso -> System.out.println(curso));

//			cursoDao.deletarCurso(49);
			
//			cursoDao.inserirSP(curso);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
