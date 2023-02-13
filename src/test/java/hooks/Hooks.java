package hooks;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static jiraSteps.AuthorizationSteps.authorizationStep;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class Hooks {
    @BeforeEach
    public void beforeEach() {
        Configuration.startMaximized = true;
        open("https://edujira.ifellow.ru/");
        authorizationStep("aosokina", "Qwerty123");
    }

    @AfterEach
    public void afterEach() {
        closeWebDriver();
    }
}
