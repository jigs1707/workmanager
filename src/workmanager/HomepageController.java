/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workmanager;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class HomepageController {

    @FXML
    private MenuItem exit;

    @FXML
    private Button profile;

    @FXML
    private Button availabilities;

    @FXML
    private Button roster;

    @FXML
    private Button staff;

    @FXML
    private Button status;

    @FXML
    private Button signOut;
    
    
    public void onSignOut()
    {
        try {
            FXMLLoader loader = new FXMLLoader(WorkManager.class.getResource("LoginScreen.fxml"));
            AnchorPane pane = loader.load();
            
            Scene scene = new Scene(pane);
            
            
            Stage primaryStage = new Stage();
            
            primaryStage.setScene(scene);
            primaryStage.setTitle("Login");
            primaryStage.setResizable(false);
            primaryStage.show();
            
            Stage stage = (Stage) signOut.getScene().getWindow();
            stage.close();
        } 
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}


