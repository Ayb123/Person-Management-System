package com.jdbc.util;



public class QueryUtil {
	
	public static String insertEmployeeQuery() {
		
		return	"INSERT INTO `employe` (`nom`,`prénom`,`Tel`,`date_anniv`,`mail`,`password_emp`) values(?,?,?,?,?,?)";
	}
	
	public static String selectAllEmployeeQuery() {
		return "SELECT * FROM `employe`";
	}
	
	public static String selectEmployeeById(int idemployee) {
		return "SELECT * FROM `employe` WHERE `idemploye` = " + idemployee; 
	}
	
	public static String supprimerEmployeeById(int idemployee) {
		return "DELETE FROM `employe` WHERE `idemploye` = " + idemployee;
		
	}
	
	public static String updateEmplyeeQuery(int idemployee) {
		return "UPDATE `employe` SET `nom` = ?, `prénom` = ?, `Tel` = ?, `date_anniv` = ?, `mail` = ?, `password_emp` = ? WHERE `idemploye` = " + idemployee;
	}

}
