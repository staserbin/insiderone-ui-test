package com.insiderone.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CookieBanner {

    private WebDriver driver;

    private By banner = By.id("cookie-law-info-bar");
    private By acceptAllBtn = By.id("wt-cli-accept-all-btn");

    public CookieBanner(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isDisplayed() {
        return !driver.findElements(banner).isEmpty()
                && driver.findElement(banner).isDisplayed();
    }

    public void acceptAllIfPresent() {
        if (isDisplayed()) {
            driver.findElement(acceptAllBtn).click();
        }
    }
}