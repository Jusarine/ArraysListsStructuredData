package Week1.Task3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by Tania on 14.07.2017.
 */
public class WordLengths {

    public static void main(String[] args) {
        File file = new File("D:\\Programming\\Java\\Coursera\\ArraysListsStructuredData\\src\\Week1\\Task3\\1.txt");
        int counts[] = new int[31];
        countWordLengths(file, counts);
        for(int i=1; i<counts.length; i++){
            if(counts[i]!=0) System.out.println("Length: " + i + ", " + "count: " + counts[i]);
        }
        System.out.println(indexOfMax(counts));
    }

    private static void countWordLengths(File file, int[] counts) {
        try {
            Scanner scanner = new Scanner(file);
            int temp;
            while(scanner.hasNext()){
                String s = scanner.next();
                temp=s.length();

                if(Character.isAlphabetic(s.charAt(temp-1))){
                    counts[temp]++;
                }
                else counts[temp-1]++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static int indexOfMax(int values[]){
        int max=values[0];
        int idx=0;
        for(int i=0; i<values.length; i++){
            if(max<values[i]){
                max = values[i];
                idx=i;
            }
        }
        return idx;
    }

}
