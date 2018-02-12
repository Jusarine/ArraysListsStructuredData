package Week2.Task3;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by Tania on 16.07.2017.
 */
public class GladLib {
    private ArrayList<String> adjectiveList;
    private ArrayList<String> nounList;
    private ArrayList<String> colorList;
    private ArrayList<String> countryList;
    private ArrayList<String> nameList;
    private ArrayList<String> animalList;
    private ArrayList<String> timeList;
    private ArrayList<String> verbList;
    private ArrayList<String> fruitList;
    private ArrayList<String> usedWordsList;

    private Random myRandom;

   // private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "D:\\Programming\\Java\\Coursera\\ArraysListsStructuredData\\src\\Week2\\Task3\\data\\";

    public GladLib(){
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
    }

    public GladLib(String source){
        initializeFromSource(source);
        myRandom = new Random();
    }

    private void initializeFromSource(String source) {
        adjectiveList= readIt(source+"adjective.txt");
        nounList = readIt(source+"noun.txt");
        colorList = readIt(source+"color.txt");
        countryList = readIt(source+"country.txt");
        nameList = readIt(source+"name.txt");
        animalList = readIt(source+"animal.txt");
        timeList = readIt(source+"timeframe.txt");
        verbList = readIt(source+"verb.txt");
        fruitList = readIt(source+"fruit.txt");
        usedWordsList = new ArrayList<>();
    }

    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }

    private String getSubstitute(String label) {
        if (label.equals("country")) {
            return randomFrom(countryList);
        }
        if (label.equals("color")){
            return randomFrom(colorList);
        }
        if (label.equals("noun")){
            return randomFrom(nounList);
        }
        if (label.equals("name")){
            return randomFrom(nameList);
        }
        if (label.equals("adjective")){
            return randomFrom(adjectiveList);
        }
        if (label.equals("animal")){
            return randomFrom(animalList);
        }
        if (label.equals("timeframe")){
            return randomFrom(timeList);
        }
        if (label.equals("number")){
            return ""+myRandom.nextInt(50)+5;
        }
        if (label.equals("verb")){
            return randomFrom(verbList);
        }
        if (label.equals("fruit")){
            return randomFrom(fruitList);
        }
        return "**UNKNOWN**";
    }

    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String sub = getSubstitute(w.substring(first+1,last));
        while(true){
            if(usedWordsList.contains(sub)){
                sub = getSubstitute(w.substring(first+1,last));
            }
            else{
                usedWordsList.add(sub);
                break;
            }
        }
        return prefix+sub+suffix;
    }

    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
        System.out.println("\n");
    }

    private String fromTemplate(String source){
        String story = "";
        try {
            if (source.startsWith("http")) {
//                URL url = new URL("source");
//                InputStream is = url.openStream();
//                BufferedReader reader = new BufferedReader (new InputStreamReader(is));
//                String line;
//                while((line=reader.readLine())!=null){
//                    story=story+processWord(line)+" ";
//                }

            } else {
                File file = new File(source);
                Scanner sc = new Scanner(file);
                while(sc.hasNext()){
                    story=story+processWord(sc.next())+" ";
                }

            }
        }
        catch(IOException e){
            e.printStackTrace();
        }


        return story;
    }

    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        try {
            if (source.startsWith("http")) {
//                URL url = new URL("source");
//                InputStream is = url.openStream();
//                BufferedReader reader = new BufferedReader (new InputStreamReader(is));
//                String line;
//                while((line=reader.readLine())!=null){
//                    list.add(line);
//                }

            } else {
                File file = new File(source);
                Scanner sc = new Scanner(file);
                String line;
                while(sc.hasNext()){
                    line=sc.nextLine();
                    list.add(line);
                }
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args) {
        GladLib gl = new GladLib();
        String story = gl.fromTemplate("D:\\Programming\\Java\\Coursera\\ArraysListsStructuredData\\src\\Week2\\Task3\\data\\madtemplate2.txt");
        gl.printOut(story, 60);
        System.out.println("Number of words that were replaced: " + gl.usedWordsList.size());

    }
}

