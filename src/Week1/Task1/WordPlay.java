package Week1.Task1;

/**
 * Created by Tania on 12.07.2017.
 */
public class WordPlay {
    public static void main(String[] args) {
        System.out.println(replaceVowels("Hello world", '*'));
    }

    static boolean isVowel(char ch){
        if(ch=='a' || ch=='e' || ch=='i' || ch=='o' || ch=='u') return true;
        else if(ch=='A' || ch=='E' || ch=='I' || ch=='O' || ch=='U') return true;
        else return false;

    }

    static String replaceVowels(String s, char ch) {

        StringBuilder sb = new StringBuilder(s);
        char temp;

        for (int i = 0; i < s.length(); i++) {
            temp = s.charAt(i);
            if (isVowel(temp)) sb.setCharAt(i, ch);
        }

        return sb.toString();
    }
}
