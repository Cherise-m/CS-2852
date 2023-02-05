/*
 * Course: CS 2852
 * Term: Spring 2021
 * Assignment Name:Lab7
 * Name: Cherise Malisa
 * Created:20/04/2021
 */

package malisac;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

/**
 * class reads in files of morsecode and writes files with the
 * decoded code
 */
public class MorseDecoder {

    MorseTree<String> morseTree = new MorseTree<>();
    private static String filename;

    /**
     * reads encoded word and prints out the decoded version
     * onto a new file
     * @param path of file being read from
     * @throws IOException when file passes in is faulty
     */
    public void readMorseCode(Path path) throws IOException {

        String code, result = "";

        try (FileWriter writer = new FileWriter(filename)) {
            Scanner input = new Scanner(path);
            while (input.hasNext()) {
                code = input.next();

                if (!(code.equals("*"))) {
                    if (code.equals(".-.-")) {
                        result += "\n";
                    } else {
                        result += morseTree.decode(code);

                    }
                } else {
                    System.out.println("Warning: Skipping: *");
                }

            }
            writer.write(result);
            System.out.println(result);

        } catch (IOException e) {
            System.out.println("I/O error has occurred");
        }

    }

    public static void main(String[] args) {
        MorseDecoder morseDecoder = new MorseDecoder();
        File file = new File("data/morsecode.txt");
        morseDecoder.loadDecoder(file.toPath());

        Scanner input = new Scanner(System.in);
        System.out.println("Enter an input filename:");
        String name = input.nextLine();
        File file1 = new File(name);
        System.out.println("Enter an output filename: ");
        filename = input.nextLine();
        try {
            morseDecoder.readMorseCode(file1.toPath());
        } catch (IOException e) {
            System.out.println("Error with entered file you are attempting to read");
        }

    }

    /**
     * reads in file with morsecode and allocates
     * the symbol into the variable symbol
     * and the code for that symbol into the variable code
     *
     * @param path of file chosen
     */
    public void loadDecoder(Path path) {
        String symbol;
        String code;
        String lettersAndMorse;
        try (Scanner in = new Scanner(path)) {

            while (in.hasNextLine()) {
                lettersAndMorse = in.nextLine();
                if (lettersAndMorse.contains("\\")) {
                    symbol = lettersAndMorse.substring(0, 2);
                    code = lettersAndMorse.substring(2);
                } else {
                    symbol = String.valueOf(lettersAndMorse.charAt(0));
                    code = lettersAndMorse.substring(1);
                }
                morseTree.add(symbol, code);
            }
        } catch (IOException e) {
            System.out.println("I/O error has occurred");
        }
    }


}