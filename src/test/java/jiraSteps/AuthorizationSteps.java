package jiraSteps;

import io.cucumber.java.ru.Когда;
import jiraPages.AuthorizationPage;

public class AuthorizationSteps {

    @Когда("^авторизация под пользователем с логин \"([^\"]*)\" и пароль \"([^\"]*)\"$")
    public static void authorizationStep(String username, String password) {
        AuthorizationPage.username.setValue(username);
        AuthorizationPage.password.setValue(password);
        AuthorizationPage.login.click();
    }

}
