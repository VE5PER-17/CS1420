package assign05;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * This class contains unit tests to check the correctness of the CalendarDate class.
 * 
 * @author CS 1420 course staff and UPDATE WITH YOUR NAME
 * @version UPDATE WITH MOST RECENT DATE
 */
public class CalendarDateTest {
	@Test
	public void testGetDay() {
		CalendarDate date = new CalendarDate(3, 15, 1950);
		assertEquals(15, date.getDay(), "getDay method incorrect");
	}
	
	@Test
	public void testToString() {
		CalendarDate date = new CalendarDate(8, 1, 1970);
		assertEquals("August 1, 1970", date.toString(),
				"toString does not return correct String -- check for typos");
	}
	
	@Test
	public void testComesBeforeTrue() {
		CalendarDate date = new CalendarDate(5, 19, 1985);
		assertTrue(date.comesBefore(new CalendarDate(6, 9, 1985)),
				"comesBefore does not return true when date on which method is called comes before argument");
	}
	
	@Test
	public void testComesAfterSameDate() {
		CalendarDate date = new CalendarDate(2, 7, 1888);
		assertFalse(date.comesAfter(new CalendarDate(2, 7, 1888)),
				"comesAfter does not return false when date on which method is called is the same as argument");
	}
	
	@Test
	public void testAdvanceOneDayEndOfMonth() {
		CalendarDate date = new CalendarDate(4, 30, 1200);
		date.advanceOneDay();
		assertEquals(5, date.getMonth().getMonthNumber(),
				"advanceOneDay does not add 1 to month when at the end of the month");
		assertEquals(1, date.getDay(),
				"advanceOneDay does set day to 1 when at the end of the month");
		assertEquals(1200, date.getYear(),
				"advanceOneDay changed year when at the end of the month (not December)");
	}
	
	@Test
	public void testDayOfYearFirst() {
		CalendarDate date = new CalendarDate(1, 1, 3000);
		assertEquals(1, date.dayOfYear(), "dayOfYear does not return 1 for the first day of a year");
	}
	
	@Test
	public void testIsLeapYearTrue() {
		CalendarDate date = new CalendarDate(1, 1, 2004);
		assertTrue(date.isLeapYear(), "isLeapYear does not return true for year divisible by 4 but not 100");
	}
	
	@Test
	public void testEqualsTrue() {
		CalendarDate date = new CalendarDate(10, 10, 3333);
		assertTrue(date.equals(new CalendarDate(10, 10, 3333)),
				"equals method does not return true for same dates");
	}

    // 边界测试：跨月（Jan 31 → Feb 1，非闰年）
	@Test
    public void testAdvanceOneDay_Jan31ToFeb1_NonLeapYear() {
        CalendarDate date = new CalendarDate(1, 31, 2023); // Jan 31, 2023
        date.advanceOneDay();
        assertEquals(2, date.getMonth().getMonthNumber());
        assertEquals(1, date.getDay());
        assertEquals(2023, date.getYear());
    }

    // 边界测试：跨月（Mar 31 → Apr 1）
    @Test
    public void testAdvanceOneDay_Mar31ToApr1() {
        CalendarDate date = new CalendarDate(3, 31, 2024);
        date.advanceOneDay();
        assertEquals(4, date.getMonth().getMonthNumber());
        assertEquals(1, date.getDay());
        assertEquals(2024, date.getYear());
    }


    // 边界测试：跨年（Dec 31 → Jan 1）
    @Test
    public void testAdvanceOneDay_Dec31ToJan1_NextYear() {
        CalendarDate date = new CalendarDate(12, 31, 2025);
        date.advanceOneDay();
        assertEquals(1, date.getMonth().getMonthNumber());
        assertEquals(1, date.getDay());
        assertEquals(2026, date.getYear());
    }


    // 闰年测试：Feb 28 → Feb 29（闰年）
    @Test
    public void testAdvanceOneDay_Feb28ToFeb29_LeapYear() {
        CalendarDate date = new CalendarDate(2, 28, 2024); // 2024 是闰年
        date.advanceOneDay();
        assertEquals(2, date.getMonth().getMonthNumber());
        assertEquals(29, date.getDay());
        assertEquals(2024, date.getYear());
    }


    // 非闰年测试：Feb 28 → Mar 1（非闰年）
    @Test
    public void testAdvanceOneDay_Feb28ToMar1_NonLeapYear() {
        CalendarDate date = new CalendarDate(2, 28, 2023); // 2023 不是闰年
        date.advanceOneDay();
        assertEquals(3, date.getMonth().getMonthNumber());
        assertEquals(1, date.getDay());
        assertEquals(2023, date.getYear());
    }
}