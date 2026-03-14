package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotPasswordPage {

    private WebDriver driver;

    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    //Локатор кнопки Войти на элементе Вспомнили пароль?
    static final By rememberedPasswordSignInButton = By.xpath("//a[@class='Auth_link__1fOlj' and text()='Войти']");

    @Step("Клик по кнопке Войти на элементе Вспомнили пароль")
    public void clickRememberedPasswordSignInButton() {
        driver.findElement(rememberedPasswordSignInButton).click();
    }

}
