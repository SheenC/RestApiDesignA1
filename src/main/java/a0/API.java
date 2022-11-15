/**
 * @author Jasmine Chu (xiyinc)
 */
package a0;

import com.google.gson.*;

import java.util.*;

import static spark.Spark.*;

public class API {
    // http://localhost:4567/
    public static void main(String[] args) {
        Database db = new Database();
        Event event1 = new Event(11,5,2022, "Jason123", "M.D. Julie", "The patient got a Flu.");
        Event event2 = new Event(11,5,2022, "LucyLee", "M.D. Patrick", "The patient had a wisdom tooth extraction.");
        db.addEvent(event1);
        db.addEvent(event2);

        // API get current date
        // GET http://localhost:4567/DAI/current/date
        JsonObject dateObject = new JsonObject();
        dateObject.addProperty("date", db.getCurrentDate());
        JsonObject currentDateObject = new JsonObject();
        currentDateObject.add("current", dateObject);
        get("/DAI/current/date", (req, res) -> currentDateObject);

        // API get current month
        // GET http://localhost:4567/DAI/current/month
        JsonObject monthObject = new JsonObject();
        monthObject.addProperty("month", db.getCurrentMonth());
        JsonObject currentMonthObject = new JsonObject();
        currentMonthObject.add("current", monthObject);
        get("/DAI/current/month", (req, res) -> currentMonthObject);

        // API get current year
        // GET http://localhost:4567/DAI/current/year
        JsonObject yearObject = new JsonObject();
        yearObject.addProperty("year", db.getCurrentYear());
        JsonObject currentYearObject = new JsonObject();
        currentYearObject.add("current", yearObject);
        get("/DAI/current/year", (req, res) -> currentYearObject);

        // API get events for a given date
        // GET http://localhost:4567/DAI/allEvents/year/2022/month/11/date/5
        get("/DAI/allEvents/year/:yyyy/month/:mm/date/:dd",
                (req, res) -> {
                    int year = Integer.parseInt(req.params(":yyyy"));
                    int month = Integer.parseInt(req.params(":mm"));
                    int date = Integer.parseInt(req.params(":dd"));

                    ArrayList<Event> events = db.getEventsByDate(month+"/"+date+"/"+year);

                    return new Gson().toJson(events);
                });

        // API add event
        // POST http://localhost:4567/DAI/event?month=11&date=6&year=2022&patient=kelly&doctor=M.D. Andrew&comment=The patient got a headache.
        post("/DAI/event", (req, res) -> {
            // Get request body attributes
            int date = Integer.parseInt(req.queryParams("date"));
            int month = Integer.parseInt(req.queryParams("month"));
            int year = Integer.parseInt(req.queryParams("year"));
            String patient = req.queryParams("patient");
            String doctor = req.queryParams("doctor");
            String comment = req.queryParams("comment");
            // Add event to database
            Event event3 = new Event(month, date, year, patient, doctor, comment);
            db.addEvent(event3);
            return "Event " + event3.getID() + " Added";
        });

        // API modify event
        // PUT http://localhost:4567/DAI/event/3
        put("/DAI/event/:id", (req, res) -> {
            // Get request body attributes
            String id = req.params(":id");
            // Search the event
            Event event = db.searchEventById(id);
            if (req.queryParams("date") != null) {
                int date = Integer.parseInt(req.queryParams("date"));
                // revise date
                event.setDate(date);
            }
            if (req.queryParams("month") != null) {
                int month = Integer.parseInt(req.queryParams("month"));
                // revise month
                event.setMonth(month);
            }
            if (req.queryParams("year") != null) {
                int year = Integer.parseInt(req.queryParams("year"));
                // revise year
                event.setYear(year);
            }
            if (req.queryParams("patient") != null) {
                String patient = req.queryParams("patient");
                // revise patient
                event.setPatient(patient);
            }
            if (req.queryParams("doctor") != null) {
                String doctor = req.queryParams("doctor");
                // revise patient
                event.setDoctor(doctor);
            }
            if (req.queryParams("comment") != null) {
                String comment = req.queryParams("comment");
                // revise comment
                event.setComment(comment);
            }
            // Replace the event in the database
            db.deleteEvent(id);
            db.addEvent(event);
            return "Event " + id + " Revised";
        });

        // API delete event
        // DELETE http://localhost:4567/DAI/event/3
        delete("/DAI/event/:id", (req, res) -> {
            String id = req.params(":id");
            // Delete the event
            db.deleteEvent(id);
            return "Event " + id + " Deleted";
        });
    }

}