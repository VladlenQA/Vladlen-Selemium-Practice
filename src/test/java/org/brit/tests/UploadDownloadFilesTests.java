package org.brit.tests;

import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.brit.Selenium.utils.MyFilesUtils;

import java.io.File;

public class UploadDownloadFilesTests extends BaseTestClass {
    @Test
    public void uploadTest() {
        File file = MyFilesUtils.generateLoremFile();
        goToPart("upload");

        driver.findElement(By.id("file-upload")).sendKeys(file.getAbsolutePath());
        driver.findElement(By.id("file-submit")).click();

        Assert.assertEquals(driver.findElement(By.id("uploaded-files")).getText().trim(), file.getName());

        goToPart("download");
        Assert.assertTrue(driver.findElement(By.linkText(file.getName())) .isDisplayed());
    }


    @SneakyThrows
    @Test
    public void downloadTest() {
        File file = MyFilesUtils.generateLoremFile();
        goToPart("upload");

        driver.findElement(By.id("file-upload")).sendKeys(file.getAbsolutePath());
        driver.findElement(By.id("file-submit")).click();

        goToPart("download");
        driver.findElement(By.linkText(file.getName())).click();

        File file1 = MyFilesUtils.waitTillFileIsLoaded(new File("downloads",file.getName()));

        Assert.assertTrue(FileUtils.contentEquals(file, file1));
    }
}
