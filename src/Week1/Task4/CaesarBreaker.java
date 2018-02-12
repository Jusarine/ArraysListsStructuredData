package Week1.Task4;

import Week1.Task2.CaesarCipher;

/**
 * Created by Tania on 15.07.2017.
 */
public class CaesarBreaker {
    public static void main(String[] args) {
        //System.out.println(decryptTwoKeys("Gwpv c vbuq pvokki yfve iqqu qc bgbgbgbgbgbgbgbgbu"));
        //System.out.println(decryptTwoKeys("Gwpv c vbuq pvokki yfve iqqu qc bgbgbgbgbgbgbgbgbu", 23, 2));
        //System.out.println(decryptTwoKeys("Top ncmy qkff vi vguv vbg ycpx", 2, 20));
        System.out.println(decryptTwoKeys("Akag tjw Xibhr awoa aoee xakex znxag xwko"));

    }

    public static String decrypt(String encrypted){
        int dkey = getKey(encrypted);
        return CaesarCipher.encrypt(encrypted,26-dkey);
    }

    public static String decryptTwoKeys(String encrypted){
        String evenString = halfOfString(encrypted, 0);
        String oddString = halfOfString(encrypted, 1);

        int evenDkey = getKey(evenString);
        int oddDkey = getKey(oddString);

        System.out.println("EvenDkey: " + evenDkey);
        System.out.println("OddDkey: " + oddDkey);

        return CaesarCipher.encryptTwoKeys(encrypted, 26-evenDkey, 26-oddDkey);
    }

    public static String decryptTwoKeys(String encrypted, int key1, int key2){
        String evenString = halfOfString(encrypted, 0);
        String oddString = halfOfString(encrypted, 1);

        return CaesarCipher.encryptTwoKeys(encrypted, 26-key1, 26-key2);
    }

    public static String halfOfString(String message, int start){
        StringBuilder line = new StringBuilder(String.valueOf(message.charAt(start)));
        for(int i=start+2; i<message.length(); i+=2){
            line.append(message.charAt(i));
        }
        return line.toString();
    }

    public static int getKey(String s){
        int[] freqs = countLetters(s);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;
        if (maxDex < 4) {
            dkey = 26 - (4-maxDex);
        }
        return dkey;
    }

    public static int[] countLetters(String encrypted) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder(encrypted.toLowerCase());
        int[] counters = new int[27];
        for(int i=0; i<sb.length(); i++){
            char temp = sb.charAt(i);
            int idx = alphabet.indexOf(temp);
            if(idx!=-1) counters[idx]++;
        }
        return counters;
    }

    public static int maxIndex(int[] vals) {
        int maxDex = 0;
        for (int k = 0; k < vals.length; k++) {
            if (vals[k] > vals[maxDex]) {
                maxDex = k;
            }
        }
        return maxDex;
    }
}

