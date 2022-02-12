import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class InterShopRegistrationAuthorizationTests {
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

    public By enterButtonLocator = By.linkText("Войти");
    public By registrationButtonLocator = By.cssSelector(".custom-register-button");
    public By nameInputLocator = By.cssSelector("#reg_username");
    public By emailInputLocator = By.cssSelector("#reg_email");
    public By passwordInputLocator = By.cssSelector("#reg_password");
    public By submitButtonLocator = By.cssSelector(".woocommerce-form-register__submit");
    public By expectedHeaderLocator = By.cssSelector(".content-page > div");
    //Проверка позитивного сценария регистрации
    @Test
    public void interShop__AuthorizationPage__RegistrationTest() {
        var expectedResult = "Регистрация завершена";
        driver.navigate().to("http://intershop5.skillbox.ru/");
        driver.findElement(enterButtonLocator).click();
        driver.findElement(registrationButtonLocator).click();
        driver.findElement(nameInputLocator).sendKeys("test@Localtest83.com");
        driver.findElement(emailInputLocator).sendKeys("test@Localtest83.com");
        driver.findElement(passwordInputLocator).sendKeys("12345678");
        driver.findElement(submitButtonLocator).click();
        Assert.assertEquals("Регистрация не завершена",expectedResult, driver.findElement(expectedHeaderLocator).getText());
    }

    public By nameInputAuthLocator = By.cssSelector("#username");
    public By passwordInputAuthLocator = By.cssSelector("#password");
    public By submitAuthButtonLocator = By.cssSelector("button[name=login]");
    public By expectedHeaderAuthLocator = By.cssSelector(".post-title");
    //Проверка позитивного сценария авторизации
    @Test
    public void interShop__AuthorizationPage__AuthorizationTest(){
        var expectedHeader = "Мой аккаунт";
        driver.navigate().to("http://intershop5.skillbox.ru/");
        driver.findElement(enterButtonLocator).click();
        driver.findElement(nameInputAuthLocator).sendKeys("test@test.com");
        driver.findElement(passwordInputAuthLocator).sendKeys("12345678");
        driver.findElement(submitAuthButtonLocator).click();
        Assert.assertEquals("Авторизация не выполнена!",expectedHeader, driver.findElement(expectedHeaderAuthLocator).getText());
    }

    public By expectedErrorHeaderAuthLocator = By.cssSelector(".woocommerce-error > li");
    //Проверка негативного сценария авторизации (оставить поле password пустым)
    @Test
    public void interShop__AuthorizationPage__NegativePasswordAuthorizationTest(){
        var expectedHeader = "Пароль обязателен.";
        driver.navigate().to("http://intershop5.skillbox.ru/");
        driver.findElement(enterButtonLocator).click();
        driver.findElement(nameInputAuthLocator).sendKeys("test@test.com");
        driver.findElement(submitAuthButtonLocator).click();
        Assert.assertEquals("Авторизация прошла успешно!",expectedHeader, driver.findElement(expectedErrorHeaderAuthLocator).getText());
    }
    //Проверка негативного сценария авторизации (оставить поле name пустым)
    @Test
    public void interShop__AuthorizationPage__NegativeNameAuthorizationTest(){
        var expectedHeader = "Error: Имя пользователя обязательно.";
        driver.navigate().to("http://intershop5.skillbox.ru/");
        driver.findElement(enterButtonLocator).click();
        driver.findElement(passwordInputAuthLocator).sendKeys("12345678");
        driver.findElement(submitAuthButtonLocator).click();
        Assert.assertEquals("Авторизация прошла успешно!",expectedHeader, driver.findElement(expectedErrorHeaderAuthLocator).getText());
    }
}
