package ApiSteps;

import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.Cookies;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
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

    private static RequestSpecification rickSpecification = new RequestSpecBuilder().setBaseUri("https://rickandmortyapi.com/api").build();
    private static RequestSpecification reqresSpecification = new RequestSpecBuilder().setBaseUri("https://reqres.in").setContentType("application/json").build();
    private static RequestSpecification jiraSpecification = new RequestSpecBuilder().setBaseUri("https://edujira.ifellow.ru").setContentType("application/x-www-form-urlencoded").build();

    @Step("Получаем персоонажа по номеру: {id}")
    public static String getCharacter(Integer id) {
        Response gettingInfo = given()
                .spec(rickSpecification)
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


    @Step("Получаем последний эпизод")
    public static void getLastEpisode() {
        Response gettingLastEpisode = given()
                .spec(rickSpecification)
                .when()
                .get("/character/" + charId)
                .then()
                .extract()
                .response();
        episode = new JSONObject(gettingLastEpisode.getBody().asString()).getJSONArray("episode").length() - 1;
        System.out.println("Последний эпизод, где появлялся Морти: " + (episode + 1));

    }

    @Step("Получаем последнего персоонажа")
    public static String getLastCharacter() {
        Response gettingLastCharacter = given()
                .spec(rickSpecification)
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

    @Step("Создаем клиена {client}")
    public static String postClient(String client) {

        Response clientInfo = given()
                .spec(reqresSpecification)
                .body(client)
                .when()
                .post("/api/users")
                .then()
                .extract()
                .response();
        Assert.assertEquals("Ошибка статус-кода", 201, clientInfo.getStatusCode());
        return clientInfo.getBody().asString();
    }

    @Step("Изменяем клиента с id = {id}")
    public static String putClient(String client, String id) {

        Response clientInfo = given()
                .spec(reqresSpecification)
                .body(client)
                .when()
                .put("/api/users/" + id)
                .then()
                .extract()
                .response();
        Assert.assertEquals("Ошибка статус-кода", 200, clientInfo.getStatusCode());
        return clientInfo.getBody().asString();
    }

    @Step("Авторизуемся в Джиру под пользователем {login}")
    public static Cookies authorizationJira(String login, String pass) {

        Map<String, String> formParam = new HashMap<>();
        formParam.put("os_username", login);
        formParam.put("os_password", pass);

        Response response = given()
                .spec(jiraSpecification)
                .formParams(formParam)
                .when()
                .post("/rest/gadget/1.0/login")
                .then()
                .extract()
                .response();
       return response.detailedCookies();
    }

    @Step("Получаем информацию по задаче {task}")
    public static void getTaskJira(String task, Cookies cookies) {
        Response response = given()
                .spec(jiraSpecification)
                .cookies(cookies)
                .when()
                .get("/browse/" + task)
                .then()
                .extract()
                .response();
        Assert.assertEquals("Ошибка статус-кода", 200, response.getStatusCode());
    }
}
