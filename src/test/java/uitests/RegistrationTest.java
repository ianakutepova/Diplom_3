package uitests;

import base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import pages.LoginPage;
import pages.MainPage;
import pages.RegistrationPage;

import static org.junit.Assert.assertTrue;

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

        MainPage.clickPersonalAccountButton();
        LoginPage.clickStartRegistrationLink();
        RegistrationPage.setRegFormWithCorrectPassword();
        RegistrationPage.clickFinishRegistrationButton();
        LoginPage.waitOnLoginPage();
        assertTrue(driver.findElement(LoginPage.loginPageHeader).isDisplayed());

    }

    @Test
    @DisplayName("Ошибка регистрации")
    @Description("Проверка предупреждения о некорректной длине пароля при вводе в поле Пароль <6 символов")
    public void testIncorrectPasswordRegistrationError() {

        MainPage.clickPersonalAccountButton();
        LoginPage.clickStartRegistrationLink();
        RegistrationPage.setRegFormWIthIncorrectPassword();
        RegistrationPage.clickFinishRegistrationButton();
        RegistrationPage.checkIncorrectPasswordWarningElement();
        assertTrue(driver.findElement(RegistrationPage.incorrectPasswordWarningElement).isDisplayed());
    }
    }
