package Week1.Task6;

/**
 * Created by Tania on 15.07.2017.
 */
public class OOPCaesarCipherTwo {
    private String alphabet;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    private static int mainKey1;
    private static int mainKey2;

    OOPCaesarCipherTwo(int key1, int key2){
        alphabet = "abcdefghijklmnopqrstuvwxyz";
        shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0, key1);
        shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0, key2);
        mainKey1=key1;
        mainKey2=key2;
    }

    public String encryptTwoKeys(String input){
        StringBuilder sb = new StringBuilder(input);

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
    public String decryptTwoKeys(String input){
        OOPCaesarCipherTwo cc = new OOPCaesarCipherTwo(26-mainKey1, 26-mainKey2);
        return cc.encryptTwoKeys(input);

    }
}
