/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senai.sc.util;

import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Caio Pereira
 */
public class ConnectionUtil {
    
    private static java.sql.Connection con;
    
    public static java.sql.Connection getConnection(){
        try { 
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/epark";
            con = DriverManager.getConnection(url,"root","root");
            con.setAutoCommit(true);
            return con;
        } catch ( ClassNotFoundException e){
            e.printStackTrace();
        } catch ( SQLException se){
            se.printStackTrace();
        }
        return null;
    }
    
    public static void closeConnection(){
        try {
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
}
    

