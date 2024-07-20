/*
    Programador: Sergio Eduardo Tepaz Vela
    Carné: 2022164
    IN5AV
    Fecha de creación: 21-03-2023
    Fecha de modificación: 21-03-2023
                           22-03-2023
*/
package org.sergiotepaz.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Principal extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("VistaCalculadora.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
