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
    /*@FXML
    private Button toSceneThree;*/
    
    
   
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

/*When I try the section below, I get a null pointer error when starting the application
If I switch the method to switchToSceneFour I get a null pointer when I click the appropiate button*/



/*private static Connection con;
    private static Statement stat;
    private PreparedStatement prep;
    private ObservableList <Note> dataNotes; 
    ResultSet resultSet = null;   
   //@Override
     private final ObservableList<Note> notes = FXCollections.observableArrayList();

  /*  public void initialize() throws SQLException {
     dataNotes = FXCollections.observableArrayList();
    try {
    String url = "jdbc:sqlite:java.db";  
    Connection conn = DriverManager.getConnection(url);
    String query = "SELECT * FROM notesTable";
    PreparedStatement ps = conn.prepareStatement(query);
    ResultSet rs = ps.executeQuery();
    while (rs.next()) {
        Note nt = new Note(rs.getString("Date"), rs.getString("Time"), rs.getString("Subject"), rs.getString("NoteContents"));
        dataNotes.add(nt);
    }
    dateColumn.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
    timeColumn.setCellValueFactory(cellData -> cellData.getValue().timeProperty());
    subjectColumn.setCellValueFactory(cellData -> cellData.getValue().subjectProperty());
    noteContentsColumn.setCellValueFactory(cellData -> cellData.getValue().noteContentsProperty());
    tableView.setItems(dataNotes);
} catch (SQLException ex) {
    ex.printStackTrace();
}
}*/
  /*public ObservableList<Note> getNotes() {
    return dataNotes;*/




/*This prints to the console but when we try something like the code above we get null pointers
we can't seem to get it to display any way in javafx*/
@FXML
void switchToScene4(ActionEvent event) throws IOException {
    Parent root = FXMLLoader.load(getClass().getResource("Console.fxml"));
    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
    String url = "jdbc:sqlite:java.db";  
        Connection conn = null; 
        String sql = "Select * from NotesTable"; 
        try {  
            conn = DriverManager.getConnection(url);  
            
            Statement stmt  = conn.createStatement();  
            ResultSet rs    = stmt.executeQuery(sql);
              while (rs.next()) {  
                System.out.println(rs.getString("date") +  "\t" +   
                                   rs.getString("time") + "\t" +  
                                   rs.getString("subject") + "\t"+
                                   rs.getString("noteContents"));  
            }    
        } catch (SQLException e) {  
            System.out.println(e.getMessage());  
        }  
    /*dateColumn.setCellValueFactory(new PropertyValueFactory<Note, String>("date"));
    timeColumn.setCellValueFactory(new PropertyValueFactory<Note, String>("time"));
    subjectColumn.setCellValueFactory(new PropertyValueFactory<Note, String>("subject"));
    noteContentsColumn.setCellValueFactory(new PropertyValueFactory<Note, String>("noteContents"));
    tableView.setItems(model.getNotes());*/
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




