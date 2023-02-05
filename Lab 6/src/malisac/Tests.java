/*
 * Course: CS 2852
 * Term: Spring 2021
 * Assignment Name:
 * Name: Cherise Malisa
 * Created:13/04/2021
 */

package malisac;

import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

/**
 * class to test sorting methods
 */
public class Tests {

    private static final double NS_TO_MS = Math.pow(10.0, -6);

    public static void main(String[] args) {

        Tests tests = new Tests();
        System.out.println("The time taken to sort a randomly sorted list: "
                + tests.sort1());
        System.out.println("The time taken to sort a partially sorted list: "
                + tests.sort2());
        System.out.println("The time taken to sort a sorted list: "
                + tests.aSortedList2());
    }

    @BeforeAll
    static void start() {
        System.out.println("Tests Starting!");
    }


    @Test
    void smallerBiggerSort1() {

        List<Integer> list = new ArrayList<>();
        list.add(8);
        list.add(3);
        list.add(8);
        list.add(5);
        list.add(12);
        list.add(1);
        list.add(2);
        list.add(11);
        list.add(3);
        list.add(2);
        list.add(8);
        list.add(18);
        list.add(13);
        System.out.println(list);
        Assertions.assertEquals(6, SmallerBiggerSort.smallerBigger(list, 0, list.size() - 1));
        System.out.println(list);
    }

    @Test
    void smallerBiggerSort2() {
        List<Integer> list = new ArrayList<>();
        list.add(8);
        list.add(3);
        list.add(8);
        list.add(5);
        list.add(12);
        list.add(1);
        list.add(8);
        list.add(18);
        list.add(13);
        System.out.println(list);
        Assertions.assertEquals(3, SmallerBiggerSort.smallerBigger(list, 0, list.size() - 1));
        System.out.println(list);
    }

    @Test
    void smallerBiggerSort3() {

        List<Integer> list = new ArrayList<>();
        for (int k = 0; k < 11; k++) {
            list.add((int) (Math.random() * 20));
        }
        System.out.println(list);
        System.out.println(SmallerBiggerSort.smallerBigger(list, 0, list.size() - 1));
        System.out.println("the sorted list: \n" + list);
    }

    @Test
    void aSortedList() {
        List<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        System.out.println(list);
        Assertions.assertEquals(0, SmallerBiggerSort.smallerBigger(list, 0, 5));
        System.out.println(list);
    }

    @Test
    void recursiveSort() {
        List<Integer> list = new ArrayList<>();

        for (int k = 0; k < 11; k++) {
            list.add((int) (Math.random() * 20));
        }
        System.out.println(list);
        SmallerBiggerSort.sort(list, 0, list.size() - 1);
        System.out.println("the sorted list:\n " + list);
    }

    @Test
    void recursiveSort2() {
        List<Integer> list = new ArrayList<>();

        list.add(8);
        list.add(3);
        list.add(8);
        list.add(5);
        list.add(12);
        list.add(1);
        list.add(2);
        list.add(11);
        list.add(3);
        list.add(2);
        list.add(8);
        list.add(18);
        list.add(13);
        System.out.println(list);
        SmallerBiggerSort.sort(list, 0, list.size() - 1);
        System.out.println("the sorted list: \n" + list);
    }

    @Test
    void recursiveSort3() {
        List<Integer> list = new ArrayList<>();

        list.add(10);
        list.add(80);
        list.add(30);
        list.add(90);
        list.add(40);
        list.add(50);
        list.add(70);

        System.out.println(list);
        SmallerBiggerSort.sort(list, 0, list.size() - 1);
        System.out.println("the sorted list: \n" + list);
    }


    @Test
    double sort1() {
        List<Integer> list = new ArrayList<>();

        list.add(8);
        list.add(3);
        list.add(8);
        list.add(5);
        list.add(12);
        list.add(1);
        list.add(2);
        list.add(11);
        list.add(3);
        list.add(2);
        list.add(8);
        list.add(18);
        list.add(13);

        System.out.println(list);

        final long startTime = System.nanoTime();
        SmallerBiggerSort.sort(list);
        final long endTime = System.nanoTime();
        final long elapsed = endTime - startTime;

        System.out.println("the sorted list: \n" + list);

        return elapsed * NS_TO_MS;
    }

    @Test
    double sort2() {
        List<Integer> list = new ArrayList<>();

        list.add(10);
        list.add(80);
        list.add(30);
        list.add(90);
        list.add(40);
        list.add(50);
        list.add(70);

        System.out.println(list);

        final long startTime = System.nanoTime();
        SmallerBiggerSort.sort(list);
        final long endTime = System.nanoTime();
        final long elapsed = endTime - startTime;

        System.out.println("the sorted list: \n" + list);

        return elapsed * NS_TO_MS;
    }

    @Test
    double aSortedList2() {
        System.out.println("sorting a sorted list with sort");
        List<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        System.out.println(list);

        final long startTime = System.nanoTime();
        SmallerBiggerSort.sort(list);
        final long endTime = System.nanoTime();
        final long elapsed = endTime - startTime;

        System.out.println(list);

        return elapsed * NS_TO_MS;
    }


    @AfterAll
    static void end() {
        System.out.println("Tests Ending");
    }

}
