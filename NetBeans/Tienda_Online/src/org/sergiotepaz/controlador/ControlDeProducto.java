/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.sergiotepaz.controlador;

import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import java.util.ArrayList;

import org.sergiotepaz.db.Conexion;
import org.sergiotepaz.modelo.Producto;


/**
 * Controlador del CRUD de Producto
 * @author Sergio Tepaz
 * @version 1.8
 */
public class ControlDeProducto {
    
    private static ControlDeProducto instancia;
    private static ArrayList<Producto> productos;
    
    /**
     * Crea el metodo ControlDeProducto
     */
    public ControlDeProducto() {
        productos = new ArrayList<Producto>();
    }
    
    /**
     * Crea la instancia unica de producto
     * @return devuelve la instancia unica
     */
    public static ControlDeProducto getInstancia() {
        if(instancia == null){
		instancia = new ControlDeProducto();
	} return instancia;
    }
    
    /**
     * Metodo para mostrar los productos
     * @return devuelve los productos agregados
     */
    public ArrayList<Producto> mostrarProductos() {
       return productos;
    }
    
    /**
     * Metodo para agregar productos a la lista
     * @return devuelve la lista de productos
     */
    public int ListaDeProductos() {
        return productos.size();
    }
    
    /**
     * Metodo para agregar productos y enviarlos a la base de datos
     * @param producto Producto agregado
     */
    public void agregarProducto(Producto producto) {
        productos.add(producto);
        try {
            PreparedStatement sentencia = Conexion.getInstancia().getConexion().prepareCall("call agregarProducto(?,?,?,?)");
            sentencia.setInt(1, producto.getId_proveedor());
            sentencia.setString(2, producto.getProducto());
            sentencia.setFloat(3, producto.getPrecio());
            sentencia.setBoolean(4, true);
            sentencia.execute();
            JOptionPane.showMessageDialog(null,"Ha agregado un nuevo producto.");
        }catch(Exception error){
            error.printStackTrace();
        }
    }
    
    /**
     * Metodo para actualizar un producto agregado
     * @param productoAntiguo Producto que se actualizara
     * @param productoActualizado Producto actualizado
     */
    public void actualizarProducto(Producto productoAntiguo, Producto productoActualizado) {
        try {
            PreparedStatement sentencia = Conexion.getInstancia().getConexion().prepareCall("call actualizarProducto(?,?,?,?,?)");
            sentencia.setInt(1, productoActualizado.getId_producto());
            sentencia.setInt(2, productoActualizado.getId_proveedor());
            sentencia.setString(3, productoActualizado.getProducto());
            sentencia.setFloat(4, productoActualizado.getPrecio());
            sentencia.setBoolean(5, true);
            sentencia.execute();
            JOptionPane.showMessageDialog(null,"Ha actualizado el producto.");
        }catch(Exception error){
            error.printStackTrace();
        }
        int auxiliar = productos.indexOf(productoAntiguo);
        productos.set(auxiliar,productoActualizado );
    }
    
    /**
     * Metodo para buscar un producto por id
     * @param id Id del producto a buscar
     * @return devuelve el producto buscado
     */
    public Producto buscarProducto(int id) {
        Producto productoEncontrado = new Producto();
           for(Producto producto : productos){
		if( producto.getId_producto()== id ){
			productoEncontrado = producto;
			break;
		}//if
	}//for
	return productoEncontrado;
    }
    
    /**
     * Metodo para eliminar un producto por su id
     * @param productoEliminado Producto que se eliminara
     */
    public void eliminarProducto(Producto productoEliminado) {
        try {
            PreparedStatement sentencia = Conexion.getInstancia().getConexion().prepareCall("call eliminarProducto(?)");
            sentencia.setInt(1, productoEliminado.getId_producto());
            sentencia.execute();
            JOptionPane.showMessageDialog(null,"Ha eliminado el producto.");
        }catch(Exception error){
            error.printStackTrace();
        }
        productos.remove(productoEliminado);
    }
}

