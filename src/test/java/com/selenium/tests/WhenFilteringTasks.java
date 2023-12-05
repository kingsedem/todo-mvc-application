package com.selenium.tests;
import com.selenium.actions.TodoListActions;
import net.serenitybdd.annotations.Managed;
import net.serenitybdd.annotations.Steps;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.thucydides.junit.annotations.Concurrent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;



@ExtendWith(SerenityJUnit5Extension.class)
@Concurrent
public class WhenFilteringTasks {

    @Managed(driver = "chrome")
    WebDriver driver;

    @BeforeEach
    public void openApp() {
        todoList.openApplication();
    }

    @Steps
    TodoListActions todoList;


    @ParameterizedTest
    @MethodSource("todoData")
    public void columnNames(String filterBy,
                            List<String> todoItems,
                            String itemToComplete,
                            List<String> filteredItems) {

        todoList.openApplication();
        todoList.addItems(todoItems);
        todoList.completeItem(itemToComplete);
        todoList.filterBy(filterBy);

        assertThat(todoList.items()).containsExactlyElementsOf(filteredItems);
    }

    public static Stream<Arguments> todoData() {
        List<Arguments> testData = new ArrayList<>();
        testData.add(Arguments.of("Active", asList("Feed the cat", "Walk the dog"),
                "Feed the cat", asList("Walk the dog")));
        testData.add(Arguments.of("Completed", asList("Feed the cat", "Walk the dog"),
                "Feed the cat", asList("Feed the cat")));
        testData.add(Arguments.of("All", asList("Feed the cat", "Walk the dog"),
                "Feed the cat", asList("Feed the cat", "Walk the dog")));

        return testData.stream();
    }
}
