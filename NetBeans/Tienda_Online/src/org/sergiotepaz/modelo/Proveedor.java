/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.sergiotepaz.modelo;

/**
 * Clase modelo del CRUD de proveedor
 * @author Sergio Tepaz
 */
public class Proveedor {
    
    private int id_proveedor;
    private String proveedor;
    private boolean estado;
    
    public Proveedor(){}

    public Proveedor(int id_proveedor, String proveedor, boolean estado) {
        this.id_proveedor = id_proveedor;
        this.proveedor = proveedor;
        this.estado = estado;
    }

    public int getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(int id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
}
