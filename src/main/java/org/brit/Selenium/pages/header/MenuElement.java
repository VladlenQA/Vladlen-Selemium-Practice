package org.brit.Selenium.pages.header;

import org.brit.Selenium.driver.WebDriverHolder;
import org.brit.Selenium.pages.login.LoginPage;
import org.brit.Selenium.pages.main.MainPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MenuElement {
    @FindBy(id = "react-burger-menu-btn")
    private WebElement burgerButton;

    public MenuElement() {
        PageFactory.initElements(WebDriverHolder.getInstance().getDriver(), this);
    }

    public LoginPage selectLogoutMenuItem() {
        selectMenuItem(MenuItems.LOGOUT);
        return new LoginPage();
    }

    public void selectAboutMenuItem() {
        selectMenuItem(MenuItems.ABOUT);
    }

    public MainPage selectAllItemsMenuItem() {
        selectMenuItem(MenuItems.All_Items);
        return new MainPage();
    }

    private void selectMenuItem(MenuItems menuItem) {
        if (burgerButton.isDisplayed()) {
            burgerButton.click();
        }
        WebDriverHolder.getInstance().getDriver()
                .findElement(By.id(menuItem.getValue() + "_sidebar_link"))
                .click();
    }
}
