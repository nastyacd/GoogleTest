package ifellow.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class TaskListPage {

    public static SelenideElement panelElement = $(By.xpath("(//a[@class='aui-nav-item '])[1]"));
    public static SelenideElement countOfTasks = $(By.xpath("(//div[contains(text(),'Список задач')]/following-sibling::div)[1]"));

    public static SelenideElement getTask(String name) {
        return $(By.xpath("//span[text()='" + name + "']/../..//a"));
    }
}
