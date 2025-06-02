package utils;

public class CalendarPrinter {
    DayHelper dayHelper;

    public CalendarPrinter () {
        this.dayHelper = new DayHelper();
    }

    private void printDesign () { // Designing the borders with lines
        System.out.println("- ".repeat(33));
    }

    public void printDays (int currentDate, int startDay, int monthId, int year) {
        String monthName = DayHelper.months[monthId - 1]; // Fetching the Month Name
        int noOfDays = dayHelper.noOfDaysInMonth[monthId - 1]; // Fetching the number of days in a month
        System.out.println("Calender Month:" + monthName + " " + year);
        printDesign();
        System.out.print("| "); // Start Border
        for (String day : DayHelper.days) { // Printing the Days sequentially
            System.out.printf("%-8s ", day);
        }
        System.out.println("|"); // End Border
        printDesign();
        int currentDay = 1;
        int day = startDay; // Copy of original day.
        System.out.print("| ");
        for (int space = 0; space < day; space++) { // Leaving the space until the currentDay reaches the day.
            System.out.printf("%-9s", "");
        }
        while (currentDay <= noOfDays) {
            if (currentDay == currentDate) {
                System.out.printf("%-8s ", "[" + currentDay + "]"); // Specifying the given date with [day].
            } else {
                System.out.printf("%-8d ", currentDay);
            }
            if ((day + 1) % 7 == 0) { // If the day is 0, then closing the current line and starting the next line.
                System.out.println("|"); // End Border
                if (currentDay != noOfDays) {
                    System.out.print("| "); // Start Border
                }
            }
            day = (day + 1) % 7;
            currentDay++;
        }
        if (day != 0) {
            for (int i = day; i < 7; i++) { // Ending the last line with extra spaces, if needed.
                System.out.printf("%-9s", "");
            }
            System.out.println("|");
        }
        printDesign();
    }
}
