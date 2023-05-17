package org.brit.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BasicAuthTests extends BaseTestClass{

    @Test
    public void basicAuthTest() {
        driver.get("https://admin:admin@the-internet.herokuapp.com/basic_auth");
        Assert.assertEquals(driver.findElement(By.xpath("//h3")).getText(), "Basic Auth");

        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("sessionStorage.clear();");
        javascriptExecutor.executeScript("localStorage.clear();");
    }

    @Test
    public void basicAuthTest1() {
        goToPart("basic_auth");
       // driver.get("https://the-internet.herokuapp.com/basic_auth");
        Assert.assertEquals(driver.findElement(By.xpath("//h3")).getText(), "Basic Auth");
    }
}
