package Week2.Task1;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * Created by Tania on 16.07.2017.
 */
public class WordFrequencies
{
    private static ArrayList<String> myWords; //unique
    private static ArrayList<Integer> myFreqs;

    public WordFrequencies() {
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }

    public static void main(String[] args) {
        WordFrequencies wf = new WordFrequencies();
        wf.findUnique();
        for(int i=0; i<myWords.size(); i++){
            System.out.println(myFreqs.get(i) + " " + myWords.get(i));
        }
        System.out.println("Number of unique words: " + myWords.size());
        int index = wf.findMax();
        System.out.println("The word that occurs most often and its count are: " + myWords.get(index) + " " + myFreqs.get(index));

    }

    public void findUnique(){
        myWords.clear();
        myFreqs.clear();

        File file = new File("D:\\Programming\\Java\\Coursera\\ArraysListsStructuredData\\src\\Week2\\Task1\\1.txt");

        try {
            Scanner sc = new Scanner(file);
            String s;
            while(sc.hasNext()){
                s=sc.next().toLowerCase();
                int index = myWords.indexOf(s);
                if (index == -1){
                    myWords.add(s);
                    myFreqs.add(1);
                }
                else {
                    int freq = myFreqs.get(index);
                    myFreqs.set(index,freq+1);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public int findMax(){
        int max = myFreqs.get(0);
        int maxIndex = 0;
        for(int k=0; k < myFreqs.size(); k++){
            if (myFreqs.get(k) > max){
                max = myFreqs.get(k);
                maxIndex = k;
            }
        }
        return maxIndex;
    }
}
