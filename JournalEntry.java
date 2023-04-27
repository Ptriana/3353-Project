package CalculatorGUI;

public class JournalEntry 
{
    private String date;
    private String time;
    private String subject;
    private String noteContents;

    public JournalEntry(String date, String time, String subject, String noteContents) 
    {
        this.date = date;
        this.time = time;
        this.subject = subject;
        this.noteContents = noteContents;
    }

    public String getDate() 
    {
        return date;
    }

    public void setDate(String date) 
    {
        this.date = date;
    }

    public String getTime() 
    {
        return time;
    }

    public void setTime(String time) 
    {
        this.time = time;
    }

    public String getSubject() 
    {
        return subject;
    }

    public void setSubject(String subject) 
    {
        this.subject = subject;
    }

    public String getNoteContents() 
    {
        return noteContents;
    }

    public void setNoteContents(String noteContents) 
    {
        this.noteContents = noteContents;
    }
}
