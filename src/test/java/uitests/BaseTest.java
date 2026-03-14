package uitests;

import baseUI.WebDriverCreator;
import io.restassured.http.ContentType;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import pages.MainPage;

import static io.restassured.RestAssured.given;

public class BaseTest {

    public static final String BASE_URL = "https://stellarburgers.education-services.ru/";
    WebDriver driver;
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
                    .delete("/api/auth/user")
                    .then()
                    .statusCode(202);
        }
    }

    public void quitBrowser() {
        driver.quit();
    }

}
