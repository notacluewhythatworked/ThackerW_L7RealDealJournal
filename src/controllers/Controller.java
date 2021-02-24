package controllers;

import lib.ConsoleIO;
import models.DateRange;
import models.NewEntry;
import models.ReviewEntries;

import java.time.LocalDate;
import java.util.Calendar;

public class Controller {

    public static void run(){
        menu();
    }

    private static void menu(){
        int userSelection = ConsoleIO.promptForMenuSelection(new String[]{"New Journal Entry", "View journal entries", "View by date"},true);

        switch(userSelection){
            case 1:
                new NewEntry().currentDateEntry();
                run();
                break;
            case 2:
                ReviewEntries.overview();
                run();
                break;
            case 3:
                DateRange.search();
                Calendar.getInstance();
                run();
                break;
        }
    }

    public static LocalDate getLocalDate() {
        return LocalDate.now();
    }

    public static String filePath(){
        return Controller.getLocalDate() + ".txt";
    }

}
