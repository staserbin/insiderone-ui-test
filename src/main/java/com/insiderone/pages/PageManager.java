package com.insiderone.pages;

import org.openqa.selenium.WebDriver;

public class PageManager {

    private WebDriver driver;

    private HomePage homePage;
    private CareersPage careersPage;
    private LeverPage leverPage;

    public PageManager(WebDriver driver) {
        this.driver = driver;
    }

    public HomePage homePage() {
        if (homePage == null) {
            homePage = new HomePage(driver);
        }
        return homePage;
    }

    public CareersPage qaPage() {
        if (careersPage == null) {
            careersPage = new CareersPage(driver);
        }
        return careersPage;
    }

    public LeverPage leverPage() {
        if (leverPage == null) {
            leverPage = new LeverPage(driver);
        }
        return leverPage;
    }
}
