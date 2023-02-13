package jiraSteps;

import io.qameta.allure.Step;
import org.junit.Assert;

import static jiraPages.TaskListPage.countOfTasks;
import static jiraPages.TaskListPage.searchField;

public class TaskListSteps {
    @Step("Проверяем количество задач")
    public static void checkCountOfTasks() {
        String countOfTasksValue = countOfTasks.getText();
        countOfTasksValue = countOfTasksValue.replace("проблем(ы)", "").trim();
        System.out.println("Количество заведенных задач " + countOfTasksValue);
        Assert.assertTrue(Integer.valueOf(countOfTasksValue) > 0);
    }
    @Step("Выполняем поиск по задаче")
    public static void searchTask(String taskNumber) {
        searchField.setValue(taskNumber).pressEnter();
    }
}
