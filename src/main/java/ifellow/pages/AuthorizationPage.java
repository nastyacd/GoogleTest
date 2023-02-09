package ifellow.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class AuthorizationPage {
    public static SelenideElement username = $(By.xpath("//input[@name='os_username']"));
    public static SelenideElement password = $(By.xpath("//input[@name='os_password']"));
    public static SelenideElement login = $(By.xpath("//input[@name='login']"));

}
