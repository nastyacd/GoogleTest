import com.codeborne.selenide.Condition;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class RunTest {
    @BeforeEach
    public void beforeEach() {
        open("https://www.google.com/");
    }

    @Test
    public void testSuccess() {


        $(By.xpath("//input[@name='q']")).setValue("Погода в Москве").pressEnter();
        $(By.xpath("//span[contains(text(),'Результаты')]/..")).shouldBe(Condition.exist);
        String result = $(By.xpath("//span[contains(text(),'Результаты')]/..")).getText();
        System.out.println(result);
        result = result.replace("Результаты: ", "").trim();
        System.out.println(result);
        Assert.assertEquals("Ошибка, необходимы результаты погоды в г. Москва", "Москва", result);

    }

    @Test
    public void testInvalid() {

        $(By.xpath("//input[@name='q']")).setValue("Погода столица").pressEnter();
        $(By.xpath("//span[contains(text(),'Результаты')]/..")).shouldBe(Condition.exist);
        String result = $(By.xpath("//span[contains(text(),'Результаты')]/..")).getText();

        result = result.replace("Результаты: ", "").trim();
        System.out.println(result);
        Assert.assertEquals("Ошибка, необходимы результаты погоды в г. Москва", "Москва", result);

    }

    @AfterEach
    public void afterEach() {
        closeWebDriver();
    }
}
