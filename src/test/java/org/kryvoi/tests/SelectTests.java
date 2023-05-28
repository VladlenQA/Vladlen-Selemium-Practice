package org.kryvoi.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SelectTests extends BaseTestClass {

    @Test
    public void selectTest() {
        driver.get("https://the-internet.herokuapp.com/dropdown");

        Select select = new Select(driver.findElement(By.id("dropdown")));
        select.selectByVisibleText("Option 2");
        Assert.assertEquals(select.getFirstSelectedOption().getAttribute("value"), "2");

        select.selectByValue("1");
        Assert.assertEquals(select.getFirstSelectedOption().getText(), "Option 1");

    }
}
