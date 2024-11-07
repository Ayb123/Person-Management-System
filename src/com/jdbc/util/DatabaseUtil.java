package com.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {
	
	Connection connection;

	public DatabaseUtil()
	{
		try
		{
			Class.forName(Credentials.getDriverClassName());
			connection = DriverManager.getConnection(Credentials.getUrl(), Credentials.getUser(), Credentials.getPassword());
		}
		catch (ClassNotFoundException e)
		{
			System.out.println("Pilote JDBC non installé.");
		}
		catch (SQLException e)
		{
			System.out.println(e);
		}
	}// Fin du Constructeur
	
	
	// Pour eviter l'erreur de la fermeture de la connexion 
	
	public Connection getConnection() throws SQLException {
		if (connection == null || connection.isClosed()) {
			// Créez une nouvelle connexion si elle n'existe pas ou si elle est fermée
			connection = DriverManager.getConnection(Credentials.getUrl(), Credentials.getUser(), Credentials.getPassword());
		}
		return connection;
	}
	
	
	
	
	

}
