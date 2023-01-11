package com.techelevator.view;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.File;
import java.io.PrintWriter;

public class Log {

    //setting and formatting local date and time:
    static LocalDateTime ldt = LocalDateTime.now();
    static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a");
    static String formattedDateTime = ldt.format(dtf);

   public static void log(String action, double money1, double money2){
       //Create the log.txt file:
       String logFile = "./log.txt";

    try(PrintWriter writeLog = new PrintWriter(new FileOutputStream(logFile, true))) {
      writeLog.printf("\n"+formattedDateTime + " " + action + " $ %.2f" + " $ %.2f", money1 , money2);


    } catch (FileNotFoundException e) {
        System.err.println("Cannot open file for writing");
    }
   }
}

