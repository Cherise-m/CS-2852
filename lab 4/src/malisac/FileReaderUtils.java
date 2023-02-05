/*
 * Course: CS 2852
 * Term: Spring 2021
 * Assignment Name:Lab 4
 * Name: Cherise Malisa
 * Created:6/04/2021
 */

package malisac;

import java.util.OptionalInt;

/**
 * class to take in each line of the file and handle it as needed
 */
public class FileReaderUtils {

    /**
     * checks if return line has a return value or not
     * @param line of file passed in
     * @return false if the method has a return value
     */
    public static boolean isVoidReturn(String line){
        String[] returnStatement;
        boolean isVoid = false;
        returnStatement = line.split(" ");
        if (returnStatement.length > 1) {
            isVoid = true;
        }

        return isVoid;
    }

    /**
     * returns the return value from the return line passed in
     * @param line passed in from the file
     * @return the value to be returned by method call
     */
    public static OptionalInt parseReturnValue(String line){
        String[] returnStatement;
        int length;
        OptionalInt value;

        if (line.contains("return")) {
            returnStatement = line.split(" ");
            length = returnStatement.length;
            if (returnStatement[0].equals("") && returnStatement.length > 1) {
                value = OptionalInt.of(Integer.parseInt(returnStatement[length-1]));
            } else {
                value = OptionalInt.of(Integer.parseInt(returnStatement[1]));
            }
            return value;
        }

        return OptionalInt.empty();
    }

    /**
     * gives name of the method that has been called
     * @param line of file passed in
     * @return the name of the method called
     */
    public static String parseMethodName(String line){
        String[] method = line.split(" ");
        String methodCall, name = "";
        int endOfName;

        endOfName = line.indexOf("(");
        if ((method[0].equals("")) && method.length > 1) {
            methodCall = method[2];
            endOfName = method[3].indexOf("(");
            if (line.contains("()") || methodCall.equals("void") || methodCall.equals("int")) {
                if (method[2].equals("void") || method[2].equals("int")) {
                    name = method[3].substring(0, endOfName);
                }
            }
        } else {
            //methodCall = method[0];
            if (line.contains("()")) {
                name = method[0].substring(0, endOfName);
            }
        }

        return name;
    }

    /**
     * returns integers that are passed in as arguments
     * @param line line of method call
     * @return an array of arguments from the method call
     */
    public static int[] parseArguments(String line){
        int startOfArgument, endOfArgument;
        String arguments;
        int number;
        int[] ints = new int[0];

        if (line.contains("(")) {
            startOfArgument = line.indexOf("(");
            endOfArgument = line.indexOf(")");

            arguments = line.substring(startOfArgument, endOfArgument);

            if (line.contains(",")) {
                arguments = line.substring(startOfArgument+1, endOfArgument);
                String[] numbers = arguments.split(",");
                ints = new int[numbers.length];


                for (int x = 0; x < numbers.length; x++) {
                    ints[x] = Integer.parseInt(numbers[x]);
                }
            } else if (!(line.charAt(startOfArgument + 1) == ')')) {
                ints = new int[1];
                number = Integer.parseInt(line.substring(startOfArgument + 1, endOfArgument));
                ints[0] = number;
            }

        } else {
            return null;
        }

        return ints;
    }
}