package Week3;
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         records = new ArrayList<>();
     }
        
     public void readFile(String filename) {
         File file = new File(filename);
         try {
             Scanner sc = new Scanner(file);
             while(sc.hasNext()){
                 String line = sc.nextLine();
                 records.add( WebLogParser.parseEntry(line));

             }
         } catch (FileNotFoundException e) {
             e.printStackTrace();
         }

     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }

     public void countUniqueIPs(){
         ArrayList<String> unique = new ArrayList<>();
         for(LogEntry le: records){
             if(!unique.contains(le.getIpAddress())){
                 unique.add(le.getIpAddress());
             }
         }
         System.out.println("Number of unique IP address: " + String.valueOf(unique));
     }

     public void  printAllHigherThanNum(int num){
         ArrayList<Integer> unique = new ArrayList<>();
         for(LogEntry le: records){
             if(!unique.contains(le.getStatusCode()) && le.getStatusCode()>num){
                 unique.add(le.getStatusCode());
             }
         }
         System.out.println("Specified status code: " + String.valueOf(unique));
     }

     public void uniqueIPVisitsOnDay(String someday){
         ArrayList<String> unique = new ArrayList<>();
         for(LogEntry le: records){
             Date d = le.getAccessTime();
             SimpleDateFormat format = new SimpleDateFormat("MMM dd", Locale.US);
             String s = format.format(d);
             if(s.equals(someday) && !unique.contains(le.getIpAddress())) unique.add(le.getIpAddress());
         }
         System.out.println("Number of unique IP address which have specified date: " + unique.size());
     }

     public void countUniqueIPsInRange(int low, int high){
         ArrayList<String> unique = new ArrayList<>();
         for(LogEntry le: records){
             if(!unique.contains(le.getIpAddress()) && le.getStatusCode()>=low && le.getStatusCode()<=high){
                 unique.add(le.getIpAddress());
             }
         }
         System.out.println("Number of unique IP address which have specified status code: " + unique.size());
     }

     public HashMap<String, Integer> countVisitsPerIP(){
         HashMap<String, Integer> counts = new HashMap<>();
         for(LogEntry le: records){
             if(!counts.containsKey(le.getIpAddress())){
                 counts.put(le.getIpAddress(), 1);
             }
             else {
                 counts.put(le.getIpAddress(), counts.get(le.getIpAddress())+1);
             }
         }
         return counts;
     }

     public int mostNumberVisitsByIP(HashMap<String, Integer> iPsCounts){
         int max=0;
         for(String s: iPsCounts.keySet()){
             int temp = iPsCounts.get(s);
             if(max<temp) max=temp;
         }
         return max;
     }

     public ArrayList<String> iPsMostVisits(HashMap<String, Integer> iPsCounts){
         int max = mostNumberVisitsByIP(iPsCounts);
         ArrayList<String> list = new ArrayList<>();
         for(String s: iPsCounts.keySet()){
             int temp = iPsCounts.get(s);
             if(max==temp && !list.contains(s)) list.add(s);
         }
         return  list;
     }

     public HashMap<String, ArrayList<String>> iPsForDays(){
         HashMap<String, ArrayList<String>> map = new HashMap<>();
         for(LogEntry le: records){
             SimpleDateFormat format = new SimpleDateFormat("MMM dd", Locale.US);
             String s = format.format(le.getAccessTime());
             if(map.containsKey(s)){
                 ArrayList<String> list = map.get(s);
                 list.add(le.getIpAddress());
                 map.put(s, list);
             }
             else {
                 ArrayList <String> list = new ArrayList<>();
                 list.add(le.getIpAddress());
                 map.put(s, list);
             }
         }
         return map;
     }

    public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> days){
         int max = 0;
         String maxStr = null;
        for(String s: days.keySet()){
            int temp = days.get(s).size();
            if(max<temp){
                max=temp;
                maxStr=s;
            }
        }
        return maxStr;
    }

    public LinkedHashSet<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> days, String day){
        LinkedHashSet <String> set = new LinkedHashSet<>();
        for(String s: days.keySet()){
            if(s.equals(day)){
                set = new LinkedHashSet<>(days.get(s));
            }
        }
        return set;
    }
     
}
