package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class MainPage {

    WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    //Локатор кнопки "Личный кабинет"
    public static final By personalAccountButton = By.xpath(".//p[text()='Личный Кабинет']");

    //Локатор кнопки "Войти в аккаунт"
    public static final By logIntoAccountButton = By.xpath(".//button[contains(@class, 'button_button_type_primary')]");

    //Локатор кнопки "Оформить заказ"
    public static final By placeAnOrderButton = By.xpath(".//button[contains(@class, 'button_button_type_primary__1O7Bx')]");

    //Локатор вкладки булки
    private final By bunButton = By.xpath(".//div[span[text()='Булки']]");
    //Локатор вкладки соусы
    private final By saucesButton = By.xpath(".//div[span[text()='Соусы']]");
    //Локатор вкладки начинки
    private final By fillingsButton = By.xpath(".//div[span[text()='Начинки']]");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Клик по ссылке Личный кабинет")
    public void clickPersonalAccountButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(personalAccountButton));
        driver.findElement(personalAccountButton).click();
    }

    @Step("Клик по ссылке Войти в аккаунт")
    public void clickLogIntoAccountButton() {
        driver.findElement(logIntoAccountButton).click();
    }

    @Step("Проверка успешного входа и возврата на главную страницу")
    public void checkPlaceAnOrderButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30)); // ожидание до 30 секунд
        wait.until(ExpectedConditions.visibilityOfElementLocated(placeAnOrderButton));
    }

    public MainPage clickBunButton() {
        driver.findElement(bunButton).click();
        return new MainPage(driver);
    }

    public MainPage clickSaucesButton() {
        driver.findElement(saucesButton).click();
        return new MainPage(driver);
    }

    public MainPage clickFillingsButton() {
        driver.findElement(fillingsButton).click();
        return new MainPage(driver);
    }

}
