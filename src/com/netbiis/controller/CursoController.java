package com.netbiis.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.netbiis.dao.CursoDAO;
import com.netbiis.factory.ConnectionFactory;
import com.netbiis.model.curso.Curso;


public class CursoController {
	
	private CursoDAO cursoDao;
	
	public CursoController() {
		Connection conexao = new ConnectionFactory().conectar();
		
		this.cursoDao = new CursoDAO(conexao);	
	}

	public List<Curso> listar() {		
		
		return this.cursoDao.listar();
	}

	public int salvar(Curso curso) {
		return this.cursoDao.inserir(curso);
	}

	public void deletar(Long idCurso) {
		this.cursoDao.deletar(idCurso);
		
	}

	public void alterar(Curso curso) {
		this.cursoDao.editar(curso);
		
	}
}
