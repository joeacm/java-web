package com.joe.connection;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

public class Conexion 
{
	private static BasicDataSource dataSource = null;
	
	private static DataSource getDataSource()
	{
		if (dataSource == null) 
		{
			dataSource = new BasicDataSource();
			dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
			dataSource.setUsername("root");
			dataSource.setPassword("admin");
			dataSource.setUrl("jdbc:mysql://localhost:3306/webcrud?serverTimezone=UTC");
			dataSource.setInitialSize(20);
			
			// maximo activas.
			dataSource.setMaxIdle(15);
			dataSource.setMaxTotal(20);
			
			// tiempo de espera en milisegundos.
			dataSource.setMaxWaitMillis(5000);
		}
		return dataSource;
	}
	
	public static Connection getConnection() throws SQLException
	{
		return getDataSource().getConnection();
	}
}
