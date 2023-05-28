package org.brit.Selenide.pages.main;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.brit.Selenide.BasePage;
import org.brit.Selenide.driver.WebDriverHolder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;

public class MainPage extends BasePage {

    private SelenideElement sortByElement = $("select.product_sort_container");

    private ElementsCollection inventoryItems = $$(".inventory_item");


    public MainPage() {
        super();
    }

    public MainPage addProductToPageByName(String name) {
//        for (SelenideElement element: inventoryItems) {
//            if (element.$(".inventory_item_name").is(Condition.text(name))) {
//                element.$("button").click();
 //           }
//        }

        $$(".inventory_item_name")
                .find(Condition.exactText(name))
                .parent().parent().parent()
                .$("button")
                .click();
        return new MainPage();
    }

    public List<Product> getProducts() {
        List<Product> productList = new ArrayList<>();

        for (SelenideElement product : $$(".inventory_item")) {
            String productName = product.$(".inventory_item_name").text();
            String productPriceAsString = product.$(".inventory_item_price").text();
            Double price = Double.parseDouble(productPriceAsString.replace("$", ""));
            Product productModel = new Product().setName(productName).setPrice(price);
            productList.add(productModel);
        }
        return productList;
    }

    public List<Product> getProductsEx() {
        String innerHTML = $(".inventory_list").innerHtml();
        List<Product> productList = new ArrayList<>();
        Document document = Jsoup.parse(innerHTML);
        Elements elements = document.select(".inventory_item");
        for (Element elementJsoup : elements) {
            String productName = elementJsoup.select(".inventory_item_name").get(0).text();
            String textPrice = elementJsoup.select(".inventory_item_price").get(0).text();
            Double price = Double.parseDouble(textPrice.replace("$", ""));
            Product productModel = new Product().setName(productName).setPrice(price);
            productList.add(productModel);
        }
        return productList;
    }

    public MainPage sortBy(SortDirection sortDirection) {
        sortByElement.selectOptionByValue(sortDirection.getValue());
        sleep(1500);
        return new MainPage();
    }

}
