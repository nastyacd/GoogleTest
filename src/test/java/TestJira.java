import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.commands.PressEnter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class TestJira {

    @BeforeEach
    public void beforeEach() {
        Configuration.startMaximized = true;
        open("https://edujira.ifellow.ru/");
        $(By.xpath("//input[@name=\"os_username\"]")).setValue("aosokina");
        $(By.xpath("//input[@name=\"os_password\"]")).setValue("Qwerty123");
        $(By.xpath("//input[@name=\"login\"]")).click();
    }
    @Test
    public void testJira()
    {


        $(By.xpath("//a[@id=\"browse_link\"]")).click();
        $(By.xpath("//a[contains(text(),\"Test (TEST)\")]")).click();

        $(By.xpath("(//a[@class='aui-nav-item '])[1]")).click();

        String taskNumber =$(By.xpath("//span[text()='TestSelenium']/../..//a")).getAttribute("title");

        String countOfTasks =$(By.xpath("(//div[contains(text(),\"Список задач\")]/following-sibling::div)[1]")).getText();
        countOfTasks = countOfTasks.replace("проблем(ы)", "").trim();

        $(By.xpath("//input[@aria-label='Поиск задач']")).setValue(taskNumber).pressEnter();
      //  $(By.xpath("//a[contains(text(),"+taskNumber+")]")).click();
        open("https://edujira.ifellow.ru/browse/"+taskNumber);
        String status =$(By.xpath("//span[@id='status-val']")).getText();



        System.out.println(countOfTasks+"frg");
    }
    @Test
    public void createBug(){



        $(By.xpath("//a[@id='create_link']")).click();


// заполнение полей


        switchTo().frame($(By.xpath("(//iframe)[1]")));
       // $(By.xpath("//html/body")).setValue("fbfgf"); //описание

        switchTo().parentFrame().switchTo().frame($(By.xpath("(//iframe)[2]")));
        $(By.xpath("//html/body")).setValue("fbfgf"); //окружение
        switchTo().parentFrame();

        $(By.xpath("//input[@id='summary']")).setValue("fbfgf"); //тема

        $(By.xpath("//div[@id='priority-single-select']//span")).click();
        $(By.xpath("//input[@id='priority-field']")).sendKeys("Highest");// приоритет
        $(By.xpath("//input[@id='priority-field']")).pressEnter();

        $(By.xpath("//div[@id='issuetype-single-select']//span")).click();
        $(By.xpath("//input[@id='issuetype-field']")).sendKeys("Ошибка");// тип задачи
        $(By.xpath("//input[@id='issuetype-field']")).pressEnter();
        System.out.println("frg");
    }
}
