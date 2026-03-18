package uitests;

import base.BaseTest;
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
import static org.junit.Assert.assertTrue;
import static pages.MainPage.personalAccountButton;
import static pages.RegistrationPage.incorrectPasswordWarningElement;

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

        mainPage.clickPersonalAccountButton();
        loginPage.clickStartRegistrationLink();
        registrationPage.setRegFormWithCorrectPassword();
        registrationPage.clickFinishRegistrationButton();
        loginPage.waitOnLoginPage();
        assertTrue(driver.findElement(LoginPage.loginPageHeader).isDisplayed());

    }

    @Test
    @DisplayName("Ошибка регистрации")
    @Description("Проверка предупреждения о некорректной длине пароля при вводе в поле Пароль <6 символов")
    public void testIncorrectPasswordRegistrationError() {

        mainPage.clickPersonalAccountButton();
        loginPage.clickStartRegistrationLink();
        registrationPage.setRegFormWIthIncorrectPassword();
        registrationPage.clickFinishRegistrationButton();
        registrationPage.checkIncorrectPasswordWarningElement();
        assertTrue(driver.findElement(RegistrationPage.incorrectPasswordWarningElement).isDisplayed());
    }
    }
