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
import javafx.scene.control.Label;
import java.sql.*;
import java.util.Scanner;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import static lrcdepartment.LRCDepartment.window;
import static lrcdepartment.LoginPageController.DID;
import static lrcdepartment.LoginPageController.access;

/**
 * FXML Controller class
 *
 * @author PC-A
 */
public class CInventoryController implements Initializable {

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
    @FXML
    private Button C1;
    @FXML
    private Button C2;
    @FXML
    private Button C3;
    @FXML
    private Button C4;
    @FXML
    private Button C5;
    @FXML
    private Button C6;
    @FXML
    private Button C7;
    
    private String[] buttonNames=new String[7];
    private int[] quantities=new int[7];
    
    public static String condition,catToChange;
    public static int quaChange;
    public String equID;
    @FXML
    private MenuItem catO;
    @FXML
    private MenuItem catT;
    @FXML
    private MenuItem catTh;
    @FXML
    private MenuItem catF;
    @FXML
    private MenuItem catFi;
    @FXML
    private MenuItem catS;
    @FXML
    private MenuItem catSe;
    @FXML
    private MenuButton mainButton;
    @FXML
    private TextField equIDToR;
    
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

    @FXML
    private void add(ActionEvent event){
        equID=equIDToR.getText();
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
                pstmt.setString(2, mainButton.getText());
                pstmt.setString(3, DID);
                ResultSet rs=pstmt.executeQuery();
                if(rs.next()){
                }else{
                int num=Integer.parseInt(IDuse.substring(1));
                if(IDuse.length()==4 && IDuse.charAt(0)=='E' && num<1000 && num>0){
                quaChange+=1;
                pstmt=con.prepareStatement("INSERT INTO EQUIPMENT VALUES(?,?,?,'N')");
                pstmt.setString(1, IDuse);
                pstmt.setString(2, DID);
                pstmt.setString(3, mainButton.getText());
                pstmt.executeUpdate();
                pstmt=con.prepareStatement("UPDATE EQUIPMENTCATEGORY SET QUANTITY=? WHERE EQUCATEGORYID=? AND ECDEPARTMENTID=?");
                pstmt.setInt(1, quaChange);
                pstmt.setString(2,mainButton.getText());
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
            initializeButtons();    
            }
            }
            
    }   catch (SQLException ex) {
            Logger.getLogger(CInventoryController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CInventoryController.class.getName()).log(Level.SEVERE, null, ex);
        }catch (RuntimeException ex){
            
        }
        }
    }

    @FXML
    private void remove(ActionEvent event) {
        equID=equIDToR.getText();
        Scanner scan=new Scanner(equID);
        scan.useDelimiter(",");
        while(scan.hasNext()){
            String IDuse=scan.next();
        try {
            int i=0;
            equID=equIDToR.getText();
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/dbproject","root","root");
            PreparedStatement pstmt=con.prepareStatement("SELECT * FROM EQUIPMENT WHERE EQUIPMENTID=? AND EQUCATEGORYID=? AND eCDiD=?");
            pstmt.setString(1, IDuse);
            pstmt.setString(2, mainButton.getText());
            pstmt.setString(3, DID);
            ResultSet rs=pstmt.executeQuery();
            if(rs.next()){
                quaChange-=1;
                pstmt=con.prepareStatement("DELETE FROM EQUIPMENT WHERE EQUIPMENTID=? AND EQUCATEGORYID=? AND eCDiD=? LIMIT 1");
                pstmt.setString(1, IDuse);
                pstmt.setString(2, mainButton.getText());
                pstmt.setString(3, DID);
                pstmt.executeUpdate();
                pstmt=con.prepareStatement("UPDATE EQUIPMENTCATEGORY SET QUANTITY=? WHERE EQUCATEGORYID=? AND ECDEPARTMENTID=?");        
                pstmt.setInt(1, quaChange);
                pstmt.setString(2,catToChange);
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
                initializeButtons();
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CInventoryController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CInventoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    }

    @FXML
    private void qODetails(ActionEvent event) {
        condition=buttonNames[0];
        changeScene();
    }

    @FXML
    private void qFiDetails(ActionEvent event) {
        condition=buttonNames[4];
        changeScene();
    }

    @FXML
    private void qFDetails(ActionEvent event) {
        condition=buttonNames[3];
        changeScene();
    }

    @FXML
    private void qSDetails(ActionEvent event) {
        condition=buttonNames[5];
        changeScene();
    }

    @FXML
    private void qSeDetails(ActionEvent event) {
        condition=buttonNames[6];
        changeScene();
    }

    @FXML
    private void qTDetails(ActionEvent event) {
        condition=buttonNames[1];
        changeScene();
    }

    @FXML
    private void qThDetails(ActionEvent event) {
        condition=buttonNames[2];
        changeScene();
    }
    
    private void changeScene(){
        try {
            Parent root=FXMLLoader.load(getClass().getResource("ICondition.fxml"));
            Scene scene=new Scene(root);
            window.setTitle("InventoryDetails");
            window.setScene(scene);
        } catch (IOException ex) {
            Logger.getLogger(CHomePageController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    private void initializeButtons(){
        C1.setText(buttonNames[0]);
        catO.setText(buttonNames[0]);
        q1.setText("" + quantities[0]);
        C2.setText(buttonNames[1]);
        catT.setText(buttonNames[1]);
        q2.setText("" + quantities[1]);
        C3.setText(buttonNames[2]);
        catTh.setText(buttonNames[2]);
        q3.setText("" + quantities[2]);
        C4.setText(buttonNames[3]);
        catF.setText(buttonNames[3]);
        q4.setText("" + quantities[3]);
        C5.setText(buttonNames[4]);
        catFi.setText(buttonNames[4]);
        q5.setText("" + quantities[4]);
        C6.setText(buttonNames[5]);
        catS.setText(buttonNames[5]);
        q6.setText("" + quantities[5]);
        C7.setText(buttonNames[6]);
        catSe.setText(buttonNames[6]);
        q7.setText("" + quantities[6]);
    }

    @FXML
    private void catOC(ActionEvent event) {
        catToChange=buttonNames[0];
        quaChange=quantities[0];
        mainButton.setText(catToChange);
    }

    @FXML
    private void catTC(ActionEvent event) {
        catToChange=buttonNames[1];
        quaChange=quantities[1];
        mainButton.setText(catToChange);
    }

    @FXML
    private void catThC(ActionEvent event) {
        catToChange=buttonNames[2];
        quaChange=quantities[2];
        mainButton.setText(catToChange);
    }

    @FXML
    private void catFC(ActionEvent event) {
        catToChange=buttonNames[3];
        quaChange=quantities[3];
        mainButton.setText(catToChange);
    }

    @FXML
    private void catFiC(ActionEvent event) {
        catToChange=buttonNames[4];
        quaChange=quantities[4];
        mainButton.setText(catToChange);
    }

    @FXML
    private void catSC(ActionEvent event) {
        catToChange=buttonNames[5];
        quaChange=quantities[5];
        mainButton.setText(catToChange);
    }

    @FXML
    private void catSeC(ActionEvent event) {
        catToChange=buttonNames[6];
        quaChange=quantities[6];
        mainButton.setText(catToChange);
    }
}
