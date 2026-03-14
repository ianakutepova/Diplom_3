package uitests;

import com.github.javafaker.Faker;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;
import pages.ForgotPasswordPage;
import pages.LoginPage;
import pages.MainPage;
import pages.RegistrationPage;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;
import static pages.MainPage.logIntoAccountButton;
import static pages.MainPage.personalAccountButton;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class SignInTest extends BaseTest {

    private MainPage mainPage;
    private LoginPage loginPage;
    private RegistrationPage registrationPage;
    private ForgotPasswordPage forgotPasswordPage;
    private static String userEmail;
    private static String userPassword;
    private Faker faker;
    private String accessToken;

    @Before
    public void setUp() {
        mainPage = new MainPage(super.driver);
        loginPage = new LoginPage(super.driver);
        registrationPage = new RegistrationPage(super.driver);
        forgotPasswordPage = new ForgotPasswordPage(super.driver);

        RestAssured.baseURI = "https://stellarburgers.education-services.ru/";
        faker = new Faker();

        userEmail = (faker.name().lastName() + faker.regexify("[0-9]{4}") + "@example.com").toLowerCase();
        userPassword = faker.regexify("[a-zA-Z0-9]{6,}");
        createUserViaApi();

    }

    public void createUserViaApi() {

        String requestBody = String.format(
                "{\"email\": \"%s\", " +
                        "\"password\": \"%s\"," +
                        "\"name\": \"%s\"}",
                userEmail, userPassword, faker.name().firstName()
        );

        System.out.println("Email: " + userEmail);
        System.out.println("Password: " + userPassword);

        accessToken = given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/api/auth/register")
                .then()
                .log().all()
                .statusCode(200)
                .body("accessToken", notNullValue())
                .extract().path("accessToken");
    }

    @Test
    @DisplayName("Вход по кнопке Войти в аккаунт")
    @Description("Проверка возможности входа по кнопке «Войти в аккаунт» на главной странице")
    public void testSignInLogIntoAccountButtonSuccess() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(logIntoAccountButton));

        mainPage.clickLogIntoAccountButton();
        loginPage.enterSignInEmail(userEmail);
        loginPage.enterSignInPassword(userPassword);
        loginPage.clickSignInButton();
        mainPage.checkPlaceAnOrderButton();
        cleanUpUser();
    }

    @Test
    @DisplayName("Вход через кнопку Личный кабинет")
    @Description("Проверка возможности входа по кнопке Личный кабинет на главной странице")
    public void testSignInPersonalAccountButtonSuccess() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(personalAccountButton));

        mainPage.clickPersonalAccountButton();
        loginPage.enterSignInEmail(userEmail);
        loginPage.enterSignInPassword(userPassword);
        loginPage.clickSignInButton();
        mainPage.checkPlaceAnOrderButton();
        cleanUpUser();
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    @Description("Проверка возможности входа по кнопке Войти элемента Уже зарегистрированы? Войти")
    public void testSignInRegFormSignInButtonSuccess() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(personalAccountButton));

        mainPage.clickLogIntoAccountButton();
        loginPage.clickStartRegistrationLink();
        registrationPage.clickAlreadyRegSignInButton();
        loginPage.enterSignInEmail(userEmail);
        loginPage.enterSignInPassword(userPassword);
        loginPage.clickSignInButton();
        mainPage.checkPlaceAnOrderButton();
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    @Description("Проверка возможности входа по кнопке Войти элемента Вспомнили пароль? Войти")
    public void testSignInPasswordResetPageSignInButtonSuccess() {
        mainPage.clickLogIntoAccountButton();
        loginPage.clickRecoverPasswordButton();
        forgotPasswordPage.clickRememberedPasswordSignInButton();
        loginPage.enterSignInEmail(userEmail);
        loginPage.enterSignInPassword(userPassword);
        loginPage.clickSignInButton();
        mainPage.checkPlaceAnOrderButton();
    }


}

