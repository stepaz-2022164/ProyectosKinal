/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.sergiotepaz.controlador;

import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import java.util.ArrayList;

import org.sergiotepaz.db.Conexion;
import org.sergiotepaz.modelo.Proveedor;

/**
 * Controlador del CRUD de Proveedor
 * @author Sergio Tepaz
 * @version 1.8
 */
public class ControlDeProveedor {
    
    private static ControlDeProveedor instancia;
    private static ArrayList<Proveedor> proveedores;
    
    /**
     * Crea el metodo ControlDeProveedor
     */
    public ControlDeProveedor() {
        proveedores = new ArrayList<Proveedor>();
    }
    
    /**
     * Crea la instancia unica de proveedor
     * @return devuelve la instancia
     */
    public static ControlDeProveedor getInstancia() {
        if(instancia == null){
		instancia = new ControlDeProveedor();
	} return instancia;
    }
    
    /**
     * Metodo para mostrar los proveedores
     * @return devuelve los proveedores agregados
     */
    public ArrayList<Proveedor> mostrarProveedores() {
     return proveedores;
    }
    
    /**
     * Metodo para agregar los proveedores a la lista
     * @return devuelve la lista de proveedores
     */
    public int ListaDeProveedores() {
        return proveedores.size();
    }
    
    /**
     * Metodo para agregar un proveedor y enviarlo a la base de datos
     * @param proveedor Proveedor agregado
     */
    public void agregarProveedor(Proveedor proveedor) {
        try {
            PreparedStatement sentencia = Conexion.getInstancia().getConexion().prepareCall("call agregarProveedor(?,?)");
            sentencia.setString(1, proveedor.getProveedor());
            sentencia.setBoolean(2, true);
            sentencia.execute();
            JOptionPane.showMessageDialog(null,"Ha agregado un nuevo proveedor.");
        }catch(Exception error){
            error.printStackTrace();
        }
        proveedores.add(proveedor);
    }
    
    /**
     * Metodo para actualizar un proveedor agregado
     * @param proveedorAntiguo Proveedor que se actualizara
     * @param proveedorActualizado Proveedor Actualizado
     */
    public void actualizarProveedor(Proveedor proveedorAntiguo, Proveedor proveedorActualizado) {
        try {
            PreparedStatement sentencia = Conexion.getInstancia().getConexion().prepareCall("call actualizarProveedor(?,?,?)");
            sentencia.setInt(1, proveedorActualizado.getId_proveedor());
            sentencia.setString(2, proveedorActualizado.getProveedor());
            sentencia.setBoolean(3, true);
            sentencia.execute();
            JOptionPane.showMessageDialog(null,"Ha actualizado el proveedor.");
        }catch(Exception error){
            error.printStackTrace();
        }
        int auxiliar = proveedores.indexOf(proveedorAntiguo);
        proveedores.set(auxiliar,proveedorActualizado );
    }
    
    /**
     * Metodo para buscar un proveedor por id
     * @param id Id del proveedor
     * @return devuelve el proveedor buscado
     */
    public Proveedor buscarProveedor(int id) {
        Proveedor proveedorEncontrado = new Proveedor();
           for(Proveedor proveedor : proveedores ){
		if( proveedor.getId_proveedor()== id ){
		proveedorEncontrado = proveedor;
		break;
		}//if
	}//for
        return proveedorEncontrado;
    }
    
    /**
     * Metodo para eliminar un proveedor por su id
     * @param proveedorEliminado Proveedor que se eliminara
     */
    public void eliminarProveedor(Proveedor proveedorEliminado) {
        try {
            PreparedStatement sentencia = Conexion.getInstancia().getConexion().prepareCall("call eliminarProveedor(?)");
            sentencia.setInt(1, proveedorEliminado.getId_proveedor());
            sentencia.execute();
            JOptionPane.showMessageDialog(null,"Ha eliminado el proveedor.");
        }catch(Exception error){
            error.printStackTrace();
        }
    }
}
