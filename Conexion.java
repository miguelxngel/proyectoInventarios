/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.sql.*;
import java.sql.Connection;
import javax.swing.JOptionPane;

/**
 *
 * @author migue
 */
public class Conexion {
    
    Connection conectar = null;
    
    String usuario = "root";
    String clave = "1234";
    String bd = "proyectoinventarios";
    String ip = "localhost";
    String puerto = "3306";
    
    String cadena = "jdbc:mysql://"+ip+":"+puerto+"/"+bd;
    
    public Connection establecerConexion(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conectar = DriverManager.getConnection(cadena,usuario,clave);
            //JOptionPane.showMessageDialog(null, "La conexion se ha realizado exitosamente...");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "No se pudo establecer la conexion a la base de datos"+ bd+"Error:"+e.getMessage());
        }
        return conectar;
    }
    
}
