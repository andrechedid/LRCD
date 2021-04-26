/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lrcdepartment;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author PC-A
 */
public class LRCDepartment extends Application{

    public static Stage window;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        window=stage;
        Parent root=FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
        Scene scene=new Scene(root);
        window.getIcons().add(new Image("icons\\LRC logo.jpg"));
        window.setResizable(false);
        window.setScene(scene);
        window.setTitle("LRCLoginPage");
        window.show();
    }
    
}
