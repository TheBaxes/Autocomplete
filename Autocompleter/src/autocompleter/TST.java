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
public class TST {

    /**
     * Root of the TST data structure
     */
    Node root;

    /**
     * Definition of the node class that will be used in this data structure
     */
    private class Node {

        /**
         * The character that will be contained in this node
         */
        char letter;

        /**
         * Maximum weight of a word in this path
         */
        int max;

        /**
         * If this is also an end node, this is the weight of the selected word
         */
        int weight;

        /**
         * Children of this node
         */
        Node left;
        Node middle;
        Node right;

        /**
         * Depth of this node for balancing it
         */
        int depth;

        /**
         * Constructor for the Node class
         *
         * @param letter The character that will be asigned to this node
         */
        public Node(char letter){
            this.letter = letter;
            max = 0;
            weight = -1;

            left = null;
            middle = null;
            right = null;

            depth = 1;
        }
    }

    public TST(){
        root = null;
    }

    public void addWord(String word){
        root = addWord(word, root);
    }

    private Node addWord(String word, Node node){
        if (!word.equals("")){

            char letter = word.charAt(0);

            if (node == null){
                node = new Node(letter);
            }

            if (letter == node.letter){
                node.middle = addWord(word.substring(1), node.middle);
                if (word.length() == 1) {
                    node.weight = 0;
                }
            } else {
                if (letter > node.letter) {
                    node.right = addWord(word, node.right);
                } else {
                    node.left = addWord(word, node.left);
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

    public String autocomplete(String word){
        return autocomplete(word, "", root);
    }

    private String autocomplete(String word, String back, Node node){
        if (node == null) {
            return "";
        }

        if (word.length() < 1){
            StringBuilder text = new StringBuilder();
            text.append(autocomplete("", back, node.left));
            if (node.weight > -1) {
                text.append(back + node.letter + "\n");
            }
            text.append(autocomplete("", back + node.letter, node.middle));
            text.append(autocomplete("", back, node.right));
            return text.toString();
        }

        char letter = word.charAt(0);

        if (letter == node.letter){
            String extra = "";
            if (word.length() == 1 && node.weight > -1) {
                extra = back + letter + "\n";
            }
            return extra + autocomplete(word.substring(1), back + letter, node.middle);
        } else if (letter > node.letter) {
            return autocomplete(word, back, node.right);
        }

        return autocomplete(word, back, node.left);
    }

    public void modifySearch(String word){
        modifySearch(word, root);
    }

    private int modifySearch(String word, Node node){
        int letter = word.charAt(0);

        if (word.length() == 1){
            if (node.letter == letter && node.weight > -1){
                node.weight++;
                return node.max = Math.max(node.max, node.weight);
            }
            return 0;
        }

        if (letter == node.letter) {
            node.max = Math.max(node.max, modifySearch(word.substring(1), node.middle));
        } else if (letter > node.letter) {
            node.max = Math.max(node.max, modifySearch(word, node.right));
        } else {
            node.max = Math.max(node.max, modifySearch(word, node.left));
        }

        return node.max;
    }

    private int calcMaxWeight(Node node){
        int l = (node.left == null) ? 0 : node.left.max;
        int m = (node.middle == null) ? 0 : node.middle.max;
        int r = (node.right == null) ? 0 : node.right.max;
        return Math.max(Math.max(l, m), r);
    }
   
    public String toString() {
        return toString(root, "");
    }

    private String toString(Node node, String back) {
        String words = "";

        if (node.left != null){
            words += toString(node.left, back);
        }
        
        if (node.weight > -1) words += back + "\n";
        if (node.middle != null){
            words += toString(node.middle, back + node.letter);
        }

        if (node.right != null){
            words += toString(node.right, back);
        }
        return words;
    }
    public static void main(String[] args){
        TST test = new TST();
        test.addWord("pew");
        test.addWord("lol");
        test.addWord("ola");
        test.addWord("asdf");
        test.addWord("baka");
        test.addWord("lolrus");
        test.addWord("lolpew");
        test.addWord("pewwep");
        test.addWord("asdfghjkl");
        test.addWord("alo");
        test.addWord("alo polisia");
        test.addWord("baka baka baka");
        test.addWord("notice");
        test.addWord("notice me senpai");
        test.addWord("ola kp2");
        test.addWord("potato");
        test.addWord("pepe");
        test.addWord("piu");
        test.addWord("");
        test.addWord("plop");
        test.addWord("patata");
        test.addWord("ploafmoasdflweia");

        System.out.println(test.autocomplete("p"));
    }
}
