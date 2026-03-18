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
    public final By bunButton = By.xpath(".//div[span[text()='Булки']]");
    //Локатор вкладки соусы
    public final By saucesButton = By.xpath(".//div[span[text()='Соусы']]");
    //Локатор вкладки начинки
    public final By fillingsButton = By.xpath(".//div[span[text()='Начинки']]");

    // Локатор активной кнопки "Булки"
    public static final By activeBunButton = By.xpath(".//div[contains(@class, 'tab_tab_type_current__2BEPc') and span[text()='Булки']]");

    // Локатор активной кнопки "Соусы"
    public static final By activeSaucesButton = By.xpath(".//div[contains(@class, 'tab_tab_type_current__2BEPc') and span[text()='Соусы']]");

    // Локатор активной кнопки "Начинки"
    public static final By activeFillingsButton = By.xpath(".//div[contains(@class, 'tab_tab_type_current__2BEPc') and span[text()='Начинки']]");


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
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(logIntoAccountButton));
        driver.findElement(logIntoAccountButton).click();
    }

    @Step("Проверка успешного входа и возврата на главную страницу")
    public void checkPlaceAnOrderButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30)); // ожидание до 30 секунд
        wait.until(ExpectedConditions.visibilityOfElementLocated(placeAnOrderButton));
    }

    public void clickBunButton() {
        driver.findElement(bunButton).click();
        new MainPage(driver);
    }

    public void clickSaucesButton() {
        driver.findElement(saucesButton).click();
        new MainPage(driver);
    }

    public void clickFillingsButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(fillingsButton));
        driver.findElement(fillingsButton).click();
        new MainPage(driver);
    }

}
