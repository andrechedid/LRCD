/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lrcdepartment;

import java.io.IOException;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import static lrcdepartment.LRCDepartment.window;
import static lrcdepartment.LoginPageController.DID;
import static lrcdepartment.LoginPageController.VCID;

/**
 * FXML Controller class
 *
 * @author PC-A
 */
public class CHomePageController implements Initializable {

    @FXML
    private Label ChiefInfo;
    @FXML
    private Label ChiefID;
    
    private String date;
    private String dateToPrint;
    @FXML
    private Label tDate;
    @FXML
    private Text dID;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ChiefID.setText("ChiefID: " + VCID);
        dID.setText("DepID: " + DID);
        calcDate();
        tDate.setText(date);
        createConnection();
    }
    
    public void createConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/dbproject","root","root");
            PreparedStatement pstmt=con.prepareStatement("SELECT DISTINCT MISSIONID,STARTTIME,ENDTIME FROM DISPATCHES WHERE MISSIONDATE=? AND DEPARTMENTID=? AND ENDTIME='Ongoing'");
            pstmt.setString(1, date);
            pstmt.setString(2, DID);
            ResultSet rs=pstmt.executeQuery();
            String missions="";
            while(rs.next()){
                String MID=rs.getString(1);
                Time sTime=rs.getTime(2);
                String eTime=rs.getString(3);
                missions+="MissionID: " +  MID + "\t" + "\t" + sTime +"-->" + eTime + "\n"; 
            }
            if(missions.equalsIgnoreCase("")){
                ChiefInfo.setText("No Ongoing Missions");
            }else{
                ChiefInfo.setText(missions);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CHomePageController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CHomePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void newMission(ActionEvent event) {
        try {
            Parent root=FXMLLoader.load(getClass().getResource("ChiefMission.fxml"));
            Scene scene=new Scene(root);
            window.setTitle("New Mission");
            window.setScene(scene);
        } catch (IOException ex) {
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

    @FXML
    private void inventory(MouseEvent event) {
        try {
            Parent root=FXMLLoader.load(getClass().getResource("CInventory.fxml"));
            Scene scene=new Scene(root);
            window.setTitle("CInventory");
            window.setScene(scene);
        } catch (IOException ex) {
            Logger.getLogger(CHomePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void calcDate(){
        DateTimeFormatter dtf=DateTimeFormatter.ofPattern("YYYY-MM-dd");
        LocalDateTime now=LocalDateTime.now();
        date=dtf.format(now);
        
    }

    @FXML
    private void register(ActionEvent event) {
        try {
            Parent root=FXMLLoader.load(getClass().getResource("RegisterV.fxml"));
            Scene scene=new Scene(root);
            window.setTitle("Register/Remove V");
            window.setScene(scene);
        } catch (IOException ex) {
            Logger.getLogger(CHomePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
