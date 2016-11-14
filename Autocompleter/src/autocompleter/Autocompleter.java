/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autocompleter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import codec.language.DoubleMetaphone;

/**
 *
 * @author Baxes
 */
public class Autocompleter {
    
    public static TST readFile(String dir) {
        TST dictionary = new TST();
        try {
            Scanner reader = new Scanner (new File(dir));
            while (reader.hasNextLine()) {
                dictionary.addWord(reader.nextLine());
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }
        return dictionary;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        TST dictionary = readFile("words.txt");
        long start = System.currentTimeMillis();
        String pew = dictionary.autocomplete("e");
        long end = System.currentTimeMillis();
        long time1 = end - start;
        //System.out.println(pew);
        start = System.currentTimeMillis();
        pew = dictionary.autocomplete("hyper");
        end = System.currentTimeMillis();
        System.out.println(pew + "time: " + time1 + " " + (end - start));
        DoubleMetaphone wep = new DoubleMetaphone();
        System.out.println(wep.doubleMetaphone("hypercube"));
    }
    
}
