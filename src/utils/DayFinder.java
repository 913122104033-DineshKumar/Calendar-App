package utils;

class DayHelper {
    public final static String[] days = {
            "Sun", "Mon", "Tues", "Wed", "Thurs", "Fri", "Sat"
    };
    public final static String[] months = {
            "January", "February", "March", "April", "May", "June", "July",
            "August", "September", "October", "November", "December"
    };
    public final static int[] noOfDaysInMonth = {
            31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
    };

    public DayHelper() {}

    public String[] parseDate (String date) {
        return date.split("[-\\s]+");
    }

    public int handleDay (int day) {
        return (day - 1 + 7) % 7;
    }

    public int[] handleJanuaryFebruary (int month, int year) {
        if (month == 1 || month == 2) {
            month += 12;
            year -= 1;
        }
        return new int[]{ month, year };
    }

    public int calculateDay (int day, int month, int year) {
        return (day + (13 * (month + 1) / 5) + year + (year / 4) - (year / 100) + (year / 400)) % 7;
    }

    public void handleParsedDateException (String[] date, int length) {
        if (date.length != length) {
            throw new ArrayIndexOutOfBoundsException("Date of length " + length + " is needed");
        }
    }
}

public class DayFinder {
    int day;
    int month;
    int year;
    DayHelper dayHelper;

    public DayFinder () { this.dayHelper = new DayHelper(); }

    public void setUsersDate (String date) {
        String[] formatedDate = dayHelper.parseDate(date);
        try {
            dayHelper.handleParsedDateException(formatedDate, 2);
            setDay(getDayOfMonth(date));
            setMonth(Integer.parseInt(formatedDate[0]));
            setYear(Integer.parseInt(formatedDate[1]));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int getDayOfMonth (String date) {
        String[] formatedDate = dayHelper.parseDate(date);
        dayHelper.handleParsedDateException(formatedDate, 2);
        int month = Integer.parseInt(formatedDate[0]);
        int year = Integer.parseInt(formatedDate[1]);
        int[] correctedMonthYear = dayHelper.handleJanuaryFebruary(month, year);
        month = correctedMonthYear[0];
        year = correctedMonthYear[1];
        return dayHelper.handleDay(dayHelper.calculateDay(1, month, year));
    }

    public int getDayOfDate (String date) {
        String[] formatedDate = dayHelper.parseDate(date);
        dayHelper.handleParsedDateException(formatedDate, 3);
        int currentDate = Integer.parseInt(formatedDate[0]);
        int month = Integer.parseInt(formatedDate[1]);
        int year = Integer.parseInt(formatedDate[2]);
        int[] correctedMonthYear = dayHelper.handleJanuaryFebruary(month, year);
        month = correctedMonthYear[0];
        year = correctedMonthYear[1];
        return dayHelper.handleDay(dayHelper.calculateDay(currentDate, month, year));
    }

    public int getDay() { return day; }

    public void setDay(int day) { this.day = day; }

    public int getMonth() {
        return month;
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
