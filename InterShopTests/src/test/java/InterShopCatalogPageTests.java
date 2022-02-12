import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;

public class InterShopCatalogPageTests {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    public By linkHouseholdEquipmentCatalogListLocator = By.linkText("Бытовая техника");
    public By addBasketButton = By.cssSelector(".product:nth-child(1) .button");
    public By moreInformationButtonLocator = By.cssSelector(".added_to_cart");
    public By expectedHeaderResultLocator = By.linkText("Стиральная машина BEKO WRE64P1BWW, фронтальная, 6кг, 800об/мин");
    //Проверить добавление стиральной машины в корзину, в разделе "Бытовая техника"
    @Test
    public void interShop__CatalogPage__First__householdEquipment__AddBasketTest() {
        var expectedResult = "Стиральная машина BEKO WRE64P1BWW, фронтальная, 6кг, 800об/мин";
        driver.navigate().to("http://intershop5.skillbox.ru/product-category/catalog/");
        driver.findElement(linkHouseholdEquipmentCatalogListLocator).click();
        driver.findElement(addBasketButton).click();
        driver.findElement(moreInformationButtonLocator).click();
        Assert.assertEquals("Данные в корзине не соответствуют выбранной модели в каталоге", expectedResult, driver.findElement(expectedHeaderResultLocator).getText());
    }

    public By addRefrigeratorBasketButton = By.cssSelector(".product:nth-child(10) .button");
    public By refrigeratorExpectedHeaderResultLocator = By.linkText("Холодильник БИРЮСА Б-M10, однокамерный, серебристый");
    //Проверить добавление холодильника в корзину, в разделе "Бытовая техника"
    @Test
    public void interShop__CatalogPage__Second__householdEquipment__AddBasketTest() {
        var expectedResult = "Холодильник БИРЮСА Б-M10, однокамерный, серебристый";
        driver.navigate().to("http://intershop5.skillbox.ru/product-category/catalog/");
        driver.findElement(linkHouseholdEquipmentCatalogListLocator).click();
        driver.findElement(addRefrigeratorBasketButton).click();
        driver.findElement(moreInformationButtonLocator).click();
        Assert.assertEquals("Данные в корзине не соответствуют выбранной модели в каталоге", expectedResult, driver.findElement(refrigeratorExpectedHeaderResultLocator).getText());
    }

    public By addCatalogBasketButtonLocator = By.cssSelector(".product:nth-child(6) .button");
    public By catalogFirstExpectedHeaderResultLocator = By.linkText("LED телевизор XIAOMI Mi TV 4S 43 Ultra HD 4K");
    //Проверить добавление холодильника в корзину, в разделе "Бытовая техника"
    @Test
    public void interShop__CatalogPage__First__Catalog__AddBasketTest() {
        var expectedResult = "LED телевизор XIAOMI Mi TV 4S 43 Ultra HD 4K";
        driver.navigate().to("http://intershop5.skillbox.ru/product-category/catalog/");
        driver.findElement(addCatalogBasketButtonLocator).click();
        driver.findElement(moreInformationButtonLocator).click();
        Assert.assertEquals("Данные в корзине не соответствуют выбранной модели в каталоге", expectedResult, driver.findElement(catalogFirstExpectedHeaderResultLocator).getText());
    }

    public By paginationButtonFirthLocator = By.linkText("4");
    public By addCatalogProductBasketButtonLocator = By.cssSelector(".add_to_cart_button");
    public By catalogSecondExpectedHeaderResultLocator = By.linkText("Штука");
    //Проверить работу пагинации, и добавления товара на крайней странице в разделе "Каталог"
    @Test
    public void interShop__CatalogPage__Second__Catalog__AddBasketTest() {
        var expectedResult = "Штука";
        driver.navigate().to("http://intershop5.skillbox.ru/product-category/catalog/");
        driver.findElement(paginationButtonFirthLocator).click();
        driver.findElement(addCatalogProductBasketButtonLocator).click();
        driver.findElement(moreInformationButtonLocator).click();
        Assert.assertEquals("Данные в корзине не соответствуют выбранной модели в каталоге", expectedResult, driver.findElement(catalogSecondExpectedHeaderResultLocator).getText());
    }

