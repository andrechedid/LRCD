/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lrcdepartment;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
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
import static lrcdepartment.IConditionController.condNU;
import static lrcdepartment.LRCDepartment.window;
import static lrcdepartment.LoginPageController.DID;
import static lrcdepartment.LoginPageController.access;

/**
 * FXML Controller class
 *
 * @author PC-A
 */
public class CategoryConditionController implements Initializable {

    @FXML
    private Label catName;
    @FXML
    private Label catInfo;
    @FXML
    private Label conditionNU;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        catName.setText(condition);
        conditionNU.setText(condNU);
        createConnection();
    }    

    @FXML
    private void back(ActionEvent event) {
            try {
                Parent root=FXMLLoader.load(getClass().getResource("ICondition.fxml"));
                Scene scene=new Scene(root);
                window.setTitle(condition + " Condition");
                window.setScene(scene);
            } catch (IOException ex) {
                Logger.getLogger(CHomePageController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    public void createConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/dbproject","root","root");
            PreparedStatement pstmt=con.prepareStatement("SELECT * FROM EQUIPMENT WHERE EQUCATEGORYID=? AND ECONDITION=? AND eCDiD=?");
            pstmt.setString(1,condition);
            pstmt.setString(2, condNU);
            pstmt.setString(3, DID);
            ResultSet rs=pstmt.executeQuery();
            String equipments="";
            while(rs.next()){
                String EID=rs.getString(1);
                equipments+="EquipmentID: " + EID + "\n"; 
            }
            catInfo.setText(equipments);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CHomePageController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CHomePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
