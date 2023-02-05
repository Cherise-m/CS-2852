/*
 * Course: CS 2852
 * Term: Spring 2021
 * Assignment Name: Lab 2
 * Name: Cherise Malisa
 * Created:18/03/2021
 */

package malisac;

/**
 * Dot class
 */
public class Dot {

    private double xcoordinate;
    private double ycoordinate;

    /**
     * dot constructor sets initial coordinates
     * @param x coordinate
     * @param y coordinate
     */
    public Dot(double x, double y) {
        xcoordinate = x;
        ycoordinate = y;
    }

    /**
     * Calculates the distance between two dots
     * @param a dot 1
     * @param b dot 2
     * @return distance between dots
     */
    private static double distance(Dot a, Dot b) {
        double distance, changey, changex;

        changex = b.getXcoordinate() - a.getXcoordinate();
        changey = b.getYcoordinate() - a.getYcoordinate();
        distance = Math.sqrt((Math.pow(changex, 2) + Math.pow(changey, 2)));

        return distance;
    }

    public double getXcoordinate() {
        return xcoordinate;
    }

    public void setXcoordinate(double xcoordinate) {
        this.xcoordinate = xcoordinate;
    }

    public double getYcoordinate() {
        return ycoordinate;
    }

    public void setYcoordinate(double ycoordinate) {
        this.ycoordinate = ycoordinate;
    }

    /**
     * calculates the critical value at the center
     * @param previous dot before the current dot
     * @param next dot after current dot
     * @return critical value of current dot
     */
    public double criticalValue(Dot previous, Dot next) {

        double critcalValue, d12, d13, d23;

        d12 = distance(previous, this);
        d13 = distance(previous, next);
        d23 = distance(this, next);

        critcalValue = d12 + d23 - d13;
        return critcalValue;
    }


}
