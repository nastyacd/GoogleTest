package tests;

import hooks.Hooks;
import org.junit.jupiter.api.Test;

import static jiraSteps.CreateTaskSteps.createTask;
import static jiraSteps.DashboardSteps.findTask;
import static jiraSteps.TaskListSteps.searchTask;
import static jiraSteps.TaskSteps.checkTaskInformation;
import static jiraSteps.TaskSteps.goToCloseStatus;

public class JiraTests extends Hooks {
    @Test
    public void checkTaskTest() {
        String taskNumber = findTask("TestSelenium");
        searchTask(taskNumber);

        checkTaskInformation("В РАБОТЕ", "Version 2.0");
    }

    @Test
    public void createBugTest() {
        String taskNumber = createTask("Баг", "Возникла ошибка работы системы", "Windows10", "Highest", "Ошибка");
        searchTask(taskNumber);

        goToCloseStatus();
    }

}
