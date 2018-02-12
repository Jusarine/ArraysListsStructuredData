package Week1.Task5;

/**
 * Created by Tania on 12.07.2017.
 */

public class OOPCaesarCipher {
    private static String alphabet;
    private static String shiftedAlphabet;
    private static int mainKey;

    public OOPCaesarCipher(int key){
        alphabet = "abcdefghijklmnopqrstuvwxyz";
        shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
        mainKey=key;
    }

    public String encrypt(String input){
        StringBuilder sb = new StringBuilder(input);
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

    public String decrypt(String input){
        OOPCaesarCipher cc = new OOPCaesarCipher(26-mainKey);
        return cc.encrypt(input);
    }
}
