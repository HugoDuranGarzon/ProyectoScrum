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
            String sql = "UPDATE trabajadores SET nombre = ? , apellidos = ?, sueldo = ? , fecha = ?, matricula = ? WHERE dni = ?";
            
            //Se crea una plataforma preparada
            PreparedStatement plataforma = conexion.prepareStatement(sql);
            
            //Se rellenan los campos de la sentencia preparada
            
            plataforma.setString(1,t.getNombre());
            plataforma.setString(2,t.getApellidos());
            plataforma.setDouble(3,t.getSueldo());
            plataforma.setString(4,t.getFecha());
            plataforma.setString(5,t.getMatricula());
            plataforma.setString(6,t.getDni());
            //Se ejecuta la sentencia
            plataforma.executeUpdate();
            
            //Se cierra la conexion
            conexion.close();
            
            
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
    	public ArrayList<Trabajador> filtrar(Trabajador t, String comparador_sueldo, String comparador_fecha, String orden, String valorOrdenacion){
		//Preparo un arraylist para el resultado
                                    
		ArrayList<Trabajador> filtrado = new ArrayList<Trabajador>();
		//pedir la conexión
                
		Connection conexion = new DBConection().getConexion();
                                    int ncond=0;
                                    String sueldo = "";
                                    if (t.sueldo != 0.0) {
                                    sueldo = String.valueOf(t.sueldo);
                                    }
                                    
                                    try {
                                    //Lanzar un SELECT
                                    String sql = "SELECT * FROM trabajadores";
                                    if( !t.dni.equals("")) {
                                    ncond++;
                                    sql+=" where dni like '%"+t.dni+"%'";
                                    }
                                    if( !t.nombre.equals( "")){
                                    ncond++;
                                    if (ncond==1){
                                    sql+=" where nombre like '%"+t.nombre+"%'";
                                    } else {
                                     sql+= " and nombre like '%"+t.nombre+"%'" ;
                                    }
                                    }
                                    if( !t.apellidos.equals( "")){
                                    ncond++;
                                    if (ncond==1){
                                    sql+=" where apellidos like '%"+t.apellidos+"%' ";
                                    } else {
                                     sql+= " and apellidos like '%"+t.apellidos+"%' " ;
                                        }        
                                    }
                                    if( !sueldo.equals( "")){
                                    ncond++;
                                    if (ncond==1){
                                            switch (comparador_sueldo) {
                                        case "<":
                                            sql+=" where sueldo < '"+t.sueldo+"'";
                                            break;
                                        case ">":
                                            sql+=" where sueldo > '"+t.sueldo+"'";
                                            break;
                                        case "=":
                                            sql+=" where sueldo = '"+t.sueldo+"'";
                                            break;
                                        case "<=":
                                            sql+=" where sueldo <= '"+t.sueldo+"'";
                                            break;
                                        case ">=":
                                            sql+=" where sueldo >= '"+t.sueldo+"'";
                                            break;
                                        case "<>":
                                            sql+=" where sueldo <> '"+t.sueldo+"'";
                                            break;
                                        default:
                                            System.out.println("Operador no reconocido");
                                    }    
                                    }else{
                                            switch (comparador_sueldo) {
                                        case "<":
                                            sql+=" and sueldo < '"+t.sueldo+"'";
                                            break;
                                        case ">":
                                            sql+=" and sueldo > '"+t.sueldo+"'";
                                            break;
                                        case "=":
                                            sql+=" and sueldo = '"+t.sueldo+"'";
                                            break;
                                        case "<=":
                                            sql+=" and sueldo <= '"+t.sueldo+"'";
                                            break;
                                        case ">=":
                                            sql+=" and sueldo >= '"+t.sueldo+"'";
                                            break;
                                        case "<>":
                                            sql+=" and sueldo <> '"+t.sueldo+"'";
                                            break;
                                        default:
                                            System.out.println("Operador no reconocido");
                                    }
                                    
                                    }
                                    }
                                    if( !t.matricula.equals( "")){
                                    ncond++;
                                    if (ncond==1){
                                    sql+=" where matricula like '%"+t.matricula+"%' ";
                                    } else {
                                     sql+= " and matricula like '%"+t.matricula+"%' " ;
                                        }
                                    }
                                    if( !t.fecha.equals( "")){
                                    ncond++;
                                    if (ncond==1){
                                            switch (comparador_fecha) {
                                        case "<":
                                            sql+=" where fecha < '"+t.fecha+"'";
                                            break;
                                        case ">":
                                            sql+=" where fecha > '"+t.fecha+"'";
                                            break;
                                        case "=":
                                            sql+=" where fecha = '"+t.fecha+"'";
                                            break;
                                        case "<=":
                                            sql+=" where fecha <= '"+t.fecha+"'";
                                            break;
                                        case ">=":
                                            sql+=" where fecha >= '"+t.fecha+"'";
                                            break;
                                        case "<>":
                                            sql+=" where fecha <> '"+t.fecha+"'";
                                            break;
                                        default:
                                            System.out.println("Operador no reconocido");
                                    }    
                                    }else{
                                            switch (comparador_fecha) {
                                        case "<":
                                            sql+=" and fecha < '"+t.fecha+"'";
                                            break;
                                        case ">":
                                            sql+=" and fecha > '"+t.fecha+"'";
                                            break;
                                        case "=":
                                            sql+=" and fecha = '"+t.fecha+"'";
                                            break;
                                        case "<=":
                                            sql+=" and fecha <= '"+t.fecha+"'";
                                            break;
                                        case ">=":
                                            sql+=" and fecha >= '"+t.fecha+"'";
                                            break;
                                        case "<>":
                                            sql+=" and fecha <> '"+t.fecha+"'";
                                            break;
                                        default:
                                            System.out.println("Operador no reconocido");
                                    }
                                    }
                                    }
                                    if( valorOrdenacion == "(Sin ordenacion)" || orden == ""){
                                    }else{
                                    ncond++; 
                                         if (ncond>=1){
                                            switch (valorOrdenacion) {
                                        case "DNI":
                                            sql+=" ORDER BY "+valorOrdenacion+" "+orden;
                                            break;
                                        case "Nombre":
                                            sql+=" ORDER BY "+valorOrdenacion+" "+orden;
                                            break;
                                        case "Apellidos":
                                            sql+=" ORDER BY "+valorOrdenacion+" "+orden;
                                            break;
                                        case "Sueldo":
                                            sql+=" ORDER BY "+valorOrdenacion+" "+orden;
                                            break;
                                        case "Fecha":
                                            sql+=" ORDER BY "+valorOrdenacion+" "+orden;
                                            break;
                                        case "Matricula":
                                            sql+=" ORDER BY "+valorOrdenacion+" "+orden;
                                            break;
                                        default:
                                            System.out.println("Operador no reconocido");
                                    }    
                                    }
                                    }
                                        System.out.println(sql);
			//Uso una plataforma "Preparada"
			PreparedStatement plataforma = conexion.prepareStatement(sql);
			ResultSet resultado_filtrar = plataforma.executeQuery();

			while(resultado_filtrar.next()) { //cuando se acaben las tuplas, next() retorna false
				//tratamiento de cada tupla
				filtrado.add(new Trabajador(resultado_filtrar.getString("dni"), resultado_filtrar.getString("nombre"),resultado_filtrar.getString("apellidos"), resultado_filtrar.getDouble("sueldo"),resultado_filtrar.getString("fecha"),resultado_filtrar.getString("matricula")));
				//	lista.add(new Persona(resultado.getString(1), resultado.getString(2), resultado.getLong(3)));

			}
			conexion.close();
		} catch (SQLException e) {
			System.out.println("Error obteniendo personas");
			e.printStackTrace();
		}
		return filtrado;
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
 