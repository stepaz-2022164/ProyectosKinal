/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.sergiotepaz.controlador;

import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import java.util.ArrayList;

import org.sergiotepaz.db.Conexion;
import org.sergiotepaz.modelo.Compra;

/**
 * Controlador del CRUD de Compra
 * @author Sergio Tepaz
 * @version 1.8
 */
public class ControlDeCompra {
    
    private static ControlDeCompra instancia;
    private static ArrayList<Compra> compras;
    
    /**
     * Crea el metodo ControlDeCompra
     */
    public ControlDeCompra() {
        compras = new ArrayList<Compra>();
    }
    
    /**
     * Crea la instancia unica de compra
     * @return devuelve la instancia unica
     */
    public static ControlDeCompra getInstancia() {
        if(instancia == null){
		instancia = new ControlDeCompra();
	} return instancia;
    }
    
    /**
     * Crea el metodo para mostrar las compras agregadas
     * @return devuelve la lista de compras
     */
    public ArrayList<Compra> mostrarCompras() {
       return compras;
    }
    
    /**
     * Crea el metodo para ir agregando las compras
     * @return deveuelve las compras agregadas
     */
    public int ListaDeCompras() {
        return compras.size();
    }
    
    /**
     * Metodo para agregar una compra y enviarla a la base de datos
     * @param compra Compra agregada
     */
    public void agregarCompra(Compra compra) {
        try {
            PreparedStatement sentencia = Conexion.getInstancia().getConexion().prepareCall("call agregarCompra(?,?,?,?)");
            sentencia.setInt(1, compra.getId_producto());
            sentencia.setInt(2, compra.getCantidad());
            sentencia.setString(3, compra.getFecha());
            sentencia.setBoolean(4, true);
            sentencia.execute();
            JOptionPane.showMessageDialog(null,"Ha agregado una nueva compra.");
        }catch(Exception error){
            error.printStackTrace();
        }
        compras.add(compra);
    }
    
    /**
     * Metodo para actualizar una Compra
     * @param compraAntigua compra que se actualizara
     * @param compraActualizada compra actualizada
     */
    public void actualizarCompra(Compra compraAntigua, Compra compraActualizada) {
        try {
            PreparedStatement sentencia = Conexion.getInstancia().getConexion().prepareCall("call actualizarCompra(?,?,?,?,?)");
            sentencia.setInt(1, compraActualizada.getId_compra());
            sentencia.setInt(2, compraActualizada.getId_producto());
            sentencia.setInt(3, compraActualizada.getCantidad());
            sentencia.setString(4, compraActualizada.getFecha());
            sentencia.setBoolean(5, true);
            sentencia.execute();
            JOptionPane.showMessageDialog(null,"Ha actualizado la compra.");
        }catch(Exception error){
            error.printStackTrace();
        }
        int auxiliar = compras.indexOf(compraAntigua);
        compras.set(auxiliar,compraActualizada );
    }
    
    /**
     * Metodo para buscar una compra agregada
     * @param id Id de la compra a buscar
     * @return devuelve la compra buscada
     */
    public Compra buscarCompra(int id) {
        Compra compraEncontrada = new Compra();
           for(Compra compra : compras){
		if( compra.getId_compra()== id ){
			compraEncontrada = compra;
			break;
		}//if
	}//for
	return compraEncontrada;
    }
    
    /**
     * Metodo para eliminar una compra
     * @param compraEliminada Compra que se eliminara
     */
    public void eliminarCompra(Compra compraEliminada) {
        try {
            PreparedStatement sentencia = Conexion.getInstancia().getConexion().prepareCall("call eliminarCompra(?)");
            sentencia.setInt(1, compraEliminada.getId_compra());
            sentencia.execute();
            JOptionPane.showMessageDialog(null,"Ha eliminado la compra.");
        }catch(Exception error){
            error.printStackTrace();
        }
        compras.remove(compraEliminada);
    }
}
 

    

