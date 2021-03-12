/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workmanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class CreateNewAccountController {

    @FXML
    private Button backToLogin;

    @FXML
    private TextField companyName;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private TextField fName;

    @FXML
    private TextField lName;

    @FXML
    private TextField email;

    @FXML
    private TextField phone;

    @FXML
    private Button continueTo;
    
       static Connection con;
    static Statement stmt;
    static ResultSet rs;
    
    
    public void back()
    {
        try
                        {
			FXMLLoader loader = new FXMLLoader(WorkManager.class.getResource("LoginScreen.fxml"));
			AnchorPane pane = loader.load();
			
			Scene scene = new Scene(pane);
			
			
			Stage primaryStage = new Stage();
			
			primaryStage.setScene(scene);
			primaryStage.setTitle("Login");
                        primaryStage.setResizable(false);
			primaryStage.show();
                        
                        Stage stage = (Stage) backToLogin.getScene().getWindow();
                            stage.close();
                       
			
			
			
			
                        }
                        catch(Exception e)
                        {
                            e.printStackTrace();
                        }
        
    }
    
    
    public void onContinue()
    {
       
        
        String sql = "INSERT INTO COMPANYDETAILS (COMPANYNAME, USERNAME, PASSWORD, FIRSTNAME, LASTNAME, EMAIL, PHONE) VALUES (?, ?, ?, ?, ?, ?, ?)";

                try {
                    
                     String host = "jdbc:derby://localhost:1527/WorkManager";
            String uName = "administration";
            String uPass = "admin";

            con = DriverManager.getConnection(host, uName, uPass);

            stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                    
                    PreparedStatement ps = con.prepareStatement(sql);
                    
               
                    
                    
                    
                    ps.setString(1, companyName.getText());
                    ps.setString(2, username.getText());
                    ps.setString(3, password.getText());
                    ps.setString(4, fName.getText());
                    ps.setString(5, lName.getText());
                    ps.setString(6, email.getText());
                    ps.setString(7, phone.getText());

                    ps.executeUpdate();
                

			FXMLLoader loader = new FXMLLoader(WorkManager.class.getResource("AddStaff.fxml"));
			AnchorPane pane = loader.load();
			
			Scene scene = new Scene(pane);
			
			
			Stage primaryStage = new Stage();
			
			primaryStage.setScene(scene);
			primaryStage.setTitle("Add Staff Details");
                        primaryStage.setResizable(false);
			primaryStage.show();
                        
                        Stage stage = (Stage) continueTo.getScene().getWindow();
                            stage.close();
                       
			
			
			
			
                        }
                        catch(Exception e)
                        {
                            e.printStackTrace();
                        }
        
        
        
    }

}



