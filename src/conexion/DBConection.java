/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
/**
 *
 * @author Administrador
 */
public class DBConection {
    
    	private Connection conexion;

    public DBConection() {
        Connection conexion = null;
        Statement statement = null;

        try {
            // Cargar el driver JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establecer la conexión
            String url = "jdbc:mysql://localhost/bdtrabajadores";
            conexion = DriverManager.getConnection(url, "root", "");

            // Crear un objeto Statement
            statement = conexion.createStatement();

            // Ejecutar el script SQL
            /*String script = "CREATE DATABASE bdtrabajadores;" +
                            "USE bdtrabajadores;" +
                            "CREATE TABLE trabajadores (" +
                            "dni VARCHAR(9) PRIMARY KEY," +
                            "nombre VARCHAR(20) NOT NULL," +
                            "apellidos VARCHAR(50) NOT NULL," +
                            "sueldo DOUBLE," +
                            "fecha DATE," +
                            "matricula VARCHAR(8) NOT NULL);" +
                            "INSERT INTO trabajadores VALUES (dni, nombre, apellidos, sueldo, fecha, matricula) VALUES " +
                            "('11111111A','Roberto','Furnea','2500.20','1-1-2001','1111-AAA'),"+
                            "('22222222B','Pedro','Villaverde Cristobal','3000.50','2-2-2002','2222-BBB'),"+
                            "('33333333C','Ruben','Garcia Galan','2700.80','3-3-2003','3333-CCC'),"+
                            "('44444444D','Hugo','Duran Garzon','4000.50','4-4-2004','4444-DDD'),"+
                            "('55555555E','Sofiane','Hminat','3100.30','5-5-2005','5555-EEE'),"+
                            "('66666666F','Sergio','Gonzalez Alcantara','2700.50','6-6-2006','6666-FFF'),"+
                            "('77777777G','Rodrigo','Casarrubios Fernandez','900.20','7-7-2007','7777-GGG'),"+
                            "('88888888H','Alvaro','Villares Rordriguez','5000.50','8-8-2008','8888-HHH'),"+
                            "('99999999I','Jorge','Cerrato Marin','5000.50','9-9-2009','9999-III'),"+
                            "('10101010J','Rodrigo','Jimenez Jimenez','4550.20','10-10-2010','1010-JJJ')";
            statement.execute(script);
            
            System.out.println("Script SQL ejecutado exitosamente");
            */
            System.out.println("yyyyy");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            // Cerrar la conexión y el statement
            try {
                if (statement != null) {
                    statement.close();
                }
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    	public Connection getConexion() {
		return conexion;
	}
}