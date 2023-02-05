/*
 * Course: CS 2852
 * Term: Spring 2021
 * Assignment Name:Lab 4
 * Name: Cherise Malisa
 * Created:6/04/2021
 */

package malisac;


import java.io.File;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.Scanner;

/**
 * class that takes in specified user file
 * name and reads in line by line
 */
public class Lab4 {

    public static void main(String[] args) throws IOException {

        String fileName, line;
        ProgramStack callStack = new ProgramStack();
        Scanner in = new Scanner(System.in);


        System.out.println("enter name of input file");
        fileName = in.nextLine();
        File file = new File(fileName);

        try {
            Scanner inputFile = new Scanner(file.toPath());
            while (inputFile.hasNextLine()) {

                line = inputFile.nextLine();
                System.out.println(line);

                if (line.contains("(") || line.contains("return")) {

                    if (line.contains("(")) {
                        callStack.callMethod(FileReaderUtils.parseMethodName(line),
                                FileReaderUtils.parseArguments(line));

                    }
                    if (line.contains("return")) {
                        if (FileReaderUtils.isVoidReturn(line)) {
                            callStack.returnFromMethod(FileReaderUtils.
                                    parseReturnValue(line).getAsInt());
                        } else {
                            callStack.returnFromMethod();
                        }
                    }
                    System.out.println(callStack.toString());

                } else {
                    System.out.println("invalid line, ignored\n");
                }

            }
        } catch (NoSuchFileException e) {
            System.out.println("file entered does not exist");
        }


    }
}