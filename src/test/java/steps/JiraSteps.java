package steps;

import com.codeborne.selenide.Condition;
import ifellow.pages.*;
import io.qameta.allure.Step;
import org.junit.Assert;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.Selenide.switchTo;

public class JiraSteps {
    @Step("Авторизация")
    public static void authorizationStep(String username, String password) {
        AuthorizationPage.username.setValue(username);
        AuthorizationPage.password.setValue(password);
        AuthorizationPage.login.click();
    }

    @Step("Находим задачу")
    public static String findTask(String nameOfTask) {
        DashboardPage.projects.click();
        DashboardPage.nameOfProject.click();
        TaskListPage.panelElement.click();
        checkCountOfTasks();
        String taskNumber = TaskListPage.getTask(nameOfTask).getAttribute("title");
        return taskNumber;

    }

    @Step("Проверяем количество задач")
    public static void checkCountOfTasks() {
        String countOfTasks = TaskListPage.countOfTasks.getText();
        countOfTasks = countOfTasks.replace("проблем(ы)", "").trim();
        System.out.println("Количество заведенных задач " + countOfTasks);
        Assert.assertTrue(Integer.valueOf(countOfTasks) > 0);
    }

    @Step("Проверяем информацию в задаче")
    public static void checkTaskInformation(String taskStatus, String taskVersion) {
        String status = TaskPage.statusTask.getText().trim();
        String version = TaskPage.version.getText().trim();
        Assert.assertEquals("Неверный статус", taskStatus, status);
        Assert.assertEquals("Неправильная привязка затронутой версии", taskVersion, version);
    }

    @Step("Создаем задачу")
    public static String createTask(String summary, String description, String environment, String priority, String issueType) {
        DashboardPage.create.click();
        // заполнение полей
        switchTo().frame(CreateTaskPage.descriptionFrame);


        CreateTaskPage.fieldBody.setValue(description); //описание

        switchTo().parentFrame().switchTo().frame(CreateTaskPage.environmentFrame);
        CreateTaskPage.fieldBody.setValue(environment); //окружение
        switchTo().parentFrame();

        CreateTaskPage.fieldSummary.setValue(summary); //тема

        CreateTaskPage.priority.click();
        CreateTaskPage.priorityField.sendKeys(priority);// приоритет
        CreateTaskPage.priorityField.pressEnter();

        CreateTaskPage.issueType.click();
        CreateTaskPage.issueTypeField.sendKeys(issueType);// тип задачи
        CreateTaskPage.issueTypeField.pressEnter();

        CreateTaskPage.create.click();

        String taskNumber = DashboardPage.issueKey.getAttribute("data-issue-key");
        return taskNumber;
    }

    @Step("Закрываем задачу")
    public static void goToCloseStatus() {
        TaskPage.needToDo.click();
        Assert.assertEquals("Неверный статус", "СДЕЛАТЬ", TaskPage.statusTask.getText());

        TaskPage.inWork.click();
        sleep(2000);
        Assert.assertEquals("Неверный статус", "В РАБОТЕ", TaskPage.statusTask.getText());

        TaskPage.process.click();
        TaskPage.done.shouldBe(Condition.visible, Duration.ofSeconds(3)).click();
        sleep(2000);
        Assert.assertEquals("Неверный статус", "ГОТОВО", TaskPage.statusTask.getText());
    }

}
