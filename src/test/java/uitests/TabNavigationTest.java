package uitests;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import pages.MainPage;

public class TabNavigationTest extends BaseTest{

    private MainPage mainPage;

    @Test
    @DisplayName("Переход к разделу Булки")
    @Description("Проверка возможности перехода в раздел Булки")
    public void openCatalogBunsTest() {
        mainPage = new MainPage(driver);
        mainPage.clickSaucesButton();
        mainPage.clickBunButton();
    }

        @Test
        @DisplayName("Переход к разделу Соусы")
        @Description("Проверка возможности перехода в раздел Соусы")
        public void openCatalogSaucesTest() {
            mainPage = new MainPage(driver);
            mainPage.clickSaucesButton();

        }

        @Test
        @DisplayName("Переход к разделу Начинки")
        @Description("Проверка возможности перехода в раздел Начинки")
        public void openCatalogFillingsTest() {
            mainPage = new MainPage(driver);
            mainPage.clickFillingsButton();

        }
}