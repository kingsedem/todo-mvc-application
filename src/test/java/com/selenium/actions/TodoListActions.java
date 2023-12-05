package com.selenium.actions;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.core.steps.UIInteractions;
import java.util.List;

import static com.selenium.actions.TodoListForm.*;
//import static com.selenium.actions.TodoListForm.*;



public class TodoListActions extends UIInteractions {
    @Step("Open the Todo-MVC application")
    public void openApplication() {
        openUrl("https://todomvc.com/examples/angularjs/#/");
    }

    @Step("Adding Items '{0}'")
    public void addItem(String item) {
         $(NEW_TODO_FIELD).typeAndEnter(item);
    }

    public List<String> items() {
        return $$(ITEM_LABELS).texts();
    }

    @Step("Add items {0}")
    public void addItems(List<String> items) {
        items.forEach(this::addItem);
    }

    @Step("Add items {0}")
    public void addItems(String... items) {
        for(String item : items){
            addItem(item);
        }
    }

    @Step("Complete item {0}")
    public void completeItem(String item) {
        $(COMPLETE_CHECKBOX, item).click();
    }

    @Step("Filter by {0}")
    public void filterBy(String filterName) {
        $(FILTER_BUTTON, filterName).click();
    }

    @Step("Delete the {0}")
    public void deleteItem(String item) {
        $(ITEM_LABEL,item).click();
        $(DELETE_ITEM, item).click();
    }
}
