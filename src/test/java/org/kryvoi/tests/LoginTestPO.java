package org.kryvoi.tests;

import org.brit.Selenium.driver.WebDriverHolder;
import org.brit.Selenium.pages.login.LoginPage;
import org.brit.Selenium.pages.main.MainPage;
import org.brit.Selenium.pages.main.Product;
import org.brit.Selenium.pages.main.SortDirection;
import org.brit.Selenium.utils.PropertiesReader;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.*;

public class LoginTestPO extends BaseTestClass{

    String userName = PropertiesReader.getInstance().getProperty("swag.lab.username");
    String userPass = PropertiesReader.getInstance().getProperty("swag.lab.userpass");


    @BeforeMethod
    public void beforeMethod() {
        WebDriverHolder.getInstance().getDriver().manage().deleteAllCookies();
        goToUrl(PropertiesReader.getInstance().getProperty("swag.lab.url"));
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
