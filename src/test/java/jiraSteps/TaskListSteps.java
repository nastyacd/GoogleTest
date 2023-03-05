package jiraSteps;

import io.cucumber.java.ru.И;

import org.junit.Assert;

import static jiraPages.TaskListPage.countOfTasks;

public class TaskListSteps {
    @И("^Проверяем количество задач$")
    public static void checkCountOfTasks() {
        String countOfTasksValue = countOfTasks.getText();
        countOfTasksValue = countOfTasksValue.replace("проблем(ы)", "").trim();
        System.out.println("Количество заведенных задач " + countOfTasksValue);
        Assert.assertTrue(Integer.valueOf(countOfTasksValue) > 0);
    }
}
