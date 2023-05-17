package org.brit.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;
import org.brit.Selenium.utils.PropertiesReader;

public class LoginTests extends BaseTestClass {
    private static final String USER_NAME = PropertiesReader.getInstance().getProperty("swag.lab.username");
    private static final String USER_PASS = PropertiesReader.getInstance().getProperty("swag.lab.userpass");


    @BeforeMethod
    public void beforeMethod() {
        goToUrl(PropertiesReader.getInstance().getProperty("swag.lab.url"));
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
