package assign05;
/*
 * CS1420
 * @author VESPER
 * @version 25-09-27 
 * 
 */


public class CalendarDate {
	
	private Month month;
	private int day; //1-based
	private int year; //1000-9999
	
	public CalendarDate() {
		this.month = new Month();
		this.day = 1;
		this.year = 1000;
	}
	

    /**
     * use numbers construct the date if valid
     * @param monthNumber(1-12)
     * @param day
     * @param year
     */
    public CalendarDate(int monthNumber, int day, int year) {
        this.month = new Month(monthNumber);
        this.day = day;
        this.year = year;
    }

    //the getters
    /**
     * return Month obj
     * @return Month instance
     */
    public Month getMonth() {
        return month;
    }

    /**
     * return day
     * @return day
     */
    public int getDay() {
        return day;
    }

    /**
     * return tear
     * @return 年
     */
    public int getYear() {
        return year;
    }

    //compare methods

    /**
     * Current date is before or not
     * @param other other date
     * @return return true if the date comes before
     */
    public boolean comesBefore(CalendarDate other) {
    	
        if (this.year != other.year) return this.year < other.year;
        
        if (this.month.getMonthNumber() != other.month.getMonthNumber()) {
            return this.month.getMonthNumber() < other.month.getMonthNumber();
        }
        
        return this.day < other.day;
    }

    /**
     * @param other
     * @return true if the date comes after
     */
    public boolean comesAfter(CalendarDate other) {
        return !this.comesBefore(other) && !this.equals(other);
    }


    /**
     * push day advance
     * <= 9999
     */
    public void advanceOneDay() {
        boolean isLeap = this.isLeapYear();
        int lastDay = this.month.lastDay(isLeap);

        if (this.day < lastDay) {
            this.day++;
        } else {
            this.day = 1;
            int monthNum = this.month.getMonthNumber();
            if (monthNum < 12) {
                this.month = new Month(monthNum + 1);
            } else {
            	//next year
                this.month = new Month(1);
                this.year++;
            }
        }
    }

    /**
     * return the day of the year
     * @return which day of the year（1-366）
     */
    public int dayOfYear() {
        int sum = 0;
        boolean isLeap = this.isLeapYear();

        // add the previous month
        for (int m = 1; m < this.month.getMonthNumber(); m++) {
            sum += new Month(m).lastDay(isLeap);
        }

        // add this month
        sum += this.day;

        return sum;
    }

    /**
     * is leap year or not
     * @return true if it is
     */
    public boolean isLeapYear() {
        if (year % 400 == 0) return true;
        if (year % 100 == 0) return false;
        
        return year % 4 == 0;
    }

    /**
     * return format such as "February 22, 2024"
     * @return date string
     */
    @Override
    public String toString() {
        return month.toString() + " " + day + ", " + year;
    }

    /**
     * compare two date factor
     * @param other 
     * @return true if equals
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || !(other instanceof CalendarDate)) return false;
        CalendarDate that = (CalendarDate) other;
        return this.month.getMonthNumber() == that.month.getMonthNumber()
            && this.day == that.day
            && this.year == that.year;
    }

  

	
	
}
