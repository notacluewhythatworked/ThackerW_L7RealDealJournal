package models;

import controllers.Controller;
import lib.ConsoleIO;
import lib.FileIO;
//Similar to ReviewEntries, the only difference is that this class allows for the search results to be narrowed down via date.

import java.io.File;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DateRange {
    private static String foundFileContents;
    static String beginningDate;
    static String endDate;

    public static void search() {
        beginningDate = ConsoleIO.promptForString("Beginning Date (dates HAVE to be separated by a dash(-) and in the order of MONTH, DAY, YEAR.) [ex. 2-15-2019]: ", false);
        endDate = ConsoleIO.promptForString("End Date (optional): ", true);

        String filePath = beginningDate + ".txt";
        File tempFile = new File(filePath);
        boolean exists = tempFile.exists();


        //Checks for files with only 1 date parameter
        if (exists) {
            FileIO.readTextFromFile(filePath);
            System.out.println("We found an entry for that date! Here's what you wrote: " + foundFileContents);
        }
        //Checks for files between two date parameters
        else if (Calendar.getInstance().after(beginningDate) && Calendar.getInstance().before(endDate)){
            getResultsBetweenDates(beginningDate, endDate); //TODO what do I do here

        }
        //When the search fails, an error is returned
        else {
            System.out.println("We couldn't find any files for those dates. Please try again.");
            search();
        }

    }

    public static void search2(String date) {
        foundFileContents = FileIO.readTextFromFile(date);
        System.out.println("We found an entry for that date! Here's what you wrote: " + foundFileContents);
        FileIO.appendTextToFile(date, ConsoleIO.promptForString("Enter what you'd like to add: ", false));
        System.out.println();
    }

    private static List<LocalDate> getResultsBetweenDates(LocalDate beginningDate, LocalDate endDate){
         return beginningDate.datesUntil(endDate).collect(Collectors.toList());
    }
}
