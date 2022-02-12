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

public class InterShopMakingOrderPageTests {
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

    public By catalogButtonLocator = By.cssSelector("#menu-item-46 > a");
    public By cardAddBasketButtonLocator = By.cssSelector(".product:nth-child(1) .button");
    public By cardMoreInformationLocator = By.cssSelector(".added_to_cart");
    public By addOrderButtonLocator = By.linkText("ОФОРМИТЬ ЗАКАЗ");
    public By pleaseAuthButtonLocator = By.linkText("Авторизуйтесь");
    public By inputNameAuthLocator = By.cssSelector("#username");
    public By inputPasswordAuthLocator = By.cssSelector("#password");
    public By authButtonLocator = By.cssSelector("[name='login']");
    public By nameInputLocator = By.cssSelector("#billing_first_name");
    public By surnameInputLocator = By.cssSelector("#billing_last_name");
    public By addressInputLocator = By.cssSelector("#billing_address_1");
    public By cityInputLocator = By.cssSelector("#billing_city");
    public By stateInputLocator = By.cssSelector("#billing_state");
    public By postCodeInputLocator = By.cssSelector("#billing_postcode");
    public By phoneNumberInputLocator = By.cssSelector("#billing_phone");
    public By paymentMethodRadioButtonLocator = By.cssSelector("#payment_method_cod");
    public By paymentSecondPaymentMethodRadioButtonLocator = By.cssSelector("#payment_method_bacs");
    public By orderByButtonLocator = By.cssSelector("#place_order");
    public By expectedHeaderLocator = By.cssSelector(".woocommerce-thankyou-order-received");
    public By errorNameExpectedHeaderLocator = By.cssSelector("[data-id='billing_first_name']");
    public By errorSurnameExpectedHeaderLocator = By.cssSelector("[data-id='billing_last_name']");
    public By addCouponButtonLocator = By.cssSelector("a[class='showcoupon']");
    public By couponInputLocator = By.cssSelector("#coupon_code");
    public By applyCouponButton = By.cssSelector("[name='apply_coupon']");
    public By expectedCouponHeaderLocator = By.cssSelector(".woocommerce-message");

