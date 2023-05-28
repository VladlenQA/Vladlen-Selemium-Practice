package org.kryvoi.tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.FileDownloadMode;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.brit.Selenide.utils.MyFilesUtils;
import org.brit.Selenium.utils.PropertiesReader;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byLinkText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class UploadDownloadFilesTestsSelenide  {

    @BeforeClass
    public void beforeclass() {
        Configuration.timeout = 15000;
        Configuration.browser = "firefox";
        Configuration.fileDownload = FileDownloadMode.FOLDER;
    }

    @Test
    public void uploadTest() {
        File file = MyFilesUtils.generateLoremFile();
        open(PropertiesReader.getInstance().getProperty("app.base.url") + "upload");

        $(byId("file-upload")).uploadFile(file);
        $(byId("file-submit")).click();
        $(byId("uploaded-files")).shouldHave(Condition.text(file.getName()));
        open(PropertiesReader.getInstance().getProperty("app.base.url") + "download");

        Assert.assertTrue($(By.linkText(file.getName())) .is(Condition.visible));
    }


    @SneakyThrows
    @Test
    public void downloadTest() {
        File file = MyFilesUtils.generateLoremFile();
        open(PropertiesReader.getInstance().getProperty("app.base.url") + "upload");

        $(byId("file-upload")).uploadFile(file);
        $(byId("file-submit")).click();

        open(PropertiesReader.getInstance().getProperty("app.base.url") + "download");
        File download = $(byLinkText(file.getName())).download();

        Assert.assertTrue(FileUtils.contentEquals(download, file));
    }
}
