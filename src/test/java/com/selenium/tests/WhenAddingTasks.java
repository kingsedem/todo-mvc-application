package com.selenium.tests;

import com.selenium.actions.TodoListActions;
import net.serenitybdd.annotations.Managed;
import net.serenitybdd.annotations.Steps;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;



@ExtendWith(SerenityJUnit5Extension.class)
public class WhenAddingTasks {

    @Managed(driver = "chrome")
    WebDriver driver;

    @BeforeEach
    public void openApp(){
        todoList.openApplication();
    }

    @Steps
    TodoListActions todoList;


    @Test
    public void addingASingleTask(){
        //Add "Feed the cat" to the list
        todoList.addItem("Feed the cat");

        //Check that "Feed the cat" appears in the list
        Assertions.assertThat(todoList.items()).containsExactly("Feed the cat");
    }

    @Test
    public void addingMultipleTasks(){
        //Add "Feed the Cat" and "Walk the dog" to the list
        todoList.addItems("Feed the cat", "Walk the dog");

        //Check that all appear in the list
        Assertions.assertThat(todoList.items()).containsExactly("Feed the cat", "Walk the dog");
    }

    @Test
    public void completedTasksShouldNotShowActiveTasks(){
        //Add "Feed the cat" and "Walk the dog" to the list
        todoList.addItems("Feed the cat","Walk the dog");
        //Complete "Feed the cat"
        todoList.completeItem("Feed the cat");
        //Filter by "Completed"
        todoList.filterBy("Completed");

        //Check that only "Feed the cat" appears
        Assertions.assertThat(todoList.items()).containsExactly("Feed the cat");
    }
}
