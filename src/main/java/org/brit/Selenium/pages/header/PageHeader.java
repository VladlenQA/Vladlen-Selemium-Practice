package org.brit.Selenium.pages.header;

import org.brit.Selenium.driver.WebDriverHolder;
import org.brit.Selenium.pages.cart.CartPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PageHeader {

    @FindBy(css = "#shopping_cart_container")
    private WebElement cartLink;

    public PageHeader() {
        PageFactory.initElements(WebDriverHolder.getInstance().getDriver(), this);
    }

    public MenuElement getMenu() {
        return new MenuElement();
    }

    public CartPage goToCartPare() {
        cartLink.click();
        return new CartPage();
    }

    public int getNumberOfProductsInCar() {
        String cartLinkText = cartLink.getText();
        return cartLinkText.isEmpty() ? 0 : Integer.parseInt(cartLinkText);
    }

}
