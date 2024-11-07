package com.jdbc.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.jdbc.model.Employee;
import com.jdbc.util.DatabaseUtil;
import com.jdbc.util.QueryUtil;

public class DatabaseService {

	DatabaseUtil databaseUtil = new DatabaseUtil(); 

	public void insertEmployee(Employee employee) {

		try (Connection connection = databaseUtil.getConnection();) {

			PreparedStatement preparedStatement = connection.prepareStatement(QueryUtil.insertEmployeeQuery());

			preparedStatement.setString(1, employee.getNomEmployee());
			preparedStatement.setString(2, employee.getPrenomEmployee());
			preparedStatement.setInt(3, employee.getTelEmployee());
			preparedStatement.setDate(4, new java.sql.Date(employee.getDateAnniv().getTime()));
			preparedStatement.setString(5, employee.getMailEmployee());
			preparedStatement.setString(6, employee.getPasswordEmployee());

			int affectedRows = preparedStatement.executeUpdate();
			if (affectedRows > 0) {
				System.out.println("Ajouter avec succes...");
			}else {
				System.out.println("Impossible d'ajouter...");
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

	} // Fin InsertEmployee()


	public void getAllEmployees() {

		try(Connection connection = databaseUtil.getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(QueryUtil.selectAllEmployeeQuery());) {


			while(resultSet.next()) {
				choisirEmployee(new Employee(resultSet.getInt("idemploye"),
											 resultSet.getString("nom"),
											 resultSet.getString("prénom"),
											 resultSet.getInt("Tel"),
											 resultSet.getDate("date_anniv"),
											 resultSet.getString("mail")));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}

	}// Fin getAllEmployees() 

	private void choisirEmployee(Employee employee) {
		System.out.println("\n" + "Id : " + employee.getIdEmplyee() + "\n" +"Nom :  " + employee.getNomEmployee() +"\n" +"Prénom : " +  employee.getPrenomEmployee() + "\n" + "Anniv : " + employee.getDateAnniv() + "\n" +  "Email : " + employee.getMailEmployee() + "\n" + "Tel : "+ employee.getTelEmployee() + "\n" + "\n--------------------------------------\n");
	}


	public boolean choisirEmployeeParId(int id) throws SQLException {

		boolean isFound = false;

		try(Connection connection = databaseUtil.getConnection();
				Statement statement =  connection.createStatement();
				ResultSet resultSet = statement.executeQuery(QueryUtil.selectEmployeeById(id));
				) {
			if (resultSet.next()) {
				isFound = true;
				choisirEmployee(new Employee(resultSet.getInt("idemploye"),
						resultSet.getString("nom"),
						resultSet.getString("prénom"),
						resultSet.getInt("Tel"),
						resultSet.getDate("date_anniv"),
						resultSet.getString("mail")));
			}else {
				System.out.println("Employé n'est pas Enregistré ...");
			}

		}
		return isFound;

	}// Fin choisirEmployeeParId()
	
	public void supprimerEmployeeById(int idemploye) {
		
		try(Connection connection =  databaseUtil.getConnection();) {
			
			Statement statement = connection.createStatement();
			int rows = statement.executeUpdate(QueryUtil.supprimerEmployeeById(idemploye));
			
			if (rows > 0 ) {
				System.out.println("Employé supprimé avec succés... ");
			}else {
				System.out.println("La supprission n'a pa eu lieu ! ");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}// Fin supprimerEmployeeById()

	
	public void updateEmployee(Employee employe) {
		try(Connection connection = databaseUtil.getConnection();
				PreparedStatement preparedStatement =  connection.prepareStatement(QueryUtil.updateEmplyeeQuery(employe.getIdEmplyee()))) {
				preparedStatement.setString(1, employe.getNomEmployee());
				preparedStatement.setString(2, employe.getPrenomEmployee());
				preparedStatement.setInt(3, employe.getTelEmployee());
				preparedStatement.setDate(4, new java.sql.Date(employe.getDateAnniv().getTime()));
				preparedStatement.setString(5, employe.getMailEmployee());
				preparedStatement.setString(6, employe.getPasswordEmployee());
				
				int rows = preparedStatement.executeUpdate();
				if (rows > 0 ) {
					System.out.println("Employé Modifié avec succés... ");
				}else {
					System.out.println("La modification n'a pa eu lieu ! ");
				}
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}// Fin UpdateEmployee()





}


















