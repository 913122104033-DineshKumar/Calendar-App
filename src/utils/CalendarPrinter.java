package utils;

public class CalendarPrinter {

    public CalendarPrinter () {}

    private void printDesign () {
        for (int line = 1; line <= 33; line++) {
            System.out.print("- ");
        }
        System.out.println();
    }

    public void printDays (int startDay, int monthId, int year) {
        String monthName = DayHelper.months[monthId - 1];
        int noOfDays = DayHelper.noOfDaysInMonth[monthId - 1];
        System.out.println("Calender Month:" + monthName + " " + year);
        printDesign();
        System.out.print("| ");
        for (String day : DayHelper.days) {
            System.out.printf("%-8s ", day);
        }
        System.out.println("|");
        printDesign();
        int currentDay = 1;
        int day = startDay;
        System.out.print("| ");
        for (int space = 0; space < day; space++) {
            System.out.printf("%-9s", "");
        }
        while (currentDay <= noOfDays) {
            System.out.printf("%-8d ", currentDay);
            if ((day + 1) % 7 == 0) {
                System.out.println("|");
                if (currentDay != noOfDays) {
                    System.out.print("| ");
                }
            }
            day = (day + 1) % 7;
            currentDay++;
        }
        if (day != 0) {
            for (int i = day; i < 7; i++) {
                System.out.printf("%-9s", "");
            }
            System.out.println("|");
        }
        printDesign();
    }
}
