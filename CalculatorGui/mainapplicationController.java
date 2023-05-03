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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import CalculatorGui.Note;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javafx.scene.layout.StackPane;

public class mainapplicationController 
{    
   private Stage stage;
   private Scene scene;
   private Parent root;
   
   private DatabaseModel model = new DatabaseModel();
   
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
    private TableColumn<Note, String> dateColumn;

    @FXML
    private TableColumn<Note, String> noteContentsColumn;

    @FXML
    private TableColumn<Note, String> subjectColumn;

    @FXML
    private TableColumn<Note, String> timeColumn;
   
    @FXML
    private TableView<Note> tableView;
  
    @FXML
    private ListView<String> listView;
   
    @FXML
    private TextArea dataArea;
   
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
   
        TableView<Note> tableView = new TableView<>();

        TableColumn<Note, String> dateCol = new TableColumn<>("Date");
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));

        TableColumn<Note, String> timeCol = new TableColumn<>("Time");
        timeCol.setCellValueFactory(new PropertyValueFactory<>("time"));

        TableColumn<Note, String> subjectCol = new TableColumn<>("Subject");
        subjectCol.setCellValueFactory(new PropertyValueFactory<>("subject"));

        TableColumn<Note, String> noteContentsCol = new TableColumn<>("Note Contents");
        noteContentsCol.setCellValueFactory(new PropertyValueFactory<>("noteContents"));

        tableView.getColumns().add(dateCol);
        tableView.getColumns().add(timeCol);
        tableView.getColumns().add(subjectCol);
        tableView.getColumns().add(noteContentsCol);
         
        ObservableList<Note> data = FXCollections.observableArrayList();
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:java.db")) 
        {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM NotesTable");
            StringBuilder sb = new StringBuilder();
            while (rs.next()) 
            {
                String date = rs.getString("date");
                String time = rs.getString("time");
                String subject = rs.getString("subject");
                String noteContents = rs.getString("noteContents");
                sb.append(rs.getString("date"))
               .append("\t")
               .append(rs.getString("time"))
               .append("\t")
               .append(rs.getString("subject"))
               .append("\t")
               .append(rs.getString("noteContents"))
               .append("\n");

                data.add(new Note(date, time, subject, noteContents));
            }
             System.out.println(sb.toString());


        } catch (SQLException e) 
        {
            System.out.printf("SQL ERROR: %s%n", e.getMessage());
        }

        tableView.setItems(data);

        StackPane rootPane = new StackPane();
        rootPane.getChildren().add(tableView);

        Scene tableViewScene = new Scene(rootPane, 600, 400);

        Stage tableViewStage = new Stage();
        tableViewStage.setTitle("All Notes");
        tableViewStage.setScene(tableViewScene);
        tableViewStage.show();
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
    
       String time = timeField.getText();
       String subject = subjectText.getText();
 
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