    public By tablesButtonLocator = By.linkText("Планшеты");
    public By tablesCardAddButtonLocator = By.cssSelector(".product:nth-child(1) .button");
    public By expectedTablesHeaderLocator = By.linkText("iPad 2020 32gb wi-fi");
    //Проверить добавление товара в корзину, в разделе "Планшеты"
    @Test
    public void interShop__CatalogPage__Tables__AddBasketTest() {
        var expectedResult = "iPad 2020 32gb wi-fi";
        driver.navigate().to("http://intershop5.skillbox.ru/product-category/catalog/");
        driver.findElement(tablesButtonLocator).click();
        driver.findElement(tablesCardAddButtonLocator).click();
        driver.findElement(moreInformationButtonLocator).click();
        Assert.assertEquals("Данные в корзине не соответствуют выбранной модели в каталоге", expectedResult, driver.findElement(expectedTablesHeaderLocator).getText());
    }

    public By watchButtonLocator = By.linkText("Часы");
    public By watchCardAddButtonLocator = By.cssSelector(".product:nth-child(2) .button");
    public By expectedWatchHeaderLocator = By.linkText("SAMSUNG Galaxy Watch 46мм, 1.3\", серебристый");
    //Проверить добавление товара в корзину, в разделе "Часы"
    @Test
    public void interShop__CatalogPage__Watch__AddBasketTest() {
        var expectedResult = "SAMSUNG Galaxy Watch 46мм, 1.3\", серебристый";
        driver.navigate().to("http://intershop5.skillbox.ru/product-category/catalog/");
        driver.findElement(watchButtonLocator).click();
        driver.findElement(watchCardAddButtonLocator).click();
        driver.findElement(moreInformationButtonLocator).click();
        Assert.assertEquals("Данные в корзине не соответствуют выбранной модели в каталоге", expectedResult, driver.findElement(expectedWatchHeaderLocator).getText());
    }

    public By electronicsButtonLocator = By.linkText("Электроника");
    public By electronicsPaginationButtonLocator = By.linkText("2");
    public By electronicsFirstAddBasketButtonLocator = By.cssSelector(".product:nth-child(2) .button");
    public By electronicsSecondAddBasketButtonLocator = By.cssSelector(".product:nth-child(6) .button");
    public By electronicsMoreInformationButtonLocator = By.cssSelector(".post-220 .added_to_cart");
    public By expectedElectronicsFirstHeaderLocator = By.linkText("Штука");
    public By expectedElectronicsSecondHeaderLocator = By.linkText("Фотоаппарат CANON EOS M50 kit ( 15-45 IS STM), белый");
    //Проверить работу пагинации, и добавления товара на крайней странице в разделе "Электроника"
    @Test
    public void interShop__CatalogPage__Electronics__AddBasketTest() {
        var firstExpectedResult = "Штука";
        var secondExpectedResult = "Фотоаппарат CANON EOS M50 kit ( 15-45 IS STM), белый";
        driver.navigate().to("http://intershop5.skillbox.ru/product-category/catalog/");
        driver.findElement(electronicsButtonLocator).click();
        driver.findElement(electronicsPaginationButtonLocator).click();
        driver.findElement(electronicsFirstAddBasketButtonLocator).click();
        driver.findElement(electronicsSecondAddBasketButtonLocator).click();
        driver.findElement(electronicsMoreInformationButtonLocator).click();
        Assert.assertEquals("Данные в корзине не соответствуют выбранной модели в каталоге", firstExpectedResult, driver.findElement(expectedElectronicsFirstHeaderLocator).getText());
        Assert.assertEquals("Данные в корзине не соответствуют выбранной модели в каталоге", secondExpectedResult, driver.findElement(expectedElectronicsSecondHeaderLocator).getText());
    }
}
