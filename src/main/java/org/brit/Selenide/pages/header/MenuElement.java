package org.brit.Selenide.pages.header;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.brit.Selenide.driver.WebDriverHolder;
import org.brit.Selenide.pages.login.LoginPage;
import org.brit.Selenide.pages.main.MainPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.$;

public class MenuElement {

    private SelenideElement burgerButton = $(byId("react-burger-menu-btn"));

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
        if (burgerButton.is(Condition.visible)) {
            burgerButton.click();
        }
        $(byId(menuItem.getValue() + "_sidebar_link")).click();
    }
}
