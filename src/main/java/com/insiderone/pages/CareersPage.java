package com.insiderone.pages;

import com.insiderone.config.UrlConfig;
import com.insiderone.models.PositionCard;
import com.insiderone.utils.WaitUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class CareersPage extends BasePage {

    private By seeAllJobsBtn = By.xpath("//a[contains(text(),'See all QA jobs')]");
    private By viewRoleBtn = By.xpath("//a[contains(text(),'View Role')]");
    private By locationSelect = By.id("filter-by-location");
    private By departmentSelect = By.id("filter-by-department");
    private By jobItems = By.cssSelector("div.position-list-item-wrapper");
    private By cardTitle = By.cssSelector(".position-title");
    private By cardDepartment = By.cssSelector(".position-department");
    private By cardLocation = By.cssSelector(".position-location");

    private WaitUtils waitUtils;

    public CareersPage(WebDriver driver) {
        super(driver);
        waitUtils = new WaitUtils(driver);
    }

    @Step("Open Careers page")
    public CareersPage open() {
        open(UrlConfig.CAREERS_QA_URL);
        return this;
    }

    @Step("Click 'See all QA jobs' button")
    public CareersPage clickSeeAllJobsButton() {
        waitUtils.waitForClickable(seeAllJobsBtn).click();
        return this;
    }

    @Step("Open first found role from the list")
    public void openToViewFirstRole() {
        List<WebElement> buttons = waitUtils.waitForAllElementsVisible(viewRoleBtn);

        if (buttons.isEmpty()) {
            throw new IllegalStateException("No 'View Role' buttons found");
        }

        String originalWindow = driver.getWindowHandle();
        buttons.getFirst().click();
        waitUtils.switchToNewTab(originalWindow);
    }

    @Step("Select 'Location'")
    public void selectLocation(String locationText) {
        waitUtils.waitForVisibility(locationSelect);
        waitUtils.waitForClickable(locationSelect);

        By optionLocator = By.xpath(String.format("//select[@id='filter-by-location']/option[normalize-space()='%s']",
                locationText));
        waitUtils.waitForElementPresence(optionLocator);

        Select select = new Select(driver.findElement(locationSelect));
        select.selectByVisibleText(locationText);

        waitUtils.waitForRoleWithLocation(locationText, jobItems);
    }

    public String getSelectedLocation() {
        Select select = new Select(driver.findElement(locationSelect));
        waitUtils.waitForVisibility(locationSelect);
        return select.getFirstSelectedOption().getText();
    }

    public String getSelectedDepartment() {
        Select select = new Select(driver.findElement(departmentSelect));
        waitUtils.waitForVisibility(departmentSelect);
        return select.getFirstSelectedOption().getText();
    }

    public List<PositionCard> getPositions() {
        waitUtils.waitForSeconds(5);
        List<WebElement> elements = waitUtils.waitForAllElementsVisible(jobItems);

        if (elements == null || elements.isEmpty()) {
            throw new IllegalStateException("Positions list is empty after applying filters");
        }

        return elements.stream()
                .map(el -> new PositionCard(
                        el.findElement(cardTitle).getText(),
                        el.findElement(cardDepartment).getText(),
                        el.findElement(cardLocation).getText()
                ))
                .toList();
    }
}