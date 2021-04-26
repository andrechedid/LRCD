/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lrcdepartment;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import static lrcdepartment.CInventoryController.quaChange;
import static lrcdepartment.LRCDepartment.window;
import static lrcdepartment.LoginPageController.DID;

/**
 * FXML Controller class
 *
 * @author PC-A
 */
public class ChiefMissionController implements Initializable {

    @FXML
    private TextField vOne;
    @FXML
    private TextField pID;
    @FXML
    private TextField pName;
    @FXML
    private TextField pAge;
    @FXML
    private TextField pGender;
    @FXML
    private TextField pCase;
    @FXML
    private TextField pCondition;
    @FXML
    private TextField pSupport;
    @FXML
    private TextField patientLocation;
    @FXML
    private TextField aID;
    @FXML
    private TextArea description;
    @FXML
    private MenuButton categories;
    @FXML
    private MenuItem cOne;
    @FXML
    private MenuItem cTwo;
    @FXML
    private MenuItem cThree;
    @FXML
    private MenuItem cFour;
    @FXML
    private MenuItem cFive;
    @FXML
    private MenuItem cSix;
    @FXML
    private MenuItem cSeven;
    @FXML
    private TextField equipments;
    @FXML
    private TextArea medicalHistory;
    
    private String equID;
    
    private String date;
    
    private String time;
    
