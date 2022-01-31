/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workmanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author jigssharma
 */
public class WorkManager extends Application 
{
    
    static Connection con;
    static Statement stmt;
    static ResultSet rs;
    
    @Override
    public void start(Stage primaryStage) {
       try
                        {
			FXMLLoader loader = new FXMLLoader(WorkManager.class.getResource("LoginScreen.fxml"));
			AnchorPane pane = loader.load();
			
			Scene scene = new Scene(pane);
			
			
			
			
			primaryStage.setScene(scene);
			primaryStage.setTitle("Login");
                        primaryStage.setResizable(false);
			primaryStage.show();
                        
                        try {
            
            String host = "jdbc:derby://localhost:1527/WorkManager";
            String uName = "administration";
            String uPass = "admin";

            con = DriverManager.getConnection(host, uName, uPass);

            stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

            
            //String sql = "SELECT * FROM STAFFDETAILS";
            //rs = stmt.executeQuery(sql);
            
            
            
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
                       
			
			
			
			
                        }
                        catch(Exception e)
                        {
                            e.printStackTrace();
                        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        launch(args);
        
        
    }
       /* try {
            
            String host = "jdbc:derby://localhost:1527/WorkManager";
            String uName = "administration";
            String uPass = "admin";

            con = DriverManager.getConnection(host, uName, uPass);

            stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

            
            //String sql = "SELECT * FROM STAFFDETAILS";
            //rs = stmt.executeQuery(sql);
            
            
            
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
*/
    
    
}



