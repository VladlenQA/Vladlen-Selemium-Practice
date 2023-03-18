package org.brit.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AlertsTests {
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
    public void beforeMethod(){
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");
    }

    @Test
    public void alertTest() {
        clickAlertButton();
        workWithAlert(true);
        String result = getResult();
        Assert.assertEquals(result, "You successfully clicked an alert");
    }

    @Test
    public void confirmTest() {
        clickConfirmButton();
        workWithAlert(true);
        String result = getResult();
        Assert.assertEquals(result, "You clicked: Ok");
    }

    @Test
    public void promptTest1() {
        String textToEnter = "TestString!!!";
        clickPromptButton();
        workWithAlert(false, textToEnter);
        String result = getResult();
        Assert.assertEquals(result,"You entered: null");
    }

    @Test
    public void promptTest2() {
        String textToEnter = "TestString!!!";
        clickPromptButton();
        workWithAlert(true, textToEnter);
        String result = getResult();
        Assert.assertEquals(result,"You entered: " + textToEnter);
    }

    @Test
    public void promptTest1JS() {
        String textToEnter = "TestString!!!";
        pressPromptButton();
        workWithAlert(false, textToEnter);
        String result = getResult();
        Assert.assertEquals(result,"You entered: null");
    }

    @Test
    public void promptTest2JS() {
        String textToEnter = "TestString!!!";
        pressPromptButton();
        workWithAlert(true, textToEnter);
        String result = getResult();
        Assert.assertEquals(result,"You entered: " + textToEnter);
    }

    @Test
    public void promptTest1JSClick() {
        String textToEnter = "TestString!!!";
        clickPromptButtonJS();
        workWithAlert(false, textToEnter);
        String result = getResultJS();
        Assert.assertEquals(result,"You entered: null");
    }

    @Test
    public void promptTest2JSClick() {
        String textToEnter = "TestString!!!";
        clickPromptButtonJS();
        workWithAlert(true, textToEnter);
        String result = getResult();
        Assert.assertEquals(result,"You entered: " + textToEnter);
    }

    public String workWithAlert(boolean accept, String...textToEnter) {
        Alert alert = driver.switchTo().alert();
        String text = alert.getText();
        if (textToEnter.length > 0) {
            alert.sendKeys(textToEnter[0]);
        }
        if (accept) {
            alert.accept();
        } else {
            alert.dismiss();
        }
        return text;
    }

    public String getResult() {
        return driver.findElement(By.id("result")).getText();
    }

    public String getResultJS() {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        return javascriptExecutor.executeScript("return document.getElementById('result').textContent").toString();
    }

    public void clickAlertButton() {
        clickOnButton(Buttons.ALLERT);
    }

    public void clickConfirmButton() {
        clickOnButton(Buttons.CONFIRM);
    }

    public void clickPromptButton() {
        clickOnButton(Buttons.PROMPT);
    }

    public void clickAlertButtonJS() {
        clickOnButtonWithJS(Buttons.ALLERT);
    }

    public void clickConfirmButtonJS() {
        clickOnButtonWithJS(Buttons.CONFIRM);
    }

    public void clickPromptButtonJS() {
        clickOnButtonWithJS(Buttons.PROMPT);
    }

    public void pressAlertButton() {
        pressButtonWithJS(Buttons.ALLERT);
    }

    public void pressConfirmButton() {
        pressButtonWithJS(Buttons.CONFIRM);
    }

    public void pressPromptButton() {
        pressButtonWithJS(Buttons.PROMPT);
    }

    private void clickOnButton(Buttons button) {
        WebElement confirmElement = driver.findElement(By.xpath("//button[contains(text(),'" + button.getText() + "')]"));
        confirmElement.click();
    }

    private void clickOnButtonWithJS(Buttons button) {
        WebElement confirmElement = driver.findElement(By.xpath("//button[contains(text(),'" + button.getText() + "')]"));
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("return arguments[0].click();", confirmElement);
    }

    private void pressButtonWithJS(Buttons button) {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("return " + button.jsScript);
    }

    enum Buttons {
        ALLERT("Click for JS Alert", "jsAlert();"),
        CONFIRM("Click for JS Confirm", "jsConfirm();"),
        PROMPT("Click for JS Prompt", "jsPrompt();");

        private String text;
        private String jsScript;

        Buttons(String text, String jsScript) {
            this.text = text;
            this.jsScript = jsScript;
        }

        public String getText() {
            return text;
        }

        public String getJsScript() {
            return jsScript;
        }
    }



}
