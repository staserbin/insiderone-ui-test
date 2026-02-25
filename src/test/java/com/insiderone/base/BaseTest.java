package com.insiderone.base;

import com.insiderone.driver.DriverFactory;
import com.insiderone.pages.HomePage;
import com.insiderone.pages.LeverPage;
import com.insiderone.pages.PageManager;
import com.insiderone.pages.CareersPage;
import org.junit.jupiter.api.BeforeEach;

public class BaseTest {

    protected PageManager pages;

    protected HomePage homePage;
    protected CareersPage careersPage;
    protected LeverPage leverPage;

    @BeforeEach
    void setUp() {
        pages = new PageManager(DriverFactory.getDriver());

        homePage = pages.homePage();
        careersPage = pages.qaPage();
        leverPage = pages.leverPage();
    }
}
