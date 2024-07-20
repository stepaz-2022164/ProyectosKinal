    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.sergiotepaz.modelo;

/**
 * Clase modelo del CRUD de producto
 * @author Sergio Tepaz
 * @version 1.8
 */
public class Producto {
    
    private int id_producto;
    private int id_proveedor;
    private String producto;
    private int precio;
    private boolean estado;
    
    /**
     * Constructor vacio
     */
    public Producto(){}

    /**
     * Constructor con los parametros de producto
     * @param id_producto Id del producto
     * @param id_proveedor Id del proveedor
     * @param producto Nombre del producto
     * @param precio Precio del producto
     * @param estado Estado activo del producto
     */
    public Producto(int id_producto, int id_proveedor, String producto, int precio, boolean estado) {
        this.id_producto = id_producto;
        this.id_proveedor = id_proveedor;
        this.producto = producto;
        this.precio = precio;
        this.estado = estado;
    }
    
    /**
     * Obtiene el id del producto
     * @return devuelve el id
     */
    public int getId_producto() {
        return id_producto;
    }

    /**
     * Envia el id del producto
     * @param id_producto Id del producto
     */
    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }
    
    /**
     * Obtiene el id del proveedor
     * @return devuelve el id del proveedor
     */
    public int getId_proveedor() {
        return id_proveedor;
    }

    /**
     * Envia el id del proveedor
     * @param id_proveedor Id del proveedor
     */
    public void setId_proveedor(int id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    /**
     * Obtiene el producto
     * @return devuelve el producto
     */
    public String getProducto() {
        return producto;
    }

    /**
     * Envia el producto
     * @param producto Nombre del producto
     */
    public void setProducto(String producto) {
        this.producto = producto;
    }
    
    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
    
}
