import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TestJira {

    @BeforeEach
    public void beforeEach() {
        open("edujira.ifellow.ru");
    }
    @Test
    public void testJira()
    {
        $(By.xpath("//input[@name=\"os_username\"]")).setValue("aosokina").pressEnter();
        $(By.xpath("//input[@name=\"os_password\"]")).setValue("Qwerty123").pressEnter();
        $(By.xpath("//input[@name=\"login\"]")).click();
    }
}
