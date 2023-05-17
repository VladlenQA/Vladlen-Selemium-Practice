package HomeworkAlerts;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AlertsHomeWorkTests {
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
        driver.get("http://the-internet.herokuapp.com/javascript_alerts");
    }

    @Test
    public void test1() {
        Buttons button = Buttons.CONFIRM;
        String buttonXPath = button.getXPath();
        WebElement webElement = driver.findElement(By.xpath(buttonXPath));
        webElement.click();
        Alert alert = driver.switchTo().alert();
        alert.accept();
        String result = driver.findElement(By.xpath("//p[@id='result']")).getText();
        Assert.assertEquals(result, "You clicked: Ok");
    }

    @Test
    public void test2() {
        Buttons button = Buttons.CONFIRM;
        String buttonXPath = button.getXPath();
        WebElement webElement = driver.findElement(By.xpath(buttonXPath));
        webElement.click();

        Alert alert = driver.switchTo().alert();
        alert.dismiss();
        String result = driver.findElement(By.xpath("//p[@id='result']")).getText();
        Assert.assertEquals(result, "You clicked: Cancel");
    }

    @Test
    public void test3() {
        Buttons button = Buttons.PROMPT;
        String buttonXPath = button.getXPath();
        WebElement webElement = driver.findElement(By.xpath(buttonXPath));
        webElement.click();

        Alert alert = driver.switchTo().alert();
        String enteredText = "Any text";
        alert.sendKeys(enteredText);
        alert.accept();
        String result = driver.findElement(By.xpath("//p[@id='result']")).getText();
        Assert.assertEquals(result, "You entered: " + enteredText);
    }

    @Test
    public void test4() {
        Buttons button = Buttons.PROMPT;
        String buttonXPath = button.getXPath();
        WebElement webElement = driver.findElement(By.xpath(buttonXPath));
        webElement.click();

        Alert alert = driver.switchTo().alert();
        alert.accept();
        String result = driver.findElement(By.xpath("//p[@id='result']")).getText();
        Assert.assertEquals(result, "You entered:");
    }

    enum Buttons {
        ALERT("//button[contains(text(),'Click for JS Alert')]"),
        CONFIRM("//button[contains(text(),'Click for JS Confirm')]"),
        PROMPT("//button[contains(text(),'Click for JS Prompt')]");

        private String xPath;

        Buttons(String xPath) {
            this.xPath = xPath;
        }

        public String getXPath() {
            return xPath;
        }
    }
}

