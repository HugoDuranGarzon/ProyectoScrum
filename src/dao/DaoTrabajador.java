/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
   */  
package dao;
import com.mysql.cj.jdbc.ConnectionImpl;
import conexion.DBConection;
import java.sql.*;
import entidades.Trabajador;
import java.util.ArrayList;
/**
 *
 * @author Administrador
 */
public class DaoTrabajador {
    
    
    
    //Constructor
    public DaoTrabajador(){
        
    }
    
    //Metodo para añadir un trabajador
    public void addTrabajador(Trabajador t){
        //Conexion
        Connection conexion = new DBConection().getConexion();   
        
        try {
            
            //Se crea una sentencia
            String sql = "INSERT INTO trabajadores VALUES(?,?,?,?,?,?)";
            
            //Se crea una plataforma preparada
            PreparedStatement plataforma = conexion.prepareStatement(sql);
            
            //Se rellenan los campos de la sentencia preparada
            plataforma.setString(1,t.getDni());
            plataforma.setString(2,t.getNombre());
            plataforma.setString(3,t.getApellidos());
            plataforma.setDouble(4,t.getSueldo());
            plataforma.setString(5,t.getFecha());
            plataforma.setString(6,t.getMatricula());
            
            //Se ejecuta la sentencia
            plataforma.executeUpdate();
            
            //Se cierra la conexion
            conexion.close();
            
        } catch (Exception e) {
            System.out.println("Se ha producido un error al añadir el trabajador");
            e.printStackTrace();
        }
    }
    
    //Metodo para borrar un trabajador
    public void borrarTrabajador(Trabajador t){
        
        //Conexion
        Connection conexion = new DBConection().getConexion();   
        
        try {
            //Se crea la sentencia
            String sql ="DELETE FROM trabajadores WHERE dni = ?";
            
            //Se crea la plataforma preparada
            PreparedStatement plataforma = conexion.prepareStatement(sql);
            
            //Se rellena el campo de la sentencia
            plataforma.setString(1, t.getDni());
            
            //Se ejecuta la sentencia
            plataforma.executeUpdate();
            
            //Se cierra la conexion
            conexion.close();
            
        } catch (Exception e) {
            System.out.println("Se ha producido un error al borrar el trabajador");
            e.printStackTrace();
        }
    }
    
    //Metodo para actualizar el registro de un trabajador en la DB
    public void updateTrabajador(Trabajador t){
        //Conexion
        Connection conexion = new DBConection().getConexion(); 
        
        try {
            //Se crea la sentencia
            String sql = "UPDATE trabajador SET nombre = ? , apellidos = ?, sueldo = ? , fecha = ?, matricula = ? WHERE dni = ?";
            
            
            
            
        } catch (Exception e) {
            System.out.println("Se ha producido un error al actualizar el trabajador");
            e.printStackTrace();
        }
        
        
    }
    
     //Este metodo esta mal, ya hay otro que hace lo mismo.
     /* public void filtrarTrabajador(Trabajador t){
        //Conexion
        Connection conexion = new DBConection().getConexion();
        
        try {
            //Se crea la sentencia
            String sql = "UPDATE trabajador SET nombre = ? , apellidos = ?, sueldo = ? , fecha = ?, matricula = ? WHERE dni = ?";
            
            
            
            
        } catch (Exception e) {
            System.out.println("Se ha producido un error al actualizar el trabajador");
            e.printStackTrace();
        }
        
        
    } */   
    
    	public ArrayList<Trabajador> get(){
		//Preparo un arraylist para el resultado
		ArrayList<Trabajador> lista = new ArrayList<Trabajador>();
		//pedir la conexión
		Connection conexion = new DBConection().getConexion();

		try {
			//Lanzar un SELECT
			String sql = "SELECT * FROM trabajadores";
			//Uso una plataforma "Preparada"
			PreparedStatement plataforma = conexion.prepareStatement(sql);
			ResultSet resultado = plataforma.executeQuery();

			while(resultado.next()) { //cuando se acaben las tuplas, next() retorna false
				//tratamiento de cada tupla
				lista.add(new Trabajador(resultado.getString("dni"), resultado.getString("nombre"),resultado.getString("apellidos"), resultado.getDouble("sueldo"),resultado.getString("fecha"),resultado.getString("matricula")));
				//	lista.add(new Persona(resultado.getString(1), resultado.getString(2), resultado.getLong(3)));

			}
			conexion.close();
		} catch (SQLException e) {
			System.out.println("Error obteniendo personas");
			e.printStackTrace();
		}
		return lista;
                //devuelve lista
	}

}
 