package Week2.Task2;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Tania on 16.07.2017.
 */
public class CharactersInPlay {
    private static ArrayList<String> names;
    private static ArrayList<Integer> counts;

    public static void main(String[] args) {
        findAllCharacters();
        System.out.println(names);
        System.out.println(counts);
        charactersWithNumParts(2, 15);
    }

    CharactersInPlay(){
        names = new ArrayList<>();
        counts = new ArrayList<>();
    }

    public void update(String person){
        if(names.contains(person)){
            int idx = names.indexOf(person);
            counts.set(idx, counts.get(idx)+1);
        }
        else {
            names.add(person);
            counts.add(1);
        }
    }

    public static void findAllCharacters(){
        CharactersInPlay cp = new CharactersInPlay();
        File file = new File("D:\\Programming\\Java\\Coursera\\ArraysListsStructuredData\\src\\Week2\\Task2\\Week2File2.txt");
        names.clear();
        counts.clear();
        try {
            Scanner sc = new Scanner(file);
            String s;
            while(sc.hasNext()){
                s=sc.nextLine().toLowerCase();
                if(s.contains(".")){
                   int idx=s.indexOf('.');
                   s=s.substring(0,idx);
                   cp.update(s);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void charactersWithNumParts(int count1, int count2){
        for(int i=0; i<names.size(); i++){
            if(counts.get(i)>=count1 && counts.get(i)<=count2) System.out.println(names.get(i) + " " + counts.get(i));
        }
    }
}
