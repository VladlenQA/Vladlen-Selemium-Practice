package org.brit.Selenium.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.util.HashMap;

public class WebDriverFactory {
    public static WebDriver initDriver(WebDriverType webDriverType) {
        WebDriver driver = null;
        switch (webDriverType) {
            case CHROME -> {
                WebDriverManager.chromedriver().setup();
                HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
                chromePrefs.put("profile.default_content_settings.popups", 0);
                chromePrefs.put("download.default_directory", new File("downloads").getAbsolutePath());
                ChromeOptions options = new ChromeOptions();
                options.setExperimentalOption("prefs", chromePrefs);
                options.addArguments("--remote-allow-origins=*");
                driver = new ChromeDriver(options);
                break;
            }
            case EDGE -> {
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            }
            case FIREFOX -> {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            }
        }
        driver.manage().window().maximize();
        return driver;
    }

    public static WebDriver initDriver() {
        WebDriverType webDriverType = null;
        try {
            webDriverType = WebDriverType.valueOf(System.getProperty("webDriverType", "chrome").toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("This driver is not supported. Please use: chrome, firefox, edge.");
            System.exit(-1);
        }
        return initDriver(webDriverType);
    }

    public static void main(String[] args) {
        System.getProperties().forEach((k, v) -> System.out.println(k + " " + v));
    }
}
