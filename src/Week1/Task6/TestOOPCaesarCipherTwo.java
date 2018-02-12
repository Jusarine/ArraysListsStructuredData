package Week1.Task6;

import Week1.Task2.CaesarCipher;
import Week1.Task5.OOPCaesarCipher;
import Week1.Task5.TestOOPCaesarCipher;

import java.io.*;

import static Week1.Task4.CaesarBreaker.getKey;
import static Week1.Task4.CaesarBreaker.halfOfString;

/**
 * Created by Tania on 15.07.2017.
 */
public class TestOOPCaesarCipherTwo {
    public static void main(String[] args) {
        simpleTest();
    }
    private static void simpleTest(){
        File file = new File("D:\\Programming\\Java\\Coursera\\ArraysListsStructuredData\\src\\Week1\\Task6\\1.txt");
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String line;
            TestOOPCaesarCipherTwo ccTest = new TestOOPCaesarCipherTwo();
            while((line=reader.readLine())!=null){
                OOPCaesarCipherTwo cc = new OOPCaesarCipherTwo(17, 3);
                System.out.println(cc.encryptTwoKeys(line));
                System.out.println(cc.decryptTwoKeys(cc.encryptTwoKeys(line)));
                System.out.println(ccTest.breakCaesarCipher(cc.encryptTwoKeys(line)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String breakCaesarCipher(String input) {
        String evenString = halfOfString(input, 0);
        String oddString = halfOfString(input, 1);

        int evenDkey = getKey(evenString);
        int oddDkey = getKey(oddString);

//        System.out.println("EvenDkey: " + evenDkey);
//        System.out.println("OddDkey: " + oddDkey);

        OOPCaesarCipherTwo cc = new OOPCaesarCipherTwo(26-evenDkey, 26-oddDkey);

        return cc.encryptTwoKeys(input);

    }
}
