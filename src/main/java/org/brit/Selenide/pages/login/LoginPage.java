package org.brit.Selenide.pages.login;

import com.codeborne.selenide.SelenideElement;
import org.brit.Selenide.BasePage;
import org.brit.Selenide.pages.main.MainPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class LoginPage extends BasePage {


    private SelenideElement userInput = $(byId("user-name"));

    private SelenideElement password = $("#password");

    private SelenideElement loginButton = $x("//input[@name='login-button']");

    private SelenideElement errorMessage = $("h3[data-test=error]");

    private LoginPage setUserName(String userName) {
        return  enterValueToWebElement(userInput, userName);
    }


    private LoginPage setUserPassword(String userPassword) {
        return enterValueToWebElement(password, userPassword);
    }


    public void clickLoginButton() {
        loginButton.click();
    }


    public MainPage login(String userName, String userPass) {
        setUserName(userName).setUserPassword(userPass).clickLoginButton();
        return new MainPage();
    }


    public LoginPage unsuccessfulLogin(String userName, String userPass) {
        setUserName(userName).setUserPassword(userPass).clickLoginButton();
        return new LoginPage();
    }


    public String getErrorMessageText() {
        return errorMessage.text();
    }


    private LoginPage enterValueToWebElement(SelenideElement element, String value) {
        element.setValue(value);
        return this;
    }


}
