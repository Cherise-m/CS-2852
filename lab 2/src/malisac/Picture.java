/*
 * Course: CS 2852
 * Term: Spring 2021
 * Assignment Name: Lab 2
 * Name: Cherise Malisa
 * Created:18/03/2021
 */
package malisac;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;


/**
 * picture class that reads in files
 * draws lines and dots
 * removes dots
 */
public class Picture {

    double ycoordinate, xcoordinate;
    List<Dot> dots;
    private static List<Dot> originalDots;
    Dot dot;


    Picture(List<Dot> emptyList) {
        dots = emptyList;
        originalDots = emptyList;
    }

    Picture(Picture original, List<Dot> emptyList) {
        dots = emptyList;
        emptyList.addAll(original.dots);
    }

    /**
     * /**
     * reads coordinates in the file line by line
     *
     * @param path path of the file
     * @throws IOException if the file is not correct
     */
    public void load(Path path) throws IOException {
        Scanner in = new Scanner(path);

        while (in.hasNextLine()) {
            String[] coordinate = in.nextLine().split(",");
            xcoordinate = Double.parseDouble(coordinate[0]);
            ycoordinate = Double.parseDouble(coordinate[1]);
            dot = new Dot(xcoordinate, ycoordinate);
            dots.add(dot);
            originalDots.add(dot);
        }

    }

    /**
     * writes a new picture of type dots
     *
     * @param path path where file will be stored
     * @throws FileNotFoundException file chosen does not exist
     */
    public void save(Path path) throws FileNotFoundException {

        PrintWriter writer = new PrintWriter(path.toFile());
        for (Dot dot : dots) {
            writer.write(dot.getXcoordinate() + ", " + dot.getYcoordinate());
            writer.write("\n");
        }
        writer.close();


    }

    /**
     * draws dots from the given list of coordinates
     *
     * @param canvas where picture will be displayed
     */
    public void drawDots(Canvas canvas) {
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        for (Dot dot : dots) {
            graphicsContext.fillOval(dot.getXcoordinate() * canvas.getWidth(),
                    (1 - dot.getYcoordinate()) * canvas.getHeight(), 5, 5);

        }

    }

    /**
     * draws lines from one dot to another
     *
     * @param canvas where picture will be displayed
     */
    public void drawLines(Canvas canvas) {
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        graphicsContext.beginPath();
//        graphicsContext.moveTo(dot.getXcoordinate() * canvas.getWidth(),
//                (1 - dot.getYcoordinate()) * canvas.getHeight());
        for (Dot dots : dots) {
            graphicsContext.lineTo(dots.getXcoordinate() * canvas.getWidth(),
                    (1 - dots.getYcoordinate()) * canvas.getHeight());

        }
        graphicsContext.closePath();
        graphicsContext.stroke();
    }

    /**
     * removes the dots with lowest critical value until
     * number of dots desired remains
     *
     * @param strategy      method chosen to removes certain number of dots to reach
     *                      number of dots desired by user
     * @param numberDesired number of dots user wishes to keep in picture
     */
    public void removeDots(int numberDesired, String strategy) {
        if (strategy.equals("a")) {
            removeDotsIndex(dots, numberDesired);
            System.out.println("removing dots using index");
            System.out.println("\nnumber desired, " + numberDesired +
                    "time taken in ns, " + removeDotsIndex(dots, numberDesired));
        } else if (strategy.equals("b")) {
            removeDotsIterator(dots, numberDesired);
            System.out.println("removing dots using iterator");
            System.out.println("\nnumber desired, " + numberDesired +
                    "time taken in ns, " + removeDotsIterator(dots, numberDesired));
        }

    }


    private static long removeDotsIndex(List<Dot> dots, int numberDesired) {

        final long startTime = System.nanoTime();

        double value;
        int totalDots = dots.size();
        double firstDotCriticalVal, lastDotCriticalVal;

        if (numberDesired < 3) {
            throw new IllegalArgumentException();

        }

        if (numberDesired > totalDots) {
            dots = originalDots;
            totalDots = originalDots.size();
        }

        while (totalDots > numberDesired) {

            double lowestVal = Double.MAX_VALUE;
            int smallIdx = -1;

            firstDotCriticalVal = dots.get(0).criticalValue(dots.get(totalDots - 1), dots.get(1));
            lastDotCriticalVal = dots.get(totalDots - 1).criticalValue(dots.get(totalDots - 2),
                    dots.get(0));

            if (firstDotCriticalVal < lowestVal) {
                lowestVal = firstDotCriticalVal;
                smallIdx = 0;
            } else if (lastDotCriticalVal < lowestVal) {
                lowestVal = lastDotCriticalVal;
                smallIdx = totalDots - 1;
            }

            for (int x = 1; x < totalDots - 1; x++) {

                Dot previous = dots.get(x - 1);
                Dot next = dots.get(x + 1);
                Dot currentdot = dots.get(x);

                value = currentdot.criticalValue(previous, next);

                if (value < lowestVal) {
                    lowestVal = value;
                    smallIdx = x;
                }
            }

            dots.remove(smallIdx);
            totalDots--;
        }

        final long endTime = System.nanoTime();

        return endTime - startTime;

    }

    private static long removeDotsIterator(Collection<Dot> dots, int numberDesired) {

        final long startTime = System.nanoTime();

        double value;
        int totalDots = dots.size();
        double firstDotCriticalVal, lastDotCriticalVal;
        Dot firstDot, lastDot = null, lowestValueDot = null,
                secondToLast = null, current, previous, next;

        if (numberDesired < 3) {
            throw new IllegalArgumentException();
        }

        if (numberDesired > totalDots) {
            dots = originalDots;
            totalDots = originalDots.size();
        }

        while (totalDots > numberDesired) {

            double lowestVal = Double.MAX_VALUE;

            Iterator<Dot> previousIterator = dots.iterator();
            Iterator<Dot> currentIterator = dots.iterator();
            Iterator<Dot> nextIterator = dots.iterator();

            firstDot = previousIterator.next();
            currentIterator.next();
            Dot secondDot = currentIterator.next();
            previous = firstDot;
            current = secondDot;
            nextIterator.next();
            nextIterator.next();


            while (nextIterator.hasNext()) {

                next = nextIterator.next();
                value = current.criticalValue(previous, next);

                if (value < lowestVal) {
                    lowestVal = value;
                    lowestValueDot = current;
                }
                previous = previousIterator.next();
                current = currentIterator.next();

                secondToLast = current;
                lastDot = next;
                
            }

            firstDotCriticalVal = firstDot.criticalValue(lastDot, secondDot);
            lastDotCriticalVal = lastDot.criticalValue(secondToLast, firstDot);

            if (firstDotCriticalVal < lowestVal) {
                lowestValueDot = firstDot;
            } else if (lastDotCriticalVal < lowestVal) {
                lowestValueDot = lastDot;
            }

            dots.remove(lowestValueDot);
            totalDots--;

        }

        final long endTime = System.nanoTime();
        return endTime - startTime;
    }

}