import utils.CalendarPrinter;
import utils.DayFinder;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DayFinder dayFinder = new DayFinder();
        CalendarPrinter calendarPrinter = new CalendarPrinter();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while (running) {
            System.out.println("Enter the Option 1 for Add / Update the Date of a month");
            System.out.println("Enter the Option 2 for Day of a Particular Date");
            System.out.println("Enter the Option 3 for Displaying Current Month Calender");
            System.out.println("Enter the Option 4 for Displaying Previous Month Calender");
            System.out.println("Enter the Option 5 for Displaying Next Month Calender");
            System.out.println("Enter the Option 6 for exiting");
            int option = scanner.nextInt();
            scanner.nextLine();
            String date;
            int day;
            switch (option) {
                case 1:
                    System.out.println("Enter Date in the format following format: ");
                    System.out.println("MM-YYYY");
                    date = scanner.nextLine();
                    dayFinder.setUsersDate(date);
                    break;
                case 2:
                    System.out.println("Enter Date in the format following format: ");
                    System.out.println("DD-MM-YYYY");
                    date = scanner.nextLine();
                    System.out.println("The Starting Day is: " + dayFinder.getDayName(dayFinder.getDayOfDate(date)));
                    break;
                case 3:
                    System.out.println("Current Month Calendar: ");
                    calendarPrinter.printDays(dayFinder.getDay(), dayFinder.getMonth(), dayFinder.getYear());
                    break;
                case 4:
                    System.out.println("Previous Month Calendar: ");
                    day = dayFinder.getDayOfMonth(String.valueOf(dayFinder.getMonth() - 1) + "-" + String.valueOf(dayFinder.getYear()));
                    System.out.println(day);
                    calendarPrinter.printDays(day, dayFinder.getMonth() - 1, dayFinder.getYear());
                    break;
                case 5:
                    System.out.println("Next Month Calendar: ");
                    day = dayFinder.getDayOfMonth(String.valueOf(dayFinder.getMonth() + 1) + "-" + String.valueOf(dayFinder.getYear()));
                    calendarPrinter.printDays(day, dayFinder.getMonth() + 1, dayFinder.getYear());
                    break;
                case 6:
                    System.out.println("Exiting...");
                    running = false;
                    break;
                default:
                    throw new RuntimeException("Wrong input");
            }
        }
    }
}