/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.sergiotepaz.controlador;

import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import java.util.ArrayList;

import org.sergiotepaz.db.Conexion;
import org.sergiotepaz.modelo.Venta;

/**
 * Controlador del CRUD de venta
 * @author Sergio Tepaz
 * @version 1.8
 */
public class ControlDeVenta {
    
    private static ControlDeVenta instancia;
    private static ArrayList<Venta> ventas;
    
    /**
     * Crea el metodo control de venta
     */
    public ControlDeVenta() {
        ventas = new ArrayList<Venta>();
    }
    
    /**
     * Crea la instancia unica de venta
     * @return devuelve la instancia
     */
    public static ControlDeVenta getInstancia() {
        if(instancia == null){
		instancia = new ControlDeVenta();
	} return instancia;
    }
    
    /**
     * Metodo para mostrar las ventas agregadas
     * @return devuelve las ventas
     */
    public ArrayList<Venta> mostrarVentas() {
       return ventas;
    }
    
    /**
     * Metodo para agregar ventas a la lista
     * @return devuelve la lista de ventas
     */
    public int ListaDeVentas() {
        return ventas.size();
    }
    
    /**
     * Metodo para agregar una venta y enviarla a la base de datos
     * @param venta Venta agregada
     */
    public void agregarVenta(Venta venta) {
        try {
            PreparedStatement sentencia = Conexion.getInstancia().getConexion().prepareCall("call agregarVenta(?,?,?,?,?)");
            sentencia.setInt(1, venta.getId_cliente());
            sentencia.setInt(2, venta.getId_producto());
            sentencia.setInt(3, venta.getCantidad());
            sentencia.setString(4, venta.getFecha());
            sentencia.setBoolean(5, true);
            sentencia.execute();
            JOptionPane.showMessageDialog(null,"Compra realizada con exito.");
        }catch(Exception error){
            error.printStackTrace();
        }
        ventas.add(venta);
    }
    
    /**
     * Metodo para actulizar una venta agregada
     * @param ventaAntigua Venta que se actualizara
     * @param ventaActualizada Venta actualizada
     */
    public void actualizarVenta(Venta ventaAntigua, Venta ventaActualizada) {
        try {
            PreparedStatement sentencia = Conexion.getInstancia().getConexion().prepareCall("call actualizarVenta(?,?,?,?,?,?)");
            sentencia.setInt(1, ventaActualizada.getId_venta());
            sentencia.setInt(2, ventaActualizada.getId_cliente());
            sentencia.setInt(3, ventaActualizada.getId_producto());
            sentencia.setInt(4, ventaActualizada.getCantidad());
            sentencia.setString(5, ventaActualizada.getFecha());
            sentencia.setBoolean(6, true);
            sentencia.execute();
            JOptionPane.showMessageDialog(null,"Compra actulizada.");
        }catch(Exception error){
            error.printStackTrace();
        }
        int auxiliar = ventas.indexOf(ventaAntigua);
        ventas.set(auxiliar,ventaActualizada );
    }
    
    /**
     * Metodo para buscar una venta por id
     * @param id Id de la venta
     * @return devuelve la venta buscada
     */
    public Venta buscarVenta(int id) {
        Venta ventaEncontrada = new Venta();
           for(Venta venta : ventas){
		if( venta.getId_venta()== id ){
			ventaEncontrada = venta;
			break;
		}//if
	}//for
	return ventaEncontrada;
    }
    
    /**
     * Metodo para eliminar una compra por su id
     * @param ventaEliminada Venta que se eliminara
     */
    public void eliminarCompra(Venta ventaEliminada) {
        try {
            PreparedStatement sentencia = Conexion.getInstancia().getConexion().prepareCall("call eliminarVenta(?)");
            sentencia.setInt(1, ventaEliminada.getId_venta());
            sentencia.execute();
            JOptionPane.showMessageDialog(null,"Ha eliminado la compra.");
        }catch(Exception error){
            error.printStackTrace();
        }
        ventas.remove(ventaEliminada);
    }
}
