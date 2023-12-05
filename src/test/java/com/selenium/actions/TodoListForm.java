package com.selenium.actions;



 class TodoListForm {
     static final String NEW_TODO_FIELD = ".new-todo";
     static final String ITEM_LABELS = ".todo-list label";
     static final String COMPLETE_CHECKBOX = "//label[.= '{0}']/preceding-sibling::input[@type='checkbox']";
     static final String FILTER_BUTTON = "//ul[@class='filters']//a[.='{0}']";
     static final String ITEM_LABEL = "//label[.='{0}']";

     static final String DELETE_ITEM = "//label[.='{0}']//following-sibling::button[@class='destroy']";
}
