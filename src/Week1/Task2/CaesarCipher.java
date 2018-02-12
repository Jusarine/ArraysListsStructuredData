package Week1.Task2;

/**
 * Created by Tania on 12.07.2017.
 */

import java.io.*;

public class CaesarCipher {

    public static void main(String[] args) {
        textCaesar();
    }

    public static String encrypt(String input, int key){
        StringBuilder sb = new StringBuilder(input);
        String alphabet = "abcdefghijklmnopqrstuvwxyz";

        String shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
        for(int i=0; i<sb.length(); i++){
            char temp = sb.charAt(i);
            if (Character.isUpperCase(temp)) {
                int idx = alphabet.indexOf(Character.toLowerCase(temp));
                if(idx!=-1) {
                    char newTemp = shiftedAlphabet.charAt(idx);
                    sb.setCharAt(i, Character.toUpperCase(newTemp));
                }
            }
            else {
                int idx = alphabet.indexOf(temp);
                if(idx!=-1) {
                    char newTemp = shiftedAlphabet.charAt(idx);
                    sb.setCharAt(i, newTemp);
                }
            }
        }
        return sb.toString();
    }

    private static void textCaesar(){

        File file = new File("D:\\Programming\\Java\\Coursera\\ArraysListsStructuredData\\src\\Week1\\Week2.Task2\\1.txt");

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String line;
            while((line=reader.readLine())!=null){
               System.out.println(encryptTwoKeys(line, 23, 17));
              // System.out.println(encrypt(line, 23));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String encryptTwoKeys(String input, int key1, int key2){
        StringBuilder sb = new StringBuilder(input);
        String alphabet = "abcdefghijklmnopqrstuvwxyz";

        String shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0, key1);
        String shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0, key2);

        for(int i=0; i<sb.length(); i++){
            char temp = sb.charAt(i);
            char newTemp;
            if (Character.isUpperCase(temp)) {
                int idx = alphabet.indexOf(Character.toLowerCase(temp));
                if(idx!=-1) {
                    if(i%2==0) newTemp = shiftedAlphabet1.charAt(idx);
                    else newTemp = shiftedAlphabet2.charAt(idx);
                    sb.setCharAt(i, Character.toUpperCase(newTemp));
                }
            }
            else {
                int idx = alphabet.indexOf(temp);
                if(idx!=-1) {
                    if(i%2==0) newTemp = shiftedAlphabet1.charAt(idx);
                    else newTemp = shiftedAlphabet2.charAt(idx);
                    sb.setCharAt(i, newTemp);
                }
            }
        }
        return sb.toString();
    }
}
