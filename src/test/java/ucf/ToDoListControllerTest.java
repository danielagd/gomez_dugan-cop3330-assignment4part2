package ucf;

/*
 *  UCF COP3330 Fall 2021 Assignment 4 Solution
 *  Copyright 2021 Daniela Gomez-Dugan
 */

import org.junit.jupiter.api.Test;

public class ToDoListControllerTest {

    // create global list of lists

    @Test
    public void addList() {
        // create to do list
        // add list to global list by calling addList from ToDoListController
        // assert global list contains passed in list
    }

    @Test
    public void removeList() {
        // remove a list from the global list by calling removeList from ToDoListController
        // assert global list does not contain passed in list
    }

    @Test
    public void saveList() {
        // create test file
        // loop through one list from global list
        // add contents to test file
        // call saveList from ToDoListController with same list
        // parse both files
        // assert files are equal in contents
    }

    @Test
    public void saveAllLists() {
        // create test file
        // loop through global list
        // add contents to test file
        // call saveList from ToDoListController with global list
        // parse both files
        // assert files are equal in contents
    }

    @Test
    public void loadList() {
        // create temp list
        // open file
        // parse file and add to temp list
        // call loadList from ToDoListController with same file
        // assert both lists are equal
    }
}
