/*
 * Course: CS 2852
 * Term: Spring 2021
 * Assignment Name:Lab 4
 * Name: Cherise Malisa
 * Created:6/04/2021
 */

package malisac;

/**
 * class that creates the stack for calls in file
 */
public class ProgramStack {

    private IntStack stack;

    /**
     * constructor
     */
    public ProgramStack() {
        stack = new IntStack();

    }

    /**
     * stack result for when a method call is read in
     *
     * @param name      of the method
     * @param arguments passed into the method
     */
    public void callMethod(String name, int... arguments) {
        int size = 0;
        stack.push(methodToProgramCounter(name, arguments));
        size = size + 1;
        if (!(arguments.length == 0)) {
            for (Integer ints : arguments) {
                stack.push(ints);
                size++;
            }
        }
        stack.push(size);

    }

    /**
     * return method for when theres no return value
     */
    public void returnFromMethod() {
        for (int x = 0; x <= stack.size() + 1; x++) {
            stack.pop();
        }
    }

    /**
     * stack result for when a return statement is read in and
     * an integer is returned
     *
     * @param returnValue the return value
     */
    public void returnFromMethod(int returnValue) {
        int size = 0;
        for (int x = 0; x <= stack.size() + 1; x++) {
            stack.pop();
        }
        size = size + 1;
        stack.push(returnValue);
        size = size + 1;
        stack.push(size);

    }

    private int methodToProgramCounter(String name, int... arguments) {
        int programCounterValue, value, k = 0;
        int numberOfArguments = arguments.length;

        char[] letter = new char[name.length()];
        for (int x = 0; x < name.length(); x++) {
            letter[x] = name.charAt(x);
        }

        value = (((letter[k] * 2 + letter[k + 1]) * 2 + letter[k + 2]) * 2);

        programCounterValue = value + numberOfArguments;

        return programCounterValue;
    }

    @Override
    public String toString() {
        return stack.toString();
    }
}