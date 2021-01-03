package com.gestLibrairie.app;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Connect{

	private static String url, bdd="gestlib";
	private static String user="root";
	private static String passwd="";

	private static Connection connect;
	
	public Connect(String bdd, String user, String passwd) {
		// TODO Auto-generated constructor stub
		this.bdd = bdd;
		this.user = user;
		this.passwd = passwd;
		
	}

	
	
	/**
	 * @return
	 */
	public static Connection getInstance(){
		url = "jdbc:mysql://localhost:3306/"+bdd;
		if(connect == null){
			try {
				connect = DriverManager.getConnection(url, user, passwd);
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Cette Base de donnée n'existe pas", "ERREUR DE CONNEXION A LA BD ! ", JOptionPane.ERROR_MESSAGE);
				
			}
		}		
		return connect;	
	}
}
