package jiraSteps;

import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import jiraPages.TaskListPage;

import static com.codeborne.selenide.Selenide.switchTo;
import static jiraPages.CreateTaskPage.*;
import static jiraPages.DashboardPage.*;
import static jiraPages.TaskListPage.*;
import static jiraSteps.TaskListSteps.checkCountOfTasks;

public class DashboardSteps {

    public static String taskNumber;

    @Когда("^находим задачу \"([^\"]*)\"$")
    public static String findTask(String nameOfTask) {
        projects.click();
        nameOfProject.click();
        panelElement.click();
        checkCountOfTasks();
        taskNumber = TaskListPage.getTask(nameOfTask).getAttribute("title");
        return taskNumber;
    }

    @Тогда("^выполняем поиск задачи$")
    public static void searchTask() {
        searchField.setValue(taskNumber).pressEnter();
    }

    @Когда("^создаем задачу с summary \"([^\"]*)\", description \"([^\"]*)\", environment \"([^\"]*)\", priority \"([^\"]*)\", issueType \"([^\"]*)\"$")
    public static String createTask(String summary, String description, String environment, String priority, String issueType) {
        create.click();
        // заполнение полей
        switchTo().frame(descriptionFrame);


        fieldBody.setValue(description); //описание

        switchTo().parentFrame().switchTo().frame(environmentFrame);
        fieldBody.setValue(environment); //окружение
        switchTo().parentFrame();

        fieldSummary.setValue(summary); //тема

        priorityButton.click();
        priorityField.sendKeys(priority);// приоритет
        priorityField.pressEnter();

        issueTypeButton.click();
        issueTypeField.sendKeys(issueType);// тип задачи
        issueTypeField.pressEnter();

        createSubmit.click();

        taskNumber = issueKey.getAttribute("data-issue-key");
        return taskNumber;
    }
}
