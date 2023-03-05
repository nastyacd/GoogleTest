package hooks;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.qameta.allure.selenide.AllureSelenide;

import static jiraSteps.AuthorizationSteps.authorizationStep;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class Hooks {
    @Before
    public void beforeEach() {
        Configuration.startMaximized = true;
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(true));

        open("https://edujira.ifellow.ru/");
        authorizationStep("aosokina", "Qwerty123");
    }

    @After
    public void afterEach() {
        closeWebDriver();
    }
}
