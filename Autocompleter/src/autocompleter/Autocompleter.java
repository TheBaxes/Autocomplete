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

    TST dictionary;
    PhoneticTST phonetic;

    public void readFile(String dir){
        try{
            Scanner reader = new Scanner(new File(dir));
            while (reader.hasNextLine()) {
                String word = reader.nextLine();
                dictionary.addWord(word);
                phonetic.addWord(word);
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }
    }
    
    public Autocompleter() {
        dictionary = new TST();
        phonetic = new PhoneticTST();
        readFile("words.txt");
    }
    
    public String autocomplete(String word) {
        return dictionary.autocomplete(word);
    }
    
    public String search(String word) {
        dictionary.modifySearch(word);
        return phonetic.search(word);
    }
    
    public void modifySearch(String word) {
        dictionary.modifySearch(word);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
//        dictionary = new TST();
//        phonetic = new PhoneticTST();
//        readFile("words.txt");
//        long start = System.currentTimeMillis();
//        String pew = dictionary.autocomplete("mouse");
//        long end = System.currentTimeMillis();
//        long time1 = end - start;
//        //System.out.println(pew);
//        dictionary.modifySearch("mouseweb");
//        start = System.currentTimeMillis();
//        pew = dictionary.autocomplete("mouse");
//        //pew = phonetic.search("mouse");
//        end = System.currentTimeMillis();
//        System.out.println(pew + "time: " + time1 + " " + (end - start));
    }

}
