package tests;

import ApiSteps.ApiHooks;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.http.Cookies;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static ApiSteps.ApiSteps.*;

@Epic("Api тесты")
public class ApiTests extends ApiHooks {

    @Feature("Тесты Рик и морти")
    @Test
    public void mortyTest() {
        String mortyInfo = getCharacter(2);
        getLastEpisode();
        String character = getLastCharacter();
        String lastCharacterInfo = getCharacter(Integer.valueOf(character));

        String speciesLastCharacter = new JSONObject(lastCharacterInfo).get("species").toString();
        String speciesMorty = new JSONObject(mortyInfo).get("species").toString();

        String locationLastCharacter = new JSONObject(lastCharacterInfo).getJSONObject("location").get("name").toString();
        String locationMorty = new JSONObject(mortyInfo).getJSONObject("location").get("name").toString();

        Assert.assertEquals("Разные расы", speciesLastCharacter, speciesMorty);
        Assert.assertEquals("Разные локации", locationLastCharacter, locationMorty);
    }

    @Feature("Тесты reqres")
    @Test
    public void reqresTest() {
        String client = "";
        try {
            client = new String(Files.readAllBytes(Paths.get("src/test/resources/client.json")));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        String answer = postClient(client);

        String clientName = new JSONObject(answer).get("name").toString();
        String clientJob = new JSONObject(answer).get("job").toString();
        String clientId = new JSONObject(answer).get("id").toString();

        String clientNameFile = new JSONObject(client).get("name").toString();
        String clientJobFile = new JSONObject(client).get("job").toString();
        Assert.assertEquals("Разные названия", clientName, clientNameFile);
        Assert.assertEquals("Разное действие", clientJob, clientJobFile);


        // создаем новый файл с измененными полями
        String clientNew = "";
        try {
            clientNew = new String(Files.readAllBytes(Paths.get("src/test/resources/clientNew.json")));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        String answerNew = putClient(clientNew, clientId);

        String clientNameNew = new JSONObject(answerNew).get("name").toString();
        String clientJobNew = new JSONObject(answerNew).get("job").toString();


        clientNameFile = new JSONObject(clientNew).get("name").toString();
        clientJobFile = new JSONObject(clientNew).get("job").toString();

        Assert.assertEquals("Разные названия", clientNameFile, clientNameNew);
        Assert.assertEquals("Разное действие", clientJobFile, clientJobNew);

    }

    @Feature("Тесты Jira")
    @Test
    public void jiraTest() {
        Cookies cookies = authorizationJira("aosokina","Qwerty123");
        getTaskJira("TEST-26622", cookies);
    }
}
