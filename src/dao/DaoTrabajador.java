/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
   */  
package dao;
import conexion.DBConection;
import java.sql.*;
import entidades.Trabajador;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    public void borrarTrabajador(Object dni){
        
        //Conexion
        Connection conexion = new DBConection().getConexion();   
        String S_dni = dni.toString();
        try {
            //Se crea la sentencia
            String sql ="DELETE FROM trabajadores WHERE dni = '"+S_dni+"' ";
            
            //Se crea la plataforma preparada
            PreparedStatement plataforma = conexion.prepareStatement(sql);
            
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
        
        public String SueldoMedio() throws SQLException{
            String columna="sueldo";
                // Realizar la conexión y calcular la media
                Connection conexion = new DBConection().getConexion();
                // Consulta SQL para obtener todos los valores de la columna
            String consulta = "SELECT sueldo FROM trabajadores";
            
            try (PreparedStatement statement = conexion.prepareStatement(consulta)) {
                // Ejecutar la consulta
                ResultSet resultSet = statement.executeQuery();

                // Calcular la media de los valores
                double suma = 0;
                int cantidadValores = 0;

                try {
                    while (resultSet.next()) {
                        double valor = resultSet.getDouble(columna);
                        suma += valor;
                        cantidadValores++;
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(DaoTrabajador.class.getName()).log(Level.SEVERE, null, ex);
                }


                    double media = suma / cantidadValores;
                    DecimalFormat formatoDecimal = new DecimalFormat("#.##");
                    String fmedia = formatoDecimal.format(media);
                    return fmedia;
            }
        }

}
 