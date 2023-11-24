package com.selenium.tests;

import com.selenium.actions.TodoListActions;
import net.serenitybdd.annotations.Managed;
import net.serenitybdd.annotations.Steps;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.assertj.core.api.Assertions;


@ExtendWith(SerenityJUnit5Extension.class)
public class WhenDeletingATask {

    @Managed(driver = "chrome")
    WebDriver driver;

    @BeforeEach
    public void openApp(){
        todoList.openApplication();
    }

    @Steps
    TodoListActions todoList;

    @Test
    public void deletedItemsShouldDisappearFromTheList(){
        //Add "Feed the ferret" and "Walk the alpaca" to the list
        todoList.addItems("Feed the ferret","Walk the alpaca");
        //Delete "Feed the cat"
        todoList.deleteItem("Feed the ferret");

        //check that only "Walk the alpaca" appears
        Assertions.assertThat(todoList.items()).containsExactly("Walk the alpaca");
    }
}
