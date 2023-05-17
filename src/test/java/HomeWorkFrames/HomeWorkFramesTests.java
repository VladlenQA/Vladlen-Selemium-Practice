package HomeWorkFrames;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class HomeWorkFramesTests extends HomeWorkBaseTestClass {

    @Test (dataProvider = "dataProviderForFrames")
    public void frameHomeWorkTest(String parentFrameName, String frameName, String frameBody) {

        driver.get("https://the-internet.herokuapp.com/nested_frames");
        if (!parentFrameName.equals(frameName)) {
            driver.switchTo().frame(parentFrameName).switchTo().frame(frameName);
        } else driver.switchTo().frame(frameName);

        String body = driver.findElement(By.xpath("//body")).getText();
        Assert.assertEquals(body,frameBody);
    }

    @DataProvider(name = "dataProviderForFrames")
    public Object[][] dataProviderForFrames() {
        return new Object[][] {
                {"frame-top", "frame-left", "LEFT"},
                {"frame-top", "frame-middle", "MIDDLE"},
                {"frame-top", "frame-right", "RIGHT"},
                {"frame-bottom", "frame-bottom", "BOTTOM"},
        };
    }
}
