/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.sergiotepaz.modelo;

/**
 * Clase modelo del CRUD compra
 * @author Sergio Tepaz
 */
public class Compra {
    
    private int id_compra;
    private int id_producto;
    private int cantidad;
    private String fecha;
    private boolean estado;
    
    /**
     * Constructor vacio
     */
    public Compra(){}
    
    /**
     * Constructor con los parametros de compra
     * @param id_compra Id de compa
     * @param id_producto Id del producto
     * @param cantidad Cantidad a comprar
     * @param fecha Fecha de la compra
     * @param estado Estado activo de la compra
     */
    public Compra(int id_compra,int id_producto,int cantidad ,String fecha, boolean estado) {
        this.id_compra = id_compra;
        this.fecha = fecha;
        this.estado = estado;
        this.id_producto = id_producto;
        this.cantidad = cantidad;
    }
    
    /**
     * Obtiene el id de la compra
     * @return devuelve el id 
     */
    public int getId_compra() {
        return id_compra;
    }
    
    /**
     * Envia el id de la compra
     * @param id_compra Id de la compra
     */
    public void setId_compra(int id_compra) {
        this.id_compra = id_compra;
    }
    
    /**
     * Obtiene la fecha de la compra
     * @return devuelve la fecha
     */
    public String getFecha() {
        return fecha;
    }
    
    /**
     * Envia la fecha de la compra
     * @param fecha Fecha de la compra
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    /**
     * Obtiene el estado de la compra
     * @return devuelve el estado
     */
    public boolean isEstado() {
        return estado;
    }
    
    /**
     * Envia el estado de la compra
     * @param estado Estado activo de la compra
     */
    public void setEstado(boolean estado) {
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
     * Obtiene la cantidad a comprar
     * @return devuelve la cantidad
     */
    public int getCantidad() {
        return cantidad;
    }
    
    /**
     * Envia la cantidad a comprar
     * @param cantidad Cantidad a comprar
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
