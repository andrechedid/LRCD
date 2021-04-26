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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import static lrcdepartment.LRCDepartment.window;
import static lrcdepartment.LoginPageController.DID;
import static lrcdepartment.LoginPageController.VCID;
import static lrcdepartment.LoginPageController.access;

/**
 * FXML Controller class
 *
 * @author PC-A
 */
public class HistoryController implements Initializable {

    @FXML
    private TextArea dmInfo;
    @FXML
    private MenuButton searchCondition;
    
    private String date;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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


    @FXML
    private void updSCM(ActionEvent event) {
        searchCondition.setText("Missions");
    }

    @FXML
    private void uSCD(ActionEvent event) {
        searchCondition.setText("Donations");
    }

    @FXML
    private void search(MouseEvent event) {
        String cond=searchCondition.getText();
        if(cond.equalsIgnoreCase("missions")){
        try {
            String toPrint="";
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/dbproject","root","root");
            PreparedStatement pstmt=con.prepareStatement("SELECT * FROM MISSION WHERE MISSIONDATE=?");
            pstmt.setString(1, date);
            ResultSet rs=pstmt.executeQuery();
            while(rs.next()){
                toPrint+= "MissionID: " + rs.getString(1) + " PatientID: " + rs.getString(3) + " ReceivedTime: " + rs.getTime(4) + " MissionType: " + rs.getString(5) + 
                        " Destination: " + rs.getString(6) + " Support: " + rs.getString(7) + "\n";
            }
            dmInfo.setText(toPrint);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CHomePageController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CHomePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }else if(cond.equalsIgnoreCase("Donations")){
            try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/dbproject","root","root");
            String toPrint="";
            PreparedStatement pstmt=con.prepareStatement("SELECT * FROM DONATION WHERE DEPARTMENTID=? AND DONATIONDATE=?");
            pstmt.setString(1, DID);
            pstmt.setString(2, date);
            ResultSet rs=pstmt.executeQuery();
            while(rs.next()){
                toPrint+= "DonationID: " + rs.getString(1) + " DonorName: " + rs.getString(3) + " Donation Type " + rs.getString(0) + " DonationAmount: " + rs.getString(6);
            }
            dmInfo.setText(toPrint);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CHomePageController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CHomePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }else{
            dmInfo.setText("Choose a condition and search to display");
    }
}
    public void calcDate(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("YYYY-MM-dd");
        LocalDateTime now=LocalDateTime.now();
        date=dtf.format(now);
    }
}