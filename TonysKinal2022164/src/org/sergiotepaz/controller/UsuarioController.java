package org.sergiotepaz.controller;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Observable;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.swing.JOptionPane;
import org.apache.commons.codec.digest.DigestUtils;
import org.sergiotepaz.bean.Usuario;
import org.sergiotepaz.db.Conexion;
import org.sergiotepaz.main.Principal;

public class UsuarioController implements Initializable{
    private Principal escenarioPrincipal;
    private enum operaciones {
        NINGUNO, GUARDAR}
    private operaciones tipoDeOperacion = operaciones.NINGUNO;
    private ObservableList<Usuario> listaUsuarios;
    
    @FXML
    private TextField txtCodigoUsuario;
    @FXML 
    private TextField txtNombreUsuario;
    @FXML
    private TextField txtApellidoUsuario;
    @FXML
    private TextField txtUsuario;
    @FXML
    private TextField txtPassword;
    @FXML 
    private Button btnNuevo;
    @FXML 
    private Button btnEliminar;
    @FXML
    private ImageView imgNuevo;
    @FXML
    private ImageView imgCancelar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        desactivarControles();
    }
    
    public ObservableList<Usuario> getUsuario() {
        ArrayList<Usuario> lista = new ArrayList<Usuario>();
        try {
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("call sp_ListarUsuarios()");
            ResultSet resultado = procedimiento.executeQuery();
            while (resultado.next()) {                
                lista.add(new Usuario( resultado.getInt("codigoUsuario"),
                                    resultado.getString("nombreUsuario"),
                                    resultado.getString("apellidoUsuario"),
                                    resultado.getString("usuarioLogin"),
                                    resultado.getString("contrasena")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaUsuarios = FXCollections.observableArrayList(lista);
    }
    
    public void nuevo() {
        switch (tipoDeOperacion) {
            case NINGUNO:
                limpiarControles();
                activarControles();
                btnNuevo.setText("Guardar");
                imgNuevo.setImage(new Image("/org/sergiotepaz/image/guardar.png"));
                tipoDeOperacion = operaciones.GUARDAR;
                break;

            case GUARDAR:
                validar();
                break;
        }
    }
    
    public void validar() {
        Usuario usuario = new Usuario();
        int x  = 0;
        boolean bandera = false;
        usuario.setUsuarioLogin(txtUsuario.getText());
        
        while (x < getUsuario().size()) {            
            String user = getUsuario().get(x).getUsuarioLogin();
            if (user.equals(usuario.getUsuarioLogin())) {
                JOptionPane.showMessageDialog(null, "Usuario ya existente");
                x = getUsuario().size();
                bandera = true;
            }
            x++;
        }
        if (bandera == false) {
            if ((txtNombreUsuario.getText().length() == 0) || (txtApellidoUsuario.getText().length() == 0) || (txtUsuario.getText().length() == 0) || (txtPassword.getText().length() == 0)) {
                    JOptionPane.showMessageDialog(null, "Por favor llene todas las casillas");
                } else {
                    guardar();
                    JOptionPane.showMessageDialog(null, "Usuario agregado correctamente");
                    limpiarControles();
                    desactivarControles();
                    btnNuevo.setText("Nuevo");
                    imgNuevo.setImage(new Image("/org/sergiotepaz/image/agregar.png"));
                    tipoDeOperacion = operaciones.NINGUNO;
                    ventanaLogin();
                }
        }
    }
    
    public void guardar() {
        Usuario registro = new Usuario();
        registro.setNombreUsuario(txtNombreUsuario.getText());
        registro.setApellidoUsuario(txtApellidoUsuario.getText());
        registro.setUsuarioLogin(txtUsuario.getText());
        registro.setContrasena(txtPassword.getText());
        try {
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_AgregarUsuario(?,?,?,?)}");
            procedimiento.setString(1, registro.getNombreUsuario());
            procedimiento.setString(2, registro.getApellidoUsuario());
            procedimiento.setString(3, registro.getUsuarioLogin());
            procedimiento.setString(4, registro.getContrasena());
            procedimiento.execute();
            String contra = txtPassword.getText();
            String encript = DigestUtils.md5Hex(contra);
            System.out.println(encript);
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
                imgNuevo.setImage(new Image("/org/sergiotepaz/image/agregar.png"));
                tipoDeOperacion = operaciones.NINGUNO;
        }
    }
    
    public void desactivarControles() {
        txtCodigoUsuario.setEditable(false);
        txtNombreUsuario.setEditable(false);
        txtApellidoUsuario.setEditable(false);
        txtUsuario.setEditable(false);
        txtPassword.setEditable(false);
    }
    
    public void activarControles() {
        txtCodigoUsuario.setEditable(false);
        txtNombreUsuario.setEditable(true);
        txtApellidoUsuario.setEditable(true);
        txtUsuario.setEditable(true);
        txtPassword.setEditable(true);
    }
    
    public void limpiarControles() {
        txtCodigoUsuario.clear();
        txtNombreUsuario.clear();
        txtApellidoUsuario.clear();
        txtUsuario.clear();
        txtPassword.clear();
    }

    public Principal getEscenarioPrincipal() {
        return escenarioPrincipal;
    }

    public void setEscenarioPrincipal(Principal escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    }
    
    public void ventanaLogin() {
        escenarioPrincipal.ventanaLogin();
    }
    
}
