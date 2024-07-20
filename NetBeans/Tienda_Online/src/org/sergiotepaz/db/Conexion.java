/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.sergiotepaz.db;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Clase que realiza la conexion con la base de datos
 * 
 * @author Sergio Tepaz
 * @version 1.8
 */
public class Conexion {
    private Connection conexion;
    private static Conexion instancia;
    private Conexion(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_tienda?useSSL=false", "root", "kinalv");
        }catch(Exception error){
            error.printStackTrace();
        }
    }
    
    public synchronized static Conexion getInstancia(){
        if(instancia==null)
            instancia = new Conexion();
        return instancia;
    }
    
    public Connection getConexion(){
        return conexion;
    }
    
     public void setConexion(Connection conexion){
        this.conexion = conexion;
    }
}
