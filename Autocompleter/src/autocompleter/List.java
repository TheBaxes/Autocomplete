/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autocompleter;

/**
 *
 * @author Baxes
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

    public List(){
        start = new Node("");
        last = start;
    }

    public void add(String word){
        last = last.next = new Node(word);
    }

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
