/*
 * Course: CS 2852
 * Term: Spring 2021
 * Assignment Name:Lab 4
 * Name: Cherise Malisa
 * Created:6/04/2021
 */

package malisac;

import java.util.ArrayList;
import java.util.EmptyStackException;


/**
 * class that uses Linked list organization and
 * structure to create a stack of integers
 */
public class IntStack {

    private Node<Integer> head = null;

    /**
     * adds an element to the top of the stack each time
     *
     * @param value int to be added to the stack
     */
    public void push(int value) {

        Node<Integer> next = new Node<>(value, head);

        head = next;

    }

    /**
     * removes top most element from the stack
     *  @return the element that was removed
     * @throws EmptyStackException when the stack is empty
     */
    public int pop(){
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        int currentNumber = head.getData();
        head = head.next;
        return currentNumber;
    }

    /**
     * returns the top most element in the stack
     *
     * @return element
     * @throws EmptyStackException when stack is empty
     */
    public int peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return head.getData();

    }

    public boolean isEmpty() {
        return head == null;
    }

    /**
     * calculates the size of the stack
     * @return size of stack
     */
    public int size(){
        int count = 0;
        Node<Integer> current = head;

        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    @Override
    public String toString() {
        ArrayList<String> output = new ArrayList<>();
        String out = "";
        Node<Integer> current = head;
        output.add("|          |\n");
        output.add("|----------|\n");

        while (current != null) {
            if (current.getData() < 9) {
                output.add("|        " + current.getData() + " |\n");
            } else {
                output.add("|      " + current.getData() + "|\n");
            }
            current = current.next;
        }

        for (String s : output) {
            out += s;
        }

        out += "+----------+";
        return out;
    }


    private static class Node<T> {

        private T data;
        private Node<T> next;

        private Node(T dataItem) {
            this.data = dataItem;
            this.next = null;
        }
        private Node(T dataItem, Node<T> next) {
            this.data = dataItem;
            this.next = next;
        }

        public T getData() {
            return data;
        }
    }
}