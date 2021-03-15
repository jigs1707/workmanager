/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workmanager;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AddStaffController implements Initializable{

        
     @FXML
    private Button back;

    @FXML
    private TextField username;

    @FXML
    private TextField password;

    @FXML
    private TextField lName;

    @FXML
    private TextField email;

    @FXML
    private TextField phone;

    @FXML
    private ChoiceBox<?> rank;

    @FXML
    private TextField fName;

    @FXML
    private Button finish;


public void backToCreateAccount()
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
                        
                        Stage stage = (Stage) back.getScene().getWindow();
                            stage.close();
                       
			
			
			
			
                        }
                        catch(Exception e)
                        {
                            e.printStackTrace();
                        }
    
}


public void generateRandomString()
{
    String alphabet = "abcdefghijklmnopqrstuvwxyz";

    StringBuilder sb = new StringBuilder();
    StringBuilder sb2 = new StringBuilder();

    // create an object of Random class
    Random random = new Random();

    // specify length of random string
    int length = 7;

    for(int i = 0; i < length; i++) {

      // generate random index number
      int index = random.nextInt(alphabet.length());
      int index2 = random.nextInt(alphabet.length());

      // get character specified by index
      // from the string
      char randomChar = alphabet.charAt(index);
      char randomChar2 = alphabet.charAt(index2);

      // append the character to string builder
      sb.append(randomChar);
      sb2.append(randomChar2);
    
}
    
    String uname = sb.toString();
    username.setText(uname);
    
    String pword = sb2.toString();
    
    password.setText(pword);
    
   
}

    @Override
    public void initialize(URL location, ResourceBundle resources) 
    {
        generateRandomString();
    }

}
