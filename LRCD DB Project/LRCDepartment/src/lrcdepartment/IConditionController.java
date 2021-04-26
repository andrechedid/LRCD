/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lrcdepartment;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import static lrcdepartment.CInventoryController.condition;
import static lrcdepartment.LRCDepartment.window;
import static lrcdepartment.LoginPageController.access;

/**
 * FXML Controller class
 *
 * @author PC-A
 */
public class IConditionController implements Initializable {

    @FXML
    private Label catName;
    public static String condNU;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        catName.setText(condition);
    }    

    @FXML
    private void nItems(ActionEvent event) {
        condNU="N";
        try {
                Parent root=FXMLLoader.load(getClass().getResource("CategoryCondition.fxml"));
                Scene scene=new Scene(root);
                window.setTitle(condition + " Condition");
                window.setScene(scene);
            } catch (IOException ex) {
                Logger.getLogger(CHomePageController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }


    @FXML
    private void uItems(ActionEvent event) {
        condNU="U";
        try {
                Parent root=FXMLLoader.load(getClass().getResource("CategoryCondition.fxml"));
                Scene scene=new Scene(root);
                window.setTitle(condition + " Condition");
                window.setScene(scene);
            } catch (IOException ex) {
                Logger.getLogger(CHomePageController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }


    @FXML
    private void back(ActionEvent event) {
            try {
                Parent root=FXMLLoader.load(getClass().getResource("CInventory.fxml"));
                Scene scene=new Scene(root);
                window.setTitle("C Inventory");
                window.setScene(scene);
            } catch (IOException ex) {
                Logger.getLogger(CHomePageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }

    
}
