/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autocompleter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Main class for autocompleter
 * @author Sebastián Patiño Barrientos
 * @author Luis Miguel Arroyave Quiñones
 */
public class Autocompleter {

    TST dictionary;
    PhoneticTST phonetic;

    /**
     * This method reads a .txt file and then calls the
     * methods addWord from the classes TST and PhoneticTST
     * @param dir The direction of the file including its name and extension
     */
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
    
    /**
     * This method inicializates the data structures TST and PhoneticTST and
     * calls the method readFile
     */
    public Autocompleter() {
        dictionary = new TST();
        phonetic = new PhoneticTST();
        readFile("words.txt");
    }
    
    /**
     * This method returns a list of words that starts with the given string 
     * @param word The word that will be used to get the list of words 
     * @return A list of words as a String
     */
    public String autocomplete(String word) {
        return dictionary.autocomplete(word);
    }
    
    /**
     * This method receives a String and returns a String with a
     * list of words sorted alphabetically with a word as a phonetical filter.
     * @param word The word that we want to use as a phonetical filter.
     * @return a list of words as a String
     */
    public String search(String word) {
        dictionary.modifySearch(word);
        return phonetic.search(word);
    }
    
    /**
     * This method looks for a word in the TST
     * @param word the wanted word
     */
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
