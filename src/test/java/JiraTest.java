import com.codeborne.selenide.Configuration;
import ifellow.pages.AuthorizationPage;
import ifellow.pages.DashboardPage;
import ifellow.pages.TaskListPage;
import ifellow.pages.TaskPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class JiraTest {

    @BeforeEach
    public void beforeEach() {
        Configuration.startMaximized = true;
        open("https://edujira.ifellow.ru/");
        AuthorizationPage.username.setValue("aosokina");
        AuthorizationPage.password.setValue("Qwerty123");
        AuthorizationPage.login.click();
        /*
        $(By.xpath("//input[@name=\"os_username\"]")).setValue("aosokina");
        $(By.xpath("//input[@name=\"os_password\"]")).setValue("Qwerty123");
        $(By.xpath("//input[@name=\"login\"]")).click();

         */
    }

    @Test
    public void checkTaskTest() {

        DashboardPage.projects.click();
        DashboardPage.nameOfProject.click();
        /*
        $(By.xpath("//a[@id=\"browse_link\"]")).click();
        $(By.xpath("//a[contains(text(),\"Test (TEST)\")]")).click();
*/

        TaskListPage.panelElement.click();
        String taskNumber = TaskListPage.taskName.getAttribute("title");
        String countOfTasks = TaskListPage.countOfTasks.getText();
        countOfTasks = countOfTasks.replace("проблем(ы)", "").trim();

        /*
        $(By.xpath("(//a[@class='aui-nav-item '])[1]")).click();

        String taskNumber =$(By.xpath("//span[text()='TestSelenium']/../..//a")).getAttribute("title");

        String countOfTasks =$(By.xpath("(//div[contains(text(),\"Список задач\")]/following-sibling::div)[1]")).getText();
        countOfTasks = countOfTasks.replace("проблем(ы)", "").trim();

       // $(By.xpath("//input[@aria-label='Поиск задач']")).setValue(taskNumber).pressEnter();
      //  $(By.xpath("//a[contains(text(),"+taskNumber+")]")).click();

         */


        open("https://edujira.ifellow.ru/browse/" + taskNumber);
        String status = TaskPage.statusTask.getText();
        String version = TaskPage.version.getText();
        //  String status =$(By.xpath("//span[@id='status-val']")).getText();


        System.out.println(countOfTasks + "frg");
    }

    @Test
    public void createBugTest() {


        $(By.xpath("//a[@id='create_link']")).click();


// заполнение полей


        switchTo().frame($(By.xpath("(//iframe)[2]")));
        $(By.xpath("//html/body")).setValue("Возникла ошибка работы системы"); //описание

        switchTo().parentFrame().switchTo().frame($(By.xpath("(//iframe)[3]")));
        $(By.xpath("//html/body")).setValue("Windows10"); //окружение
        switchTo().parentFrame();

        $(By.xpath("//input[@id='summary']")).setValue("Баг"); //тема

        $(By.xpath("//div[@id='priority-single-select']//span")).click();
        $(By.xpath("//input[@id='priority-field']")).sendKeys("Highest");// приоритет
        $(By.xpath("//input[@id='priority-field']")).pressEnter();

        $(By.xpath("//div[@id='issuetype-single-select']//span")).click();
        $(By.xpath("//input[@id='issuetype-field']")).sendKeys("Ошибка");// тип задачи
        $(By.xpath("//input[@id='issuetype-field']")).pressEnter();

        $(By.xpath("//input[@id='create-issue-submit']")).click();

        String taskNumber = $(By.xpath("//a[@class='issue-created-key issue-link']")).getAttribute("data-issue-key");
        open("https://edujira.ifellow.ru/browse/" + taskNumber);

        $(By.xpath("//span[contains(text(),'Нужно сделать')]/..")).click();
        sleep(2000);
        $(By.xpath("//span[contains(text(),'В работе') and @class='trigger-label']/..")).click();
        sleep(2000);
        $(By.xpath("//span[contains(text(),'Бизнес-процесс') and @class='dropdown-text']/..")).click();
        $(By.xpath("//span[contains(text(),'Выполнено') and @class='trigger-label']/..")).click();


        System.out.println("frg");
    }
}
