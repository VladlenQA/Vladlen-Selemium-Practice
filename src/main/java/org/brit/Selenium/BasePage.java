package org.brit.Selenium;

import lombok.SneakyThrows;
import org.brit.Selenium.driver.WebDriverHolder;
import org.brit.Selenium.pages.header.PageHeader;
import org.openqa.selenium.support.PageFactory;

public class BasePage {

    public PageHeader getPageHeader() {
        return new PageHeader();
    }

    public BasePage() {
        PageFactory.initElements(WebDriverHolder.getInstance().getDriver(), this);
    }

    @SneakyThrows
    public void sleep(long msec) {
        Thread.sleep(msec);
    }
}
