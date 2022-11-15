/**
 * @author Jasmine Chu (xiyinc)
 */
package a0;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.util.*;

public class Database {
    private HashMap<String, ArrayList<Event>> eventDB;

    public Database() {
        eventDB = new HashMap<>();
    }

    public void addEvent(@NotNull Event event) {
        String date = event.getMonth()+"/"+event.getDate()+"/"+event.getYear();
        ArrayList list = eventDB.getOrDefault(date, new ArrayList<>());
        list.add(event);
        eventDB.put(date, list);
    }

    public void deleteEvent(String id) {
        for (String key : eventDB.keySet()) {
            ArrayList<Event> eventList = eventDB.get(key);
            for (Event e : eventList) {
                if (e.getID().equals(id)) {
                    eventList.remove(e);
                    break;
                }
            }
            eventDB.put(key, eventList);
        }
    }

    public int getCurrentDate() {
        LocalDate currentDate = LocalDate.now();
        return currentDate.getDayOfMonth();
    }

    public int getCurrentMonth() {
        LocalDate currentDate = LocalDate.now();
        return currentDate.getMonthValue();
    }

    public int getCurrentYear() {
        LocalDate currentDate = LocalDate.now();
        return currentDate.getYear();
    }

    public ArrayList<Event> getEventsByDate(String date) {
        return eventDB.get(date);
    }

    public int getNumberOfDates() {
        return this.eventDB.keySet().size();
    }

    public Event searchEventById(String id) {
        for (String key : eventDB.keySet()) {
            ArrayList<Event> eventList = eventDB.get(key);
            for (Event e : eventList) {
                if (e.getID().equals(id)) {
                    return e;
                }
            }
        }
        return null;
    }

}
