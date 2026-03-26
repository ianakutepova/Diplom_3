package base;

import com.fasterxml.jackson.core.JsonProcessingException;
import util.WebDriverCreator;
import io.restassured.http.ContentType;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import pages.MainPage;

import static base.Endpoints.DELETE_USER_URL;
import static base.Endpoints.REGISTER_URL;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BaseTest {

    public static final String BASE_URL = "https://stellarburgers.education-services.ru/";
    protected WebDriver driver;
    MainPage mainPage;
    String accessToken;

    @Before
    public void startUp() {

        driver = WebDriverCreator.createWebDriver();
        mainPage = new MainPage(driver);
        driver.get(BASE_URL);
    }

    private String getBrowser() {

        return "chrome"; // или "yandex"
    }

    public String createUserViaApi(String email, String password, String name) throws JsonProcessingException {
        UserModel userModel = new UserModel(email, password, name);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(userModel);

        return given()
                .contentType(ContentType.JSON)
                .log().all()
                .body(jsonString)
                .when()
                .post(BASE_URL + REGISTER_URL)
                .then()
                .log().all()
                .statusCode(200)
                .body("accessToken", notNullValue())
                .extract().path("accessToken");
    }


    @After
    public void tearDown() {
        cleanUpUser();
        quitBrowser();
    }

    public void cleanUpUser() {
        if (accessToken != null) {
            given()
                    .contentType(ContentType.JSON)
                    .header("Authorization", accessToken)
                    .when()
                    .delete(BASE_URL + DELETE_USER_URL);
        }
    }

    public void quitBrowser() {
        driver.quit();
    }

}
