package com.selenium.tests;

;
import com.selenium.actions.TodoListActions;
import net.serenitybdd.annotations.Managed;
import net.serenitybdd.annotations.Steps;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.serenitybdd.junit5.JUnit5DataDrivenAnnotations;
import net.serenitybdd.junit5.ParameterizedTestsOutcomeAggregator;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.thucydides.junit.annotations.Concurrent;
import net.thucydides.junit.annotations.TestData;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import java.util.Collection;
import java.util.List;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SerenityJUnit5Extension.class)
@Concurrent
public class WhenFilteringTasks {

    @Managed(driver = "chrome")
    WebDriver driver;

    @BeforeEach
    public void openApp(){
        todoList.openApplication();
    }
    @Steps
    TodoListActions todoList;

    private List<String> todoItems;
    private String itemToComplete;
    private String filterBy;
    private List<String>  filteredItems;


    public WhenFilteringTasks(String filterBy, List<String> todoItems, String itemToComplete, List<String> filteredItems) {
        this.todoItems = todoItems;
        this.itemToComplete = itemToComplete;
        this.filterBy = filterBy;
        this.filteredItems = filteredItems;
    }

    @TestData(columnNames = "Filter By, Todo Items, Completed Items, Filtered Items")
    public static Collection<Object[]> testData() {
        return asList(
                new Object[][]{
                        {"Active", asList("Feed the cat", "Walk the dog"), "Feed the cat", asList("Walk the dog")},
                        {"Completed", asList("Feed the cat", "Walk the dog"), "Feed the cat", asList("Feed the cat")},
                        {"All", asList("Feed the cat", "Walk the dog"), "Feed the cat",asList("Feed the cat", "Walk the dog")},
                }
        );
    }

    @Test
    public void shouldDisplayCorrectlyFilteredItems(){
        todoList.openApplication();
        todoList.addItems(todoItems);
        todoList.completeItem(itemToComplete);
        todoList.filterBy(filterBy);

        assertThat(todoList.items()).containsExactlyElementsOf(filteredItems);
    }
}
