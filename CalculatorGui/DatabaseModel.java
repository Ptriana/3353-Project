package CalculatorGui;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DatabaseModel
{
   public ObservableList<Note> notes;
   public DatabaseModel() {
      notes = FXCollections.observableArrayList();
      notes.add(new Note("11/21/2022" , " Test" , "Test 1", "test 2"));
      notes.add(new Note("11/21/2032" , " Test" , "Test 2", "test 3"));
   }
   public ObservableList<Note> getNotes()
   {
   return notes;
   }
   public void setNotes(ObservableList<Note> notes)
   {
   this.notes = notes;
   }
   
   
   
   public void update(){
   
    ObservableList<Note> notes = FXCollections.observableArrayList();
    try {
    String url = "jdbc:sqlite:java.db";  
    Connection conn = DriverManager.getConnection(url);
    String query = "SELECT * FROM notesTable";
    PreparedStatement ps = conn.prepareStatement(query);
    ResultSet rs = ps.executeQuery();
    while (rs.next()) {
        Note nt = new Note(rs.getString("Date"), rs.getString("Time"), rs.getString("Subject"), rs.getString("NoteContents"));
        notes.add(nt);
    }}
    catch (SQLException ex) {
    ex.printStackTrace();
}


   }
}