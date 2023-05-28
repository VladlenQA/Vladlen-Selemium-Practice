package org.kryvoi.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.testng.annotations.Test;

public class CookiesTests extends BaseTestClass {

    String userName = "tomsmith";
    String userPass = "SuperSecretPassword!";

    @Test
    public void cookiesTest() {
        driver.get("https://the-internet.herokuapp.com/login");
        driver.findElement(By.id("username")).sendKeys(userName);
        driver.findElement(By.id("password")).sendKeys(userPass);
        driver.findElement(By.cssSelector(".radius")).click();

        for (Cookie cookie : driver.manage().getCookies()) {
            System.out.println(cookie.getName() + " ==> " + cookie.getValue());
        }

        Cookie cookie = new Cookie("Some Cookie", "Cookie value");
        driver.manage().addCookie(cookie);

        Cookie someCookie = driver.manage().getCookieNamed("Some Cookie");
        System.out.println(someCookie.getName() + " ==> " + someCookie.getValue());

        driver.manage().deleteCookieNamed(someCookie.getName());
        System.out.println(someCookie);
    }
}
