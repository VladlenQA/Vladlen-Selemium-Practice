package org.kryvoi.tests;

import org.brit.Selenium.driver.WebDriverHolder;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.brit.Selenium.utils.MyFilesUtils;
import org.brit.Selenium.utils.PropertiesReader;
import org.testng.annotations.Listeners;

@Listeners({MyListener.class})
public class BaseTestClass {
    protected WebDriver driver;

    @BeforeSuite
    public void beforeClass() {
        driver = WebDriverHolder.getInstance().getDriver();
        MyFilesUtils.cleanFilesFolder();
        MyFilesUtils.cleanDownloadsDirectory();
        MyFilesUtils.cleanScreenshotsDirectory();
    }

    @AfterSuite
    public void afterClass() {
        WebDriverHolder.getInstance().driverQuit();
    }

    public void goToUrl(String url) {
        WebDriverHolder.getInstance().getDriver().get(url);
    }

    public void goToUrl() {
        goToUrl(PropertiesReader.getInstance().getProperty("app.base.url"));
    }

    public void goToPart(String part) {
        goToUrl(PropertiesReader.getInstance().getProperty("app.base.url") + part);
    }
}
