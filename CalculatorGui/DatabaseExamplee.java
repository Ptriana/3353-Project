package CalculatorGUI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class DatabaseExamplee extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        // create a JavaFX TableView to display the data
        TableView<JournalEntry> tableView = new TableView<>();

        // create columns for the table view
        //idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<JournalEntry, String> dateCol = new TableColumn<>("Date");
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));

        TableColumn<JournalEntry, String> timeCol = new TableColumn<>("Time");
        timeCol.setCellValueFactory(new PropertyValueFactory<>("time"));

        TableColumn<JournalEntry, String> subjectCol = new TableColumn<>("Subject");
        subjectCol.setCellValueFactory(new PropertyValueFactory<>("subject"));

        TableColumn<JournalEntry, String> noteContentsCol = new TableColumn<>("Note Contents");
        noteContentsCol.setCellValueFactory(new PropertyValueFactory<>("noteContents"));

        // add the columns to the table view
        tableView.getColumns().add(dateCol);
        tableView.getColumns().add(timeCol);
        tableView.getColumns().add(subjectCol);
        tableView.getColumns().add(noteContentsCol);

        // populate the table view with data from the database
        ObservableList<JournalEntry> data = FXCollections.observableArrayList();
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:java.db")) 
        {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM journal_entries");

            while (rs.next()) 
            {
                String date = rs.getString("date");
                String time = rs.getString("time");
                String subject = rs.getString("subject");
                String noteContents = rs.getString("noteContents");

                data.add(new JournalEntry(date, time, subject, noteContents));
            }
        } catch (SQLException e) 
        {
            System.out.printf("SQL ERROR: %s%n", e.getMessage());
        }

        tableView.setItems(data);

        // create a layout and add the table view to it
        StackPane root = new StackPane();
        root.getChildren().add(tableView);

        // create a scene and add the layout to it
        Scene scene = new Scene(root, 600, 400);

        // set the scene and show the stage
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
