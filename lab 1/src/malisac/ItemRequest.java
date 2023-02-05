/*
 * Course: CS 2852
 * Term:Spring
 * Assignment Name: Lab 1
 * Name: Cherise Malisa
 * Created:10/03/2021
 */

package malisac;

/**
 * class that populates requests as they come in
 */
public class ItemRequest {

    private final int userId;
    private final int itemId;

    /**
     * sets the userId and itemId specifi to request
     * @param userId from benchmark class
     * @param itemId from benchmark class
     */
    public ItemRequest(int userId, int itemId) {
        this.userId = userId;
        this.itemId = itemId;
    }

    public int getUserId() {
        return userId;
    }

    public int getItemId() {
        return itemId;
    }
}