    private String[] buttonNames=new String[7];
    private int[] quantities=new int[7];
    @FXML
    private TextArea catAvailable;
    private String toUse;
    @FXML
    private TextArea available;
    @FXML
    private TextField missionID;
    @FXML
    private TextField mType;
    @FXML
    private TextField mDest;
    @FXML
    private TextArea ambulances;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initButtons();
        checkV();
        checkA();
    }    

    @FXML
    private void back(ActionEvent event) {
        try {
                Parent root=FXMLLoader.load(getClass().getResource("CHomePage.fxml"));
                Scene scene=new Scene(root);
                window.setTitle("LRC ChiefHomePage");
                window.setScene(scene);
        } catch (IOException ex) {
                Logger.getLogger(CHomePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void modifyData(ActionEvent event) {
            equID=equipments.getText();
            Scanner scan=new Scanner(equID);
            scan.useDelimiter(",");
            while(scan.hasNext()){
                String IDuse=scan.next();
        try {
            int i=0;
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/dbproject","root","root");
            PreparedStatement pstmt=con.prepareStatement("SELECT * FROM EQUIPMENT WHERE EQUIPMENTID=? AND EQUCATEGORYID=? AND eCDiD=?");
            pstmt.setString(1, IDuse);
            pstmt.setString(2, categories.getText());
            pstmt.setString(3, DID);
            ResultSet rs=pstmt.executeQuery();
            if(rs.next()){
                quaChange-=1;
                pstmt=con.prepareStatement("DELETE FROM EQUIPMENT WHERE EQUIPMENTID=? AND EQUCATEGORYID=? AND eCDiD=? LIMIT 1");
                pstmt.setString(1, IDuse);
                pstmt.setString(2, categories.getText());
                pstmt.setString(3, DID);
                pstmt.executeUpdate();
                pstmt=con.prepareStatement("UPDATE EQUIPMENTCATEGORY SET QUANTITY=? WHERE EQUCATEGORYID=? AND ECDEPARTMENTID=?");        
                pstmt.setInt(1, quaChange);
                pstmt.setString(2,categories.getText());
                pstmt.setString(3, DID);
                pstmt.executeUpdate();
                pstmt=con.prepareStatement("SELECT * FROM EQUIPMENTCATEGORY WHERE ECDEPARTMENTID=?");
                pstmt.setString(1, DID);
                rs=pstmt.executeQuery();
                while(rs.next()){
                    buttonNames[i]=rs.getString(1);
                    quantities[i]=rs.getInt(3);
                    i++;
                }
                createConnection();
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CInventoryController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CInventoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    }

    @FXML
    private void updCOne(ActionEvent event) {
        categories.setText(buttonNames[0]);
        createConnection();
    }

    @FXML
    private void updCTwo(ActionEvent event) {
        categories.setText(buttonNames[1]);
        createConnection();
    }

    @FXML
    private void updCThree(ActionEvent event) {
        categories.setText(buttonNames[2]);
        createConnection();
    }

    @FXML
    private void updCFour(ActionEvent event) {
        categories.setText(buttonNames[3]);
        createConnection();
    }

    @FXML
    private void updCFive(ActionEvent event) {
        categories.setText(buttonNames[4]);
        createConnection();
    }

    @FXML
    private void updCSix(ActionEvent event) {
        categories.setText(buttonNames[5]);
        createConnection();
    }

    @FXML
    private void updCSeven(ActionEvent event) {
        categories.setText(buttonNames[6]);
        createConnection();
    }
    
    private void initButtons(){
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
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CInventoryController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CInventoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
        cOne.setText(buttonNames[0]);
        cTwo.setText(buttonNames[1]);
        cThree.setText(buttonNames[2]);
        cFour.setText(buttonNames[3]);
        cFive.setText(buttonNames[4]);
        cSix.setText(buttonNames[5]);
        cSeven.setText(buttonNames[6]);
    }
    
    public void createConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/dbproject","root","root");
            PreparedStatement pstmt=con.prepareStatement("SELECT * FROM EQUIPMENT WHERE EQUCATEGORYID=? AND eCDiD=?");
            pstmt.setString(1,categories.getText());
            pstmt.setString(2, DID);
            ResultSet rs=pstmt.executeQuery();
            String equipments="";
            while(rs.next()){
                String EID=rs.getString(1);
                equipments+="" + EID + "\n"; 
            }
            catAvailable.setText(equipments);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CHomePageController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CHomePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void checkV(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/dbproject","root","root");
            PreparedStatement pstmt=con.prepareStatement("SELECT * FROM VOLUNTEER WHERE DEPARTMENTID=? AND STATUS='A'");
            pstmt.setString(1,DID);
            ResultSet rs=pstmt.executeQuery();
            String equipments="";
            while(rs.next()){
                String VID=rs.getString(1);
                equipments+="" + VID + "\n"; 
            }
            available.setText(equipments);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CHomePageController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CHomePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void checkA(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/dbproject","root","root");
            PreparedStatement pstmt=con.prepareStatement("SELECT * FROM AMBULANCE WHERE ASTATUS='A'");
            ResultSet rs=pstmt.executeQuery();
            String ambs="";
            while(rs.next()){
                String AID=rs.getString(1);
                ambs+="" + AID + "\n"; 
            }
            ambulances.setText(ambs);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CHomePageController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CHomePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void start(ActionEvent event) {
        try {
            calcDate();
            calcTime();
            String volunteers=vOne.getText();
            String patientID=pID.getText();
            String mID=missionID.getText();
            String patientName=pName.getText();
            String patientAge=pAge.getText();
            String patientGender=pGender.getText();
            String patientCase=pCase.getText();
            String patientCondition=pCondition.getText();
            String sUsed=pSupport.getText();
            String location=patientLocation.getText();
            String medH=medicalHistory.getText();
            String ambulanceID=aID.getText();
            String des=description.getText();
            String missionType=mType.getText();
            String missionD=mDest.getText();
            
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/dbproject","root","root");
                PreparedStatement pstmt=con.prepareStatement("INSERT INTO MISSION VALUES(?,?,?,?,?,?,?)");
                pstmt.setString(1, mID);
                pstmt.setString(2,date);
                pstmt.setString(3, patientID);
                pstmt.setString(4,time);
                pstmt.setString(5, missionType);
                pstmt.setString(6, missionD);
                pstmt.setString(7, sUsed);
                pstmt.executeUpdate();
                pstmt=con.prepareStatement("INSERT INTO PATIENT VALUES(?,?,?,?,?,?,?)");
                pstmt.setString(1, patientID);
                pstmt.setString(2, patientName);
                pstmt.setInt(3, Integer.parseInt(patientAge));
                pstmt.setString(4, patientGender);
                pstmt.setString(5, patientCase);
                pstmt.setString(6, patientCondition);
                pstmt.setString(7, medH);
                pstmt.executeUpdate();
                pstmt=con.prepareStatement("UPDATE AMBULANCE SET ASTATUS='N' WHERE AMBULANCEID=?");
                pstmt.setString(1, ambulanceID);
                pstmt.executeUpdate();
                pstmt=con.prepareStatement("INSERT INTO TRANSPORTS VALUES(?,?,?)");
                pstmt.setString(1, patientID);
                pstmt.setString(2, ambulanceID);
                pstmt.setString(3, location);
                pstmt.executeUpdate();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(CHomePageController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(CHomePageController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Scanner scan=new Scanner(volunteers);
            scan.useDelimiter(",");
            while(scan.hasNext()){
                String vID=scan.next();
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/dbproject","root","root");
                    PreparedStatement pstmt=con.prepareStatement("UPDATE VOLUNTEER SET STATUS='N' WHERE VOLUNTEERID=?");
                    pstmt.setString(1, vID);
                    pstmt.executeUpdate();
                    pstmt=con.prepareStatement("INSERT INTO DISPATCHES VALUES(?,?,?,?,?,?)");
                    pstmt.setString(1,mID);
                    pstmt.setString(2,date);
                    pstmt.setString(3, vID);
                    pstmt.setString(4, DID);
                    pstmt.setString(5,time);
                    pstmt.setString(6, "Ongoing");
                    pstmt.executeUpdate();
                    
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(CHomePageController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(CHomePageController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            Parent root=FXMLLoader.load(getClass().getResource("CHomePage.fxml"));
            Scene scene=new Scene(root);
            window.setTitle("LRC ChiefHomePage");
            window.setScene(scene);
        } catch (IOException ex) {
            Logger.getLogger(ChiefMissionController.class.getName()).log(Level.SEVERE, null, ex);
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
    
}
