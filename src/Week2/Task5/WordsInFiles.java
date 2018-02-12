package Week2.Task5;

import Week2.Task1.WordFrequencies;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by Tania on 17.07.2017.
 */
public class WordsInFiles {
    private static HashMap<String, ArrayList<String>> map;

    public WordsInFiles(){
        map = new HashMap<>();
    }

    public static void main(String[] args) {
        WordsInFiles wif = new WordsInFiles();
        wif.buildWordFileMap();
        System.out.println(wif.maxNumber());
        System.out.println( String.valueOf(wif.wordsInNumFiles(3).size()));
        wif.printFilesIn("dogs");

    }
    private static void addWordsFromFile(File file){
        try {
            Scanner sc = new Scanner(file);
            String s;
            while(sc.hasNext()){
                ArrayList<String> list = new ArrayList<>();
                s=sc.next();
                if(map.containsKey(s) && !map.get(s).contains(file.getName())){
                    (map.get(s)).add(file.getName());

                }
                else if(!map.containsKey(s)){
                    list.add(file.getName());
                    map.put(s, list);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void buildWordFileMap(){
        map.clear();
        addWordsFromFile(new File("D:\\Programming\\Java\\Coursera\\ArraysListsStructuredData\\src\\Week2\\Task5\\1.txt"));
        addWordsFromFile(new File("D:\\Programming\\Java\\Coursera\\ArraysListsStructuredData\\src\\Week2\\Task5\\2.txt"));
        addWordsFromFile(new File("D:\\Programming\\Java\\Coursera\\ArraysListsStructuredData\\src\\Week2\\Task5\\3.txt"));
        addWordsFromFile(new File("D:\\Programming\\Java\\Coursera\\ArraysListsStructuredData\\src\\Week2\\Task5\\4.txt"));
        System.out.println(String.valueOf(map));
    }

    private int maxNumber(){
        int max=0;
        Iterator<Map.Entry<String, ArrayList<String>>> iterator = map.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry<String, ArrayList<String>> pair = iterator.next();
            ArrayList<String> list = pair.getValue();
            int max1=list.size();
            if(max<max1) max=max1;
        }
        return max;

    }

    private ArrayList<String> wordsInNumFiles(int number){
        Iterator<Map.Entry<String, ArrayList<String>>> iterator = map.entrySet().iterator();
        ArrayList<String> words = new ArrayList<>();
        while(iterator.hasNext()){
            Map.Entry<String, ArrayList<String>> pair = iterator.next();
            ArrayList<String> list = pair.getValue();
            String word = pair.getKey();
            if(list.size()==number) words.add(word);

        }
        return words;

    }

    private void printFilesIn(String word){
        System.out.println(String.valueOf(map.get(word)));

    }


}
