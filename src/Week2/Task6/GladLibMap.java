package Week2.Task6;

import java.io.*;
import java.sql.Array;
import java.util.*;

public class GladLibMap {
    private ArrayList<String> usedCategoriesList = new ArrayList<>();
    private ArrayList<String> usedWordsList;
    private HashMap<String, ArrayList<String>> myMap;

    private Random myRandom;

    // private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "D:\\Programming\\Java\\Coursera\\ArraysListsStructuredData\\src\\Week2\\Task6\\data\\";

    public GladLibMap(){
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
    }

    public GladLibMap(String source){
        initializeFromSource(source);
        myRandom = new Random();
    }

    private void initializeFromSource(String source) {
        myMap = new HashMap<>();
        myMap.put("adjectiveList", readIt(source + "adjective.txt"));
        myMap.put("nounList", readIt(source + "noun.txt"));
        myMap.put("colorList", readIt(source + "color.txt"));
        myMap.put("countryList", readIt(source + "country.txt"));
        myMap.put("nameList", readIt(source + "name.txt"));
        myMap.put("animalList", readIt(source + "animal.txt"));
        myMap.put("timeframeList", readIt(source + "timeframe.txt"));
        myMap.put("verbList", readIt(source + "verb.txt"));
        myMap.put("fruitList", readIt(source + "fruit.txt"));
        usedWordsList = new ArrayList<>();
    }

    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }

    private String getSubstitute(String label) {
        if (label.equals("country")) {
            if(!usedCategoriesList.contains("countryList")) usedCategoriesList.add("countryList");
            return randomFrom(myMap.get("countryList"));
        }
        if (label.equals("color")){
            if(!usedCategoriesList.contains("colorList")) usedCategoriesList.add("colorList");
            return randomFrom(myMap.get("colorList"));
        }
        if (label.equals("noun")){
            if(!usedCategoriesList.contains("nounList")) usedCategoriesList.add("nounList");
            return randomFrom(myMap.get("nounList"));
        }
        if (label.equals("name")){
            if(!usedCategoriesList.contains("nameList")) usedCategoriesList.add("nameList");
            return randomFrom(myMap.get("nameList"));
        }
        if (label.equals("adjective")){
            if(!usedCategoriesList.contains("adjectiveList")) usedCategoriesList.add("adjectiveList");
            return randomFrom(myMap.get("adjectiveList"));
        }
        if (label.equals("animal")){
            if(!usedCategoriesList.contains("animalList")) usedCategoriesList.add("animalList");
            return randomFrom(myMap.get("animalList"));
        }
        if (label.equals("timeframe")){
            if(!usedCategoriesList.contains("timeframeList")) usedCategoriesList.add("timeframeList");
            return randomFrom(myMap.get("timeframeList"));
        }
        if (label.equals("number")){
            return String.valueOf(myRandom.nextInt(50)+5);
        }
        if (label.equals("verb")){
            if(!usedCategoriesList.contains("verbList")) usedCategoriesList.add("verbList");
            return randomFrom(myMap.get("verbList"));
        }
        if (label.equals("fruit")){
            if(!usedCategoriesList.contains("fruitList")) usedCategoriesList.add("fruitList");
            return randomFrom(myMap.get("fruitList"));
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
        GladLibMap glm = new GladLibMap();
        String story = glm.fromTemplate("D:\\Programming\\Java\\Coursera\\ArraysListsStructuredData\\src\\Week2\\Task6\\data\\madtemplate2.txt");
        glm.printOut(story, 60);
        System.out.println("Number of words that were replaced: " + glm.usedWordsList.size());
        System.out.println("Total number of words we could use: " + glm.totalWordsInMap());
        System.out.println("Number of words in each type we used: " + glm.totalWordsConsidered());
    }

    private int totalWordsInMap(){
        int words = 0;
        for (String key : myMap.keySet()) {
            ArrayList<String> list = myMap.get(key);
            words+=list.size();
        }
        return words;
    }

    private int totalWordsConsidered(){
        int words=0;
        for(String s: usedCategoriesList){
            words+=myMap.get(s).size();
        }
        return words;
    }
}


