package ApiSteps;

import io.restassured.http.Cookies;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ApiSteps {
    public static String info;
    public static String charId;
    public static int episode;
    public static String characterNum;

    public static String getCharacter(Integer id) {

        Response gettingInfo = given()
                .baseUri("https://rickandmortyapi.com/api")
                .when()
                .get("/character/" + id)
                .then()
                .extract()
                .response();
        info = gettingInfo.getBody().asString();

        System.out.println("Информация по персонажу: " + info);
        charId = new JSONObject(info).get("id").toString();
        return info;
    }


    public static void getLastEpisode() {
        Response gettingLastEpisode = given()
                .baseUri("https://rickandmortyapi.com/api")
                .when()
                .get("/character/" + charId)
                .then()
                .extract()
                .response();
        episode = new JSONObject(gettingLastEpisode.getBody().asString()).getJSONArray("episode").length() - 1;
        System.out.println("Последний эпизод, где появлялся Морти: " + (episode + 1));

    }

    public static String getLastCharacter() {
        Response gettingLastCharacter = given()
                .baseUri("https://rickandmortyapi.com/api")
                .when()
                .get("/episode/" + (episode + 1))
                .then()
                .extract()
                .response();
        int index = new JSONObject(gettingLastCharacter.getBody().asString()).getJSONArray("characters").length() - 1;
        characterNum = new JSONObject(gettingLastCharacter.getBody().asString()).getJSONArray("characters").get(index).toString();
        System.out.println("Последний персонаж последнего эпизода " + characterNum);
        characterNum = characterNum.replaceAll("https://rickandmortyapi.com/api/character/","");
        return characterNum;
    }

    public static String postClient(String client) {

        Response clientInfo = given()
                .baseUri("https://reqres.in")
                .contentType("application/json")
                .body(client)
                .when()
                .post("/api/users")
                .then()
                .extract()
                .response();
        Assert.assertEquals("Ошибка статус-кода", 201, clientInfo.getStatusCode());
        return clientInfo.getBody().asString();
    }


    public static Cookies authorizationJira(String login, String pass) {

        Map<String, String> formParam = new HashMap<>();
        formParam.put("os_username", login);
        formParam.put("os_password", pass);

        Response response = given()
                .baseUri("https://edujira.ifellow.ru")
                .contentType("application/x-www-form-urlencoded")
                .formParams(formParam)
                .when()
                .post("/rest/gadget/1.0/login")
                .then()
                .log().all()
                .extract()
                .response();
       return response.detailedCookies();
    }
}