/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autocompleter1;

/**
 * Trie data structure for finding words in a list in O(log N)
 * @author Sebastián Patiño Barrientos
 */
public class Trie {
    /** Root of the Trie data structure */
    Node root;
    
    /**
     * Definition of the node class that will be used in this data structure
     */
    private class Node {
        /** The letter that will be contained in this node */
        char letter;
        
        /** Maximum weight of a word in this path */
        int max;
        
        /** If this is also an end node, this is the weight of the selected word */
        int weight; 
        
        /**
         * Constructor for the Node class
         * @param letter The character that will be asigned to this node
         */
        public Node(char letter) {
            this.letter = letter;
            max = 0;
            weight = 0;
        }
    }
    
    
    
}
