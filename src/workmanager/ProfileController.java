/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workmanager;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;

public class ProfileController implements Initializable{

    @FXML
    private MenuItem menuProfile;

    @FXML
    private MenuItem menuAvailabilities;

    @FXML
    private MenuItem menuRoster;

    @FXML
    private MenuItem menuStaff;

    @FXML
    private MenuItem menuStatus;

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

}

