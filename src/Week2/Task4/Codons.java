package Week2.Task4;

import java.util.*;

/**
 * Created by Tania on 17.07.2017.
 */
public class Codons {
    HashMap<String, Integer> map;

    public Codons(){
        map = new HashMap<>();
    }

    public static void main(String[] args) {
        Codons codons = new Codons();
        String dna = "CGTTCAAGTTCAA";
        //String dna = "ATTAATACTTTGTTTAACAGTAATTATTCAACTATTAAATATTTAAATAATTAAGTTATTAAACTATTAAGTACAGGGCCTGTATCTCTGATGCTGAACTATGATGTGTGACTTAAGCCCCCAAATACATCATGTTATTTGGATCCAAGGTGCTGCACAGAACGCTGACCCTCTCTAAGAGCTGGGTATTACT";
        codons.buildCodonMap(0,dna);
        System.out.println(dna);
        System.out.println("Most common codon: " + codons.getMostCommonCodon());
        codons.printCodonCounts(0,100);
    }
    private void buildCodonMap(int start, String dna) {
        String s;
        while(dna.length()>=start+3){
            s = dna.substring(start, start+3);
            if (map.containsKey(s)) {
                map.put(s, map.get(s) + 1);
            } else {
                map.put(s, 1);
            }
            start += 3;
        }
    }

    private String getMostCommonCodon(){
        ArrayList<String> key = new ArrayList<>();
        ArrayList<Integer> value = new ArrayList<>();
        key.addAll( map.keySet());
        value.addAll(map.values());
        int maxValue = value.get(0);
        String maxKey = key.get(0);
        for(int i=1; i<key.size(); i++){
            if(value.get(i)>maxValue) {
                maxValue = value.get(i);
                maxKey = key.get(i);
            }
        }
        return maxKey;
    }

    private void printCodonCounts(int start, int end){

        Iterator<Map.Entry<String, Integer>> iterator = map.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry<String, Integer> pair = iterator.next();
            String key = pair.getKey();
            Integer value = pair.getValue();
            if(value>=start && value<=end) System.out.println(key + " - " + value);
        }
    }
}
