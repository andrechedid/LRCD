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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import static lrcdepartment.LRCDepartment.window;
import static lrcdepartment.LoginPageController.DID;
import static lrcdepartment.LoginPageController.access;

/**
 * FXML Controller class
 *
 * @author PC-A
 */
public class VInventoryController implements Initializable {

    
    private String[] buttonNames=new String[7];
    private int[] quantities=new int[7];
    @FXML
    private Button C1;
    @FXML
    private Button C5;
    @FXML
    private Button C4;
    @FXML
    private Button C6;
    @FXML
    private Button C7;
    @FXML
    private Button C2;
    @FXML
    private Button C3;
    @FXML
    private Label q1;
    @FXML
    private Label q2;
    @FXML
    private Label q3;
    @FXML
    private Label q4;
    @FXML
    private Label q5;
    @FXML
    private Label q6;
    @FXML
    private Label q7;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            int i=0;
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/dbproject","root","root");
            PreparedStatement pstmt=con.prepareStatement("SELECT * FROM EQUIPMENTCATEGORY WHERE ECDEPARTMENTID=?");
            pstmt.setString(1, DID);
            ResultSet rs=pstmt.executeQuery();
            while(rs.next()){
                buttonNames[i]=rs.getString(1);
                quantities[i]=rs.getInt(3);
                i++;
            }
            initializeButtons();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CInventoryController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CInventoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }     

    @FXML
    private void back(ActionEvent event) {
        if(access.equalsIgnoreCase("C")){
            try {
                Parent root=FXMLLoader.load(getClass().getResource("CHomePage.fxml"));
                Scene scene=new Scene(root);
                window.setTitle("LRC ChiefHomePage");
                window.setScene(scene);
            } catch (IOException ex) {
                Logger.getLogger(CHomePageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            try {
                Parent root=FXMLLoader.load(getClass().getResource("VHomePage.fxml"));
                Scene scene=new Scene(root);
                window.setTitle("LRC VolunteerHomePage");
                window.setScene(scene);
            } catch (IOException ex) {
                Logger.getLogger(CHomePageController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    private void initializeButtons(){
        C1.setText(buttonNames[0]);
        q1.setText("" + quantities[0]);
        C2.setText(buttonNames[1]);
        q2.setText("" + quantities[1]);
        C3.setText(buttonNames[2]);
        q3.setText("" + quantities[2]);
        C4.setText(buttonNames[3]);
        q4.setText("" + quantities[3]);
        C5.setText(buttonNames[4]);
        q5.setText("" + quantities[4]);
        C6.setText(buttonNames[5]);
        q6.setText("" + quantities[5]);
        C7.setText(buttonNames[6]);
        q7.setText("" + quantities[6]);
    }
    
}
