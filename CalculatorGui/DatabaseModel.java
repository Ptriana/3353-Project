package CalculatorGui;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;


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
}