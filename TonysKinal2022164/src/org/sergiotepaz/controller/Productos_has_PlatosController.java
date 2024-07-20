package org.sergiotepaz.controller;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.swing.JOptionPane;
import org.sergiotepaz.bean.Plato;
import org.sergiotepaz.bean.Producto;
import org.sergiotepaz.bean.Productos_has_Platos;
import org.sergiotepaz.db.Conexion;
import org.sergiotepaz.main.Principal;

public class Productos_has_PlatosController implements Initializable{
    private Principal escenarioPrincipal;
    private enum operaciones {NINGUNO,GUARDAR,ACTUALIZAR,ELIMINAR}
    private operaciones tipoDeOperacion = operaciones.NINGUNO;
    private ObservableList<Productos_has_Platos> listaProductos_has_Platos;
    private ObservableList<Plato> listaPlato;
    private ObservableList<Producto> listaProducto;
    
    @FXML
    private TextField txtProductos_codigoProducto;
    @FXML
    private ComboBox cmbCodigoPlato;
    @FXML
    private ComboBox cmbCodigoProducto;
    @FXML
    private TableView tblProductos_has_Platos;
    @FXML
    private TableColumn colProductos_CodigoProducto;
    @FXML
    private TableColumn colCodigoPlato;
    @FXML
    private TableColumn colCodigoProducto;
    @FXML
    private Button btnNuevo;
    @FXML
    private Button btnEliminar;
    @FXML 
    private Button btnEditar;
    @FXML 
    private Button btnReporte;
    @FXML
    private ImageView imgNuevo;
    @FXML
    private ImageView imgEliminar;
    @FXML 
    private ImageView imgEditar;
    @FXML
    private ImageView imgReporte;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cargarDatos();
        desactivarControles();
        cmbCodigoPlato.setItems(getPlato());
        cmbCodigoProducto.setItems(getProducto());
        colProductos_CodigoProducto.setCellValueFactory(new PropertyValueFactory<Productos_has_Platos, Integer>("Productos_codigoProducto"));
        colCodigoPlato.setCellValueFactory(new PropertyValueFactory<Productos_has_Platos, Integer>("codigoPlato"));
        colCodigoProducto.setCellValueFactory(new PropertyValueFactory<Productos_has_Platos, Integer>("codigoProducto"));
    }
    
    public void cargarDatos() {
        tblProductos_has_Platos.setItems(getProductos_has_Platos());
    }
    
    public ObservableList<Productos_has_Platos> getProductos_has_Platos() {
        ArrayList<Productos_has_Platos> lista = new ArrayList<Productos_has_Platos>();
        try {
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("call sp_ListarProductos_has_Platos()");
            ResultSet resultado = procedimiento.executeQuery();
            while (resultado.next()) {
                lista.add(new Productos_has_Platos(resultado.getInt("Productos_codigoProducto"),
                        resultado.getInt("codigoPlato"),
                        resultado.getInt("codigoProducto")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaProductos_has_Platos = FXCollections.observableArrayList(lista);
    }
    
    public void seleccionarElemento() {
        if (tipoDeOperacion == operaciones.GUARDAR) {
            limpiarControles();
        } else {
            if (tblProductos_has_Platos.getSelectionModel().getSelectedItem() == null) {
                JOptionPane.showMessageDialog(null, "Seleccione un elemento existente");
            } else {
                txtProductos_codigoProducto.setText(String.valueOf(((Productos_has_Platos) tblProductos_has_Platos.getSelectionModel().getSelectedItem()).getProductos_codigoProducto()));
                cmbCodigoPlato.getSelectionModel().select(buscarPlato(((Productos_has_Platos) tblProductos_has_Platos.getSelectionModel().getSelectedItem()).getCodigoPlato()));
                cmbCodigoProducto.getSelectionModel().select(buscarProducto(((Productos_has_Platos) tblProductos_has_Platos.getSelectionModel().getSelectedItem()).getCodigoProducto()));
            }
        }
    }
    
    public Producto buscarProducto(int codigoProducto) {
        Producto resultado = null;
        try {
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("call sp_BuscarProducto(?)");
            procedimiento.setInt(1, codigoProducto);
            ResultSet registro = procedimiento.executeQuery();
            while (registro.next()) {                
                resultado = new Producto(registro.getInt("codigoProducto"),
                registro.getString("nombreProducto"),
                registro.getInt("cantidadProducto"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultado;
    }
    
    public Plato buscarPlato(int codigoPlato) {
        Plato resultado = null;
        try {
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("call sp_BuscarPlato(?)");
            procedimiento.setInt(1, codigoPlato);
            ResultSet registro = procedimiento.executeQuery();
            while (registro.next()) {                
                resultado = new Plato(registro.getInt("codigoPlato"),
                registro.getInt("cantidad"),
                registro.getString("nombrePlato"),
                registro.getString("descripcionPlato"),
                registro.getDouble("precioPlato"),
                registro.getInt("codigoTipoPlato"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultado;
    }
    
    public ObservableList<Producto> getProducto() {
        ArrayList<Producto> lista = new ArrayList<Producto>();
        try {
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("call sp_ListarProductos");
            ResultSet resultado = procedimiento.executeQuery();
            while (resultado.next()) {
                lista.add(new Producto(resultado.getInt("codigoProducto"),
                        resultado.getString("nombreProducto"),
                        resultado.getInt("cantidadProducto")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaProducto = FXCollections.observableArrayList(lista);
    }
    
    public ObservableList<Plato> getPlato() {
        ArrayList<Plato> lista = new ArrayList<Plato>();
        try {
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("call sp_ListarPlatos()");
            ResultSet resultado = procedimiento.executeQuery();
            while (resultado.next()) {                
                lista.add(new Plato(resultado.getInt("codigoPlato"),
                        resultado.getInt("cantidad"),
                        resultado.getString("nombrePlato"),
                        resultado.getString("descripcionPlato"),
                        resultado.getDouble("precioPlato"),
                        resultado.getInt("codigoTipoPlato")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaPlato = FXCollections.observableArrayList(lista);
    }
    
    public void nuevo() {
        switch (tipoDeOperacion) {
            case NINGUNO:
                limpiarControles();
                activarControles();
                btnNuevo.setText("Guardar");
                btnEliminar.setText("Cancelar");
                imgNuevo.setImage(new Image("/org/sergiotepaz/image/guardar.png"));
                imgEliminar.setImage(new Image("/org/sergiotepaz/image/cancelar.png"));
                tipoDeOperacion = operaciones.GUARDAR;
                break;

            case GUARDAR:
                if ((txtProductos_codigoProducto.getText().length() == 0) || (cmbCodigoPlato.getValue() == "") || (cmbCodigoProducto.getValue() == "")) {
                    JOptionPane.showMessageDialog(null, "Por favor llene todos los campos");
                } else {
                    guardar();
                    limpiarControles();
                    desactivarControles();
                    btnNuevo.setText("Nuevo");
                    btnEliminar.setText("Eliminar");
                    imgNuevo.setImage(new Image("/org/sergiotepaz/image/agregar.png"));
                    imgEliminar.setImage(new Image("/org/sergiotepaz/image/eliminar.png"));
                    tipoDeOperacion = operaciones.NINGUNO;
                    cargarDatos();
                    break;
                }
        }
    }
    
    public void guardar() {
        Productos_has_Platos registro = new Productos_has_Platos();
        registro.setProductos_codigoProducto(Integer.parseInt(txtProductos_codigoProducto.getText()));
        registro.setCodigoPlato(((Plato) cmbCodigoPlato.getSelectionModel().getSelectedItem()).getCodigoPlato());
        registro.setCodigoProducto(((Producto) cmbCodigoProducto.getSelectionModel().getSelectedItem()).getCodigoProducto());
        try {
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("call sp_AgregarProductos_has_Platos(?,?,?)");
            procedimiento.setInt(1, registro.getProductos_codigoProducto());
            procedimiento.setInt(2, registro.getCodigoPlato());
            procedimiento.setInt(3, registro.getCodigoProducto());
            procedimiento.execute();
            listaProductos_has_Platos.add(registro);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void eliminar() {
        switch (tipoDeOperacion) {
            case GUARDAR:
                limpiarControles();
                desactivarControles();
                btnNuevo.setText("Nuevo");
                btnEliminar.setText("Eliminar");
                imgNuevo.setImage(new Image("/org/sergiotepaz/image/agregar.png"));
                imgEliminar.setImage(new Image("/org/sergiotepaz/image/eliminar.png"));
                tipoDeOperacion = operaciones.NINGUNO;
                cargarDatos();
                break;
        }
    }
    
    public void desactivarControles() {
        txtProductos_codigoProducto.setEditable(false);
        cmbCodigoPlato.setDisable(true);
        cmbCodigoProducto.setDisable(true);
    }
    
    public void activarControles() {
        txtProductos_codigoProducto.setEditable(true);
        cmbCodigoPlato.setDisable(false);
        cmbCodigoProducto.setDisable(false);
    }
    
    public void limpiarControles() {
        txtProductos_codigoProducto.clear();
        cmbCodigoPlato.setValue("");
        cmbCodigoProducto.setValue("");
    }

    public Principal getEscenarioPrincipal() {
        return escenarioPrincipal;
    }

    public void setEscenarioPrincipal(Principal escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    }
    
    public void menuPrincipal() {
        escenarioPrincipal.menuPrincipal();
    }
}
