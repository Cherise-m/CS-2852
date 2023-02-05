/*
 * Course: CS 2852
 * Term: Spring 2021
 * Assignment Name:Lab 6
 * Name: Cherise Malisa
 * Created:13/04/2021
 */

package malisac;

import java.util.List;

class SmallerBiggerSort {

    private static int called = 0;

    /**
     * method puts the first element in its correct position
     * making sure the elements before it are less that first
     * and the elements after it are greater than first
     *
     * This method was inspired by the partition method from
     * https://www.geeksforgeeks.org/quick-sort/
     *
     * @param list list to be sorted
     * @param start start of the list
     * @param end end of the list
     * @param <T> generic list variable
     * @return the last index of the element that was sorted
     */
    public static <T extends Comparable<T>> int smallerBigger(List<T> list, int start, int end) {
        T first = list.get(start);
        T temp;
        int smallIdx = start - 1;
        int sorted = 0;

        for (int x = start; x <= end - 1; x++) {
            if (list.get(x).compareTo(first) < 0) {

                smallIdx++;
                temp = list.get(smallIdx);
                list.set(smallIdx, list.get(x));
                list.set(x, temp);
                sorted++;
            }
        }

        if (sorted > 0 || list.get(end).compareTo(first) < 0) {
            temp = list.get(smallIdx + 1);
            list.set(smallIdx + 1, list.get(end));
            list.set(end, temp);
        }

        if (list.indexOf(first) != 0) {
            int num = list.indexOf(first) - 1;
            while (list.get(num).compareTo(first) > 0) {
                temp = list.get(num);
                list.set(num, first);
                list.set(list.indexOf(first) + 1, temp);
                num = list.indexOf(first) - 1;
            }
        }

        if (called > 0) {
            if (list.get(end).compareTo(list.get(end - 1)) < 0) {
                temp = list.get(end);
                list.set(end, list.get(end - 1));
                list.set(end - 1, temp);
            }
        }

        return list.indexOf(first);
    }

    /**
     * method recursively calls and sorts the whole list
     * from beginning to end
     *
     * @param list list to be sorted
     * @param start the start of the list
     * @param end the end of the list
     * @param <T> a generic variable
     */
    public static <T extends Comparable<T>> void sort(List<T> list, int start, int end) {
        called++;
        if (list != null && list.size() >= end && start + 1 < end) {

            int result = smallerBigger(list, start, end);

            sort(list, start, result - 1);
            sort(list, result + 1, end);

        }
    }

    /**
     * method calls the recursively sorted method
     * @param list list to be passed in
     * @param <T> generic variable
     */
    public static <T extends Comparable<T>> void sort(List<T> list) {
        sort(list, 0, list.size()-1);
    }
}