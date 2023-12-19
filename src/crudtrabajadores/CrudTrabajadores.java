/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package crudtrabajadores;

import conexion.DBConection;
import java.sql.Connection;
import vistas.FrmTrabajadores;

/**
 *
 * @author Administrador
 */
public class CrudTrabajadores {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
                Connection conexion = new DBConection().getConexion();
                FrmTrabajadores vista =new FrmTrabajadores();
                vista.setVisible(true);                
     
    }
    
}
