package CalculatorGui;

import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


public class mainapplicationController {

   private Stage stage;
   private Scene scene;
   private Parent root;
   
    @FXML
    private Label invalidPassword;

    @FXML
    private PasswordField password;

    @FXML
    private TextField userText;

   
   @FXML
    void switchToScene1(ActionEvent event) throws IOException {
    Parent root = FXMLLoader.load(getClass().getResource("CalculatorGUIFXML.fxml"));
    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
   
   
   }
   
   @FXML
    void switchToScene2(ActionEvent event) throws IOException {
    Parent root = FXMLLoader.load(getClass().getResource("LoginGUIFXML.fxml"));
    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
    }
    
    
   @FXML
    void switchToScene3(ActionEvent event) throws IOException {
    String user = userText.getText();
    String passwordText = password.getText();
    if(user.equals("Admin") && passwordText.equals("Password1"))
    {
    Parent root = FXMLLoader.load(getClass().getResource("NoteGUIFXML.fxml"));
    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
    }
    else
    {
    invalidPassword.setText("Invalid Login");
    }

   
      }
   
   }

   
   
   
  

