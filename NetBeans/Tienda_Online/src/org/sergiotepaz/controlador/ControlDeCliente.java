/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.sergiotepaz.controlador;

import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import java.util.ArrayList;

import org.sergiotepaz.modelo.Cliente;
import org.sergiotepaz.db.Conexion;

/**
 * Controlador del CRUD de Cliente
 * @author Sergio Tepaz
 * @version 1.8
 */
public class ControlDeCliente {
    
    private static ControlDeCliente instancia;
    private static ArrayList<Cliente> clientes;
    
    /**
     * Crea el metodo control de cliente
     */
    public ControlDeCliente() {
        clientes = new ArrayList<Cliente>();
    }
    
    /**
     * Crea la instancia unica
     * @return devuelve la instancia unica
     */
    public static ControlDeCliente getInstancia() {
        if(instancia == null){
			instancia = new ControlDeCliente();
		} return instancia;
    }
    
    /**
     * Crea el metodo para mostrar los clientes agregados
     * @return devuelve la lista de clientes
     */
     public ArrayList<Cliente> mostrarClientes() {
        return clientes;
    }
    
     /**
      * Crea el metodo para ir agregando los clientes
      * @return devuelve los clientes agregados
      */
    public int ListaDeClientes() {
        return clientes.size();
    }
    
    /**
     * Metodo para agregar un cliente y enviarlo a la base de datos
     * @param cliente Cliente agregado 
     */
    public void agregarCliente(Cliente cliente) {
        
        try {
            PreparedStatement sentencia = Conexion.getInstancia().getConexion().prepareCall("call agregarCliente(?,?,?,?,?,?)");
            sentencia.setString(1, cliente.getNombre());
            sentencia.setString(2, cliente.getApellido());
            sentencia.setString(3, cliente.getUsuario());
            sentencia.setString(4, cliente.getDireccion());
            sentencia.setFloat(5, cliente.getTelefono());
            sentencia.setBoolean(6, true);
            sentencia.execute();           
        }catch(Exception error){
            error.printStackTrace();
        }
        clientes.add(cliente);
    }
    
    /**
     * Metodo para actualizar el cliente
     * @param clienteAntiguo Cliente que se actualizara
     * @param clienteActualizado Cliente actualizado
     */
    public void actualizarCliente(Cliente clienteAntiguo, Cliente clienteActualizado) {
        
        try {
            PreparedStatement sentencia = Conexion.getInstancia().getConexion().prepareCall("call actualizarCliente(?,?,?,?,?,?,?)");
            sentencia.setInt(1, clienteActualizado.getId_cliente());
            sentencia.setString(2, clienteActualizado.getNombre());
            sentencia.setString(3, clienteActualizado.getApellido());
            sentencia.setString(4, clienteActualizado.getUsuario());
            sentencia.setString(5, clienteActualizado.getDireccion());
            sentencia.setInt(6, clienteActualizado.getTelefono());
            sentencia.setBoolean(7, true);
            sentencia.execute();
            JOptionPane.showMessageDialog(null,"Ha actualizado el cliente."); 
        }catch(Exception error){
            error.printStackTrace();
        }
        int auxiliar = clientes.indexOf(clienteAntiguo);
        clientes.set(auxiliar,clienteActualizado );
    }
    
    /**
     * Metodo para buscar el cliente por id
     * @param id Id del cliente a buscar
     * @return devuelve el cliente buscado
     */
    public Cliente buscarCliente(int id) {
        Cliente clienteEncontrado = new Cliente();
            for(Cliente cliente : clientes){
			if( cliente.getId_cliente() == id ){
				clienteEncontrado = cliente;
				break;
			}//if
		}//for
		return clienteEncontrado;
    }
    
    /**
     * Metodo para eliminar un cliente por id
     * @param clienteEliminado Cliente que se eliminara
     */
    public void eliminarCliente(Cliente clienteEliminado) {
        
        try{
            PreparedStatement sentencia = Conexion.getInstancia().getConexion().prepareCall("call eliminarCliente(?)");
            sentencia.setInt(1, clienteEliminado.getId_cliente());
            sentencia.execute();
            JOptionPane.showMessageDialog(null,"Ha eliminado el cliente.");
        }catch(Exception error){
            error.printStackTrace();
        }
    }
}

