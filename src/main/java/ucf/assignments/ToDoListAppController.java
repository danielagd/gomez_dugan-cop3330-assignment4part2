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

    @FXML
    public void addItemGateway(){
        String itemDescription = newItemDescription.getText();
        String itemDueDate = newItemDueDate.getText();
        int dueDateLen = itemDueDate.length();
        if (dueDateLen == 0)
            dueDateError.setText("Please enter a due date.");
        else if (dueDateLen != 10)
            dueDateError.setText("Please enter due date in YYYY-MM-DD format.");
        else {
            if (itemDueDate.indexOf("-") != 4 && itemDueDate.indexOf("-") != 7)
                dueDateError.setText("Please enter due date in YYYY-MM-DD");
            else if ((int)itemDueDate.charAt(5) < 48  || (int)itemDueDate.charAt(5) > 49)
                dueDateError.setText("Please enter a valid month in YYYY-MM-DD format.");
            else if ((int)itemDueDate.charAt(8) < 48  || (int)itemDueDate.charAt(8) > 51)
                dueDateError.setText("Please enter a valid date in YYYY-MM-DD format.");
            else
                addItem(itemDescription, itemDueDate);
        }
    }

    @FXML
    public void addItem(String description, String dueDate) {
        Item newItem = new Item(description, dueDate);
        if (toDoList.contains(newItem))
            dueDateError.setText("This item is already in the list.");
        else
        {
            toDoList.add(newItem);
            dueDateError.setText(description + " is added to your To-Do list!");
        }
    }

    @FXML
    public void removeItemGateway(){
        int removeNum = Integer.parseInt(removeItemGiven.getText());
        removeItem(removeNum-1);
    }

    @FXML
    public void removeItem(int itemNumber) {
        Item removed = toDoList.remove(itemNumber);
        System.out.println(removed.description);
    }

    @FXML
    public void clearItems(){
        toDoList.clear();
    }

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

    @FXML
    public void loadGateway() throws FileNotFoundException {
        String fp = filePathForList.getText();
        loadList(fp);
    }

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

    @FXML
    public void editDueDateGateway() {
        int itemNum = Integer.parseInt(itemNumber.getText());
        String newDueDate = editItemDueDate.getText();
        if (itemNum <= 0 || itemNum > toDoList.size())
            dueDateError2.setText("Item number out of range.");
        int dueDateLen = newDueDate.length();
        if (dueDateLen == 0)
            dueDateError2.setText("Please enter a due date.");
        else if (dueDateLen != 10)
            dueDateError2.setText("Please enter due date in YYYY-MM-DD format.");
        else {
            if (newDueDate.indexOf("-") != 4 && newDueDate.indexOf("-") != 7)
                dueDateError2.setText("Please enter due date in YYYY-MM-DD");
            else if ((int) newDueDate.charAt(5) < 48 || (int) newDueDate.charAt(5) > 49)
                dueDateError2.setText("Please enter a valid month in YYYY-MM-DD format.");
            else if ((int) newDueDate.charAt(8) < 48 || (int) newDueDate.charAt(8) > 51)
                dueDateError2.setText("Please enter a valid date in YYYY-MM-DD format.");
            else
                editDueDate(itemNum - 1, newDueDate);
        }
    }

    @FXML
    public void editDueDate(int itemNum, String newDueDate) {
        toDoList.get(itemNum).setDueDate(newDueDate);
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