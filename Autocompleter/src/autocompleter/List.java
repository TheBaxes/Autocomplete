/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autocompleter;

/**
 * @author Sebastián Patiño Barrientos
 * @author Luis Miguel Arroyave Quiñones
 */
public class List {

    Node start;
    Node last;

    private class Node {

        String word;
        Node next;

        public Node(String word){
            this.word = word;
            next = null;
        }
    }

    /**
     * Constructor for the List Class
     */
    public List(){
        start = new Node("");
        last = start;
    }

    /**
     * This method inserts a word to the List
     * @param word the word that we want to add to the list
     */
    public void add(String word){
        last = last.next = new Node(word);
    }

    /**
     * This method returns a list of words without an specific word
     * @param word The word that we don't want to be on the list
     * @return A list of words
     */
    public String getListWithout(String word){
        StringBuilder list = new StringBuilder();
        Node node = start;
        while (node.next != null) {
            node = node.next;
            if (!node.word.equals(word)) {
                list.append(node.word + "\n");
            }
        }
        return list.toString();
    }
}
