package com.insiderone.tests;

import com.insiderone.base.BaseTest;
import com.insiderone.extensions.WatcherExtension;
import com.insiderone.models.PositionCard;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(WatcherExtension.class)
public class InsiderOneCareerTest extends BaseTest {

    private final static String LOCATION = "Istanbul, Turkiye";
    private static final String QA = "Quality Assurance";
    public static final String QUALITY = "Quality";

    @Test
    @Description("Verify Insider One QA careers page loads, filters work correctly, and roles redirect to Lever")
    void insiderOneCareerTest() {
        homePage.open();

        Allure.step("Verify the following on the Home page:", () -> {
            Allure.step("Logo is visible");
            assertTrue(homePage.isLogoDisplayed(), "Home page logo should be visible");

            Allure.step("Header block is loaded");
            assertTrue(homePage.isHeaderLoaded(), "Header should be loaded on the Home page");

            Allure.step("Main block is loaded");
            assertTrue(homePage.isMainLoaded(), "Main block should be loaded on the Home page");

            Allure.step("Footer block is loaded");
            assertTrue(homePage.isFooterLoaded(), "Footer block should be loaded on the Home page");
        });

        careersPage.open()
                .clickSeeAllJobsButton()
                .selectLocation(LOCATION);

        Allure.step("Verify selected filters:", () -> {
            Allure.step(String.format("Location is '%s'", LOCATION));
            assertEquals(LOCATION, pages.qaPage().getSelectedLocation());

            Allure.step(String.format("Department is '%s'", QA));
            assertEquals(QA, pages.qaPage().getSelectedDepartment());
        });

        Allure.step("Verify each found position card:", () -> {
            List<PositionCard> positions = pages.qaPage().getPositions();

            for (PositionCard job : positions) {
                Allure.step(String.format("Title contains '%s'", QUALITY));
                assertTrue(job.getTitle().contains(QUALITY), "Title mismatch");

                Allure.step(String.format("Department is '%s'", QA));
                assertTrue(job.getDepartment().contains(QA), "Department mismatch");

                Allure.step(String.format("Location is '%s'", LOCATION));
                assertTrue(job.getLocation().contains(LOCATION), "Location mismatch");
            }
        });

        careersPage.openToViewFirstRole();

        Allure.step("Verify redirection to Lever page");
        assertTrue(leverPage.isRedirectedToLever(), "Should be redirected to Lever page");
    }
}