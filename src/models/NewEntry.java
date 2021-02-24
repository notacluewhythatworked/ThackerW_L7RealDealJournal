package models;

import controllers.Controller;
import lib.ConsoleIO;
import lib.FileIO;

import java.io.File;
import java.util.ArrayList;

public class NewEntry {

    public void currentDateEntry() {
        File tempFile = new File(Controller.filePath());
        boolean exists = tempFile.exists();

        if (exists) {
            modifiedDateEntry();
        } else {
            String contentBody = ConsoleIO.promptForString("\nEnter your journal entry: ", false);
            FileIO.writeTextToFile(Controller.filePath(), contentBody);

            System.out.println("\nWonderful! We've saved your journal entry.\n");
        }
    }

    private void modifiedDateEntry() {
        System.out.println("\nA file for today already exists. You can either modify this entry or use a different date. What would you like to do?");
        int userSelection = ConsoleIO.promptForMenuSelection(new String[]{"Modify today's entry", "Choose a different date"}, false);
        switch(userSelection){
            case 1:
                String currentDate = Controller.getLocalDate().toString();
                DateRange.search2(currentDate + ".txt");
                break;
            case 2:
                String backdated = ConsoleIO.promptForString("Please enter the desired date (YYYY-MM-DD) [ex. 2001-08-23]: ", false);
                backdatedEntry(backdated);
                break;
        }
    }

    private void backdatedEntry(String date){
        String contentBody = ConsoleIO.promptForString("\nEnter your journal entry: ", false);
        FileIO.appendTextToFile(date + ".txt", contentBody);

        System.out.println("\nWonderful! We've saved your journal entry.\n");
    }
}
