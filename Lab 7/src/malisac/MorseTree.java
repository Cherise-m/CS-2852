/*
 * Course: CS 2852
 * Term: Spring 2021
 * Assignment Name:Lab7
 * Name: Cherise Malisa
 * Created:20/04/2021
 */

package malisac;

/**
 * The class that administers the creation of the tree that
 * stores the alphabet as symbols and their corresponding code values
 *
 * @param <E> generic type
 */
public class MorseTree<E> {

    Node root;

    /**
     * constructor
     * initialises the root of the tree to empty
     */
    public MorseTree() {
        root = new Node(null);
    }

    /**
     * adds symbols and their corresponding code values to the binary tree
     *
     * @param symbol of the word passed in
     * @param code   the code to represent the word passed in
     */
    public void add(E symbol, String code) {
        Node current = root;
        for (int i = 0; i < code.length(); i++) {

            if (code.charAt(i) == '.') {
                if (current.lKid == null) {
                    current.lKid = new Node(null);
                }
                current = current.lKid;

            } else if (code.charAt(i) == '-') {
                if (current.rKid == null) {
                    current.rKid = new Node(null);
                }
                current = current.rKid;
            }
        }

        current.value = symbol;
    }

    /**
     * takes in a code and searches the tree for it corresponding letter
     *
     * @param code in the line passed in
     * @return returns the decoded symbol
     * @throws IllegalArgumentException if code is invalid
     */
    public E decode(String code) {
        E result;
        result = searchTree(root, code);
        return result;
    }

    private E searchTree(Node current, String code) {

        if (current == null) {
            return null;
        }

        if (code.length() == 0) {
            return current.value;
        } else {
            if (code.charAt(0) == '.') {
                return searchTree(current.lKid, code.substring(1));
            } else if (code.charAt(0) == '-') {
                return searchTree(current.rKid, code.substring(1));
            }

        }
        return null;
    }

    class Node {
        E value;
        public Node lKid;
        public Node rKid;

        public Node(E value) {
            this.value = value;
            rKid = null;
            lKid = null;
        }

    }
}