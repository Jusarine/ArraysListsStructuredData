package Week3;
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    private static void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }

    public static void main(String[] args) {
       //testLogAnalyze();
       testUniqueIP();
    }
    private static void testLogAnalyze(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("D:\\Programming\\Java\\Coursera\\ArraysListsStructuredData\\src\\Week3\\Week3File.txt");
        la.printAll();
    }

    private static void testUniqueIP(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("D:\\Programming\\Java\\Coursera\\ArraysListsStructuredData\\src\\Week3\\Week3File.txt");
        la.countUniqueIPs();
        la.printAllHigherThanNum(400);
        la.uniqueIPVisitsOnDay("Mar 24");
        la.countUniqueIPsInRange(200, 299);
        la.countVisitsPerIP();
        HashMap<String, Integer> iPsCounts = la.countVisitsPerIP();
        System.out.println("Counts of IP address: " + String.valueOf(iPsCounts));
        System.out.println("Most visits by IP: " + la.mostNumberVisitsByIP(iPsCounts));
        System.out.println("IPs of most visits: " + String.valueOf(la.iPsMostVisits(iPsCounts)));
        HashMap<String, ArrayList<String>> days = la.iPsForDays();
        System.out.println("IPs for days: " + String.valueOf(days));
        System.out.println("Day with most IP visits: " + la.dayWithMostIPVisits(days));
        System.out.println("IPs with most visits on day: "+ la.iPsWithMostVisitsOnDay(days, "Sep 30"));
    }
}
