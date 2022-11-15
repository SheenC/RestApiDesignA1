/**
 * @author Jasmine Chu (xiyinc)
 */
package a0;

public class Event {
    private static int count = 0;

    private String eventID;
    private int month;
    private int date;
    private int year;

    private String patient;
    private String doctor;
    private String comment;

    public Event(int month, int date, int year, String patient, String doctor, String comment) {
        count++;
        this.eventID = count+"";
        this.month = month;
        this.date = date;
        this.year = year;
        this.patient = patient;
        this.doctor = doctor;
        this.comment = comment;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getID() {
        return this.eventID;
    }

    public int getDate() {
        return this.date;
    }

    public int getMonth() {
        return this.month;
    }

    public int getYear() {
        return this.year;
    }

    public String getPatient() {
        return this.patient;
    }

    public String getDoctor() {
        return this.doctor;
    }

    public String getComment() {
        return this.comment;
    }

}