    //Негативный сценарий офрмления заказа: Оставить незаполнеными два поля first name и last name
    @Test
    public void interShop__MakingOrderPage__NegativeOrderTest() {
        var firstExpectedResult = "Имя для выставления счета обязательное поле.";
        var secondExpectedResult = "Фамилия для выставления счета обязательное поле.";
        driver.navigate().to("http://intershop5.skillbox.ru/product-category/catalog/");
        driver.findElement(catalogButtonLocator).click();
        driver.findElement(cardAddBasketButtonLocator).click();
        driver.findElement(cardMoreInformationLocator).click();
        driver.findElement(addOrderButtonLocator).click();
        driver.findElement(pleaseAuthButtonLocator).click();
        driver.findElement(inputNameAuthLocator).sendKeys("test@Localtest85.com");
        driver.findElement(inputPasswordAuthLocator).sendKeys("12345678");
        driver.findElement(authButtonLocator).click();
        driver.findElement(nameInputLocator).sendKeys("");
        driver.findElement(surnameInputLocator).sendKeys("");
        driver.findElement(addressInputLocator).sendKeys("ул. Кутузова 16");
        driver.findElement(cityInputLocator).sendKeys("Ульяновск");
        driver.findElement(stateInputLocator).sendKeys("Ульяновская область");
        driver.findElement(postCodeInputLocator).sendKeys("320456");
        driver.findElement(phoneNumberInputLocator).sendKeys("+79173063467");
        driver.findElement(paymentMethodRadioButtonLocator).click();
        driver.findElement(orderByButtonLocator).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(errorNameExpectedHeaderLocator));
        Assert.assertEquals("Заказ оформлен! Так быть не должно!", firstExpectedResult, driver.findElement(errorNameExpectedHeaderLocator).getText());
        Assert.assertEquals("Заказ оформлен! Так быть не должно!", secondExpectedResult, driver.findElement(errorSurnameExpectedHeaderLocator).getText());
    }

    //Позитивный сценарий оформления заказа, с выбором способа оплаты "Оплата при доставке"
    @Test
    public void interShop__MakingOrderPage__FirstPositiveOrderTest() {
        var expectedResult = "Спасибо! Ваш заказ был получен.";
        driver.navigate().to("http://intershop5.skillbox.ru/product-category/catalog/");
        driver.findElement(catalogButtonLocator).click();
        driver.findElement(cardAddBasketButtonLocator).click();
        driver.findElement(cardMoreInformationLocator).click();
        driver.findElement(addOrderButtonLocator).click();
        driver.findElement(pleaseAuthButtonLocator).click();
        driver.findElement(inputNameAuthLocator).sendKeys("test@Localtest82.com");
        driver.findElement(inputPasswordAuthLocator).sendKeys("12345678");
        driver.findElement(authButtonLocator).click();
        driver.findElement(nameInputLocator).sendKeys("Зина");
        driver.findElement(surnameInputLocator).sendKeys("Малышева");
        driver.findElement(addressInputLocator).sendKeys("ул. Кутузова 16");
        driver.findElement(cityInputLocator).sendKeys("Ульяновск");
        driver.findElement(stateInputLocator).sendKeys("Ульяновская область");
        driver.findElement(postCodeInputLocator).sendKeys("320456");
        driver.findElement(phoneNumberInputLocator).sendKeys("+79173063467");
        driver.findElement(paymentMethodRadioButtonLocator).click();
        driver.findElement(orderByButtonLocator).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(expectedHeaderLocator));
        Assert.assertEquals("Заказ не оформлен!", expectedResult, driver.findElement(expectedHeaderLocator).getText());
    }

    //Позитивный сценарий оформления заказа, с выбором способа оплаты "Банковской картой"
    @Test
    public void interShop__MakingOrderPage__SecondPositiveOrderTest() {
        var expectedResult = "Спасибо! Ваш заказ был получен.";
        driver.navigate().to("http://intershop5.skillbox.ru/product-category/catalog/");
        driver.findElement(catalogButtonLocator).click();
        driver.findElement(cardAddBasketButtonLocator).click();
        driver.findElement(cardMoreInformationLocator).click();
        driver.findElement(addOrderButtonLocator).click();
        driver.findElement(pleaseAuthButtonLocator).click();
        driver.findElement(inputNameAuthLocator).sendKeys("test@Localtest83.com");
        driver.findElement(inputPasswordAuthLocator).sendKeys("12345678");
        driver.findElement(authButtonLocator).click();
        driver.findElement(nameInputLocator).sendKeys("Зина");
        driver.findElement(surnameInputLocator).sendKeys("Малышева");
        driver.findElement(addressInputLocator).sendKeys("ул. Кутузова 16");
        driver.findElement(cityInputLocator).sendKeys("Ульяновск");
        driver.findElement(stateInputLocator).sendKeys("Ульяновская область");
        driver.findElement(postCodeInputLocator).sendKeys("320456");
        driver.findElement(phoneNumberInputLocator).sendKeys("+79173063467");
        driver.findElement(paymentSecondPaymentMethodRadioButtonLocator).click();
        driver.findElement(orderByButtonLocator).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(expectedHeaderLocator));
        Assert.assertEquals("Заказ не оформлен!", expectedResult, driver.findElement(expectedHeaderLocator).getText());
    }
    //Добавить купон в форме оформления заказа
    @Test
    public void interShop__MakingOrderPage__AddCouponTest() {
        var expectedCouponHeader = "Купон успешно добавлен.";
        driver.navigate().to("http://intershop5.skillbox.ru/product-category/catalog/");
        driver.findElement(catalogButtonLocator).click();
        driver.findElement(cardAddBasketButtonLocator).click();
        driver.findElement(cardMoreInformationLocator).click();
        driver.findElement(addOrderButtonLocator).click();
        driver.findElement(pleaseAuthButtonLocator).click();
        driver.findElement(inputNameAuthLocator).sendKeys("test@Localtest84.com");
        driver.findElement(inputPasswordAuthLocator).sendKeys("12345678");
        driver.findElement(authButtonLocator).click();
        driver.findElement(nameInputLocator).sendKeys("Зина");
        driver.findElement(surnameInputLocator).sendKeys("Малышева");
        driver.findElement(addressInputLocator).sendKeys("ул. Кутузова 16");
        driver.findElement(cityInputLocator).sendKeys("Ульяновск");
        driver.findElement(stateInputLocator).sendKeys("Ульяновская область");
        driver.findElement(postCodeInputLocator).sendKeys("320456");
        driver.findElement(phoneNumberInputLocator).sendKeys("+79173063467");
        driver.findElement(addCouponButtonLocator).click();
        driver.findElement(couponInputLocator).sendKeys("sert500");
        driver.findElement(applyCouponButton).click();
        Assert.assertEquals("Купон не применён!", expectedCouponHeader, driver.findElement(expectedCouponHeaderLocator).getText());
    }
}
