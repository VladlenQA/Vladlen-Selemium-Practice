package org.brit.Selenium.pages.main;

import org.brit.Selenium.BasePage;
import org.brit.Selenium.driver.WebDriverHolder;
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

public class MainPage extends BasePage {

    @FindBy(css = "select.product_sort_container")
    private WebElement sortByElement;

    @FindBy(css = ".inventory_item")
    private List<WebElement> inventoryItems;


    public MainPage() {
        super();
    }

    public MainPage addProductToPageByName(String name) {
        for (WebElement element: inventoryItems) {
            if (element.findElement(By.cssSelector(".inventory_item_name")).getText().equals(name)) {
                element.findElement(By.cssSelector("button")).click();
            }
        }

        //WebDriver driver = WebDriverHolder.getInstance().getDriver();
        //By elementByName = By.xpath("//div[@class='inventory_item_name'][contains(.,'%s')]".formatted(name));
        //driver.findElement(elementByName)
       //         .findElement(By.xpath("./../../.."))
        //        .findElement(By.xpath(".//button"))
        //        .click();
        return new MainPage();
    }

    public List<Product> getProducts() {
        List<WebElement> productItems = WebDriverHolder.getInstance().getDriver().findElements(By.cssSelector(".inventory_item"));
        List<Product> productList = new ArrayList<>();

        for (WebElement product : productItems) {
            String productName = product.findElement(By.cssSelector(".inventory_item_name")).getText();
            String productPriceAsString = product.findElement(By.cssSelector(".inventory_item_price")).getText();
            Double price = Double.parseDouble(productPriceAsString.replace("$", ""));
            Product productModel = new Product().setName(productName).setPrice(price);
            productList.add(productModel);
        }
        return productList;
    }

    public List<Product> getProductsEx() {
        WebElement element = WebDriverHolder.getInstance().getDriver().findElement(By.cssSelector(".inventory_list"));
        String innerHTML = element.getAttribute("innerHTML");
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
        Select select = new Select(sortByElement);
        select.selectByValue(sortDirection.getValue());
        sleep(1500);
        return new MainPage();
    }

}
