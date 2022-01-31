/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workmanager;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ProfileController implements Initializable{

    @FXML
    private MenuItem menuHome;

    @FXML
    private MenuItem menuSignout;

    @FXML
    private Label cName;
    
   
    
    
    public void setCompanyName()
    {
        cName.setText("name");
    }

   
    
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) 
    {
        setCompanyName();
    }
    
    
    public void goHome()
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
                        
                        Stage stage = (Stage) cName.getScene().getWindow();
                            stage.close();
                       
			
			
			
			
                        }
                        catch(Exception e)
                        {
                            e.printStackTrace();
                        }
    }
    
    public void signOut()
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
                        
                        Stage stage = (Stage) cName.getScene().getWindow();
                            stage.close();
                       
			
			
			
			
                        }
                        catch(Exception e)
                        {
                            e.printStackTrace();
                        }
    }

}

