package steps;

import ifellow.pages.AuthorizationPage;
import io.qameta.allure.Step;

public class JiraSteps {
    @Step("Авторизация")
    public static void authorizationStep(String username, String password) {
        AuthorizationPage.username.setValue(username);
        AuthorizationPage.password.setValue(password);
        AuthorizationPage.login.click();
    }
}
