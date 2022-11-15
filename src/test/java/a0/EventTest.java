/**
 * @author Jasmine Chu (xiyinc)
 */
package a0;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {
    static Event e1;
    static Event e2;

    @BeforeAll
    static void setUp() {
        e1 = new Event(11,5,2022,"p", "d", "c");
        e2 = new Event(11,5,2022,"p2", "d", "c");
    }

    @Test
    void setMonth() {
        assertEquals(11, e1.getMonth());
        e1.setMonth(12);
        assertEquals(12, e1.getMonth());
    }

    @Test
    void setDate() {
        assertEquals(5, e1.getDate());
        e1.setDate(8);
        assertEquals(8, e1.getDate());
    }

    @Test
    void setYear() {
        assertEquals(2022, e1.getYear());
        e1.setYear(2023);
        assertEquals(2023, e1.getYear());
    }

    @Test
    void setPatient() {
        assertEquals("p", e1.getPatient());
        e1.setPatient("pp");
        assertEquals("pp", e1.getPatient());
    }

    @Test
    void setDoctor() {
        assertEquals("d", e1.getDoctor());
        e1.setDoctor("dd");
        assertEquals("dd", e1.getDoctor());
    }

    @Test
    void setComment() {
        assertEquals("c", e1.getComment());
        e1.setComment("cc");
        assertEquals("cc", e1.getComment());
    }

    @Test
    void getDate() {
        assertEquals(5, e2.getDate());
    }

    @Test
    void getMonth() {
        assertEquals(11, e2.getMonth());
    }

    @Test
    void getYear() {
        assertEquals(2022, e2.getYear());
    }

    @Test
    void getPatient() {
        assertEquals("p2", e2.getPatient());
    }

    @Test
    void getDoctor() {
        assertEquals("d", e2.getDoctor());
    }

    @Test
    void getComment() {
        assertEquals("c", e2.getComment());
    }
}