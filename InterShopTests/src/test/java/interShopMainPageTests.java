import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class interShopMainPageTests {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 12);
        driver.manage().timeouts().implicitlyWait(12, TimeUnit.SECONDS);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    public By bookCardLocator = By.cssSelector("#accesspress_storemo-2 .caption");
    public By sectionBookTitleLocator = By.cssSelector("h1.entry-title.ak-container");
    //Проверка перехода в раздел "Книги", кликая на карточку "Книги"
    @Test
    public void interShop__Homepage__BookCardTest() {
        driver.navigate().to("http://intershop5.skillbox.ru/");
        driver.findElement(bookCardLocator).click();
        Assert.assertEquals("Заголовок раздела не соответствует выбранной карточке", "КНИГИ", driver.findElement(sectionBookTitleLocator).getText());
    }

    public By tabletCardLocator = By.cssSelector("#accesspress_storemo-3 .caption");
    public By sectionTabletTitleLocator = By.cssSelector("h1.entry-title.ak-container");
    //Проверка перехода в раздел "Планшеты", кликая на карточку "Планшеты"
    @Test
    public void interShop__Homepage__tabletCardTest(){
        driver.navigate().to("http://intershop5.skillbox.ru/");
        driver.findElement(tabletCardLocator).click();
        Assert.assertEquals("Заголовок раздела не соответствует выбранной карточке", "ПЛАНШЕТЫ", driver.findElement(sectionTabletTitleLocator).getText());
    }

    public By cameraCardLocator = By.cssSelector("#accesspress_storemo-4 .caption");
    public By sectionCameraTitleLocator = By.cssSelector("h1.entry-title.ak-container");
    //Проверка перехода в раздел "Фото/Видео", кликая на карточку "Фото/Видео"
    @Test
    public void interShop__Homepage__cameraCardTest(){
        driver.navigate().to("http://intershop5.skillbox.ru/");
        driver.findElement(cameraCardLocator).click();
        Assert.assertEquals("Заголовок раздела не соответствует выбранной карточке", "ФОТО/ВИДЕО", driver.findElement(sectionCameraTitleLocator).getText());
    }

    public By seeProductButtonLocator = By.cssSelector(".promo-widget-wrap-full .btn.promo-link-btn");
    public By tabletTitleHeaderLocator = By.cssSelector(".product_title.entry-title");
    //Проверка перехода в раздел "Уже в продаже", кликая в карточке "Уже в продаже" на кнопку "Посмотреть товар"
    @Test
    public void interShop__Homepage__ProductButtonTest(){
        driver.navigate().to("http://intershop5.skillbox.ru/");
        driver.findElement(seeProductButtonLocator).click();
        Assert.assertEquals("Модель планшета указана не корректно", "iPad 2020 32gb wi-fi", driver.findElement(tabletTitleHeaderLocator).getText());
    }

    public By viewedProductsModuleLocator = By.cssSelector("#woocommerce_recently_viewed_products-2");
    public By mainPageButtonLocator = By.cssSelector("#menu-item-26 > a");
    //Проверка появления модуля "Ранее просмотренные товары"
    @Test
    public void interShop__Homepage__viewedProductsModuleTest(){
       driver.navigate().to("http://intershop5.skillbox.ru/");
       driver.findElement(seeProductButtonLocator).click();
       driver.findElement(mainPageButtonLocator).click();
       wait.until(ExpectedConditions.presenceOfElementLocated(viewedProductsModuleLocator));
       Assert.assertTrue("Раздел не появился", driver.findElement(viewedProductsModuleLocator).isDisplayed());
    }

    public By phoneTextLocator = By.cssSelector(".text-5-value:nth-child(1)");
    public By htmlLocator = By.cssSelector("html");
    //Проверка указанного в разделе контакты, в футере странице телефона
    @Test
    public void footerPhoneTextTest(){
        driver.navigate().to("http://intershop5.skillbox.ru/");
        driver.findElement(htmlLocator).sendKeys(Keys.END);
        wait.until(ExpectedConditions.visibilityOfElementLocated(phoneTextLocator));
        Assert.assertEquals("Телефон указан некорректно", "Телефон: +7-999-123-12-12", driver.findElement(phoneTextLocator).getText());
    }

    public By emailTextLocator = By.cssSelector(".text-5-value:nth-child(2)");
    //Проверка указанного в разделе контакты, в футере странице e-mail
    @Test
    public void interShop__Homepage__footerEmailTextTest(){
        driver.navigate().to("http://intershop5.skillbox.ru/");
        driver.findElement(htmlLocator).sendKeys(Keys.END);
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailTextLocator));
        Assert.assertEquals("Телефон указан некорректно", "Email: skillbox@skillbox.ru", driver.findElement(emailTextLocator).getText());
    }
}
