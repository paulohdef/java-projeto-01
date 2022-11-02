package com.netbiis.factory;

import java.sql.SQLException;

public class TestPoolConexoes {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		ConnectionFactory connectionFactory = new ConnectionFactory();
		
		for(int i = 0; i< 20; i++) {
			connectionFactory.conectar();
			System.out.println("Conexão de número: " + i );
		}
	}

}
