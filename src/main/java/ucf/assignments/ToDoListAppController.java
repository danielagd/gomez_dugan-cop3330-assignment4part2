package ucf.assignments;

/*
 *  UCF COP3330 Fall 2021 Assignment 4 Solution
 *  Copyright 2021 Daniela Gomez-Dugan
 */

import javafx.fxml.FXML;
import java.util.ArrayList;
import java.util.List;

public class ToDoListAppController {
    @FXML
    private List<ToDoList> lists;

    @FXML
    protected void addList() {
        // if name entered in text box
        // create ToDo_List object with name passed
        // add to lists
    }

    @FXML
    protected void removeList() {
        // if name entered in text box
        // remove ToDo_List object with name passed from lists
    }

    @FXML
    protected void saveList(){
        // if name of list entered in text box
        // loop through lists
        // if list name found
        // parse through list items and add to file
        // save file to external storage
    }

    @FXML
    protected void saveAllLists(){
        // if button pressed
        // loop through lists
        // parse though list and add items to file
        // save file to external storage
    }

    @FXML
    protected void loadList() {
        // if file was entered in text box
        // add_new_list(file path)
        // Parse through file
        // add_item() with each line
    }
}

class ToDoList {
    protected String title;
    protected List<Item> list;

    protected ToDoList(String title){
        this.title = title;
        list = new ArrayList<Item>();
    }

    protected ToDoList() {
        list = new ArrayList<Item>();
        this.title = "";
    }

    protected String getTitle(){ return title; }

    protected static void editListTitle() {
        // if new title was entered in text box
        // loop through list to find object
        // if object found
        // replace title with new_title
    }
    protected static void addItem() {
        // if item entered in text box and not in list
        // create Item object with information passed
        // add to list
    }
    protected static void removeItem() {
        // if item entered in text box
        // remove Item object with information passed from lists
    }
    protected static void displayItems() {
        // if button pressed
        // loop through list
        // print each item
    }
    protected static void displayCompletedItems() {
        // if button pressed
        // loop through list
        // if item.completed is true
        // print item
    }
    protected static void displayIncompleteItems() {
        // if button pressed
        // loop through list
        // if item.completed is false
        // print item
    }

}

class Item extends ToDoList {
    protected String description;
    protected String dueDate;
    protected boolean completed;

    protected Item(String description, String date) {
        this.description = description;
        this.dueDate = date;
        this.completed = false;
    }

    protected String getDescription() {
        return description;
    }

    protected String getDueDate() {
        return dueDate;
    }

    protected boolean isCompleted() {
        return completed;
    }

    protected static void editDescription() {
        // if description entered in text box
        // replace description with the string passed in
    }

    protected static void editDueDate() {
        // if due date entered in text box and in "YYYY-MM-DD"
        // replace due date with the string passed in
    }

    protected static void markComplete() {
        // if button pressed
        // change completed field to be true
    }
}