package com.netbiis.factory;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionFactory {

	private static String url =  "jdbc:mysql://localhost:3306/formacaoJava";
	private static String usuario = "root";
	private static String senha = "bobowna123";
	
	public DataSource dataSource;
	
	public ConnectionFactory(){
		ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
		comboPooledDataSource.setJdbcUrl(url);
		comboPooledDataSource.setUser(usuario);
		comboPooledDataSource.setPassword(senha);
		
		comboPooledDataSource.setMaxPoolSize(15);
		
		this.dataSource = comboPooledDataSource;
	}
	
	public Connection conectar()
	{
		try {
			Connection conn = this.dataSource.getConnection();
			
			conn.setAutoCommit(false);
			
			return conn; 			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
