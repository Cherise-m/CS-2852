/*
 * Course: CS 2852
 * Term: Spring
 * Assignment Name: Lab 1
 * Name: Cherise Malisa
 * Created:10/03/2021
 */

package malisac;

import java.util.List;

/**
 * class that handles request list
 */
public class WaitingList {

    private List<ItemRequest> requests;

    /**
     * constructor of waiting list class
     * populates the list with requests
     *
     * @param requests from the item request class
     */
    public WaitingList(List<ItemRequest> requests) {
        this.requests = requests;

    }

    /**
     * returns the first item in stock
     *
     * @param isFulfillable from benchmark class
     * @return null or the item request if found
     */
    public ItemRequest nextFulfillableRequest(boolean isFulfillable) {
        ItemRequest itemRequest = null;
        if (isFulfillable) {
            itemRequest = requests.remove(0);
        } else {
            for (ItemRequest request : requests) {

            }
        }
        return itemRequest;
    }

    /**
     * adds requests to the list
     *
     * @param request list of requests is passed in
     */
    public void requestItem(ItemRequest request) {
        requests.add(request);

    }

    /**
     * checks list for requests under a specific userId and adds them
     * to the user requests list
     *
     * @param userId       user id for request
     * @param userRequests list of user requested items
     */
    public void getAllRequestsFromUser(int userId, List<ItemRequest> userRequests) {

        for (ItemRequest request : requests) {
            if (request.getUserId() == userId) {
                userRequests.add(request);
            }
        }

    }

    /**
     * removes a specifi request from the list
     *
     * @param request list of requests
     * @return true if request has been removed false otherwise
     */
    public boolean cancelRequest(ItemRequest request) {
        for (int x = 0; x < requests.size(); x++) {
            if (requests.get(x).equals(request)) {
                requests.remove(x);
                return true;
            }
        }
        return false;
    }

    /**
     * removes all elements in a given list
     */
    public void clear() {
        requests.clear();
    }

    /**
     * checks if the list is empty
     *
     * @return true if it is and false if it is not
     */
    public boolean isEmpty() {
        return requests.isEmpty();
    }
}
