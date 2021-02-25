package models;

import controllers.Controller;
import lib.ConsoleIO;
import lib.FileIO;
//Similar to ReviewEntries, the only difference is that this class allows for the search results to be narrowed down via date.

import java.io.File;

import java.io.FilenameFilter;
import java.time.LocalDate;


public class DateRange {
    private static String foundFileContents;
    private static LocalDate dateEnd = Controller.getLocalDate();

    public static void search() {
        String beginningDate = ConsoleIO.promptForString("Beginning Date (dates HAVE to be separated by a dash(-) and in the order of YEAR, MONTH, DAY.) [ex. 2019-02-19]: ", false);
        String endDate = ConsoleIO.promptForString("End Date (optional): ", true);
        if(endDate == null){
            dateEnd = Controller.getLocalDate();
        }

        String filePath = beginningDate + ".txt";
        File tempFile = new File(filePath);
        boolean exists = tempFile.exists();
        LocalDate dateStart = LocalDate.parse(beginningDate);

        //Checks for files with only 1 date parameter
        if (exists) {
            FileIO.readTextFromFile(filePath);
            System.out.println("We found an entry for that date! Here's what you wrote: " + foundFileContents);
        }

        //Checks for files between two date parameters
        else if (dateStart.isBefore(dateEnd) && dateEnd.isAfter(dateStart)){
//            for(LocalDate date = dateStart; date.isBefore(dateEnd); date = date.plusDays(1)) {
                String[] pathnames;
                File f = new File("C://Users//wthacker//OneDrive - Neumont College of Computer Science//CSC150//ThackerW_L7RealDealJournal");

                FilenameFilter filter = new FilenameFilter() {
                    @Override
                    public boolean accept(File f, String name) {
                        return name.endsWith(".txt");
                    }
                };

                pathnames = f.list(filter);
                for(String pathname : pathnames){
                    System.out.println(pathname);
                    System.out.println("Here's what that entry said: " + FileIO.readTextFromFile(pathname) + "\n");
                }
        }
        //When the search fails, an error is returned
        else {
            System.out.println("\nWe couldn't find any files for those dates. Please try again.\n");
            search();
        }
    }

    public static void search2(String date) {
        foundFileContents = FileIO.readTextFromFile(date);
        System.out.println("We found an entry for that date! Here's what you wrote: " + foundFileContents);
        FileIO.appendTextToFile(date, ConsoleIO.promptForString("Enter what you'd like to add: ", false));
        System.out.println();
    }
}
