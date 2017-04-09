//package Vigenere.Cipher;
import java.util.Scanner;
class Cipher {
    static Scanner sIn = new Scanner(System.in);
    public static void main(final String[] args){        
        System.out.println("Encrypt or Decrypt?");
        String thingToDo = sIn.nextLine();
        if(thingToDo.toLowerCase().contains("encrypt")){
            System.out.println("What's the key?");
            String key = sIn.nextLine();
            System.out.println("What you want to encrypt?");
            String plaintext = sIn.nextLine();
            System.out.println("Here's the encrypted text:");
            System.out.println(Encrypt(key,plaintext));
        } else{
            if(thingToDo.toLowerCase().contains("decrypt")){
                System.out.println("What's the key?");
                String key = sIn.nextLine();
                System.out.println("What you want to decrypt?");
                String plaintext = sIn.nextLine();
                System.out.println("Here's the decrypted text:");
                System.out.println(Decrypt(key,plaintext));
            } else{
                System.out.print(String.join("\n",
                ".---------.",
                "|.-------.|",
                "||HAL9000||",
                "|'-------'|",
                "|         |",
                "|         |    \"I'm sorry Dave.\"",
                "|   .-.   |    \"I'm afraid I can't do that.\"",
                "|  ( o )  |",
                "|   `-'   |",
                "|_________|",
                "|*%*%*%*%*|",
                "|%*%*%*%*%|",
                "|*%*%*%*%*|",
                "'========='",
                "",
                "",
                "HAL from: https://groups.google.com/forum/#!topic/alt.ascii-art/B84tVcTcBLM"));
                //System.out.println("I'm sorry Dave, I'm afraid I can't do that");
        }}
    }
    static String Encrypt(String key, String ptext){
        //Remove everything that's not a letter and make stuff uppercase so it's easier to process.
        ptext = ptext.replaceAll("[^a-zA-Z]", "").toUpperCase();
        key = key.replaceAll("[^a-zA-Z]", "").toUpperCase();
        //make Bunches of arrays to hold the transmogrificated strings
        char[] kChars = key.toCharArray();
        char[] pChars = ptext.toCharArray();
        int[] pAlph = new int[pChars.length];
        int[] cAlph = new int[pAlph.length];
        char[] cChars = new char[cAlph.length];
        int[] kAlph = new int[kChars.length];
        //Turn Unicode letters into Alphabetical letters 0-26
        for(int i = 0; i < kChars.length; i ++){
            kAlph[i] = (int) kChars[i]-65;
        }
        for(int i = 0; i < pChars.length; i ++){
            pAlph[i] = ((int) pChars[i])-65;
            //Use the algorithm on the Wikipedia page to encrypt each letter, use modulus in key so that it can repeat easily.
            cAlph[i] = Math.floorMod(kAlph[i % kAlph.length] + pAlph[i], 26);
            cChars[i] = (char) (cAlph[i]+65);
        }
        //make the new Ciphertext array back into a string
        String ctext = new String(cChars);
        return(ctext);     
    }
    static String Decrypt(String key, String ctext){
        //Remove everything that's not a letter and make stuff uppercase so it's easier to process.
        ctext = ctext.replaceAll("[^a-zA-Z]", "").toUpperCase();
        key = key.replaceAll("[^a-zA-Z]", "").toUpperCase();
        //make Bunches of arrays to hold the transmogrificated strings
        char[] kChars = key.toCharArray();
        char[] cChars = ctext.toCharArray();
        int[] cAlph = new int[cChars.length];
        int[] pAlph = new int[cAlph.length];
        char[] pChars = new char[pAlph.length];
        int[] kAlph = new int[kChars.length];
        //Turn Unicode letters into Alphabetical letters 0-25
        for(int i = 0; i < kChars.length; i ++){
            kAlph[i] = (int) kChars[i]-65;
        }
        for(int i = 0; i < pChars.length; i ++){
            cAlph[i] = ((int) cChars[i])-65;
            //Use the algorithm on the Wikipedia page to decrypt each letter, use modulus in key so that it can repeat easily.
            pAlph[i] = Math.floorMod(cAlph[i] - kAlph[i % kAlph.length], 26);
            pChars[i] = (char) (pAlph[i]+65);
        }
        String ptext = new String(pChars);
        return(ptext);
    }
}