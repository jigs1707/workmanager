/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workmanager;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import static workmanager.WorkManager.con;


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
    private ChoiceBox<String> rank;
    
    ObservableList<String> rankList = FXCollections.observableArrayList("Manager", "Staff Member");
    

    @FXML
    private TextField fName;

    @FXML
    private Button finish;
    
        static Statement stmt;
    static ResultSet rs;
    
    String companyName;


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
        
        rank.setItems(rankList);
        
        
    }
    
    
    

    public void onFinish()
    {
        String sql = "INSERT INTO STAFFDETAILS (COMPANYNAME, USERNAME, PASSWORD, FIRSTNAME, LASTNAME, EMAIL, PHONE, RANK) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try{
            
             String host = "jdbc:derby://localhost:1527/WorkManager";
            String uName = "administration";
            String uPass = "admin";

            Connection con = DriverManager.getConnection(host, uName, uPass);

            stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            
            
        PreparedStatement ps = con.prepareStatement(sql);
                    
               
                    
                    
                    
                   ps.setString(1, companyName);
                    ps.setString(2, username.getText());
                    ps.setString(3, password.getText());
                    ps.setString(4, fName.getText());
                    ps.setString(5, lName.getText());
                    ps.setString(6, email.getText());
                    ps.setString(7, phone.getText());
                    ps.setString(8, rank.getValue().toString());

                    ps.executeUpdate();
                

			FXMLLoader loader = new FXMLLoader(WorkManager.class.getResource("LoginScreen.fxml"));
			AnchorPane pane = loader.load();
			
			Scene scene = new Scene(pane);
			
			
			Stage primaryStage = new Stage();
			
			primaryStage.setScene(scene);
			primaryStage.setTitle("Login");
                        primaryStage.setResizable(false);
			primaryStage.show();
                        
                        Stage stage = (Stage) finish.getScene().getWindow();
                            stage.close();
        }
        
        catch(Exception e)
        {
            e.printStackTrace();
        }
                       
    }
    
    /* public void setStaffDetails(String cName)
    {
        
        
        String sql = "INSERT INTO STAFFDETAILS (COMPANYNAME, USERNAME, PASSWORD, FIRSTNAME, LASTNAME, EMAIL, PHONE, RANK) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try{
            
             String host = "jdbc:derby://localhost:1527/WorkManager";
            String uName = "administration";
            String uPass = "admin";

            Connection con = DriverManager.getConnection(host, uName, uPass);

            stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            
            
        PreparedStatement ps = con.prepareStatement(sql);
                    
               
                    
                    
                    ps.setString(1, cName);
                    ps.setString(2, username.getText());
                    ps.setString(3, password.getText());
                    ps.setString(4, fName.getText());
                    ps.setString(5, lName.getText());
                    ps.setString(6, email.getText());
                    ps.setString(7, phone.getText());
                    ps.setString(8, rank.getValue());

                    ps.executeUpdate();
        }
        
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }*/
    
    public void setCompanyName(String cName)
    {
       companyName = cName;
    }
    
    public String getCompanyName()
    {
        return companyName;
    }
    
   
}
