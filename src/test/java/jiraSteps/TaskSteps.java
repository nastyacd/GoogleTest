package jiraSteps;

import com.codeborne.selenide.Condition;
import io.cucumber.java.ru.И;
import org.junit.Assert;

import java.time.Duration;

import static jiraPages.TaskPage.*;
import static com.codeborne.selenide.Selenide.sleep;

public class TaskSteps {

    @И("^проверяем информацию в задаче со статусом \"([^\"]*)\" и версией \"([^\"]*)\"$")
    public static void checkTaskInformation(String taskStatus, String taskVersion) {
        String status = statusTask.getText().trim();
        String version = versionTask.getText().trim();
        Assert.assertEquals("Неверный статус", taskStatus, status);
        Assert.assertEquals("Неправильная привязка затронутой версии", taskVersion, version);
    }

    @И("^закрываем задачу$")
    public static void goToCloseStatus() {
        needToDo.click();
        Assert.assertEquals("Неверный статус", "СДЕЛАТЬ", statusTask.getText());

        inWork.click();
        sleep(2000);
        Assert.assertEquals("Неверный статус", "В РАБОТЕ", statusTask.getText());

        process.click();
        done.shouldBe(Condition.visible, Duration.ofSeconds(3)).click();
        sleep(2000);
        Assert.assertEquals("Неверный статус", "ГОТОВО", statusTask.getText());
    }
}
