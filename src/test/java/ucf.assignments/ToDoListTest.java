package ucf.assignments;

/*
 *  UCF COP3330 Fall 2021 Assignment 4 Solution
 *  Copyright 2021 Daniela Gomez-Dugan
 */

import org.junit.jupiter.api.Test;

public class ToDoListTest {
    // create test list with title

    @Test
    public void editListTitle() {
        // call editListTitle from ToDoList and change title to global list
        // assert the title is equal to new title passed
    }

    @Test
    public void addItem() {
        // add a new item to global list by calling addItem from ToDoList
        // assert list contains new item
    }

    @Test
    public void removeItem() {
        // remove item from global list by calling removeItem from ToDoList
        // assert the list does not contain the item
    }

    @Test
    public void displayItems() {
        // loop through list
        // return contents to a temp list
        // compare global list and temp list
    }

    @Test
    public void displayCompletedItems() {
        // loop through global list
        // if item is completed
        // add to temp list
        // assert the temp list contains all complete items
    }

    @Test
    public void displayIncompleteItems() {
        // loop through global list
        // if item is incomplete
        // add to temp list
        // assert the temp list contains all complete items
    }
}

