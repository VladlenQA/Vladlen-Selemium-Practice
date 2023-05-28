package org.kryvoi.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.FileDownloadMode;
import com.codeborne.selenide.Selenide;
import org.brit.Selenide.driver.WebDriverHolder;
import org.brit.Selenide.pages.login.LoginPage;
import org.brit.Selenide.pages.main.MainPage;
import org.brit.Selenide.pages.main.Product;
import org.brit.Selenide.pages.main.SortDirection;
import org.brit.Selenide.utils.PropertiesReader;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

import static com.codeborne.selenide.Selenide.open;

public class LoginTestPOSelenide {
    String userName = PropertiesReader.getInstance().getProperty("swag.lab.username");
    String userPass = PropertiesReader.getInstance().getProperty("swag.lab.userpass");

    @BeforeClass
    public void beforeClass() {
        Configuration.timeout = 10000;
        Configuration.browser = "firefox";
        Configuration.fileDownload = FileDownloadMode.FOLDER;
    }

    @BeforeMethod
    public void beforeMethod() {
        open(PropertiesReader.getInstance().getProperty("swag.lab.url"));
        Selenide.clearBrowserCookies();
    }

    @Test
    public void unsuccessLoginTest() {
        LoginPage loginPage = new LoginPage().unsuccessfulLogin(userName, userPass + "1");
        Assert.assertNotEquals(loginPage.getErrorMessageText(), "Epic sadface: Username and password do not match any user in this service");//специально тест завален, чтобы сработал скриншот
    }

    @Test
    public void loginSuccessful() {
        String errorMessageText = new LoginPage()
                .login(userName, userPass)
                .getPageHeader()
                .getMenu()
                .selectLogoutMenuItem()
                .unsuccessfulLogin("", "")
                .getErrorMessageText();
        Assert.assertEquals(errorMessageText, "Epic sadface: Username is required");
    }

    @Test
    public void addProductToCartTest() {
        String productName = "Sauce Labs Backpack";
        int numberOfProductsInCart = new LoginPage()
                .login(userName, userPass)
                .addProductToPageByName(productName)
                .getPageHeader().getNumberOfProductsInCar();
        Assert.assertEquals(numberOfProductsInCart, 1);
    }

    @Test
    public void getAllProductsTest() {
        List<Product> products = new LoginPage().login(userName, userPass).getProducts();
        Assert.assertEquals(products.size(), 6);
    }

    @Test
    public void checkSortingTest() {
        MainPage mainPage = new LoginPage().login(userName, userPass);
        List<Product> productsAsIs = mainPage.getProducts();

        List<Product> productsAfterSorting =  mainPage.sortBy(SortDirection.PRICE_LOW_TO_HIGH).getProducts();

        Collections.sort(productsAsIs, Product.getComparatorForSorting(SortDirection.PRICE_LOW_TO_HIGH));

        Assert.assertEquals(productsAfterSorting, productsAsIs);
    }

}
