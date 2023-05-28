package org.brit.Selenide;

import lombok.SneakyThrows;
import org.brit.Selenide.driver.WebDriverHolder;
import org.brit.Selenide.pages.header.PageHeader;
import org.openqa.selenium.support.PageFactory;

public class BasePage {

    public PageHeader getPageHeader() {
        return new PageHeader();
    }

}
