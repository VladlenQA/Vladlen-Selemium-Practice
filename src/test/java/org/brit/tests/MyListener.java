package org.brit.tests;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.brit.Selenium.driver.WebDriverHolder;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.util.Date;

@Slf4j
public class MyListener implements ITestListener {

    private File makeScreenshot(String screenShotName) {
        WebDriver driver = WebDriverHolder.getInstance().getDriver();
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File file = new File("screenshots", screenShotName +".png");
        try {
            FileUtils.copyFile(screenshot, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    @Override
    public void onTestStart(ITestResult result) {
        log.info(result.getName() + " started!!! ");
        ITestListener.super.onTestStart(result);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        log.info(result.getName() +" PASSED!!!");
        ITestListener.super.onTestSuccess(result);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        log.info(result.getName() + " Failed!!!");
        log.info("Trying to make screenshot...");
        File file = makeScreenshot(result.getName() + new Date().getTime());
        log.info("Screenshot saved to " + file.getAbsolutePath());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        log.info(result.getName() + " Skipped!!!");
        ITestListener.super.onTestSkipped(result);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        ITestListener.super.onTestFailedWithTimeout(result);
    }

    @Override
    public void onStart(ITestContext context) {
        ITestListener.super.onStart(context);
    }

    @Override
    public void onFinish(ITestContext context) {
        ITestListener.super.onFinish(context);
    }
}
