package com.jdbc.model;

import java.util.Date;

public class Employee {
	
	private int idEmplyee, telEmployee;
	private String nomEmployee, prenomEmployee, mailEmployee, passwordEmployee;
	private Date dateAnniv;
	
	
	
	
	// Costructeur : 
	
	
	public Employee(String nomEmployee, String prenomEmployee, int telEmployee, Date dateAnniv, String mailEmployee, String passwordEmployee) {
		this.nomEmployee = nomEmployee;
		this.prenomEmployee = prenomEmployee;
		this.telEmployee = telEmployee;
		this.dateAnniv = dateAnniv;
		this.mailEmployee = mailEmployee;
		this.passwordEmployee = passwordEmployee;
	}
	
	// Costructeur avec ID : 
	
	
	public Employee(int idEmployee, String nomEmployee, String prenomEmployee, int telEmployee, Date dateAnniv, String mailEmployee, String passwordEmployee) {
		this.idEmplyee = idEmployee;
		this.nomEmployee = nomEmployee;
		this.prenomEmployee = prenomEmployee;
		this.telEmployee = telEmployee;
		this.dateAnniv = dateAnniv;
		this.mailEmployee = mailEmployee;
		this.passwordEmployee = passwordEmployee;
	}
	
	
	// Costructeur SANS le Password : 
	
	
	public Employee(int idEmployee, String nomEmployee, String prenomEmployee, int telEmployee, Date dateAnniv, String mailEmployee) {
		this.idEmplyee = idEmployee;
		this.nomEmployee = nomEmployee;
		this.prenomEmployee = prenomEmployee;
		this.telEmployee = telEmployee;
		this.dateAnniv = dateAnniv;
		this.mailEmployee = mailEmployee;
	}
	
	
	
	// Getters : 
	
	public int getIdEmplyee() {
		return idEmplyee;
	}
	

	public int getTelEmployee() {
		return telEmployee;
	}
	
	public String getNomEmployee() {
		return nomEmployee;
	}
	
	public String getPrenomEmployee() {
		return prenomEmployee;
	}
	
	public String getMailEmployee() {
		return mailEmployee;
	}
	
	public String getPasswordEmployee() {
		return passwordEmployee;
	}
	
	public Date getDateAnniv() {
		return dateAnniv;
	}
	
	
	
	// Setters : 
	
	
	public void setIdEmplyee(int idEmplyee) {
		this.idEmplyee = idEmplyee;
	}
	
	public void setTelEmployee(int telEmployee) {
		this.telEmployee = telEmployee;
	}
	
	public void setNomEmployee(String nomEmployee) {
		this.nomEmployee = nomEmployee;
	}
	
	public void setPrenomEmployee(String prenomEmployee) {
		this.prenomEmployee = prenomEmployee;
	}
	
	public void setMailEmployee(String mailEmployee) {
		this.mailEmployee = mailEmployee;
	}
	
	public void setPasswordEmployee(String passwordEmployee) {
		this.passwordEmployee = passwordEmployee;
	}
	
	public void setDateAnniv(Date dateAnniv) {
		this.dateAnniv = dateAnniv;
	}


}
