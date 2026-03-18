package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage {

    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Локатор ссылки "Зарегистрироваться"
    static final By startRegistrationLink = By.xpath(".//a[@class='Auth_link__1fOlj']");
    // Локатор поля ввода email
    static final By signInEmailField = By.xpath(".//label[contains(text(), 'Email')]/following-sibling::input");
    // Локатор поля ввода пароля
    static final By signInPasswordField = By.xpath("//label[contains(text(), 'Пароль')]/following-sibling::input");
    //Локатор кнопки "Войти"
    static final By signInButton = By.xpath(".//button[contains(@class, 'button_button_type_primary__1O7Bx')]");
    //Локатор кнопки "Восстановить пароль"
    static final By recoverPasswordButton = By.xpath("//a[@class='Auth_link__1fOlj' and text()='Восстановить пароль']");
    //Локатор заголовка страницы авторизации
    public static final By loginPageHeader = By.xpath("//h2[text()='Вход']");


    @Step("Заполнение поля email")
    public void enterSignInEmail(String email) {
        WebElement emailFieldElement = driver.findElement(signInEmailField);
        emailFieldElement.click();
        emailFieldElement.sendKeys(email);
    }

    @Step("Заполнение поля пароля")
    public void enterSignInPassword(String password) {
        WebElement passwordFieldElement = driver.findElement(signInPasswordField);
        passwordFieldElement.click();
        passwordFieldElement.sendKeys(password);
    }

    @Step("Клик по ссылке Зарегистрироваться")
    public void clickSignInButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(signInButton));
        driver.findElement(signInButton).click();
    }

    @Step("Клик по ссылке Зарегистрироваться")
    public void clickStartRegistrationLink() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(startRegistrationLink));
        driver.findElement(startRegistrationLink).click();
    }

    @Step("Клик по ссылке Восстановить пароль")
    public void clickRecoverPasswordButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(recoverPasswordButton));
        driver.findElement(recoverPasswordButton).click();
    }

    @Step("Переход на страницу авторизации")
    public void waitOnLoginPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30)); // ожидание до 30 секунд
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='Вход']")));
    }
}