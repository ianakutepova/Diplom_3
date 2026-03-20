package uitests;

import base.BaseTest;
import com.github.javafaker.Faker;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import pages.ForgotPasswordPage;
import pages.LoginPage;
import pages.MainPage;
import pages.RegistrationPage;

import static org.junit.Assert.assertTrue;


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

        faker = new Faker();

        userEmail = (faker.name().lastName() + faker.regexify("[0-9]{4}") + "@example.com").toLowerCase();
        userPassword = faker.regexify("[a-zA-Z0-9]{6,}");

    }


    @Test
    @DisplayName("Вход по кнопке Войти в аккаунт")
    @Description("Проверка возможности входа по кнопке «Войти в аккаунт» на главной странице")
    public void testSignInLogIntoAccountButtonSuccess() {

        MainPage.clickLogIntoAccountButton();
        LoginPage.enterSignInEmail(userEmail);
        LoginPage.enterSignInPassword(userPassword);
        LoginPage.clickSignInButton();

        assertTrue(driver.findElement(MainPage.placeAnOrderButton).isDisplayed());

    }

    @Test
    @DisplayName("Вход через кнопку Личный кабинет")
    @Description("Проверка возможности входа по кнопке Личный кабинет на главной странице")
    public void testSignInPersonalAccountButtonSuccess() {

        MainPage.clickPersonalAccountButton();
        LoginPage.enterSignInEmail(userEmail);
        LoginPage.enterSignInPassword(userPassword);
        LoginPage.clickSignInButton();

        assertTrue(driver.findElement(MainPage.placeAnOrderButton).isDisplayed());

    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    @Description("Проверка возможности входа по кнопке Войти элемента Уже зарегистрированы? Войти")
    public void testSignInRegFormSignInButtonSuccess() {

        MainPage.clickLogIntoAccountButton();
        LoginPage.clickStartRegistrationLink();
        RegistrationPage.clickAlreadyRegSignInButton();
        LoginPage.enterSignInEmail(userEmail);
        LoginPage.enterSignInPassword(userPassword);
        LoginPage.clickSignInButton();

        assertTrue(driver.findElement(MainPage.placeAnOrderButton).isDisplayed());

    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    @Description("Проверка возможности входа по кнопке Войти элемента Вспомнили пароль? Войти")
    public void testSignInPasswordResetPageSignInButtonSuccess() {
        MainPage.clickLogIntoAccountButton();
        LoginPage.clickRecoverPasswordButton();
        ForgotPasswordPage.clickRememberedPasswordSignInButton();
        LoginPage.enterSignInEmail(userEmail);
        LoginPage.enterSignInPassword(userPassword);
        LoginPage.clickSignInButton();

        assertTrue(driver.findElement(MainPage.placeAnOrderButton).isDisplayed());
    }

}

