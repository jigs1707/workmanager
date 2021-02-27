/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package workmanager;

import static com.sun.deploy.uitoolkit.ToolkitStore.dispose;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class LoginScreenController {

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Button login;

    @FXML
    private Hyperlink newAccount;
    
           static Connection con;
    static Statement stmt;
    static ResultSet rs;
    
    
    public void createNewAccount()
    {
         try
                        {
			FXMLLoader loader = new FXMLLoader(WorkManager.class.getResource("CreateNewAccount.fxml"));
			AnchorPane pane = loader.load();
			
			Scene scene = new Scene(pane);
			
			
			Stage primaryStage = new Stage();
			
			primaryStage.setScene(scene);
			primaryStage.setTitle("Create New Account");
                        primaryStage.setResizable(false);
			primaryStage.show();
                        
                        Stage stage = (Stage) newAccount.getScene().getWindow();
                            stage.close();
                       
			
			
			
			
                        }
                        catch(Exception e)
                        {
                            e.printStackTrace();
                        }
    }
    
    
    public void loginTrial()
    {
        String login = "SELECT COMPANYDETAILS.USERNAME, COMPANYDETAILS.PASSWORD, STAFFDETAILS.USERNAME, STAFFDETAILS.PASSWORD FROM"
                + " COMPANYDETAILS, STAFFDETAILS WHERE COMPANYDETAILS.USERNAME = ? AND COMPANYDETAILS.PASSWORD = ? OR STAFFDETAILS.USERNAME = ? AND STAFFDETAILS.PASSWORD = ?";
        
        try
        {
             String host = "jdbc:derby://localhost:1527/WorkManager";
            String uName = "administration";
            String uPass = "admin";

            con = DriverManager.getConnection(host, uName, uPass);

            stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                    
                    
                    PreparedStatement ps = con.prepareStatement(login);
                    ps.setString(1, username.getText());
                     ps.setString(2, password.getText());
                     ps.setString(3, username.getText());
                     ps.setString(4, password.getText());
                     
                     rs = ps.executeQuery();
                     
                     if(rs.next())
                     {
                         try
                        {
			FXMLLoader loader = new FXMLLoader(WorkManager.class.getResource("Homepage.fxml"));
			AnchorPane pane = loader.load();
			
			Scene scene = new Scene(pane);
			
			
			Stage primaryStage = new Stage();
			
			primaryStage.setScene(scene);
			primaryStage.setTitle("Homepage");
                        primaryStage.setResizable(false);
			primaryStage.show();
                        
                        Stage stage = (Stage) newAccount.getScene().getWindow();
                            stage.close();
                       
			
			
			
			
                        }
                        catch(Exception e)
                        {
                            e.printStackTrace();
                        }
                         
                     }
                     
                     else
                     {
                         Alert errorAlert = new Alert(AlertType.ERROR);
                        errorAlert.setHeaderText("Input not correct");
                        errorAlert.setContentText("Login Failed! Please try again");
                        errorAlert.showAndWait();
                     }
        } 
        catch (SQLException ex) {
            Logger.getLogger(LoginScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

