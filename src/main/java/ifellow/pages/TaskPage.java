package ifellow.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class TaskPage {

    public static SelenideElement statusTask = $(By.xpath("//span[@id='status-val']"));
    public static SelenideElement version=$(By.xpath("//span[@id='versions-val']"));
}
