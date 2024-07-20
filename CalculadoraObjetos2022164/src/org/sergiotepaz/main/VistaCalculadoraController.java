/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sergiotepaz.main;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 *
 * @author Eduardo
 */
public class VistaCalculadoraController implements Initializable {
    
    String pantalla;
    double num;
    double num1;
    double num2;
    double decimal;
    double resultado;
    int op;
    
    @FXML private TextField txtPantalla;
    @FXML private Button btnCero;
    @FXML private Button btnUno;
    @FXML private Button btnDos;
    @FXML private Button btnTres;
    @FXML private Button btnCuatro;
    @FXML private Button btnCinco;
    @FXML private Button btnSeis;
    @FXML private Button btnSiete;
    @FXML private Button btnOcho;
    @FXML private Button btnNueve;
    @FXML private Button btnMasMenos;
    @FXML private Button btnPunto;
    @FXML private Button btnIgual;
    @FXML private Button btnSuma;
    @FXML private Button btnResta;
    @FXML private Button btnMulti;
    @FXML private Button btnDivi;
    @FXML private Button btnRaiz;
    @FXML private Button btnElevar;
    @FXML private Button btnC;
    @FXML private Button btnCE;
    @FXML private Button btnPorcentaje;
    @FXML private Button btnFraccion;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        if (event.getSource() == btnUno) 
            txtPantalla.setText(txtPantalla.getText() + "1");
        else if (event.getSource() == btnDos) 
            txtPantalla.setText(txtPantalla.getText() + "2");
        else if (event.getSource() == btnTres) 
            txtPantalla.setText(txtPantalla.getText() + "3");
        else if (event.getSource() == btnCuatro) 
            txtPantalla.setText(txtPantalla.getText() + "4");
        else if (event.getSource() == btnCinco) 
            txtPantalla.setText(txtPantalla.getText() + "5");
        else if (event.getSource() == btnSeis) 
            txtPantalla.setText(txtPantalla.getText() + "6");
        else if (event.getSource() == btnSiete) 
            txtPantalla.setText(txtPantalla.getText() + "7");
        else if (event.getSource() == btnOcho) 
            txtPantalla.setText(txtPantalla.getText() + "8");
        else if (event.getSource() == btnNueve) 
            txtPantalla.setText(txtPantalla.getText() + "9");
        else if (event.getSource() == btnCero) 
            txtPantalla.setText(txtPantalla.getText() + "0");
        else if (event.getSource() == btnMasMenos) {
            num = Double.parseDouble(txtPantalla.getText());
            txtPantalla.setText(String.valueOf(num * -1));
        } else if (event.getSource() == btnC) {
            txtPantalla.setText("");
            btnPunto.setDisable(false);
        } else if (event.getSource() == btnCE) {
            pantalla = txtPantalla.getText();
            pantalla = "";
            txtPantalla.setText(pantalla);
            btnPunto.setDisable(false);
        } 
        else if (event.getSource() == btnSuma) {
            num1 = Double.parseDouble(txtPantalla.getText());
            txtPantalla.setText("");
            op = 1;
            btnPunto.setDisable(false);
        } else if (event.getSource() == btnResta) {
            num1 = Double.parseDouble(txtPantalla.getText());
            txtPantalla.setText("");
            op = 2;
            btnPunto.setDisable(false);
        } else if (event.getSource() == btnMulti) {
            num1 = Double.parseDouble(txtPantalla.getText());
            txtPantalla.setText("");
            op = 3;
            btnPunto.setDisable(false);
        } else if (event.getSource() == btnDivi) {
            num1 = Double.parseDouble(txtPantalla.getText());
            txtPantalla.setText("");
            op = 4;
            btnPunto.setDisable(false);
        } else if (event.getSource() == btnFraccion) {
            num = Double.parseDouble(txtPantalla.getText());
            txtPantalla.setText(String.valueOf(1 / num));
        } else if (event.getSource() == btnElevar) {
            num = Double.parseDouble(txtPantalla.getText());
            txtPantalla.setText(String.valueOf(num * num));
        } else if (event.getSource() == btnRaiz) {
            num = Double.parseDouble(txtPantalla.getText());
            txtPantalla.setText(String.valueOf(Math.sqrt(num)));
        } else if (event.getSource() == btnPunto) {
            if (txtPantalla.getText().length() == 0) {
            txtPantalla.setText("0" + "." +txtPantalla.getText());
            btnPunto.setDisable(true);
            } else if (txtPantalla.getText().length() > 0) {
                txtPantalla.setText(txtPantalla.getText() + ".");
                btnPunto.setDisable(true);
            }
        } else if (event.getSource() == btnIgual) {
            num2 = Double.parseDouble(txtPantalla.getText());
            txtPantalla.setText("");
            btnPunto.setDisable(true);
            switch (op) {
                case 1:
                    resultado = num1 + num2;
                    txtPantalla.setText(String.valueOf(resultado));
                    break;
                case 2:
                     resultado = num1 - num2;
                     txtPantalla.setText(String.valueOf(resultado));
                     break;
                case 3:
                     resultado = num1 * num2;
                     txtPantalla.setText(String.valueOf(resultado));
                     break;
                case 4:
                    resultado = num1 / num2;
                    txtPantalla.setText(String.valueOf(resultado));
                    break;
            }
        } else if (event.getSource() == btnPorcentaje) {
            num = Double.parseDouble(txtPantalla.getText());
            txtPantalla.setText(String.valueOf(num / 100));
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
