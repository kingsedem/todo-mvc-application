package com.selenium.tests;

import com.selenium.actions.TodoListActions;
import net.serenitybdd.annotations.Managed;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.annotations.Steps;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.assertj.core.api.Assertions;


@ExtendWith(SerenityJUnit5Extension.class)
public class WhenCompletingATask {

    @Managed(driver = "chrome")
    WebDriver driver;

    @Steps
    TodoListActions todoList;

    @BeforeEach
    public void openApp(){
        todoList.openApplication();
    }


    @Test
    public void activeTaskShouldNotShowCompletedTasks(){
        //Add "Feed the cat" and "Walk the dog" to the list
        todoList.addItems("Feed the cat","Walk the Dog");
        //Complete "Feed the cat"
        todoList.completeItem("Feed the cat");
        //Filter by "Completed"
        todoList.filterBy("Active");
        //Check that only "Walk the dog" appears
        Assertions.assertThat(todoList.items()).containsExactly("Walk the Dog");
    }
}
