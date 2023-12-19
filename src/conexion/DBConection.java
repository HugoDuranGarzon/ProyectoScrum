/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Administrador
 */
public class DBConection {
    
    	private Connection conexion;

    public DBConection() {
       
        
    }
    	public Connection getConexion() {
            
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    String url = "jdbc:mysql://localhost/bdtrabajadores";
                    conexion = DriverManager.getConnection(url, "root", "");
                    
                    
                } catch (Exception e) {
                    System.out.println("Error conectando a la base de datos");
                    e.printStackTrace();
                }
                return conexion;
	}
}