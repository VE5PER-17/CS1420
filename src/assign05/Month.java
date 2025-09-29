/**
 * 
 */
package assign05;

import java.util.Arrays;

/**
 * 
 */
public class Month {
	private int monthNumber; // 1-12
    private static final String[] monthName = {
        "January", "February", "March", "April", "May", "June",
        "July", "August", "September", "October", "November", "December"
    };
    private static Integer[] month31 = {1, 3, 5, 7, 8, 10, 12};
    private static Integer[] month30 = {4, 6, 9, 11};

    public Month() {
        this.monthNumber = 1;
    }

    /**
     * plain
     * @param monthNumber month number (1-12)
     */
    public Month(int monthNumber) {
        this.monthNumber = monthNumber;
    }

    /**
     * return month number  (1-12)
     * @return monthNumber
     */
    public int getMonthNumber() {
        return monthNumber;
    }

    /**
     * return the last day
     * @param isLeapYear 
     * @return Last day
     */
    public int lastDay(boolean isLeapYear) {
        if (monthNumber == 2 && isLeapYear) return 29;
        if (monthNumber == 2 && (isLeapYear == false)) return 28;
        
        if (Arrays.asList(month30).contains(monthNumber)) {
        	return 30;
        }else {
        	return 31;
        }
    }

    /**
     * check whether the date is valid or nor
     * @param day 
     * @param isLeapYear 
     * @return TF
     */
    public boolean validDay(int day, boolean isLeapYear) {
        return day >= 1 && day <= lastDay(isLeapYear);
    }

    /**
     * return the name
     * @return Monthname
     */
    public String toString() {
        return monthName[monthNumber - 1];
    }

    /**
     * check two month is equal or not
     * @param other
     * @return  true or false
     */
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null) return false;
        if (!(other instanceof Month)) return false;
        
        Month that = (Month) other;
        
        return this.monthNumber == that.monthNumber;
    }
}
