package Week1.Task5;

import java.io.*;

import static Week1.Task4.CaesarBreaker.getKey;

/**
 * Created by Tania on 15.07.2017.
 */

public class TestOOPCaesarCipher {
    public static void main(String[] args) {
        simpleTest();
    }
    private static void simpleTest(){
        File file = new File("D:\\Programming\\Java\\Coursera\\ArraysListsStructuredData\\src\\Week1\\Task5\\1.txt");
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String line;
            TestOOPCaesarCipher ccTest = new TestOOPCaesarCipher();
            while((line=reader.readLine())!=null){
                OOPCaesarCipher cc = new OOPCaesarCipher(18);
                System.out.println(cc.encrypt(line));
                System.out.println(cc.decrypt(cc.encrypt(line)));
                System.out.println(ccTest.breakCaesarCipher(cc.encrypt(line)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private String breakCaesarCipher(String input){
        int dkey = getKey(input);
        OOPCaesarCipher cc = new OOPCaesarCipher(26-dkey);
        return cc.encrypt(input);
    }
}
