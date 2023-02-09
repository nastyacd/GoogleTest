package ifellow.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class DashboardPage {

    public static SelenideElement projects = $(By.xpath("//a[@id='browse_link']"));
    public static SelenideElement nameOfProject = $(By.xpath("//a[contains(text(),'Test (TEST)')]"));
    public static SelenideElement create = $(By.xpath("//a[@id='create_link']"));
    public static SelenideElement issueKey = $(By.xpath("//a[@class='issue-created-key issue-link']"));

}
