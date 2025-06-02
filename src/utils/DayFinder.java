package utils;

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

    public boolean isLeapYear (int year) {
        return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
    }
}

public class DayFinder {
    int day;
    int month;
    int year;
    DayHelper dayHelper;

    public DayFinder () { this.dayHelper = new DayHelper(); }

    public void setUsersDate (String date) {
        String[] formattedDate = dayHelper.parseDate(date);
        try {
            if (formattedDate.length < 2 || formattedDate.length > 3) {
                throw new IllegalArgumentException("Invalid date format. Expected 2 or 3 parts but got " + formattedDate.length);
            }
            setDay(getDay(date));
            if (formattedDate.length == 3) {
                setMonth(Integer.parseInt(formattedDate[1]));
                setYear(Integer.parseInt(formattedDate[2]));
            } else {
                setMonth(Integer.parseInt(formattedDate[0]));
                setYear(Integer.parseInt(formattedDate[1]));
            }
            if (dayHelper.isLeapYear(getYear())) {
                dayHelper.noOfDaysInMonth[1] += 1;
            } else {
                dayHelper.noOfDaysInMonth[1] = 28;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int getDay (String date) {
        try {
            String[] formattedDate = dayHelper.parseDate(date);
            if (formattedDate.length < 2 || formattedDate.length > 3) {
                throw new IllegalArgumentException("Invalid date format. Expected 2 or 3 parts but got " + formattedDate.length);
            }
            int currentDate = 1;
            int month, year;
            if (formattedDate.length == 3) {
                currentDate = Integer.parseInt(formattedDate[0]);
                month = Integer.parseInt(formattedDate[1]);
                year = Integer.parseInt(formattedDate[2]);
            } else {
                month = Integer.parseInt(formattedDate[0]);
                year = Integer.parseInt(formattedDate[1]);
            }
            int[] correctedMonthYear = dayHelper.handleJanuaryFebruary(month, year);
            month = correctedMonthYear[0];
            year = correctedMonthYear[1];
            return dayHelper.handleDay(dayHelper.calculateDay(currentDate, month, year));
        } catch (IllegalArgumentException e) {
            System.out.println("Data Parsing Error: " + e.getMessage());
            return -1;
        }
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
