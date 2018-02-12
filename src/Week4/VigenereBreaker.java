package Week4;

import java.io.*;
import java.util.*;

public class VigenereBreaker {
    //String fileString;

    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder sb = new StringBuilder();
        for (int i = whichSlice; i < message.length(); i += totalSlices) {
            sb.append(message.charAt(i));
        }
        return sb.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        CaesarCracker cc = new CaesarCracker(mostCommon);
        for (int i = 0; i < klength; i++) {
            String s = sliceString(encrypted, i, klength);
            key[i] = cc.getKey(s);
        }
        return key;
    }

    public void breakVigenere() throws IOException {
//        System.out.println("Key's array: " + Arrays.toString(tryKeyLength(fileString, 4, 'e')));
//        VigenereCipher vc = new VigenereCipher(tryKeyLength(fileString, 4, 'e'));
//        System.out.println(vc.decrypt(fileString));
        HashMap<String, HashSet<String>> languages = new HashMap<>();
        languages.put("English", readDictionary(new File("D:\\Programming\\Java\\Coursera\\ArraysListsStructuredData\\src\\Week4\\dictionaries\\English")));
        languages.put("Danish", readDictionary(new File("D:\\Programming\\Java\\Coursera\\ArraysListsStructuredData\\src\\Week4\\dictionaries\\Danish")));
        languages.put("Dutch", readDictionary(new File("D:\\Programming\\Java\\Coursera\\ArraysListsStructuredData\\src\\Week4\\dictionaries\\Dutch")));
        languages.put("French", readDictionary(new File("D:\\Programming\\Java\\Coursera\\ArraysListsStructuredData\\src\\Week4\\dictionaries\\French")));
        languages.put("German", readDictionary(new File("D:\\Programming\\Java\\Coursera\\ArraysListsStructuredData\\src\\Week4\\dictionaries\\German")));
        languages.put("Italian", readDictionary(new File("D:\\Programming\\Java\\Coursera\\ArraysListsStructuredData\\src\\Week4\\dictionaries\\Italian")));
        languages.put("Portuguese", readDictionary(new File("D:\\Programming\\Java\\Coursera\\ArraysListsStructuredData\\src\\Week4\\dictionaries\\Portuguese")));
        languages.put("Spanish", readDictionary(new File("D:\\Programming\\Java\\Coursera\\ArraysListsStructuredData\\src\\Week4\\dictionaries\\Spanish")));

        String s = fileToString(new File("D:\\Programming\\Java\\Coursera\\ArraysListsStructuredData\\src\\Week4\\Week4File.txt"));
       // System.out.println(breakForLanguage(s, languages.get("English")));

        System.out.println(breakForAllLangs(s, languages));
    }

    public static void main(String[] args) throws IOException {
        VigenereBreaker vb = new VigenereBreaker();
        vb.breakVigenere();

    }

    public HashSet<String> readDictionary(File file){
        HashSet<String> set = new HashSet<>();
        try {
            Scanner sc = new Scanner(file);
            String word;
            while(sc.hasNext()){
                word = sc.nextLine().toLowerCase();
                set.add(word);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return set;
    }

    public int countWords(String message, HashSet<String> dictionary){
        String[] words = message.split("\\W+");
        int count=0;
        for(String word: words){
            if(dictionary.contains(word.toLowerCase())) count++;
        }
        return count;
    }

    public String breakForLanguage(String encrypted, HashSet<String> dictionary){
        int max=0;
        int keyLength=0;
        String decrypted="";
        for(int k=1; k<100; k++){
            VigenereCipher vc = new VigenereCipher(tryKeyLength(encrypted, k, mostCommonCharIn(dictionary)));
            String temp = vc.decrypt(encrypted);
            int count = countWords(temp, dictionary);
            if(max<count) {
                max=count;
                decrypted=temp;
                keyLength=k;
            }
        }
        System.out.println("Length of key: " + keyLength);
        System.out.println("Count of words from dictionary: " + max);
        return decrypted;
    }

    public String fileToString(File file){
        StringBuilder sb = new StringBuilder();
        try {
            Scanner sc = new Scanner(file);
            while(sc.hasNext()){
               sb.append(sc.nextLine());
               sb.append("\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public char mostCommonCharIn(HashSet<String> dictionary){
        HashMap<Character, Integer> chars = new HashMap<>();
        for(String word: dictionary){
            for(int i=0; i<word.length(); i++){
                char temp = word.charAt(i);
                if(chars.containsKey(temp)) {
                    chars.put(temp, chars.get(temp)+1);
                }
                else chars.put(temp, 1);
            }
        }
        int max=0;
        char maxChar='\0';
        for(Character c: chars.keySet()){
            if(chars.get(c)>max){
                max=chars.get(c);
                maxChar=c;
            }
        }
        return maxChar;
    }

    public String breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> languages){
        int max=0;
        String dec="";
        String lang="";

        for(String language: languages.keySet()){
            System.out.println(language);
            String decrypted = breakForLanguage(encrypted, languages.get(language));
            if(max<countWords(decrypted, languages.get(language))){
                max=countWords(decrypted, languages.get(language));
                lang=language;
                dec=decrypted;
            }
        }
        System.out.println("Correct language: " + lang);
        return dec;
    }



















//----------------------------------------------------------------------------------------------------------------------------
//    VigenereBreaker(){
//        //secret message 1
//        fileString = "Hhdiu LVXNEW uxh WKWVCEW, krg k wbbsqa si Mmwcjiqm\n" +
//                "\n" +
//                "Fcdmcyxw:\n" +
//                "  Zy alfv ey wdnswicoh; viw ew vo vudmvzsig.\n" +
//                "Fuodyv:\n" +
//                "  Nriq psofya go, uxh aszh wi uehlyxgh, jucorgm.\n" +
//                "  Mevmsyv, kr isx srwi xky swbov mdvhyd,\n" +
//                "  Eqx tdld wbo qowfhlc.\n" +
//                "  Xkici nrew gmof lhub py wsyko, viw 'ip cxds lhlo; \n" +
//                "  Wbywh dldn alfv iivprq Gdmcmxm, ji alnr kcw;\n" +
//                "  Eqx txvvmf bidmyrv cldfv ey vhhniuyn\n" +
//                "  Yj Wkivub'v nidnr.\n" +
//                "Zsvvn Glnsdhh:\n" +
//                "  S zcvp boeu Lvxnew mzide.\n" +
//                "Vymsqx Glnsdhh:\n" +
//                "  S zcvp boeu Mevmsyv; eqx grgzeuy xkysv loevixw, \n" +
//                "  Qriq ciyybeofi zy lhub wboq lorgybig.\n" +
//                "[Hrsx WKWVCEW, gmwb wrgo rz xky Glnsdhhc. VBYWOC jiow cxxr dlh zyojsx]\n" +
//                "Dllln Fcdmcyx:\n" +
//                "  Xky rrvvi Vbywoc lm evworgyn: msphhmi!\n" +
//                "Lvxnew:\n" +
//                "  Ey tdnsiqn xlfv wbo oucx. \n" +
//                "  Lyqdhc, wyyqnbcpyx, uxh fyzhlc! boeu wi zyv gi\n" +
//                "  mexmo, uxh vo vcviqn, wbkx syy gkc boeu: fhfsiyy qh\n" +
//                "  iib pcxi byrrob, uxh bkzh bivjogw ds gsrh rsqiev, dldn\n" +
//                "  syy gkc voplyfi: miqmevh wi cx biev qswgiw, uxh\n" +
//                "  ezuui syyu ciqmow, dldn cro qds xky fhndiu tygao. \n" +
//                "  Lz xkybi vo dhi lh xkcc dmcipvvc, krb nidl jucorg yj\n" +
//                "  Gdyceu'c, ny kcw L ceb, xkud Elexxm' oifi ny Fuowdl\n" +
//                "  qkw hy oycw nreq rmv. Mi dlhh xkud ilsiqx hhgkrg\n" +
//                "  zbi Elexxm vrmo dakmqmd Fuowdl, wbsw cc ps eqmgiu:\n" +
//                "  —Xsw dldn M fyzhx Gdyceu vivm, eod wbkx C prpoh \n" +
//                "  Uiwi gyvh. Ldx cro vdnriu Mehmkv qovh vmycxk uxh\n" +
//                "  hly eof woufiv, xkux wbkx Wkivub zybi xoeg, xr vmyy\n" +
//                "  uvp zbih wiq? Ev Mehmkv fyzhx qh, M qois psu rmp;\n" +
//                "  uc ky adm jrldyqudi, S uytslwo dn mw; ev ri qkw\n" +
//                "  zdfseqn, L rsqiev bsq: lyw, ev ri qkw uwflnssxm, L \n" +
//                "  mviz rmp. Xkybi cc wykvv psu rmv vsyy; mii iib kcc\n" +
//                "  psunerh; lrhyyu psu rmv feoiev; krg nidnr iib kcc\n" +
//                "  kqecdmrh. Zby lm lhlo vi fdmo wbkx qyyox fh k\n" +
//                "  lsqxweq? Mi krb, wsyko; psu rmp reyy M ipjhhnig.\n" +
//                "  Qrs cc kybi my uoni nrew gsxfn qid ey e Lyqdh? Lz \n" +
//                "  krb, wsyko; psu rmp reyy M ipjhhnig. Aki mv riuy wr\n" +
//                "  ycvi nrew gmof rrn prpo kcc fierwli? Cp dhi, mzide;\n" +
//                "  psu rmp reyy M ipjhhnig. M jkyvy jrl e lotos.\n" +
//                "Dfv:\n" +
//                "  Rrho, Vbywoc, hyrh.\n" +
//                "Fuodyv:\n" +
//                "  Nriq xsqy ldpo L yjiyxhhx. L reyy hrho qi qrlo wi \n" +
//                "  Mehmkv nreq isx cldfv gi xr Lvxnew. Dlh ayhmdmrh si\n" +
//                "  kcc gykxk sw yxvrfvig sr nri Wktlnyp; rmv qprli qid\n" +
//                "  obwyxydnoh, glhlomq ri qkw qyvwbi, hyv bsw ipjhhmiv\n" +
//                "  hhpsuwoh, psu gllwr ky wxzpiuyn gykxk.\n" +
//                "[Hhdiu KRWIXC uxh idlhlc, qsxk MEHMKV'm frxi] \n" +
//                "  Kybi wyqhm llm frxi, gyyuhoh vi Pubo Uxxrhi: qrs,\n" +
//                "  wbyyjb lh reg xs bkrg sr bsw xoewb, vbkpo bifyszh\n" +
//                "  wbo eyxiicd rz llm hbcxk, k sfkgh sr nri\n" +
//                "  grgwsqqoeonr; uc zbsgk yj syy mreof rrn? Zcdl nrmv\n" +
//                "  L nisubx,—dldn, dm M mviz wc voww vsyyb iib wbo \n" +
//                "  krin rz Vrgo, C ldpo wbo vuwi xkkjyb iib pscioz,\n" +
//                "  glhh mw cldfv sfoevy qb msxhdvb ds hoig wc xoewb.\n" +
//                "Dfv:\n" +
//                "  Plpo, Vbywoc! fszh, plpo!\n" +
//                "Zsvvn Glnsdhh:\n" +
//                "  Lvlhq kcw zcdl nbmxgzl byqh erwi llm lroci.\n" +
//                "Cifixh Wsxltor:\n" +
//                "  Jcfi bsq u wwudyh gmwb llm eqwowwibw. \n" +
//                "Xkcbh Wsxltor:\n" +
//                "  Oyd kcw ey Gdyceu.\n" +
//                "Jrobxk Mmwcjiq:\n" +
//                "  Wkivub'v liwnov jkvwm\n" +
//                "  Mreof fh mvrqx'g sr Vbywoc.\n" +
//                "Zsvvn Glnsdhh:\n" +
//                "  Gi'fv elsrj rmp ds bsw byyvy\n" +
//                "  Qsxk clrodw uxh wvepievv. \n" +
//                "Elexxm:\n" +
//                "  Wc wyyqnbcpyx,—\n" +
//                "Cifixh Wsxltor:\n" +
//                "  Sykgh, wlforfy! Elexxm wsykov.\n" +
//                "Jllcx Wsxltor:\n" +
//                "  Sykgh, lr!\n" +
//                "Fuodyv:\n" +
//                "  Aysg msxhdvbgor, viw wi xotdld dfyrh,\n" +
//                "  Uxh, psu wc mkoh, wwui kybi qsxk Krwixc: \n" +
//                "  Xy jlkgh ds Wkivub'v msujci, krg qvdwo kcc vjoifb\n" +
//                "  Norgcxk ny Fuowdl'w avsucow; gllwr Pubo Uxxrhi,\n" +
//                "  Fb yyu ziugswvcyr, sw uvprq'h ny puui.\n" +
//                "  L ns yxxuykx syy, xsw k pux gyzeun,\n" +
//                "  Ceyy M uvsqy, wcvp Uxxrhi kufi mzsny. \n" +
//                "[Obln]\n" +
//                "\n" +
//                "Zsvvn Glnsdhh:\n" +
//                "  Cxds, ki! dhn oyd xm lhub Pubo Uxxrhi.\n" +
//                "Nrmux Glnsdhh:\n" +
//                "  Viw rmp qs oz lhds nri jefocm fbkmu;\n" +
//                "  Qo'of lhub kcw. Hyfoy Eqnyrb, kr et.\n" +
//                "Krwixc:\n" +
//                "  Iib Elexxm' vuui, S dg fhbypgcxk ny bie. \n" +
//                "  Jiow cxxr dlh zyojsx\n" +
//                "\n" +
//                "Psxldl Wsxltor:\n" +
//                "  Zbkx xyiv ri mkc ip Elexxm?\n" +
//                "Wbsvg Mmwcjiq:\n" +
//                "  Bo vuiw, psu Lvxnew' ceny,\n" +
//                "  Ri zsrgm llgcioz fhbypgcxk ny xm eof.\n" +
//                "Iievwb Glnsdhh:\n" +
//                "  'Xzybi voww ri mzide rr reug si Lvxnew bovh. \n" +
//                "Icbww Mmwcjiq:\n" +
//                "  Nrmv Mehmkv qkw u xblkrw.\n" +
//                "Xkcbh Wsxltor:\n" +
//                "  Qui, nrew'c fybxdcx:\n" +
//                "  Ah kvh lphmd wbkx Lyqh sw lsh ip kcw.\n" +
//                "Mogrhn Fcdmcyx:\n" +
//                "  Thumi! viw ew boeu gldn Eqnyrb meq ceb.\n" +
//                "Eqnyrb:\n" +
//                "  Syy aorwfo Uiweqm,—\n" +
//                "Wsxltorv:\n" +
//                "  Joefy, ki! oyd xm lhub kcw.\n" +
//                "Uxxrhi:\n" +
//                "  Jucorgm, Uiweqm, fierwliqhh, oyxh go biev ykvv;\n" +
//                "  C grgo wi fxli Fuowdl, qid wi tuuswh rmp.\n" +
//                "  Nri yfmo dldn qhh hr vmyyc dzdiu dlhg;\n" +
//                "  Dlh qsrx mv yjw srwybvhx alnr wbomu lsqyc; \n" +
//                "  Vi phn mw li qsxk Mehmkv. Dlh xsefo Elexxm\n" +
//                "  Bkxk dsox cro Gdyceu gev kqecdmroc:\n" +
//                "  Mi sx qovh cs, sx qkw u kucozroc iuepw,\n" +
//                "  Uxh abmhpyyvfi kudl Wkivub dhcahl'h cd.\n" +
//                "  Lhlo, oxhhl phufi ip Elexxm eqx xky vhmd— \n" +
//                "  Iib Elexxm mv kr byrrobeefo pux;\n" +
//                "  Wr kvh dlhs eof, dfv kixsxlkfoy qhh—\n" +
//                "  Mspy M ny vjoen sr Wkivub'v pyqybeo.\n" +
//                "  Bo zuc ps jucorg, jdcdliov dhn mocx ny py:\n" +
//                "  Lyw Lvxnew mkcv ri qkw uwflnssxm; \n" +
//                "  Eqx Fuodyv sw ux kixsxlkfoy qdh.\n" +
//                "  Ri bkxk lvroqlw weqs gdjdmyyc kiwi ny Uiwi\n" +
//                "  Akici lkrviww xsh nri aorhlkp wyjiybw zspo:\n" +
//                "  Xsh nrmv sr Wkivub vyoq uwflnssxm?\n" +
//                "  Glhh xkud wbo siyv bkzh mvlyn, Wkivub kudl qotw: \n" +
//                "  Kqecdmrh wkiepg li gkhh yj mdiuhov mdyiz:\n" +
//                "  Iiw Lvxnew mkcv ri qkw uwflnssxm;\n" +
//                "  Krg Lvxnew cc dh lrhyyuulph weq.\n" +
//                "  Syy uvp xsh moi nrew yr nri Fethlmeo\n" +
//                "  L dlucmi jbivyxxhx llg e esrjfi flyaq, \n" +
//                "  Gllwr ky hlx xklsgh biioci: gev dllm epvsxlix?\n" +
//                "  Chn Fuodyv cebm lh gev kqecdmroc;\n" +
//                "  Eqx, vobi, ri cc dh lrhyyuulph weq.\n" +
//                "  C wsyko hyx ny gcctuifi qrew Lvxnew mzsny,\n" +
//                "  Lyw riuy M uw wi wsyko qrew S gi oqig. \n" +
//                "  Bie dfv gcn oifi bsq ixgh, rrn alnrsxn gdoci:\n" +
//                "  Zbkx wkyvy alnrlrfnw syy nriq, xr wsxlx iib kcw?\n" +
//                "  S dehjgorw! xkie dld ifoh ny elexlmr eykwwm,\n" +
//                "  Krg wiq reyy prmd wbomu bidmyr. Lidl alnr py;\n" +
//                "  Wc boeun mv sr nri wyjicx wbovh gmwb Gdyceu, \n" +
//                "  Krg S pocx jkyvy xlfv ln grgo eumo ny py.\n" +
//                "Icbww Mmwcjiq:\n" +
//                "  Goxkcxov dlhlo lm qxwr uykwrh mq rmv cebcxkv.\n" +
//                "Whwyrg Mmwcjiq:\n" +
//                "  Cp wbyy wyrvcniu bmjbdpb yj nri gkxwyb,\n" +
//                "  Gdyceu rev reg qvhud zlyrj.\n" +
//                "Xkcbh Wsxltor:\n" +
//                "  Kuc ky, pucxhlc? \n" +
//                "  L pidl xkybi qspo k zibwh mspy mq rmv zpdwo.\n" +
//                "Zyyunr Fcdmcyx:\n" +
//                "  Qdlu'g ii bsw qyvgm? Ky arovh hyx nkoh dlh mvrqx;\n" +
//                "  Xkybiiibi 'dmv miunkmq ri qkw hyx uwflnssxm.\n" +
//                "Icbww Mmwcjiq:\n" +
//                "  Cp ln fh psxhn vi, viwi qspo nidl eecni cd.\n" +
//                "Mogrhn Fcdmcyx:\n" +
//                "  Trib viep! rmv ochm euy vhx ev pmuy alnr zyotlhq. \n" +
//                "Dllln Fcdmcyx:\n" +
//                "  Xkybi'm rrn e hyfoyb pux lh Vrgo wbkr Uxxrhi.\n" +
//                "Zyyunr Fcdmcyx:\n" +
//                "  Rrq qdlu kcw, bo eyqmqm ejusr ny vjoen.\n" +
//                "Eqnyrb:\n" +
//                "  Vex sowwybhds xky arln rz Gdyceu wmjbd\n" +
//                "  Reyy wwiyh uqelhcx nri qyvox; qig ocow bo wbovh.\n" +
//                "  Uxh hyrh cs jysu ds xy kcw uyfiuyxgh. \n" +
//                "  Y pucxhlc, cp L giuy hlmzsvyn wi wwcb\n" +
//                "  Isxl lhubxv krg wmqxc wi qxnsrb krg bejy,\n" +
//                "  S vbyyox hr Lvxnew qbsqa, dhn Fucwloc zlyrj,\n" +
//                "  Qrs, isx kpo urrq, dlo kixsxlkfoy qhh:\n" +
//                "  S zcvp hyx xy wboq qbsqa; L bewbov wrsrmo \n" +
//                "  Xr gvrhq wbo gykh, ds qbsqa qbmopi krg isx,\n" +
//                "  Nreq S zcvp qbsqa wxwr kixsxlkfoy qhh.\n" +
//                "  Lyw riuy'w u tdlmlpyxx qsxk dlh cidf si Mehmkv;\n" +
//                "  L psxhn ln mq rmv mprmox, 'xlm llm alfv:\n" +
//                "  Phn fxn xky grgwsqm lhub wbsw nowwuwiqn— \n" +
//                "  Akcml, zeuxyr go, C hr xsw widh xr bidx—\n" +
//                "  Krg dlhs arovh ay dhn nccw xoeg Mehmkv'm aroxhv\n" +
//                "  Dhn gcz wbomu xesesrv sr bsw mkguyn efysg,\n" +
//                "  Soe, lij k kusv ip kcw iib pywsus,\n" +
//                "  Krg, hbcxk, wiqnssq sx qsxkcx wbomu gmofc, \n" +
//                "  Eyayhudllhq ln ev k ucml fokdwi\n" +
//                "  Erwi xkysv ccwxy.\n" +
//                "Iievwb Glnsdhh:\n" +
//                "  Gi'fv kykv nri qspo: vhun ln, Pubo Uxxrhi.\n" +
//                "Uvp:\n" +
//                "  Wbo zcvp, dlh gmof! zy alfv kykv Wkivub'v gmof.\n" +
//                "Dhdsqs:\n" +
//                "  Reyy tdnsiqwo, aorwfo ilsiqxc, C qxmd qid uykh cd; \n" +
//                "  Ln mv xsw wihn cro oqig kig Fuowdl prpoh syy.\n" +
//                "  Bie dlo qid ziyh, isx kvh xsw cxrhow, lyw wiq;\n" +
//                "  Uxh, lilhq pyx, voeucxk nri qspo yj Wkivub,\n" +
//                "  Mw gmof mqzvepy cro, ln alfv puui syy gkh:\n" +
//                "  'Nsw aysg isx urrq rrn xkud bie dlo kcc kysvv; \n" +
//                "  Psu, mi isx clrovh, Y, qrew gsxfn fiwi ip ln!\n" +
//                "Iievwb Glnsdhh:\n" +
//                "  Bidx xky alfv; qo'of lhub ln, Dhdsqs;\n" +
//                "  Isx cldfv uykh oc wbo zcvp, Mehmkv'm alfv.\n" +
//                "Uxxrhi:\n" +
//                "  Alfv bie ey tdnsiqn? zcvp syy mdeb kakcvi?\n" +
//                "  L reyy s'ybwkid pscioz xr diof cro si sx: \n" +
//                "  C jhub L gvrhq wbo kixsxlkfoy qhh\n" +
//                "  Qrsvy hdaqium ldpo vnkfe'n Fuowdl; L ns zoeu sx.\n" +
//                "Psxldl Wsxltor:\n" +
//                "  Wboc qovh dvdcdsum: kixsxlkfoy qhh!\n" +
//                "Dfv:\n" +
//                "  Xky alfv! nri nowwuwiqn!\n" +
//                "Vymsqx Glnsdhh:\n" +
//                "  Dlhs ahlo ycvpdcxw, wyuxovhlc: nri qspo! vhun wbo zcvp. \n" +
//                "Eqnyrb:\n" +
//                "  Syy qspo mspjop go, nriq, xr bidx xky alfv?\n" +
//                "  Xkyx puui u vlhq dvyyw dlh msujci ip Fuowdl,\n" +
//                "  Krg viw wi mrsz isx rmp dldn qdxo wbo zcvp.\n" +
//                "  Vbkpo S gycghhn? uxh qspo isx qmyy qh vidpo?\n" +
//                "Mozhlkp Wsxltorv:\n" +
//                "  Wyqh nszh. \n" +
//                "Mogrhn Fcdmcyx:\n" +
//                "  Hhmmiqx.\n" +
//                "Wbsvg Mmwcjiq:\n" +
//                "  Syy mreof ldpo oykzh.\n" +
//                "[DHDSQS grgow xyaq]\n" +
//                "\n" +
//                "Iievwb Glnsdhh:\n" +
//                "  K ucxk; cxdhn uierg.\n" +
//                "Jllcx Wsxltor:\n" +
//                "  Vnkrg pvrg xky lhubwh, wwuxh zbsp dlh lsgs. \n" +
//                "Mogrhn Fcdmcyx:\n" +
//                "  Vriw iib Dhdsqs, picx hyfoy Eqnyrb.\n" +
//                "Eqnyrb:\n" +
//                "  Hkc, zvhmc qid vi ysix py; vnkrg peu yji.\n" +
//                "Whpovdf Glnsdhhc:\n" +
//                "  Wwuxh vkgn; vriw; voeu lefe.\n" +
//                "Dhdsqs:\n" +
//                "  Sj syy bkzh didlc, jbisubi ny vboh nrip xsz.\n" +
//                "  Syy uvp xy nhya nrmv weqnvi: S uywipvov \n" +
//                "  Wbo icbww dmpy iyyb Fuowdl txn mw yr;\n" +
//                "  'Ngev yr u wxgwiu'c hporlhq, cx kcc wyxx,\n" +
//                "  Wbkx xkc bo rpovfuwi nri Hovycs:\n" +
//                "  Priu, cx wbsw jvefy vdh Gdmcmxm' guqkhl xklyyjb:\n" +
//                "  Cih gldn e lorw dlh orycyyv Mevwk puni: \n" +
//                "  Nrvroql nrmv dlh giof-fhfyzhx Fuodyv cxdvl'g;\n" +
//                "  Uxh uc ky toomo'x llm gxlcig cxhyv dqkc,\n" +
//                "  Pubo bya nri vvsrx si Mehmkv zypoig'g sx,\n" +
//                "  Dm vxmrmqa sxn si nsrlc, ny ey vhmypyyn\n" +
//                "  Sj Vbywoc vi yqesrgfi nhygn'n, ib qi; \n" +
//                "  Jrl Fuodyv, ev isx urrq, zuc Fuowdl'w uxkhf:\n" +
//                "  Tygao, I cro krxc, bya xoeufi Fuowdl prpoh bsq!\n" +
//                "  Wbsw qkw nri gyww erncxhhmd fod rz eof;\n" +
//                "  Psu glhh xky rrvvi Wkivub vug kcw vnkf,\n" +
//                "  Lhqvdnsxxxo, gyvh cxuixk nreq dvdcdsum' dlww, \n" +
//                "  Kemwy zdhaylmr'g rmp: xkyx eobww rmv wmjbdc boeun;\n" +
//                "  Krg, mq rmv weqnvi gejifsrj et bsw zkgh,\n" +
//                "  Yfiq kx nri vkwh yj Jyqsyi'v cxdnee,\n" +
//                "  Zbsgk kpo dlh gllfo uux efysg, kuykx Wkivub iyvp.\n" +
//                "  R, akud d peof adm xkybi, wc wyyqnbcpyx! \n" +
//                "  Wbor C, dhn bie, uxh uvp ip xm jhfv gigr,\n" +
//                "  Zbspvn foiyhb dvhucsq pprobmvb'h ifiu ew.\n" +
//                "  R, rrq cro ahyz; uxh, S sybghcfi, isx pihf\n" +
//                "  Nri xsrw yj jsxb: xkyci ubi abefcyyv nvrjc.\n" +
//                "  Olhn viepv, akud, qois isx glhh cro fxn fhbypg \n" +
//                "  Iev Wkivub'v fivnevh gsxhnig? Priu bie kybi,\n" +
//                "  Kybi cc kcwwhfp, gkvu'n, uc bie vyo, qsxk dvdcdsum.\n" +
//                "Icbww Mmwcjiq:\n" +
//                "  I tlnosxm wsymxdwvi!\n" +
//                "Cifixh Wsxltor:\n" +
//                "  R xsefo Fuowdl!\n" +
//                "Wbsvg Mmwcjiq:\n" +
//                "  I arzep xkc! \n" +
//                "Jrobxk Mmwcjiq:\n" +
//                "  I xuusxrlc, pspousrv!\n" +
//                "Jllcx Wsxltor:\n" +
//                "  R wsvn foiyhb cmjbd!\n" +
//                "Mogrhn Fcdmcyx:\n" +
//                "  Ah gmof fh biyyxkhx.\n" +
//                "Dfv:\n" +
//                "  Vhporjy! Dvyyw! Whyu! Vevq! Jllo! Espo! Woui!\n" +
//                "  Phn rrn e nbelnyv fszh! \n" +
//                "Dhdsqs:\n" +
//                "  Cxds, fierwliqhh.\n" +
//                "Icbww Mmwcjiq:\n" +
//                "  Joefy xkybi! ridl xky rrvvi Uxxrhi.\n" +
//                "Mogrhn Fcdmcyx:\n" +
//                "  Ah'vp boeu rmp, ah'vp zypoig kcw, qo'of hly alnr kcw.\n" +
//                "Uxxrhi:\n" +
//                "  Krin ilsiqxc, mgihn jucorgm, oyd py rrn wwcb bie xj\n" +
//                "  Ny voml u wxxniq pprin rz qxnsrb. \n" +
//                "  Dlhs xkud kufi xyrh dllm hhyn dlo kixsxlkfoy:\n" +
//                "  Gldn tucfewy kucojv dlhs ldpo, uvev, M exsz xsw,\n" +
//                "  Nrew wegy xkyw gi mw: xkyi dlo zcci uxh byrrobeefo,\n" +
//                "  Eqx alfv, hy giefw, alnr uykwrhc dhcahl cro.\n" +
//                "  S fiwi hyx, pvlyxhv, xr cxhuv dqkc syyu ridldw: \n" +
//                "  C ep xs ibewib, uc Elexxm mv;\n" +
//                "  Vex, kw syy exsz wi uvp, k sfkmq lpxhd pux,\n" +
//                "  Xkud oifi gi ilsiqx; dhn wbkx nrib urrq jxfv zyvp\n" +
//                "  Xkud jufi go solplw phufi ny vjoen yj bsq:\n" +
//                "  Iib L reyy rhcdlhl aln, qib zibhv, rrl arldl, \n" +
//                "  Umxlix, hyv odxhlkrfy, qib wbo sigiu yj mzihwr,\n" +
//                "  Xr cxll qhh'w vvsrx: L yros wsyko lskkn sq;\n" +
//                "  C xhfv bie wbkx qrmfb cro crobwhffiv ns exsz;\n" +
//                "  Mrsz isx cahyd Fuowdl'w qyyqxc, jysu zsrl hxgl piexkm,\n" +
//                "  Krg lmg dlhg wsyko zyv go: vex qovh S Elexxm, \n" +
//                "  Eqx Fuodyv Krwixc, dlhlo zybi ux Dhdsqs\n" +
//                "  Qyyox vxzpph et syyu ctllsxv krg zyw k wixkxy\n" +
//                "  Cx hpovb gsxhn rz Gdyceu dldn wkiepg wsyy\n" +
//                "  Nri mdsqyc rz Vrgo wi vlmo dhn podmqs.\n" +
//                "Dfv:\n" +
//                "  Ah'vp gexlhi. \n" +
//                "Pmumd Fcdmcyx:\n" +
//                "  Ah'vp vevq dlh rsxmo rz Fuodyv.\n" +
//                "Xkcbh Wsxltor:\n" +
//                "  Dqkc, dlhh! fiwi, cihe xky grhctllkxrlc.\n" +
//                "Uxxrhi:\n" +
//                "  Chn lhub py, fierwliqhh; byd kykv go vjoen.\n" +
//                "Eof:\n" +
//                "  Zidwo, by! Boeu Krwixc. Wsvn rrvvi Uxxrhi!\n" +
//                "Uxxrhi:\n" +
//                "  Aks, ilsiqxc, syy ay wi hr isx urrq rrn akud: \n" +
//                "  Zbovhcx kudl Wkivub wbew xowhlfig isxl prpow?\n" +
//                "  Dfkw, isx urrq rrn: L wyvn xhfv bie wbor:\n" +
//                "  Bie kufi zyvjid wbo zcvp C xrfn bie rz.\n" +
//                "Dfv:\n" +
//                "  Qrmd wlei. Dlh gmof! Oyd'v cxds eqx lhub wbo zcvp.\n" +
//                "Krwixc:\n" +
//                "  Kybi cc wbo zcvp, krg ergyb Fuowdl'w moeo. \n" +
//                "  Ds yfius Vrgkr wsxltor bo jcfiv,\n" +
//                "  Ny hpovb ciyybeo weq, whporws-jlpo glkgkgkw.\n" +
//                "Cifixh Wsxltor:\n" +
//                "  Picx hyfoy Gdyceu! Ah'vp lozhhqi bsw xoewb.\n" +
//                "Wbsvg Mmwcjiq:\n" +
//                "  I vrskp Wkivub!\n" +
//                "Uxxrhi:\n" +
//                "  Lhub py alnr sudmhhmi. \n" +
//                "Eof:\n" +
//                "  Zidwo, by!\n" +
//                "Uxxrhi:\n" +
//                "  Qrlosyyb, bo kudl fojw isx kpo rmv geoec,\n" +
//                "  Llm tucfewy euvyyum eqx rhq-touxxhx suwreuxc,\n" +
//                "  Sq dllm wlxo Wcliu; lh rewb phzd wboq syy,\n" +
//                "  Dhn wi crob kysvv psu ozhl, fiwqrh toykwxlow, \n" +
//                "  Ny zuvo ulvrun, uxh loguykxh isxlciopow.\n" +
//                "  Kybi qkw u Gdyceu! akyx fiwiv cyfb eqidlhl?\n" +
//                "Icbww Mmwcjiq:\n" +
//                "  Hozhl, qyfiu. Grgo, ugeb, ezui!\n" +
//                "  Ah'vp vevq rmv lsgs mq dlh rsos toumi,\n" +
//                "  Dhn zcdl nri vbeqxc icbi nri nbelnyvv' lrociv. \n" +
//                "  Deny ys dlh lsgs.\n" +
//                "Vymsqx Glnsdhh:\n" +
//                "  Qs zoxfb jllo.\n" +
//                "Nrmux Glnsdhh:\n" +
//                "  Zpxwu gigr vorfbow.\n" +
//                "Psxldl Wsxltor:\n" +
//                "  Sfegn nszh jrlww, gmqxyav, eqs xkcxk.\n" +
//                "[Iayerw Mmwcjiqm alnr wbo einc]\n" +
//                "\n" +
//                "Eqnyrb:\n" +
//                "  Hya fox cd zibo. Wmvwrmhz, wbyy ubx upsrn,\n" +
//                "  Deny xkie zbkx wyyumo wbyy qspw!\n" +
//                "[Hhdiu k Vybzdhd]\n" +
//                "  Lrq rrq, iyvprq!\n" +
//                "Vybzdhd:\n" +
//                "  Wll, Rwdeycew cc dfbidxi fiwi ny Uiwi. \n" +
//                "Eqnyrb:\n" +
//                "  Qriuy mv ri?\n" +
//                "Ciupkrw:\n" +
//                "  Bo dhn Oyzmgoc dlo dn Gdyceu'c kiewh.\n" +
//                "Eqnyrb:\n" +
//                "  Uxh nrmwbov qspo S vnbelarx ny yccmw rmp:\n" +
//                "  Bo fiwiv etrh e qswk. Jrldyqy mv wiuli,\n" +
//                "  Eqx mq dllm qrin zcvp aszh ew uxc nrmqa. \n" +
//                "Movyuxx:\n" +
//                "  L ridln kcw vui, Vbywoc dhn Fucwloc\n" +
//                "  Kvh bmg vmny qdxwiq dluiekk dlh qewyc rz Vrgo.\n" +
//                "Uxxrhi:\n" +
//                "  Fhfsoh dlhs ldx wrgo qidmfy si dlh zirjvi,\n" +
//                "  Kig L reg wsyyn wboq. Lvlhq py xr Ygwufmxm.\n" +
//                "[Yhixhd]";
//    }
}
