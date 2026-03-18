package pages;

import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class RegistrationPage {

    private WebDriver driver;
    static Faker faker = new Faker();

    // Локатор поля ввода имени
    static private final By nameField = By.xpath("//label[text()='Имя']/following-sibling::input");
    // Локатор поля ввода email
    static private final By emailField = By.xpath("//label[text()='Email']/following-sibling::input");
    // Локатор поля ввода пароля
    static private final By passwordField = By.xpath("//label[text()='Пароль']/following-sibling::input");
    //Локатор кнопки "Зарегистрироваться"
    static final By finishRegistrationButton = By.xpath("//button[text()='Зарегистрироваться']");
    //Локатор кнопки "Войти" на элементе "Уже зарегистрированы?"
    static final By alreadyRegSignInButton = By.xpath("//a[@class='Auth_link__1fOlj' and text()='Войти']");
    //Локатор сообщения с предупреждением о неверной длине пароля
    public static final By incorrectPasswordWarningElement = By.xpath("//p[@class='input__error text_type_main-default'][text()='Некорректный пароль']");

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Заполнение формы регистрации")
    public void setRegFormWithCorrectPassword() {
        String name = faker.name().fullName();
        String email = faker.internet().emailAddress();
        String correctPassword = faker.regexify("[a-zA-Z0-9]{6,}"); // Корректный пароль, соответствует регулярному выражению

        enterName(name);
        enterEmail(email);
        enterPassword(correctPassword);
    }


    @Step("Заполнение имени")
    public void enterName(String name) {
        WebElement nameFieldElement = driver.findElement(nameField);
        nameFieldElement.click();
        nameFieldElement.sendKeys(name);
    }

    @Step("Заполнение email")
    public void enterEmail(String email) {
        WebElement emailFieldElement = driver.findElement(emailField);
        emailFieldElement.click();
        emailFieldElement.sendKeys(email);
    }

    @Step("Заполнение пароля")
    public void enterPassword(String password) {
        WebElement passwordFieldElement = driver.findElement(passwordField);
        passwordFieldElement.click();
        passwordFieldElement.sendKeys(password);
    }

    @Step("Клик по кнопке Зарегистрироваться")
    public void clickFinishRegistrationButton() {
        driver.findElement(finishRegistrationButton).click();
    }

    @Step("Клик по кнопке Уже зарегистрированы? Войти")
    public void clickAlreadyRegSignInButton() {
        driver.findElement(alreadyRegSignInButton).click();
    }

    public void setRegFormWIthIncorrectPassword() {
        String name = faker.name().fullName();
        String email = faker.internet().emailAddress();
        String incorrectPassword = faker.regexify("[a-zA-Z0-9]{1,5}"); // Некорректный пароль, меньше 6 символов

        enterName(name);
        enterEmail(email);
        enterPassword(incorrectPassword);
    }

    public void checkIncorrectPasswordWarningElement() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(incorrectPasswordWarningElement));
    }

    }

