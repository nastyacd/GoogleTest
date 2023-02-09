package ifellow.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class CreateTaskPage {
    public static SelenideElement descriptionFrame = $(By.xpath("(//iframe)[2]"));
    public static SelenideElement fieldBody = $(By.xpath("//html/body"));
    public static SelenideElement environmentFrame = $(By.xpath("(//iframe)[3]"));
    public static SelenideElement fieldSummary = $(By.xpath("//input[@id='summary']"));

    public static SelenideElement priority = $(By.xpath("//div[@id='priority-single-select']//span"));
    public static SelenideElement priorityField = $(By.xpath("//input[@id='priority-field']"));
    public static SelenideElement issueType = $(By.xpath("//div[@id='issuetype-single-select']//span"));
    public static SelenideElement issueTypeField = $(By.xpath("//input[@id='issuetype-field']"));

    public static SelenideElement create = $(By.xpath("//input[@id='create-issue-submit']"));

}
