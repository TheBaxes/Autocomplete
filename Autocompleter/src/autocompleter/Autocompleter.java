/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autocompleter;

import codec.language.DoubleMetaphone;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author Baxes
 */
public class Autocompleter {
    static TST dictionary;
    static PhoneticTST phonetic;
    
    public static void readFile(String dir) {
        try {
            Scanner reader = new Scanner (new File(dir));
            while (reader.hasNextLine()) {
                String word = reader.nextLine();
                dictionary.addWord(word);
                phonetic.addWord(word);
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        dictionary = new TST();
        phonetic = new PhoneticTST();
        readFile("words.txt");
        long start = System.currentTimeMillis();
        String pew = dictionary.autocomplete("mouse");
        long end = System.currentTimeMillis();
        long time1 = end - start;
        //System.out.println(pew);
        start = System.currentTimeMillis();
        pew = phonetic.search("mouse");
        end = System.currentTimeMillis();
        System.out.println(pew + "time: " + time1 + " " + (end - start));
    }
    
}
