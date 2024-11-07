package com.jdbc.util;

public class Credentials {

	private static String driver = "mysql";
	private static String driverClassName = "com.mysql.cj.jdbc.Driver";
	private static String host = "localhost";
	private static String port = "3001";
	private static String database = "personnel";
	private static String user = "newroot";
	private static String password = "";

	static String getUrl() 
	{
		return "jdbc:" + driver + "://" + host + ":" + port + "/" + database ;
	}

	static String getDriverClassName()
	{
		return driverClassName;
	}

	static String getUser() 
	{
		return user;
	}

	static String getPassword() 
	{
		return password;
	}
}

