import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class InterShopBasketPageTests {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 5);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    public By addProductButtonLocator = By.cssSelector(".product:nth-child(1) .button");
    public By moreInformationProductButtonLocator = By.cssSelector(".added_to_cart");
    public By confirmOrderButtonLocator = By.linkText("ОФОРМИТЬ ЗАКАЗ");
    public By expectedHeaderResult = By.cssSelector(".post-title");

    //Позитивный сценарий добавления товара в корзину, и перехода на страницу "Оформление заказа"
    @Test
    public void interShop__BasketPage__PositiveTest() {
        var expectedHeader = "Оформление заказа";
        driver.navigate().to("http://intershop5.skillbox.ru/product-category/catalog/");
        driver.findElement(addProductButtonLocator).click();
        driver.findElement(moreInformationProductButtonLocator).click();
        driver.findElement(confirmOrderButtonLocator).click();
        Assert.assertEquals("Произошёл переход не в тот раздел сайта", expectedHeader, driver.findElement(expectedHeaderResult).getText());
    }

    public By inputCouponLocator = By.cssSelector("#coupon_code");
    public By buttonApplyCouponLocator = By.cssSelector("[name=apply_coupon]");
    public By finalPriceLocator = By.cssSelector("strong bdi");

    //Проверка применения купона на странице "Корзина"
    @Test
    public void interShop__BasketPage__CouponApplicationTest() {
        driver.navigate().to("http://intershop5.skillbox.ru/product-category/catalog/");
        driver.findElement(addProductButtonLocator).click();
        driver.findElement(moreInformationProductButtonLocator).click();
        driver.findElement(inputCouponLocator).sendKeys("sert500");
        driver.findElement(buttonApplyCouponLocator).click();
        var expectedResult = "34990,00₽";
        var actualResult = driver.findElement(finalPriceLocator).getText();
        Assert.assertEquals("Итоговая сумма отображается без применения купона", actualResult, expectedResult);
    }

    public By errorStringLocator = By.cssSelector(".woocommerce-error > li");
    //Негативный сценарий. Ввести не корректный купон, в поле для купона, на странице "Корзина"
    @Test
    public void interShop__BasketPage__NegativeCouponApplicationTest() {
        var expectedErrorString = "Неверный купон.";
        driver.navigate().to("http://intershop5.skillbox.ru/product-category/catalog/");
        driver.findElement(addProductButtonLocator).click();
        driver.findElement(moreInformationProductButtonLocator).click();
        driver.findElement(inputCouponLocator).sendKeys("sert3000");
        driver.findElement(buttonApplyCouponLocator).click();
        Assert.assertEquals("Не отображается сообщение об ошибке", expectedErrorString, driver.findElement(errorStringLocator).getText());
    }

    public By deleteCouponButtonLocator = By.cssSelector("a.woocommerce-remove-coupon");
    public By expectedErrorStringLocator = By.cssSelector(".woocommerce-message");
    //Проверка удаления купона из корзины
    @Test
    public void interShop__BasketPage__DeleteCouponTest() {
        var expectedCouponString = "Купон удален.";
        driver.navigate().to("http://intershop5.skillbox.ru/product-category/catalog/");
        driver.findElement(addProductButtonLocator).click();
        driver.findElement(moreInformationProductButtonLocator).click();
        driver.findElement(inputCouponLocator).sendKeys("sert500");
        driver.findElement(buttonApplyCouponLocator).click();
        driver.findElement(deleteCouponButtonLocator).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(deleteCouponButtonLocator));
        Assert.assertEquals("Не отображается сообщение об удалении купона", expectedCouponString, driver.findElement(expectedErrorStringLocator).getText());
    }

    public By deleteProductButtonLocator = By.linkText("×");
    public By expectedHeaderLocator = By.cssSelector(".cart-empty");
    //Проверка удаления товара из корзины
    @Test
    public void interShop__BasketPage__DeleteProductTest() {
        var expectedHeader = "Корзина пуста.";
        driver.navigate().to("http://intershop5.skillbox.ru/product-category/catalog/");
        driver.findElement(addProductButtonLocator).click();
        driver.findElement(moreInformationProductButtonLocator).click();
        driver.findElement(deleteProductButtonLocator).click();
        Assert.assertEquals("Товар из корзины не удалился", expectedHeader, driver.findElement(expectedHeaderLocator).getText());
    }
}
