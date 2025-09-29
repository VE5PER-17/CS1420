package assign05;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CalendarDateDemoTest {

    @Test
    public void testCountDatesBefore_EmptyArray() {
        CalendarDate[] dates = {};
        CalendarDate target = new CalendarDate(6, 15, 2025);
        int result = CalendarDateDemo.countDatesBefore(dates, target);
        assertEquals(0, result);
    }

    @Test
    public void testCountDatesBefore_AllBefore() {
        CalendarDate[] dates = {
            new CalendarDate(1, 1, 2025),
            new CalendarDate(3, 10, 2025),
            new CalendarDate(6, 14, 2025)
        };
        CalendarDate target = new CalendarDate(6, 15, 2025);
        int result = CalendarDateDemo.countDatesBefore(dates, target);
        assertEquals(3, result);
    }


    @Test
    public void testCountDatesBefore_AllAfter() {
        CalendarDate[] dates = {
            new CalendarDate(6, 16, 2025),
            new CalendarDate(12, 25, 2025),
            new CalendarDate(1, 1, 2026)
        };
        CalendarDate target = new CalendarDate(6, 15, 2025);
        int result = CalendarDateDemo.countDatesBefore(dates, target);
        assertEquals(0, result);
    }


    @Test
    public void testCountDatesBefore_AllEqual() {
        CalendarDate[] dates = {
            new CalendarDate(6, 15, 2025),
            new CalendarDate(6, 15, 2025)
        };
        CalendarDate target = new CalendarDate(6, 15, 2025);
        int result = CalendarDateDemo.countDatesBefore(dates, target);
        assertEquals(0, result);
    }


    @Test
    public void testCountDatesBefore_Mixed() {
        CalendarDate[] dates = {
            new CalendarDate(2, 14, 2025),   // before
            new CalendarDate(6, 15, 2025),   // equal → not before
            new CalendarDate(12, 31, 2024),  // before (previous year)
            new CalendarDate(7, 1, 2025),    // after
            new CalendarDate(6, 14, 2025)    // before
        };
        CalendarDate target = new CalendarDate(6, 15, 2025);
        int result = CalendarDateDemo.countDatesBefore(dates, target);
        assertEquals(3, result); // Feb 14, Dec 31 2024, Jun 14
    }

    @Test
    public void testCountDatesBefore_NullArray() {
        assertThrows(NullPointerException.class, () -> {
            CalendarDateDemo.countDatesBefore(null, new CalendarDate(1, 1, 2025));
        });
    }

   
    @Test
    public void testCountDatesBefore_ArrayWithNullElement() {
        CalendarDate[] dates = {
            new CalendarDate(1, 1, 2025),
            null,
            new CalendarDate(2, 1, 2025)
        };
        CalendarDate target = new CalendarDate(3, 1, 2025);
        
        assertThrows(NullPointerException.class, () -> {
            CalendarDateDemo.countDatesBefore(dates, target);
        });
    }
    
	

}
