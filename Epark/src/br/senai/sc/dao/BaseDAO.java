package br.senai.sc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseDAO {
	
	protected Connection con;
	protected Statement comando;

	protected void conectar() {  
		try {  
			Class.forName("com.mysql.jdbc.Driver");  
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/grupo7", "root", "");    
			comando = con.createStatement();   
		} catch (SQLException e) {  
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			e.printStackTrace();
		}  
	}  

	protected void fechar() {  
		try {  
			comando.close();  
			con.close();   
		} catch (SQLException e) {  
			e.printStackTrace();  
		}  
	}  

}
