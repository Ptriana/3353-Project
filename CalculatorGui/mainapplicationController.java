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
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.PreparedStatement;  
import java.sql.SQLException;
import javafx.scene.control.DatePicker;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.sql.Statement;
import java.sql.ResultSet;


public class mainapplicationController 
{
    
      
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
    private Button allNotes;
   
    @FXML
    private DatePicker dateField;

    @FXML
    private TextArea textArea;

    @FXML
    private TextField subjectText;

    @FXML
    private TextField timeField;
    
    @FXML
    private TableColumn<?, ?> dateColumn;

    @FXML
    private TableColumn<?, ?> noteContentsColumn;

    @FXML
    private TableColumn<?, ?> subjectColumn;

    @FXML
    private TableColumn<?, ?> timeColumn;
   
   
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
    void switchToScene3WithoutPassword(ActionEvent event) throws IOException {
    Parent root = FXMLLoader.load(getClass().getResource("NoteGUIFXML.fxml"));
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
   @FXML
    void switchToScene4(ActionEvent event) throws IOException {
    Parent root = FXMLLoader.load(getClass().getResource("AllNotesGui.fxml"));
    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
    try{
         String url = "jdbc:sqlite:C://sqlite/SSSIT.db";  
         Connection conn = null;  
         conn = DriverManager.getConnection(url);  
         String sql = "Select * From NotesTable";
         ResultSet rs = stmt.executeQuery(sql);
        
    }
      
   @FXML
    void saveToDatabase(ActionEvent event) {
   try{ String url = "jdbc:sqlite:java.db";  
        Connection conn = DriverManager.getConnection(url);      
            conn = DriverManager.getConnection(url);
    String sql1 = "CREATE TABLE IF NOT EXISTS NotesTable (\n" 
    + " Date text,\n"
    + " Time text,\n"
    + " Subject text,\n"
    + " NoteContents text\n"
    + ");";
    
             
            Statement stmt = conn.createStatement();  
            stmt.execute(sql1);  
        
    
    
    String sql = "INSERT INTO NotesTable(Date, Time, Subject, NoteContents) VALUES(?,?,?,?)";  
    LocalDate date = dateField.getValue();
    DateTimeFormatter fDate = DateTimeFormatter.ofPattern("dd/LLLL/yyyy");
    String formatDate = fDate.format(date);
    /*String tempDate = tempDateText.getText();*/
    String time = timeField.getText();
    String subject = subjectText.getText();
    /*String noteContents = noteTextField.getText();*/
    String noteContents = textArea.getText();
    
         
            PreparedStatement pstmt = conn.prepareStatement(sql);  
            pstmt.setString(1, formatDate);  
            pstmt.setString(2, time);
            pstmt.setString(3, subject);
            pstmt.setString(4, noteContents);  
            pstmt.executeUpdate();  
        } catch (SQLException e) {  
            System.out.println(e.getMessage());  
        }  
    }  
        
        
    }  





    
   

   
   
   
  

