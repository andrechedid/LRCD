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
import javafx.scene.control.TextField;
import static lrcdepartment.CInventoryController.condition;
import static lrcdepartment.IConditionController.condNU;
import static lrcdepartment.LRCDepartment.window;
import static lrcdepartment.LoginPageController.DID;

/**
 * FXML Controller class
 *
 * @author PC-A
 */
public class RegisterVController implements Initializable {

    @FXML
    private TextField volID;
    @FXML
    private TextField passID;
    @FXML
    private TextField depID;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void add(ActionEvent event) {
        String volunteerID=volID.getText();
        String password=passID.getText();
        String department=depID.getText();
        String charV=volunteerID.charAt(0) + "";
        int numV=Integer.parseInt(volunteerID.substring(1));
        String charD=department.charAt(0) + "";
        int numD=Integer.parseInt(department.substring(1));
        if(charV.equalsIgnoreCase("V") && numV>0 && numV<1000 && charD.equalsIgnoreCase("D") && numD>0 && numD<1000){
            try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/dbproject","root","root");
            PreparedStatement pstmt=con.prepareStatement("INSERT INTO LOGIN VALUES(?,?,?)");
            pstmt.setString(1, volunteerID);
            pstmt.setString(2, department);
            pstmt.setString(3, password);
            pstmt.executeUpdate();
            pstmt=con.prepareStatement("INSERT INTO VOLUNTEER VALUES(?,?,'A')");
            pstmt.setString(1, volunteerID);
            pstmt.setString(2, department);
            pstmt.executeUpdate();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CHomePageController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CHomePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }

    @FXML
    private void remove(ActionEvent event) {
        String volunteerID=volID.getText();
        String password=passID.getText();
        String department=depID.getText();
        String charV=volunteerID.charAt(0) + "";
        int numV=Integer.parseInt(volunteerID.substring(1));
        String charD=department.charAt(0) + "";
        int numD=Integer.parseInt(department.substring(1));
        if(charV.equalsIgnoreCase("V") && numV>0 && numV<1000 && charD.equalsIgnoreCase("D") && numD>0 && numD<1000){
            try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/dbproject","root","root");
            PreparedStatement pstmt=con.prepareStatement("SELECT * FROM LOGIN WHERE VOLUNTEERID=? AND DEPARTMENTID=? AND PASSWORD=?");
            pstmt.setString(1, volunteerID);
            pstmt.setString(2, department);
            pstmt.setString(3, password);
            ResultSet rs=pstmt.executeQuery();
            if(rs.next()){
                pstmt=con.prepareStatement("DELETE FROM LOGIN WHERE VOLUNTEERID=? AND DEPARTMENTID=? AND PASSWORD=?");
                pstmt.setString(1, volunteerID);
                pstmt.setString(2, department);
                pstmt.setString(3, password);
                pstmt.executeUpdate();
                pstmt=con.prepareStatement("DELETE FROM VOLUNTEER WHERE VOLUNTEERID=? AND DEPARTMENTID=?");
                pstmt.setString(1, volunteerID);
                pstmt.setString(2, department);
                pstmt.executeUpdate();
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CHomePageController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CHomePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }

    @FXML
    private void back(ActionEvent event) {
        try {
            Parent root=FXMLLoader.load(getClass().getResource("CHomePage.fxml"));
            Scene scene=new Scene(root);
            window.setTitle("C HomePage");
            window.setScene(scene);
        } catch (IOException ex) {
            Logger.getLogger(CHomePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
