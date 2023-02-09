package tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import ifellow.pages.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import steps.JiraSteps;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;

public class JiraTests {

    @BeforeEach
    public void beforeEach() {
        Configuration.startMaximized = true;
        open("https://edujira.ifellow.ru/");
        JiraSteps.authorizationStep("aosokina", "Qwerty123");
    }

    @Test
    public void checkTaskTest() {

        DashboardPage.projects.click();
        DashboardPage.nameOfProject.click();


        TaskListPage.panelElement.click();
        String taskNumber = TaskListPage.taskName.getAttribute("title");
        String countOfTasks = TaskListPage.countOfTasks.getText();
        countOfTasks = countOfTasks.replace("проблем(ы)", "").trim();




        open("https://edujira.ifellow.ru/browse/" + taskNumber);
        String status = TaskPage.statusTask.getText();
        String version = TaskPage.version.getText();
        //  String status =$(By.xpath("//span[@id='status-val']")).getText();


        System.out.println(countOfTasks + "frg");
    }

    @Test
    public void createBugTest() {

        DashboardPage.create.click();
      //  $(By.xpath("//a[@id='create_link']")).click();


// заполнение полей
        switchTo().frame(CreateTaskPage.descriptionFrame);

       // switchTo().frame($(By.xpath("(//iframe)[2]")));

        $(By.xpath("//html/body")).setValue("Возникла ошибка работы системы"); //описание
        CreateTaskPage.fieldBody.setValue("Возникла ошибка работы системы"); //описание

        switchTo().parentFrame().switchTo().frame(CreateTaskPage.environmentFrame);
        CreateTaskPage.fieldBody.setValue("Windows10"); //окружение
        switchTo().parentFrame();

     /*   switchTo().parentFrame().switchTo().frame($(By.xpath("(//iframe)[3]")));
        $(By.xpath("//html/body")).setValue("Windows10"); //окружение
        switchTo().parentFrame();
*/

        CreateTaskPage.fieldSummary.setValue("Баг"); //тема

        CreateTaskPage.priority.click();
        CreateTaskPage.priorityField.sendKeys("Highest");// приоритет
        CreateTaskPage.priorityField.pressEnter();

        CreateTaskPage.issueType.click();
        CreateTaskPage.issueTypeField.sendKeys("Ошибка");// тип задачи
        CreateTaskPage.issueTypeField.pressEnter();

        CreateTaskPage.create.click();

        String taskNumber = DashboardPage.issueKey.getAttribute("data-issue-key");
        /*
        $(By.xpath("//input[@id='summary']")).setValue("Баг"); //тема

        $(By.xpath("//div[@id='priority-single-select']//span")).click();
        $(By.xpath("//input[@id='priority-field']")).sendKeys("Highest");// приоритет
        $(By.xpath("//input[@id='priority-field']")).pressEnter();

        $(By.xpath("//div[@id='issuetype-single-select']//span")).click();
        $(By.xpath("//input[@id='issuetype-field']")).sendKeys("Ошибка");// тип задачи
        $(By.xpath("//input[@id='issuetype-field']")).pressEnter();

        $(By.xpath("//input[@id='create-issue-submit']")).click();
*/

        open("https://edujira.ifellow.ru/browse/" + taskNumber);
        TaskPage.needToDo.click();
        TaskPage.inWork.click();

        TaskPage.process.click();
        TaskPage.done.shouldBe(Condition.visible, Duration.ofSeconds(3)).click();

        /*
        String taskNumber = $(By.xpath("//a[@class='issue-created-key issue-link']")).getAttribute("data-issue-key");
        open("https://edujira.ifellow.ru/browse/" + taskNumber);

        $(By.xpath("//span[contains(text(),'Нужно сделать')]/..")).click();
        sleep(2000);
        $(By.xpath("//span[contains(text(),'В работе') and @class='trigger-label']/..")).click();
        sleep(2000);
        $(By.xpath("//span[contains(text(),'Бизнес-процесс') and @class='dropdown-text']/..")).click();
        $(By.xpath("//span[contains(text(),'Выполнено') and @class='trigger-label']/..")).click();
*/

        System.out.println("frg");
    }
}
