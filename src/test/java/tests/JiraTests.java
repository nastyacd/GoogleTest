package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import steps.JiraSteps;


import static com.codeborne.selenide.Selenide.*;

public class JiraTests {

    @BeforeEach
    public void beforeEach() {
        Configuration.startMaximized = true;
        open("https://edujira.ifellow.ru/");
        JiraSteps.authorizationStep("aosokina", "Qwerty123");
    }

    @Test
    public void checkTaskTest() {
        String taskNumber = JiraSteps.findTask("TestSelenium");

        open("https://edujira.ifellow.ru/browse/" + taskNumber);

        JiraSteps.checkTaskInformation("В РАБОТЕ", "Version 2.0");
    }

    @Test
    public void createBugTest() {
        String taskNumber = JiraSteps.createTask("Баг", "Возникла ошибка работы системы", "Windows10", "Highest", "Ошибка");

        open("https://edujira.ifellow.ru/browse/" + taskNumber);

        JiraSteps.goToCloseStatus();
    }

    @AfterEach
    public void afterEach() {
        closeWebDriver();
    }
}
