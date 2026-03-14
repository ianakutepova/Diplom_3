package uitests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginPage;
import pages.MainPage;
import pages.RegistrationPage;
import java.time.Duration;

import static io.restassured.RestAssured.given;
import static pages.MainPage.personalAccountButton;

public class RegistrationTest extends BaseTest {
    MainPage mainPage;
    LoginPage loginPage;
    RegistrationPage registrationPage;

    @Before
    public void setUp() {
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        registrationPage = new RegistrationPage(driver);
    }

    @Test
    @DisplayName("Успешная регистрация пользователя")
    @Description("Проверка успешной регистрации пользователя при заполнении обязательных полей и длине пароля 6=< символов")
    public void testRegistrationSuccessfully() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(personalAccountButton));
        mainPage.clickPersonalAccountButton();
        loginPage.clickStartRegistrationLink();
        registrationPage.setRegFormWithCorrectPassword();
        registrationPage.clickFinishRegistrationButton();
        loginPage.waitOnLoginPage();

    }

    @Test
    @DisplayName("Ошибка регистрации")
    @Description("Проверка предупреждения о некорректной длине пароля при вводе в поле Пароль <6 символов")
    public void testIncorrectPasswordRegistrationError() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(personalAccountButton));
        mainPage.clickPersonalAccountButton();
        loginPage.clickStartRegistrationLink();
        registrationPage.setRegFormWIthIncorrectPassword();
        registrationPage.clickFinishRegistrationButton();
        registrationPage.checkIncorrectPasswordWarningElement();
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
}
