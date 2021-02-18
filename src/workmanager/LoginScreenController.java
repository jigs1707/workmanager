/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package workmanager;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoginScreenController {

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Button login;

    @FXML
    private Hyperlink newAccount;
    
    
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

}

