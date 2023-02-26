package tests;

import io.restassured.http.Cookies;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static ApiSteps.ApiSteps.*;

public class ApiTests {
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

    @Test
    public void reqresTest() {
        String client = "";
        try {
            client = new String(Files.readAllBytes(Paths.get("src/test/resources/client.json")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String answer = postClient(client);
        String clientName = new JSONObject(answer).get("name").toString();
        String clientJob = new JSONObject(answer).get("job").toString();

        String clientNameFile = new JSONObject(client).get("name").toString();
        String clientJobFile = new JSONObject(client).get("job").toString();

        Assert.assertEquals("Разные названия", clientName, clientNameFile);
        Assert.assertEquals("Разное действие", clientJob, clientJobFile);
    }

    @Test
    public void jiraTest() {
        Cookies cookies = authorizationJira("aosokina","Qwerty123");
        getTaskJira("TEST-26622", cookies);
    }
}
