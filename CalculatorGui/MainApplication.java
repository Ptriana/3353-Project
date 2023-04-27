package CalculatorGui;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import java.io.IOException;

public class MainApplication extends Application
{
   @Override
   public void start(Stage stage) 
   {
       try{
       
          Parent root = FXMLLoader.load(getClass().getResource("CalculatorGUIFXML.fxml"));
          Scene scene = new Scene(root);
          stage.setScene(scene);
          stage.setTitle("Java Alchemist's Calculator App");
          stage.show();
       } catch(Exception e) {
        e.printStackTrace();
     }
  }
   
   
   public static void main(String[] args)
   {
      launch(args);
    }
   
}