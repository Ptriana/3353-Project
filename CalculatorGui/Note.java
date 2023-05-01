package CalculatorGui;
public class Note {
    public String date;
    public String time;
    public String subject;
    public String noteContents;

    public Note(String date, String time, String subject, String noteContents) {
        this.date = date;
        this.time = time;
        this.subject = subject;
        this.noteContents = noteContents;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getSubject() {
        return subject;
    }

    public String getNoteContents() {
        return noteContents;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setNoteContents(String noteContents) {
        this.noteContents = noteContents;
    }

    @Override
    public String toString() {
        return "Note{" +
                "date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", subject='" + subject + '\'' +
                ", noteContents='" + noteContents + '\'' +
                '}';
    }
}
/*
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Note {
    private final StringProperty date;
    private final StringProperty time;
    private final StringProperty subject;
    private final StringProperty noteContents;
    
    public Note(String date, String time, String subject, String noteContents) {
        this.date = new SimpleStringProperty(date);
        this.time = new SimpleStringProperty(time);
        this.subject = new SimpleStringProperty(subject);
        this.noteContents = new SimpleStringProperty(noteContents);
    }
    
    public String getDate() {
        return date.get();
    }
    
    public void setDate(String date) {
        this.date.set(date);
    }
    
    public StringProperty dateProperty() {
        return date;
    }
    
    public String getTime() {
        return time.get();
    }
    
    public void setTime(String time) {
        this.time.set(time);
    }
    
    public StringProperty timeProperty() {
        return time;
    }
    
    public String getSubject() {
        return subject.get();
    }
    
    public void setSubject(String subject) {
        this.subject.set(subject);
    }
    
    public StringProperty subjectProperty() {
        return subject;
    }
    
    public String getNoteContents() {
        return noteContents.get();
    }
    
    public void setNoteContents(String noteContents) {
        this.noteContents.set(noteContents);
    }
    
    public StringProperty noteContentsProperty() {
        return noteContents;
    }
}
*/