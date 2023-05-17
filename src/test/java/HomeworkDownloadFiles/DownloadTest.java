package HomeworkDownloadFiles;

import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.brit.Selenium.utils.MyFilesUtils;

import java.io.File;
import java.io.FileReader;

public class DownloadTest extends BaseDownloadTest {

    @SneakyThrows
    @Test(dataProvider = "dataProviderForDownloads")
    public void downloadTest(String amount, String type) {
        driver.get("https://www.webfx.com/tools/lorem-ipsum-generator/");

        Select select = new Select(driver.findElement(By.name("type")));
        select.selectByVisibleText(type);

        driver.findElement(By.id("amount_generator")).clear();
        driver.findElement(By.id("amount_generator")).sendKeys(amount);

        driver.findElement(By.xpath("//div[@id='form-actions1']/input")).click();

        String text = driver.findElement(By.id("result_field")).getText();

        driver.get("https://demo.seleniumeasy.com/generate-file-to-download-demo.html");
        driver.findElement(By.id("textbox")).sendKeys(text);
        driver.findElement(By.id("create")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("link-to-download")).click();

        File file = MyFilesUtils.waitTillFileIsLoaded(new File("downloads","easyinfo.txt"));
        FileReader fileReader = new FileReader(file);
        String textFromFile = "";
        int i;
        while ((i = fileReader.read()) != -1){
            textFromFile = textFromFile + (char) i;
        }

        Assert.assertEquals(text,textFromFile);
        MyFilesUtils.cleanDownloadsDirectory();
    }

    @DataProvider(name = "dataProviderForDownloads")
    public Object[][] dataProviderForDownloads() {
        return new Object[][]
                {
                        {"1", "Paragraphs"},
                        {"2", "Words"},
                        {"3", "Sentences"}
                };
    }
}
