/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package workmanager;

import static com.sun.deploy.uitoolkit.ToolkitStore.dispose;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
    
//    username.setOnKeyPressed(e ->
//            {
//                if(e.getCode() == KeyCode.ENTER)
//                {
//                    try
//                    {
//                        loginTrial();
//                    }
//                }
//            })
    
   public void keyPressed(KeyEvent e)
   {
       if(e.getCode() == KeyCode.ENTER)
       {
           loginTrial();
       }
   }
    
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
 
        //String tableNames = "";
        //String login = "SELECT COMPANYDETAILS.USERNAME, COMPANYDETAILS.PASSWORD, STAFFDETAILS.USERNAME, STAFFDETAILS.PASSWORD FROM"
               // + " COMPANYDETAILS, STAFFDETAILS WHERE COMPANYDETAILS.USERNAME = ? AND COMPANYDETAILS.PASSWORD = ? OR STAFFDETAILS.USERNAME = ? AND STAFFDETAILS.PASSWORD = ?";
     String tableQuery = "Show tables";
            if(username.getText().isEmpty() || password.getText().isEmpty())
            {
                Alert errorAlert = new Alert(AlertType.ERROR);
                        errorAlert.setHeaderText("Input not correct");
                        errorAlert.setContentText("Login Failed! Please try again");
                        errorAlert.showAndWait();
            }  
            else
            {
        try
        {
             String host = "jdbc:derby://localhost:1527/WorkManager";
            String uName = "administration";
            String uPass = "admin";

            con = DriverManager.getConnection(host, uName, uPass);
            
   

            stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            /*        
            rs = stmt.executeQuery(tableQuery);
            
            ArrayList<String> tableNamesList = new ArrayList<>();
            */
            
            ArrayList<String> tableNamesList = new ArrayList<>();
            
            DatabaseMetaData metaData = con.getMetaData();
      String[] types = {"TABLE"};
      //Retrieving the columns in the database
      ResultSet tables = metaData.getTables(null, null, "%", types);
      
      String tableCompanyValue = "";
           String tableNames = "";
      
      while(tables.next())
            {
//                tableNamesList.add(tables.getString("TABLE_NAME"));
                //System.out.println(tables.getString(3));
//                tableCompanyValue = tables.getString("TABLE_NAME") + ".COMPANYNAME ";
//                //tableNames = tables.getString("TABLE_NAME") + " ";
                //tableNames += tables.getString(3) + ", ";
                //System.out.println(tableNames);
              tableNamesList.add(tables.getString(3) + ", ");
              //System.out.println("Before replace");
              //System.out.println(tableNamesList);
              
            
                
//                if(tables.last())
//                {
//                    tableNames = tables.getString(3) + " ";
//                }
//                else
//                {
//                    tableNames = tables.getString(3) + ", ";
//                }
//                System.out.println(tableNames);
            }
      
     // System.out.println();
      
      int lastIndex = tableNamesList.size() - 1;
      //
      
            String tempValue = tableNamesList.get(lastIndex).replace(",", "");
            
            //System.out.println(tempValue);
            
            tableNamesList.remove(lastIndex);
            tableNamesList.add(lastIndex, tempValue);
            
            //System.out.println("After replace");
            //System.out.println(tableNamesList);
            
            for(int i = 0; i < tableNamesList.size(); i++)
            {
                tableNames += tableNamesList.get(i);
            }
            
            //System.out.println(tableNames);
      /*
            while(rs.next())
            {
                tableNamesList.add(rs.getString(1));
            }
*/
            
           
           
           /*
            for(int i = 0; i < tableNamesList.size(); i++)
            {
                tableCompanyValue = tableNamesList.get(i) + ".COMPANYNAME ";
                tableNames = tableNamesList.get(i) + " ";
            }
*/

//            for(int i = 0; i <= tableNamesList.size(); i++)
//            {
//                System.out.println(tableNamesList.get(i));
//            }
//            System.out.println(tableNames);
            
             //String login = "SELECT * FROM FLYMOTION, STAFFDETAILS WHERE FLYMOTION.USERNAME = ? AND FLYMOTION.PASSWORD = ? OR STAFFDETAILS.USERNAME = ? AND STAFFDETAILS.PASSWORD = ?";
             /*Getting error when more than one table present: java.sql.SQLSyntaxErrorException: Column name 'USERNAME' is in more than one table in the FROM list.
             Could implement two sets of table with one holding information on the different companies and perform sql statement with an INNER JOIN
             */
             String login = "SELECT * FROM " + tableNames + "WHERE USERNAME = ? AND PASSWORD = ?";
                    
                    PreparedStatement ps = con.prepareStatement(login);
                    ps.setString(1, username.getText());
                     ps.setString(2, password.getText());
                    //ps.setString(3, username.getText());
                     //ps.setString(4, password.getText());
                   
                     rs = ps.executeQuery();
                    
                      //System.out.println(rs.getString("USERNAME")); 
                      
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
}

