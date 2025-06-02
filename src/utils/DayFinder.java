package utils;

import java.time.LocalDate;

class DayHelper {
    public final static String[] days = {
            "Sun", "Mon", "Tues", "Wed", "Thurs", "Fri", "Sat"
    };
    public final static String[] months = {
            "January", "February", "March", "April", "May", "June", "July",
            "August", "September", "October", "November", "December"
    };
    public int[] noOfDaysInMonth = {
            31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
    };

    public DayHelper() {}

    public String[] parseDate (String date) {
        return date.split("[-\\s]+");
    }

    public int handleDay (int day) { // Zeller formula considers the first day as Saturday, so handling with minus of the day.

        return (day - 1 + 7) % 7;
    }

    public int[] handleJanuaryFebruary (int month, int year) { // Handling according to Zeller formulae
        if (month == 1 || month == 2) {
            month += 12;
            year -= 1;
        }
        return new int[]{ month, year };
    }

    public int calculateDay (int day, int month, int year) { // Zeller formulae
        return (day + (13 * (month + 1) / 5) + year + (year / 4) - (year / 100) + (year / 400)) % 7;
    }

    public boolean isLeapYear (int year) { // Checking whether the year is leap year
        return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
    }
}

public class DayFinder {
    int date;
    int day;
    int month;
    int year;
    DayHelper dayHelper;

    public DayFinder () {
        LocalDate today = LocalDate.now();
        this.dayHelper = new DayHelper();
        this.date = today.getDayOfMonth();
        this.month = today.getMonthValue();
        this.year = today.getYear();
        this.day = getDay(this.month,this.year);
    }

    public void setUsersDate (String date) { // Sets the Date to the Day Finder class.
        String[] formattedDate = dayHelper.parseDate(date); // Formatting the date for segregation of Date, month, year.
        try {
            if (formattedDate.length < 2 || formattedDate.length > 3) { // For throwing erroring when the date format is incorrect.
                throw new IllegalArgumentException("Invalid date format. Expected 2 or 3 parts but got " + formattedDate.length);
            }
            if (formattedDate.length == 3) { // Setting custom date
                setDate(Integer.parseInt(formattedDate[0]));
                setMonth(Integer.parseInt(formattedDate[1]));
                setYear(Integer.parseInt(formattedDate[2]));
            } else { // Setting date as default 1
                setDate(1);
                setMonth(Integer.parseInt(formattedDate[0]));
                setYear(Integer.parseInt(formattedDate[1]));
            }
            if (dayHelper.isLeapYear(getYear())) { // Increasing days in february, if leap year.
                dayHelper.noOfDaysInMonth[1] += 1;
            } else {
                dayHelper.noOfDaysInMonth[1] = 28;
            }
            if (getMonth() < 1 || getMonth() > 12) { // Handling invalid Month
                throw new IllegalArgumentException("Invalid month mentioned. Expected between 1 and 12 but got " + getMonth());
            }
            if (getDate() < 1 || getDate() > dayHelper.noOfDaysInMonth[getMonth()]) { // Handling invalid Date
                throw new IllegalArgumentException("Invalid date mentioned. Expected between 1 and " + dayHelper.noOfDaysInMonth[getMonth()] +" but got " + getMonth());
            }
            setDay(getDay(getMonth(), getYear())); // Pre-calculating the day for the given date.
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        }
    }

    public int getDayOfDate () { // Gets the Day of a particular Date.
        int currentDate = getDate();
        int month = getMonth();
        int year = getYear();
        int[] correctedMonthYear = dayHelper.handleJanuaryFebruary(month, year);
        month = correctedMonthYear[0];
        year = correctedMonthYear[1];
        return dayHelper.handleDay(dayHelper.calculateDay(currentDate, month, year));
    }

    public int getDay (int month, int year) { // Method Overloading for getting Day with Calculations.
        int[] correctedMonthYear = dayHelper.handleJanuaryFebruary(month, year);
        month = correctedMonthYear[0];
        year = correctedMonthYear[1];
        return dayHelper.handleDay(dayHelper.calculateDay(1, month, year));
    }

    public int getDate () { return date; }

    public int getDay() { return day; }

    public void setDay(int day) { this.day = day; }

    public int getMonth() {
        return month;
    }

    public void setDate (int date) {
        this.date = date;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDayName (int day) {
        return DayHelper.days[day];
    }
}
