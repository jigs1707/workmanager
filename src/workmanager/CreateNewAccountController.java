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
import java.util.Random;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
    String randomCode;
    
    
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
    
    
    public void generateRandomString()
{
    String alphabet = "abcdefghijklmnopqrstuvwxyz";

    StringBuilder sb = new StringBuilder();

    // create an object of Random class
    Random random = new Random();

    // specify length of random string
    int length = 7;

    for(int i = 0; i < length; i++) {

      // generate random index number
      int index = random.nextInt(alphabet.length());

      // get character specified by index
      // from the string
      char randomChar = alphabet.charAt(index);

      // append the character to string builder
      sb.append(randomChar);
    
    }
    
    randomCode = sb.toString();
}
    
    
    public void onContinue()
    {
       
        String createTable = "create table ADMINISTRATION." + companyName.getText().toUpperCase() +
                " (COMPANYNAME VARCHAR(100) not null, " +
                "USERNAME VARCHAR(15) not null primary key, " +
                "PASSWORD VARCHAR(15) not null, " +
                "FIRSTNAME VARCHAR(20) not null, " +
                "LASTNAME VARCHAR(20) not null, " +
                "EMAIL VARCHAR(70) not null, " +
                "PHONE CHAR(10), " +
                "RANK VARCHAR(20), " + 
                "CODE CHAR(7))";
                
//"(\n" +
//"	COMPANYNAME VARCHAR(100) not null,\n" +
//"	USERNAME VARCHAR(15) not null primary key,\n" +
//"	PASSWORD VARCHAR(15) not null,\n" +
//"	FIRSTNAME VARCHAR(20) not null,\n" +
//"	LASTNAME VARCHAR(20) not null,\n" +
//"	EMAIL VARCHAR(70) not null,\n" +
//"	PHONE CHAR(10),\n" +
//"	RANK VARCHAR(20)\n" +
//")";
        
        String sql = "INSERT INTO " +  companyName.getText().toUpperCase() + " (COMPANYNAME, USERNAME, PASSWORD, FIRSTNAME, LASTNAME, EMAIL, PHONE, RANK, CODE) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        //boolean status = validPhoneNumber(phone.getText());
        if(validPhoneNumber(phone.getText()))
        {

                try {
                    
                     String host = "jdbc:derby://localhost:1527/WorkManager";
            String uName = "administration";
            String uPass = "admin";

            con = DriverManager.getConnection(host, uName, uPass);

            stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            
            stmt.executeUpdate(createTable);
            generateRandomString();
                    
                    PreparedStatement ps = con.prepareStatement(sql);
                    
                    
             
                    
                    
                    
                    ps.setString(1, companyName.getText());
                    ps.setString(2, username.getText());
                    ps.setString(3, password.getText());
                    ps.setString(4, fName.getText());
                    ps.setString(5, lName.getText());
                    ps.setString(6, email.getText());
                    ps.setString(7, phone.getText());
                    ps.setString(8, "Admin");
                    ps.setString(9, randomCode);

                    ps.executeUpdate();
                

//			FXMLLoader loader = new FXMLLoader(WorkManager.class.getResource("AddStaff.fxml"));
//                        
//			AnchorPane pane = loader.load();
//                        AddStaffController x = loader.getController();
//                        x.setCompanyName(companyName.getText());
//                        
//                        
//			
//			Scene scene = new Scene(pane);
//			
//			
//			Stage primaryStage = new Stage();
//			
//			primaryStage.setScene(scene);
//			primaryStage.setTitle("Add Staff Details");
//                        primaryStage.setResizable(false);
//			primaryStage.show();
//                        
//                        Stage stage = (Stage) continueTo.getScene().getWindow();
//                            stage.close();
                       
			
			Alert confirmation = new Alert(AlertType.INFORMATION);
                        confirmation.setHeaderText("Account created successfully");
                        confirmation.setContentText("A random code has been generated which is to be used by staff to register with your company. \n"
                                + "The code can be found in the profile section of the homepage. Thank you for using our service. \n"
                                + "Code: " + randomCode);
                        confirmation.showAndWait();
                        
            FXMLLoader loader = new FXMLLoader(WorkManager.class.getResource("LoginScreen.fxml"));
            AnchorPane pane = loader.load();
            
            Scene scene = new Scene(pane);
            
            
            Stage primaryStage = new Stage();
            
            primaryStage.setScene(scene);
            primaryStage.setTitle("Login");
            primaryStage.setResizable(false);
            primaryStage.show();
            
            Stage stage = (Stage) continueTo.getScene().getWindow();
            stage.close();
			
			
                        }
                        catch(Exception e)
                        {
                             Alert errorAlert = new Alert(AlertType.ERROR);
                        errorAlert.setHeaderText("Error");
                        errorAlert.setContentText(e.getMessage());
                        errorAlert.showAndWait();
                        }
        
        }
        
        else
        {
            Alert errorAlert = new Alert(AlertType.ERROR);
                        errorAlert.setHeaderText("Error");
                        errorAlert.setContentText("Please enter valid phone number");
                        errorAlert.showAndWait();
        }
        
    }
    
    public String getCompanyName()
    {
        return companyName.getText();
    }
    
    public void setCompanyName(String cName)
    {
        cName = companyName.getText();
    }
    
    public static boolean validPhoneNumber(String x) 
    {
        boolean status = false;
    try 
    { 
        
        if(x.length() == 10)
        {
            Integer.parseInt(x);
            status = true;
        }
        
        else
        {
           status = false;
        }
    } 
    catch(NumberFormatException e) 
    { 
        Alert errorAlert = new Alert(AlertType.ERROR);
                        errorAlert.setHeaderText("Error");
                        errorAlert.setContentText(e.getMessage());
                        errorAlert.showAndWait();
                        status = false;
    }
    // if exception isn't thrown, then it is an integer
    return status;
    }
    


}



