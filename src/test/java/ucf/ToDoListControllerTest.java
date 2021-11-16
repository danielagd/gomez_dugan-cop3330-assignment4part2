package ucf;

/*
 *  UCF COP3330 Fall 2021 Assignment 4 Solution
 *  Copyright 2021 Daniela Gomez-Dugan
 */

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import ucf.assignments.ToDoListAppController;

public class ToDoListControllerTest {

    @Test
    public void dueDateValid(){
        assertEquals("Please enter a due date.", ToDoListAppController.dueDateValid(""));
        assertEquals("Please enter due date in YYYY-MM-DD format.", ToDoListAppController.dueDateValid("2021-1111111-08"));
        assertEquals("Please enter due date in YYYY-MM-DD format.", ToDoListAppController.dueDateValid("11-08-2021"));
        assertEquals("Please enter a valid month in YYYY-MM-DD format.", ToDoListAppController.dueDateValid("2021-22-08"));
        assertEquals("Please enter a valid date in YYYY-MM-DD format.", ToDoListAppController.dueDateValid("2021-11-40"));
    }

}
