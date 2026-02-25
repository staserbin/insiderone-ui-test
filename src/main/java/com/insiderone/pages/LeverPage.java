package com.insiderone.pages;

import com.insiderone.utils.WaitUtils;
import org.openqa.selenium.WebDriver;

public class LeverPage extends BasePage {

    private WaitUtils waitUtils;

    public LeverPage(WebDriver driver) {
        super(driver);
        waitUtils = new WaitUtils(driver);
    }

    public boolean isRedirectedToLever() {
        return waitUtils.waitForUrlContains("lever.co");
    }
}
