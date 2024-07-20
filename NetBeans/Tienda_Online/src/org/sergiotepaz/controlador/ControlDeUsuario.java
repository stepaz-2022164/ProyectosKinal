/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.sergiotepaz.controlador;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import java.util.ArrayList;

import org.sergiotepaz.db.Conexion;
import org.sergiotepaz.modelo.Usuario;

/**
 * Controlador del CRUD de Usuario
 * @author Sergio Tepaz
 * @version 1.8
 */
public class ControlDeUsuario {
    
    private static ControlDeUsuario instancia;
    private static ArrayList<Usuario> usuarios;
    
    /**
     * Crea el metodo Control de usuario
     */
    public ControlDeUsuario() {
        usuarios = new ArrayList<Usuario>();
    }
    
    /**
     * Crea la instancia unica de usuario
     * @return  devuelve la instancia
     */
    public static ControlDeUsuario getInstancia() {
        if(instancia == null){
		instancia = new ControlDeUsuario();
	} return instancia;
    }
    
    /**
     * Crea el metodo para mostrar los usuarios agregados
     * @return muestra los usuarios agregados
     */
    public ArrayList<Usuario> mostrarUsuarios() {
       return usuarios;
    }
    
    /**
     * Crea el metodo para agregar los a usuarios a la lista
     * @return devuelve la lista de usuarios
     */
    public int ListaDeUsuarios() {
        return usuarios.size();
    }
    
    /**
     * Crea el metodo para agregar un usuario y enviarlo a la base de datos
     * @param usuario Usuario agregado
     */
    public void agregarUsuario(Usuario usuario) {
        try {
            PreparedStatement sentencia = Conexion.getInstancia().getConexion().prepareCall("call agregarUsuario(?,?,?,?,?)");
            sentencia.setInt(1, usuario.getId_cliente());
            sentencia.setString(2, usuario.getUsuario());
            sentencia.setString(3,usuario.getPassword());
            sentencia.setBoolean(4, true);
            sentencia.setBoolean(5, true);
            sentencia.execute();
            JOptionPane.showMessageDialog(null,"Ha agregado un nuevo usuario.");
        }catch(Exception error){
            error.printStackTrace();
        }
        usuarios.add(usuario);
    }
    
    /**
     * Crea el metodo para actualizar un usuario
     * @param usuarioAntiguo Usuario que se actualizara
     * @param usuarioActualizado Usuario actualizado
     */
    public void actualizarUsuario(Usuario usuarioAntiguo, Usuario usuarioActualizado) {
        try {
            PreparedStatement sentencia = Conexion.getInstancia().getConexion().prepareCall("call actualizarUsuario(?,?,?,?,?,?)");
            sentencia.setInt(1, usuarioActualizado.getId_usuario());
            sentencia.setInt(2, usuarioActualizado.getId_cliente());
            sentencia.setString(3, usuarioActualizado.getUsuario());
            sentencia.setString(4, usuarioActualizado.getPassword());
            sentencia.setBoolean(5, true&false);
            sentencia.setBoolean(6, true);
            sentencia.execute();
            JOptionPane.showMessageDialog(null,"Ha actualizado el usuario.");
        }catch(Exception error){
            error.printStackTrace();
        }
         int auxiliar = usuarios.indexOf(usuarioAntiguo);
         usuarios.set(auxiliar,usuarioActualizado );
    }
    
    /**
     * Metodo para buscar un usuario por id
     * @param id Id del usuario
     * @return devuelve el usuario buscado
     */
    public Usuario buscarUsuario(int id) {
        Usuario usuarioEncontrado = new Usuario();
           for(Usuario usuario : usuarios){
		if( usuario.getId_usuario()== id ){
			usuarioEncontrado = usuario;
			break;
		}//if
	}//for
	return usuarioEncontrado;
    }
    
    /**
     * Metodo para eliminar un usuario por su id
     * @param usuarioEliminado Usuario que se eliminara
     */
    public void eliminarUsuario(Usuario usuarioEliminado) {
        try {
            PreparedStatement sentencia = Conexion.getInstancia().getConexion().prepareCall("call eliminarUsuario(?)");
            sentencia.setInt(1, usuarioEliminado.getId_usuario());
            sentencia.execute();
            JOptionPane.showMessageDialog(null,"Ha eliminado el usuario.");
        }catch(Exception error){
            error.printStackTrace();
        }
    }
}
