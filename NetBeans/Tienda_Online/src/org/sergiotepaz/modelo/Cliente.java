/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.sergiotepaz.modelo;

/**
 * Clase modelo del CRUD cliente
 * @author Sergio Tepaz
 */
public class Cliente {
    
    private int id_cliente;
    private String nombre;
    private String apellido;
    private String usuario;
    private String direccion;
    private int telefono;
    private boolean estado;
    
    /**
     * Constructor vacio
     */
    public Cliente(){}
    
    /**
     * Constructor con los paramentros de cliente
     * @param id_cliente Id del cliente
     * @param nombre Nombre del cliente
     * @param apellido Apellido del cliente
     * @param usuario Usuario del cliente
     * @param direccion Direccion del cliente
     * @param telefono Teledono del cliente
     * @param estado Estado activo del cliente
     */
    public Cliente(int id_cliente, String nombre, String apellido, String usuario, String direccion, int telefono, boolean estado) {
        this.id_cliente = id_cliente;
        this.nombre = nombre;
        this.apellido = apellido;
        this.usuario = usuario;
        this.direccion = direccion;
        this.telefono = telefono;
        this.estado = estado;
    }
    
    /**
     * Obtiene el id del cliente
     * @return devuelve el id del cliente
     */
    public int getId_cliente() {
        return id_cliente;
    }
    
    /**
     * Envia el id del cliente
     * @param id_cliente Id del cliente
     */
    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }
    
    /**
     * Obtiene el nombre del cliente
     * @return devuelve el nombre
     */
    public String getNombre() {
        return nombre;
    }
    
    /**
     * Envia el nombre del cliente
     * @param nombre Nombre del clliente
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    /**
     * Obtiene el apellido del cliente
     * @return devuelve el apellido
     */
    public String getApellido() {
        return apellido;
    }
    
    /**
     * Envia el apellido del cliente
     * @param apellido Apellido del cliente
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    
    /**
     * Obtiene el usuario el cliente
     * @return devuelve el usuario
     */
    public String getUsuario() {
        return usuario;
    }
    
    /**
     * Envia el usuario del cliente
     * @param usuario Usuario del cliente
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
    /**
     * Obtiene la direccion del cliente
     * @return devuelve la direccion 
     */
    public String getDireccion() {
        return direccion;
    }
    
    /**
     * Envia la direccion del cliente
     * @param direccion Direccion del cliente
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    /**
     * Obtiene el telefono del cliente
     * @return devuelve el telefono
     */
    public int getTelefono() {
        return telefono;
    }
    
    /**
     * Envia el telefono del cliente
     * @param telefono Telefono del cliente
     */
    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }
    
    /**
     * Obtiene el estado del cliente
     * @return devuelve el estado
     */
    public boolean isEstado() {
        return estado;
    }
    
    /**
     * Envia el estado del cliente
     * @param estado Estado activo del cliente
     */
    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
}
