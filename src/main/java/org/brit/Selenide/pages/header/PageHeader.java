package org.brit.Selenide.pages.header;

import com.codeborne.selenide.SelenideElement;
import org.brit.Selenide.driver.WebDriverHolder;
import org.brit.Selenide.pages.cart.CartPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.codeborne.selenide.Selenide.$;

public class PageHeader {

    private SelenideElement cartLink = $("#shopping_cart_container");

    public MenuElement getMenu() {
        return new MenuElement();
    }

    public CartPage goToCartPare() {
        cartLink.click();
        return new CartPage();
    }

    public int getNumberOfProductsInCar() {
        String cartLinkText = cartLink.text();
        return cartLinkText.isEmpty() ? 0 : Integer.parseInt(cartLinkText);
    }

}
