/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autocompleter;

import codec.language.DoubleMetaphone;
import java.util.Scanner;

/**
 *
 * @author Baxes
 */
public class PhoneticTST {
    /** Root of the TST data structure */
    Node root;
    DoubleMetaphone sound;

    /**
     * Definition of the node class that will be used in this data structure
     */
    private class Node {

        /** The character that will be contained in this node */
        char letter;

        /** If this is also an end node, this is the weight of the selected word */
        List words;

        /** Children of this node */
        Node left;
        Node middle;
        Node right;

        /** Depth of this node for balancing it */
        int depth;

        /**
         * Constructor for the Node class
         * @param letter The character that will be asigned to this node
         */
        public Node(char letter){
            this.letter = letter;
            words = null;

            left = null;
            middle = null;
            right = null;

            depth = 1;
        }
    }

    public PhoneticTST(){
        root = null;
        sound = new DoubleMetaphone();
    }

    public void addWord(String word){
        String code = sound.doubleMetaphone(word);
        if (code != null) root = addWord(code, root, word);
    }

    private Node addWord(String code, Node node, String word){
        if (!code.equals("")){

            char letter = code.charAt(0);

            if (node == null){
                node = new Node(letter);
            }

            if (letter == node.letter){
                node.middle = addWord(code.substring(1), node.middle, word);
                if (code.length() == 1) {
                    if (node.words == null) node.words = new List();
                    node.words.add(word);
                }
            } else {
                if (letter > node.letter) {
                    node.right = addWord(code, node.right, word);
                } else {
                    node.left = addWord(code, node.left, word);
                }

                int diff = getLeftDepth(node) - getRightDepth(node);
                if (diff >= 2) {
                    node = rotateL(node);
                } else if (diff <= -2) {
                    node = rotateR(node);
                }
                node.depth = Math.max(getLeftDepth(node), getRightDepth(node)) + 1;
            }

        }

        return node;
    }

    private int getRightDepth(Node node){
        if (node.right == null) {
            return 0;
        }
        return node.right.depth;
    }

    private int getLeftDepth(Node node){
        if (node.left == null) {
            return 0;
        }
        return node.left.depth;
    }

    private Node rotateL(Node root){
        Node pivot = root.left;
        if (getLeftDepth(pivot) < getRightDepth(pivot)) {
            pivot = rotateLR(pivot);
        }
        root.left = pivot.right;
        pivot.right = root;
        root.depth = Math.max(getLeftDepth(root), getRightDepth(root)) + 1;
        return pivot;
    }

    private Node rotateLR(Node root){
        Node pivot = root.right;
        root.right = pivot.left;
        pivot.left = root;
        return pivot;
    }

    private Node rotateR(Node root){
        Node pivot = root.right;
        if (getLeftDepth(pivot) > getRightDepth(pivot)) {
            pivot = rotateRL(pivot);
        }
        root.right = pivot.left;
        pivot.left = root;
        root.depth = Math.max(getLeftDepth(root), getRightDepth(root)) + 1;
        return pivot;
    }

    private Node rotateRL(Node root){
        Node pivot = root.left;
        root.left = pivot.right;
        pivot.right = root;
        return pivot;
    }

    public String search(String word){
        String code = sound.doubleMetaphone(word);
        int max = (code.length() <= 2)? 1: 2;
        Scanner read = new Scanner(search(code, root, word, 0, max));
        TST order = new TST();
        while (read.hasNextLine()) {
            order.addWord(read.nextLine());
        }
        return order.toString();
    }

    private String search(String code, Node node, String word, int edits, int max){
        String words = "";
        
        if (edits <= max) {
            if (node.left != null) {
                words += search(code, node.left, word, edits, max);
            }

            char letter = code.charAt(0);

            int add = (node.letter != letter) ? 1 : 0;

            if (code.length() == 1){
                if (edits + add <= max && node.words != null){
                    words += node.words.getListWithout(word);
                }
            } else if (node.middle != null) {
                words += search(code.substring(1), node.middle, word, edits + add, max);
            }

            if (node.right != null) {
                words += search(code, node.right, word, edits, max);
            }
        }
        return words;
    }

    public static void main(String[] args){
        PhoneticTST test = new PhoneticTST();
        test.addWord("mouse");
        test.addWord("house");
        test.addWord("trouse");
        test.addWord("blouse");
        test.addWord("grouse");
        test.addWord("rouse");
        System.out.println(test.search("mouse"));
    }
}
