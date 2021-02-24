package models;

import controllers.Controller;

import javax.naming.directory.SearchResult;
import java.io.File;
import java.io.FilenameFilter;

public class ReviewEntries {
//This class searches through all journal entries with no parameters, from newest to oldest. DateRange is used for date parameters.


    public static void overview(){
        directory();

    }



    private static void directory(){
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
        }

    }

}