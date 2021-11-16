package ucf.assignments;

/*
 *  UCF COP3330 Fall 2021 Assignment 4 Solution
 *  Copyright 2021 Daniela Gomez-Dugan
 */

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ToDoListAppController {
    @FXML
    private TextArea dueDateError;

    @FXML
    private TextArea dueDateError2;

    @FXML
    private TextArea display;

    @FXML
    private TextField filePathForList;

    @FXML
    private TextField newItemDescription;

    @FXML
    private TextField newItemDueDate;

    @FXML
    private TextField itemNumber;

    @FXML
    private TextField editItemDescription;

    @FXML
    private TextField editItemDueDate;

    @FXML
    private TextField removeItemGiven;

    @FXML
    private TextField itemDueDate;

    public List<Item> toDoList = new ArrayList<Item>();

    // Gets description and due date. Checks to see if due date is valid; if so, then add item to list
    @FXML
    public void addItemGateway(){
        String itemDescription = newItemDescription.getText();
        String itemDueDate = newItemDueDate.getText();
        String dueDateValidity = dueDateValid(itemDueDate);
        if (dueDateValidity.equals(""))
            addItem(itemDescription, itemDueDate);
        else
            dueDateError.setText(dueDateValidity);
    }

    // Add item passed
    @FXML
    public void addItem(String description, String dueDate) {
        Item newItem = new Item(description, dueDate);
        toDoList.add(newItem);
        dueDateError.setText(description + " is added!");
    }

    // Gets item number to remove from fields
    @FXML
    public void removeItemGateway(){
        int removeNum = Integer.parseInt(removeItemGiven.getText());
        removeItem(removeNum-1);
    }

    // Remove item passed
    @FXML
    public void removeItem(int itemNumber) {
        Item removed = toDoList.remove(itemNumber);
        System.out.println(removed.description);
    }

    // Remove all items from list
    @FXML
    public void clearItems(){
        toDoList.clear();
    }

    // Loop through list and assign to output to print
    @FXML
    public void displayAllItems() {
        int size = toDoList.size();
        String output = "";

        if (size == 0)
            output = "To-Do list is currently empty.";

        for(int i = 0; i < size; i++)
        {
            output += String.valueOf(i+1) + ": ";
            output += toDoList.get(i).getDescription() + ", ";
            output += toDoList.get(i).getDueDate();
            if(toDoList.get(i).isCompleted())
                output += ", Completed.";
            else
                output += ", Incomplete.";
            output += "\n";
        }

        display.setText(output);
    }

    // Loop through list and assign to output to print only if completed field is true
    @FXML
    public void displayCompletedItems() {
        int size = toDoList.size();
        String output = "";

        if (size == 0)
            output = "To-Do list is currently empty.";

        for(int i = 0; i < size; i++)
        {
            if(toDoList.get(i).isCompleted())
            {
                output += String.valueOf(i+1) + ": ";
                output += toDoList.get(i).getDescription() + ", ";
                output += toDoList.get(i).getDueDate() + "\n";
            }
        }

        display.setText(output);
    }

    // Loop through list and assign to output to print only if completed field is false
    @FXML
    public void displayIncompleteItems() {
        int size = toDoList.size();
        String output = "";

        if (size == 0)
            output = "To-Do list is currently empty.";

        for(int i = 0; i < size; i++)
        {
            if(!toDoList.get(i).isCompleted())
            {
                output += String.valueOf(i+1) + ": ";
                output += toDoList.get(i).getDescription() + ", ";
                output += toDoList.get(i).getDueDate() + "\n";
            }
        }

       display.setText(output);
    }

    // Loop through list and output to external file
    @FXML
    public void saveList() throws IOException {
        FileWriter fw = new FileWriter("src/main/java/ucf/assignments/ToDoList.txt");
        PrintWriter fp = new PrintWriter(fw);
        int size = toDoList.size();
        String output = "";

        if (size == 0)
            output = "To-Do list is currently empty.";

        for(int i = 0; i < size; i++)
        {
            output += String.valueOf(i+1) + ": ";
            output += toDoList.get(i).getDescription() + ", ";
            output += toDoList.get(i).getDueDate();
            if(toDoList.get(i).isCompleted())
                output += ", Completed.";
            else
                output += ", Incomplete.";
            output += "\n";
        }

        fp.printf(output);
        fp.close();
    }

    // Get file path from field and pass into loadList
    @FXML
    public void loadGateway() throws FileNotFoundException {
        String fp = filePathForList.getText();
        loadList(fp);
    }

    // Parse through file and add to list
    @FXML
    public void loadList(String filePath) throws FileNotFoundException {
        File fp = new File(filePath);
        Scanner input = new Scanner(fp);

        String temp = "";
        String[] fields;

        while(input.hasNextLine())
        {
            temp = input.nextLine();
            fields = temp.split(", ", 3);
            toDoList.add(new Item(fields[0], fields[1]));
        }
        input.close();
    }

    // Get item number from field, checks validity of number, and passes to markComplete
    @FXML
    public void markCompleteGateway(){
        int itemNum = Integer.parseInt(itemNumber.getText());
        if (itemNum <= 0 || itemNum > toDoList.size())
            dueDateError2.setText("Item number out of range.");
        markComplete(itemNum-1);
    }

    @FXML
    public void markComplete(int itemNum) {
        toDoList.get(itemNum).setComplete();
    }

    // Get item number from field, checks validity of number, and passes to markIncomplete
    @FXML
    public void markIncompleteGateway(){
        int itemNum = Integer.parseInt(itemNumber.getText());
        if (itemNum <= 0 || itemNum > toDoList.size())
            dueDateError2.setText("Item number out of range.");
        markIncomplete(itemNum-1);
    }

    @FXML
    public void markIncomplete(int itemNum) {
        toDoList.get(itemNum).setIncomplete();
    }

    // Get item number and description from field, checks validity of number, and passes to editDescription
    @FXML
    public void editDescriptionGateway() {
        int itemNum = Integer.parseInt(itemNumber.getText());
        String newDescription = editItemDescription.getText();
        if (itemNum <= 0 || itemNum > toDoList.size())
            dueDateError2.setText("Item number out of range.");
        editDescription(itemNum-1, newDescription);
    }

    public void editDescription(int itemNum, String newDescription) {
        toDoList.get(itemNum).setDescription(newDescription);
    }

    // Get item number and due date from field, checks validity of number and due date, and passes to editDueDate
    @FXML
    public void editDueDateGateway() {
        int itemNum = Integer.parseInt(itemNumber.getText());
        String newDueDate = editItemDueDate.getText();
        if (itemNum <= 0 || itemNum > toDoList.size())
            dueDateError2.setText("Item number out of range.");
        String dueDateValidity = dueDateValid(newDueDate);
        if (dueDateValidity.equals(""))
            editDueDate(itemNum - 1, newDueDate);
        else
            dueDateError2.setText(dueDateValidity);
    }

    @FXML
    public void editDueDate(int itemNum, String newDueDate) {
        toDoList.get(itemNum).setDueDate(newDueDate);
    }

    public static String dueDateValid(String dueDate) {
        int dueDateLen = dueDate.length();
        String output = "";
        if (dueDateLen == 0) {
            output = "Please enter a due date.";
        }
        else if (dueDateLen != 10) {
            output = "Please enter due date in YYYY-MM-DD format.";
        }
        else {
            if (dueDate.indexOf("-") != 4 && dueDate.indexOf("-") != 7) {
                output = "Please enter due date in YYYY-MM-DD format.";
            }
            else if ((int) dueDate.charAt(5) < 48 || (int) dueDate.charAt(5) > 49) {
                output = "Please enter a valid month in YYYY-MM-DD format.";
            }
            else if ((int) dueDate.charAt(8) < 48 || (int) dueDate.charAt(8) > 51) {
                output = "Please enter a valid date in YYYY-MM-DD format.";
            }
        }
        return output;
    }

}
class Item extends ToDoListAppController {
    protected String description;
    protected String dueDate;
    protected boolean completed;

    public Item(String description, String date) {
        this.description = description;
        this.dueDate = date;
        this.completed = false;
    }

    public String getDescription() {
        return description;
    }

    public String getDueDate() {
        return dueDate;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setDescription(String newDescription) {
        this.description = newDescription;
    }

    public void setDueDate(String newDueDate) {
        this.dueDate = newDueDate;
    }

    public void setIncomplete() {
        this.completed = false;
    }

    public void setComplete() {
        this.completed = true;
    }
}