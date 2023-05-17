package org.brit.Selenium.pages.login;

import org.brit.Selenium.BasePage;
import org.brit.Selenium.pages.main.MainPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(id = "user-name")
    private WebElement userInput;

    @FindBy(css = "#password")
    private WebElement password;

    @FindBy (xpath = "//input[@name='login-button']")
    private WebElement loginButton;

    @FindBy(css = "h3[data-test=error]")
    private WebElement errorMessage;

    public LoginPage() {
        super();
    }


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
        return errorMessage.getText();
    }


    private LoginPage enterValueToWebElement(WebElement element, String value) {
        element.clear();
        element.sendKeys(value);
        return this;
    }


}
