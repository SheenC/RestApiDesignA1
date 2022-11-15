/**
 * @author Jasmine Chu (xiyinc)
 */
package a0;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseTest {
    static Database db;
    static Event e1;

    @BeforeAll
    static void setUp() {
        db = new Database();
        e1 = new Event(11,5,2022,"p","d", "c");
    }

    @Test
    void addEvent() {
        assertEquals(0, db.getNumberOfDates());
        db.addEvent(e1);
        assertEquals(1, db.getNumberOfDates());
        db.deleteEvent(e1.getID());
    }

    @Test
    void deleteEvent() {
        db.addEvent(e1);
        String id = e1.getID();
        assertEquals(e1, db.searchEventById(id));
        db.deleteEvent(id);
        assertEquals(null, db.searchEventById(id));
    }

    @Test
    void getCurrentDate() {
        assertEquals(LocalDate.now().getDayOfMonth(), db.getCurrentDate());
    }

    @Test
    void getCurrentMonth() {
        assertEquals(LocalDate.now().getMonthValue(), db.getCurrentMonth());
    }

    @Test
    void getCurrentYear() {
        assertEquals(LocalDate.now().getYear(), db.getCurrentYear());
    }

    @Test
    void getEventsByDate() {
        db.addEvent(e1);
        ArrayList<Event> list = db.getEventsByDate("11/5/2022");
        assertEquals(1, list.size());
        assertEquals(e1, list.get(0));
    }

    @Test
    void getNumberOfDates() {
        assertEquals(1, db.getNumberOfDates());
    }

    @Test
    void searchEventById() {
        assertEquals(null, db.searchEventById("1000"));
    }
}