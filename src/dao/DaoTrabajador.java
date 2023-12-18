/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import com.mysql.cj.jdbc.ConnectionImpl;
import conexion.DBConection;
import java.sql.*;
import entidades.Trabajador;
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
        //private Connection conexion = new DBConection().getConexion();   Se debe crear la clase de DBConection aún
        
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
        //private Connection conexion = new DBConection().getConexion();   Se debe crear la clase de DBConection aún
        
        try {
            //Se crea la sentencia
            String sql ="DELETE FROM trabajadores WHERE dni = ?";
            
            //Se crea la plataforma preparada
            PreparedStatement plataforma = conexion.prepareStatement(sql);
            
            //Se rellena el campo de la sentencia
            plataforma.setString(1, t.getDni);
            
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
        //private Connection conexion = new DBConection().getConexion();   Se debe crear la clase de DBConection aún
        
        try {
            //Se crea la sentencia
            String sql = "UPDATE trabajador SET nombre = ? , apellidos = ?, sueldo = ? , fecha = ?, matricula = ? WHERE dni = ?";
            
            
            
            
        } catch (Exception e) {
            System.out.println("Se ha producido un error al actualizar el trabajador");
            e.printStackTrace();
        }
        
        
    }
    
    
}
