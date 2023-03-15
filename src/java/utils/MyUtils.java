/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.sql.*;

/**
 *
 * @author ASUS
 */
public class MyUtils {
    private static Scanner sc = new Scanner(System.in);
    private static final String IGNORE_CASE_PATTERN = "(?i)";

    /**
     * prevent class MyTool from initialize
     */
    public MyUtils() {
    }
    
    public static int getInteger(String value){
        int k = 0;
        
        try {
            k = value == null || value.trim().isEmpty() ? 0 : Integer.parseInt(value);
            return k;
        } catch(Exception e){
            return k;
        }
    }
    /**
     * Input the integer that constraint in an interval [min, max]
     *
     * @param min
     * @param max
     * @param beginMessage
     * @param Warning
     * @return
     */
    public static boolean askAgain(String message, String y, String n) {
        String tmp = inputStringWithRegex("y|n", message, "Invalid choice!!! Type 'y' for yes or 'n' for no\nTry again: ");
        if (tmp.matches("y")) {
            System.out.println(y);
            return true;
        } else {
            System.out.println(n);
            return false;
        }
    }

    public static boolean isDouble(String tmp) {
        try {
            Double.parseDouble(tmp);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isInt(String tmp) {
        try {
            Integer.parseInt(tmp);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean valideString(String input, String regex, String warning, boolean ignoreCase) {
        if (ignoreCase) {
            regex = IGNORE_CASE_PATTERN + regex;
        }
        if (input.matches(regex)) {
            return true;
        }
        return false;
    }

    public static boolean valideDouble(double input, double min, double max, String warning) {
        try {
            if (input <= max && input >= min) {
                return true;
            }
        } catch (Exception e) {
            if (warning != null) {
                System.out.print("Not a double!!!\nTry again: ");
            }

            return false;
        }
        if (warning != null) {
            System.out.print(warning);
        }
        return false;
    }

    public static boolean valideInteger(int input, int min, int max, String warning) {
        try {
            if (input <= max && input >= min) {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        if (warning != null) {
            System.out.println(warning);
        }
        return false;
    }

    public static int inputInteger(int min, int max, String beginMessage, String Warning) {
        if (beginMessage != null) {
            System.out.print(beginMessage);
        }
        //temporary variable to store output of function
        int temp;
        //catch case that can't parse temp to integer and make user try again
        while (true) {
            try {
                temp = Integer.parseInt(sc.nextLine());
                //Make temp in an interval[min, max]
                if (temp >= min && temp <= max) {
                    break;
                }
                if (Warning != null) {
                    System.out.print(Warning);
                }
            } catch (NumberFormatException e) {
                System.out.print("Not an integer!!! Try again: ");
            }

        }
        return temp;
    }

    public static int inputDouble(double min, double max, String beginMessage, String Warning) {
        if (beginMessage != null) {
            System.out.print(beginMessage);
        }
        //temporary variable to store output of function
        int temp;
        //catch case that can't parse temp to integer and make user try again
        while (true) {
            try {
                temp = Integer.parseInt(sc.nextLine());
                //Make temp in an interval[min, max]
                if (temp >= min && temp <= max) {
                    break;
                }
                if (Warning != null) {
                    System.out.print(Warning);
                }
            } catch (NumberFormatException e) {
                System.out.print("Not a double!!! Try again: ");
            }

        }
        return temp;
    }

    /**
     * input string that constraint is a regex, make user try again when input
     * does not matches regex
     *
     * @param regex
     * @param beginMessage
     * @param warningMessage
     * @return
     */
    public static String inputStringWithRegex(String regex, String beginMessage, String warningMessage) {
        if (beginMessage != null) {
            System.out.print(beginMessage);
        }

        while (true) {
            String temp = sc.nextLine();//output of function
            if (temp.matches(regex)) {
                return temp;
            }
            if (warningMessage != null) {
                System.out.print(warningMessage);//warning when temp doesn't matches regex
            }
        }
    }

    /**
     * Read data in file by line and construct to a record of output list string
     *
     * @param fileName
     * @return
     */
    public static List<String> readFileInLine(String fileName) {
        //temporary list to store information from file
        List<String> tmpList = new ArrayList();
        try {
            File file = new File(fileName);
            if (!file.exists()) {
                return null;
            }

            FileReader fr = new FileReader(file);//catch FileNotFoundException here
            BufferedReader bf = new BufferedReader(fr);
            //temporary variable to store to List output
            String details;

            while ((details = bf.readLine()) != null) {//catch IOException here
                tmpList.add(details);
                details = "";
            }
            bf.close();
            fr.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
        return tmpList;
    }

    /**
     * Write list of generic T to file
     *
     * @param <T>
     * @param fileName
     * @param list
     * @return
     */
    public static <T> boolean writeDataToFile(String fileName, List<T> list) {
        try {
            File file = new File(fileName);
            if (!file.exists()) {
                return false;
            }
            FileWriter fw = new FileWriter(file);//catch IOException here
            PrintWriter pw = new PrintWriter(fw);
            for (T t : list) {

                ///////////////////////////////////////////////////
            }
            pw.close();
            fw.close();
            return true;
        } catch (IOException e) {
            System.out.println(e);
            return false;
        }
    }

    /**
     * Read data by line and add it to list of generic T
     *
     * @param <T>
     * @param fileName
     * @param list
     * @param a
     */
    public static <T> void readDataFromFile(String fileName, List<T> list, T a) {
        try {
            File file = new File(fileName);
            if (!file.exists()) {
                return;
            }

            FileReader fr = new FileReader(file);//catch FileNotFoundException here
            BufferedReader bf = new BufferedReader(fr);
            //temporary variable to store data from file
            String details;
            while ((details = bf.readLine()) != null) {//catch IOException here

                details = "";
            }
            bf.close();
            fr.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * Convert String to Date with pattern parameter
     *
     * @param date
     * @param pattern
     * @return
     */
    public static java.util.Date convertToDate(String date, String pattern) {
        if(date == null){
            return null;
        }
        java.util.Date dateTemp = null;
        SimpleDateFormat formater = new SimpleDateFormat(pattern);
        try {
            dateTemp = formater.parse(date);
        } catch (ParseException ex) {
            return null;
        }
        return dateTemp;
    }

    /**
     * Convert object Date to String with pattern parameter
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String convertDateToString(java.util.Date date, String pattern) {
        if (date == null) {
            return null;
        }
        String output = "";
        SimpleDateFormat formater = new SimpleDateFormat(pattern);
        output = formater.format(date);
        return output;
    }

    /**
     * Input Date instance with some constraint, interval after Date to before
     * Date and valid pattern with parameter
     *
     * @param before
     * @param after
     * @param Pattern
     * @param message
     * @param warning
     * @return
     */
    public static java.util.Date inputDate(java.util.Date before, java.util.Date after, String Pattern, String message, String warning) {
        SimpleDateFormat formater = new SimpleDateFormat(Pattern);
        java.util.Date date = new java.util.Date();
        if (message != null) {
            System.out.print(message);
        }

        while (true) {
            String date2 = sc.nextLine();
            formater.setLenient(false);
            try {
                date = formater.parse(date2);
                if (checkDate(date, after, before)) {
                    break;
                }
                System.out.print("Invalid, be careful of the date's domain!!! Try again: ");
            } catch (ParseException ex) {
                if (warning != null) {
                    System.out.print(warning);
                }
            }
        }
        return date;
    }

    /**
     * Check date in the interval or not, from after Date to before Date
     *
     * @param check
     * @param after
     * @param before
     * @return
     */
    public static boolean checkDate(java.util.Date check, java.util.Date after, java.util.Date before) {
        if (after == null && before != null) {
            return check.before(before) && check != null;
        } else if (before == null && after != null) {
            return check.after(after) && check != null;
        } else if (after == null && before == null) {
            return true;
        }
        return check.before(before) && check.after(after);

    }

    /**
     * Compare 2 parameters with pattern is also parameter
     *
     * @param date1
     * @param date2
     * @param pattern
     * @return
     */
    public static boolean compareDate(java.util.Date date1, java.util.Date date2, String pattern) {
        SimpleDateFormat formater = new SimpleDateFormat(pattern);
        String parseDate1 = formater.format(date1);
        String parseDate2 = formater.format(date2);
        return parseDate1.equalsIgnoreCase(parseDate2);
    }
    
    public static java.sql.Date utilDateToSqlDate(java.util.Date utilDate){
        if(utilDate == null){
            return null;
        }
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        return sqlDate;
    }
    
    public static java.util.Date sqlDateToUtilDate(java.sql.Date sqlDate){
        if(sqlDate == null){
            return null;
        }
        java.util.Date utilDate = new java.util.Date(sqlDate.getTime());
        return utilDate;
    }
    
    public static void main(String[] args) {
        java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
        
        System.out.println(convertDateToString(sqlDateToUtilDate(date), "yyyy/MM/dd"));
    }
}


    