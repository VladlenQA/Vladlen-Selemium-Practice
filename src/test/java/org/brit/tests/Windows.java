package org.brit.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Set;

public class Windows extends BaseTestClass {

    @Test
    public void switchToWindow() {
        driver.get("https://the-internet.herokuapp.com/windows");
        String currentWindow = driver.getWindowHandle();

        driver.findElement(By.linkText("Click Here")).click();

        Set<String> windowHandles = driver.getWindowHandles();

        for (String handle : windowHandles) {
            if (!handle.equals(currentWindow)) {
                driver.switchTo().window(handle);
                break;
            }
        }

        Assert.assertEquals(driver.findElement(By.xpath("//h3")).getText(), "New Window");

        driver.close();
        driver.switchTo().window(currentWindow);

        Assert.assertTrue(driver.findElement(By.linkText("Click Here")).isDisplayed());
    }

}
