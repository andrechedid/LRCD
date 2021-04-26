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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import static lrcdepartment.CInventoryController.condition;
import static lrcdepartment.IConditionController.condNU;
import static lrcdepartment.LRCDepartment.window;
import static lrcdepartment.LoginPageController.DID;
import static lrcdepartment.LoginPageController.VCID;

/**
 * FXML Controller class
 *
 * @author PC-A
 */
public class VHomePageController implements Initializable {

    private String amb;
    
    @FXML
    private TextArea VolunteerInfo;
    @FXML
    private Label VolunteerID;
    @FXML
    private Text endMissionText;
    @FXML
    private Button endMissionBut;
    @FXML
    private Label tDate;

    private String date;
    private String time;
    @FXML
    private Text dID;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        VolunteerID.setText("VolunteerID:" + VCID);
        calcDate();
        dID.setText(DID);
        tDate.setText(date);
        createConnection();
    }    

    @FXML
    private void endMission(ActionEvent event) {
        VolunteerInfo.setText("No missions for now!");
        calcTime();
         try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/dbproject","root","root");
            PreparedStatement pstmt=con.prepareStatement("UPDATE DISPATCHES SET ENDTIME=? WHERE VOLUNTEERID=? AND DEPARTMENTID=?");
            pstmt.setString(1, time);
            pstmt.setString(2, VCID);
            pstmt.setString(3, DID);
            pstmt.executeUpdate();
            pstmt=con.prepareStatement("UPDATE VOLUNTEER SET STATUS=? WHERE VOLUNTEERID=? AND DEPARTMENTID=?");
            pstmt.setString(1, "A");
            pstmt.setString(2, VCID);
            pstmt.setString(3, DID);
            pstmt.executeUpdate();
            System.out.println(amb);
            pstmt=con.prepareStatement("UPDATE AMBULANCE SET ASTATUS='A' WHERE AMBULANCEID=?");
            pstmt.setString(1, amb);
            pstmt.executeUpdate();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CHomePageController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CHomePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void logout(ActionEvent event) {
        try {
            Parent root=FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
            Scene scene=new Scene(root);
            window.setTitle("LRC LoginPage");
            window.setScene(scene);
        } catch (IOException ex) {
            Logger.getLogger(CHomePageController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    @FXML
    private void inventory(MouseEvent event) {
        try {
            Parent root=FXMLLoader.load(getClass().getResource("VInventory.fxml"));
            Scene scene=new Scene(root);
            window.setTitle("VInventory");
            window.setScene(scene);
        } catch (IOException ex) {
            Logger.getLogger(CHomePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void history(MouseEvent event) {
        try {
            Parent root=FXMLLoader.load(getClass().getResource("History.fxml"));
            Scene scene=new Scene(root);
            window.setTitle("History");
            window.setScene(scene);
        } catch (IOException ex) {
            Logger.getLogger(CHomePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void calcDate(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("YYYY-MM-dd");
        LocalDateTime now=LocalDateTime.now();
        date=dtf.format(now);
    }
    
    public void calcTime(){
        DateTimeFormatter dtf=DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime now=LocalDateTime.now();
        time=dtf.format(now);
    }
    
    public void createConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/dbproject","root","root");
            PreparedStatement pstmt=con.prepareStatement("SELECT * FROM MISSION NATURAL JOIN PATIENT NATURAL JOIN TRANSPORTS NATURAL JOIN DISPATCHES WHERE VOLUNTEERID=? AND DEPARTMENTID=? AND ENDTIME='Ongoing'");
            pstmt.setString(1, VCID);
            pstmt.setString(2, DID);
            ResultSet rs=pstmt.executeQuery();
            if(rs.next()){
                endMissionBut.setVisible(true);
                endMissionText.setText("End Mission!");
                amb=rs.getString(14);
                VolunteerInfo.setText("MissionID: " + rs.getString(1) + " MissionType: " + rs.getString(5) + "\n" + "MissionDestination: " 
                        + rs.getString(6) + "Ambulance: " + rs.getString(14) + "\n" + "PatientID: " + rs.getString(3) + " PatientName: " + rs.getString(8) 
                        + " PatientAge: " + rs.getString(9) + " PatientLocation: " + rs.getString(15) + "\n" +  
                        "PatientCase: " + rs.getString(11) + " PatientCondition: " + rs.getString(12) + " PatientMedicalHistory: " + rs.getString(13));
            }else{
                endMissionText.setText("No Missions");
            }
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CHomePageController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CHomePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
