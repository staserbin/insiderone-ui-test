package com.insiderone.pages;

import com.insiderone.config.UrlConfig;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    private By logo = By.cssSelector("body.home div.header-logo");
    private By header = By.cssSelector("header[id='navigation']");
    private By main = By.cssSelector("main.flexible-layout:first-of-type");
    private By footer = By.cssSelector("footer.footer");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Step("Open Insider home page")
    public HomePage open() {
        open(UrlConfig.BASE_URL);
        return this;
    }

    public boolean isLogoDisplayed() {
        return driver.findElement(logo).isDisplayed();
    }

    public boolean isHeaderLoaded() {
        return driver.findElement(header).isDisplayed();
    }

    public boolean isMainLoaded() {
        return driver.findElement(main).isDisplayed();
    }

    public boolean isFooterLoaded() {
        return driver.findElement(footer).isDisplayed();
    }
}
