package com.insiderone.extensions;

import com.insiderone.driver.DriverFactory;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.extension.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class WatcherExtension implements BeforeEachCallback, AfterEachCallback, TestExecutionExceptionHandler {

    private String getScreenshotDir() {
        String screenshotFolderEnv = System.getenv("SCREENSHOT_FOLDER");
        return screenshotFolderEnv != null ? screenshotFolderEnv : "build/screenshots";
    }

    @Override
    public void beforeEach(ExtensionContext context) {
        DriverFactory.initDriver(System.getProperty("browser", "chrome"));
    }

    @Override
    public void afterEach(ExtensionContext context) {
        DriverFactory.quitDriver();
    }

    @Override
    public void handleTestExecutionException(ExtensionContext context, Throwable throwable) throws Throwable {
        WebDriver driver = DriverFactory.getDriver();
        if (driver != null) {
            takeScreenshot(driver, context);
        }
        throw throwable;
    }

    private void takeScreenshot(WebDriver driver, ExtensionContext context) {
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File screenshotFile = ts.getScreenshotAs(OutputType.FILE);

            String directory = getScreenshotDir();
            File screenshotDir = new File(directory);
            if (!screenshotDir.exists()) screenshotDir.mkdirs();

            LocalDateTime now = LocalDateTime.now();
            String timestamp = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
            String fileName = context.getRequiredTestMethod().getName() + "_" + timestamp + ".png";
            File localScreenshotFile = new File(directory + "/" + fileName);

            Files.copy(screenshotFile.toPath(), localScreenshotFile.toPath());

            try (var inputStream = Files.newInputStream(localScreenshotFile.toPath())) {
                Allure.addAttachment(
                        "Screenshot for " + context.getRequiredTestMethod().getName(),
                        "image/png",
                        inputStream,
                        "png"
                );
            }
        } catch (IOException e) {
            System.err.println("Failed to save screenshot or attach to Allure: " + e.getMessage());
        }
    }
}