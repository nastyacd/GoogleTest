package ifellow.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class TaskPage {

    public static SelenideElement statusTask = $(By.xpath("//span[@id='status-val']"));
    public static SelenideElement version = $(By.xpath("//span[@id='versions-val']"));
    public static SelenideElement needToDo = $(By.xpath("//span[contains(text(),'Нужно сделать')]/.."));
    public static SelenideElement inWork = $(By.xpath("//span[contains(text(),'В работе') and @class='trigger-label']/.."));
    public static SelenideElement process = $(By.xpath("//span[contains(text(),'Бизнес-процесс') and @class='dropdown-text']/.."));
    public static SelenideElement done = $(By.xpath("//span[contains(text(),'Выполнено') and @class='trigger-label']/.."));

}
