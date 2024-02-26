/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author PC
 */
    public class Conexion {

    public static Connection getConection() {
        return conection;
    }

    public static void setConection(Connection conection) {
        Conexion.conection = conection;
    }
      public static Connection conection = null;
    public Connection getConexion() {
		try {
			
		//Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                      conection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/barberia", "root", "12345678");
                      return conection;
                        
                    } 
                       catch (SQLException ex) {
			return conection;
			
		}

	}   
}
