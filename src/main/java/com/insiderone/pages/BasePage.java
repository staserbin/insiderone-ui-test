package com.insiderone.pages;

import com.insiderone.components.CookieBanner;
import org.openqa.selenium.WebDriver;

public abstract class BasePage {

    protected WebDriver driver;
    protected CookieBanner cookieBanner;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.cookieBanner = new CookieBanner(driver);
    }

    protected void open(String url) {
        driver.get(url);
        cookieBanner.acceptAllIfPresent();
    }
}
