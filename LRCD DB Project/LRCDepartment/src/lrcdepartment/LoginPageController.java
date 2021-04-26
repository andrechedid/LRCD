/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lrcdepartment;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import static lrcdepartment.LRCDepartment.window;

/**
 * FXML Controller class
 *
 * @author PC-A
 */
public class LoginPageController implements Initializable {

    @FXML
    private PasswordField Password;
    @FXML
    private TextField DepartmentID;
    @FXML
    private TextField VolunteerChiefID;
    
    public static String VCID,DID;
    
    private static String PASS;
    @FXML
    private Button LButton;
    
    public static String access;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    @FXML
    private void login(ActionEvent event) throws IOException {
        VCID=VolunteerChiefID.getText();
        DID=DepartmentID.getText();
        PASS=Password.getText();
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/dbproject","root","root");
                PreparedStatement pstmt=con.prepareStatement("SELECT * FROM LOGIN WHERE volunteerID=? AND departmentID=? AND password=?");
                pstmt.setString(1, VCID);
                pstmt.setString(2, DID);
                pstmt.setString(3, PASS);
                
                ResultSet rs=pstmt.executeQuery();
                //Login Successful move to new page
                if(rs.next()){
                    access=VCID.substring(0,1);
                    if(access.equalsIgnoreCase("C")){
                        Parent root=FXMLLoader.load(getClass().getResource("CHomePage.fxml"));
                        Scene scene=new Scene(root);
                        window.setTitle("LRC ChiefHomePage");
                        window.setScene(scene);
                    }else{
                        Parent root=FXMLLoader.load(getClass().getResource("VHomePage.fxml"));
                        Scene scene=new Scene(root);
                        window.setTitle("LRC VolunteerHomePage");
                        window.setScene(scene);
                    }
                }else{
                    LButton.setStyle("-fx-background-color: RED;");
                }
            } catch (SQLException ex) {
                Logger.getLogger(LoginPageController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(LoginPageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
