package org.brit.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;

public class LoginTests {
    private static final String USER_NAME = "standard_user";
    private static final String USER_PASS = "secret_sauce";
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
    }

    @AfterClass
    public void afterClass() {
        if (driver != null) {
            driver.quit();
        }
    }

    @BeforeMethod
    public void beforeMethod() {
        driver.navigate().to("https://www.saucedemo.com/");
    }

    @AfterMethod
    public void afterMethod() {
        driver.manage().deleteAllCookies();
    }

    @Test
    public void loginWithXpath() {
        var userNameField = driver.findElement(By.xpath("//input[@id='user-name']"));
        WebElement userPassField = driver.findElement(By.xpath("//input[@name='password']"));

        userNameField.clear();
        userNameField.sendKeys(USER_NAME);

        userPassField.clear();
        userPassField.sendKeys(USER_PASS);

        driver.findElement(By.xpath("//input[contains(@class, 'submit')]")).click();
    }

    @Test
    public void loginWithCSS() {
        var userNameField = driver.findElement(By.cssSelector("input[data-test='username']"));
        WebElement userPassField = driver.findElement(By.cssSelector("#password"));

        userNameField.clear();
        userNameField.sendKeys(USER_NAME);

        userPassField.clear();
        userPassField.sendKeys(USER_PASS);

        driver.findElement(By.cssSelector(".submit-button")).click();
    }
}
