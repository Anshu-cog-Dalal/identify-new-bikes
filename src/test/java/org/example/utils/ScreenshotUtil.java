package org.example.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScreenshotUtil {

    private static final String SCREENSHOT_DIR = "output/screenshots/";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");

    private ScreenshotUtil() {}

    /**
     * Captures a screenshot and saves it to output/screenshots/.
     * Returns the absolute file path for use in Extent Reports.
     */
    public static String captureScreenshot(WebDriver driver, String testName) {
        new File(SCREENSHOT_DIR).mkdirs();

        String timestamp = LocalDateTime.now().format(FORMATTER);
        String sanitized = testName.replaceAll("[^a-zA-Z0-9_]", "_");
        String fileName = sanitized + "_" + timestamp + ".png";
        Path destination = Paths.get(SCREENSHOT_DIR + fileName);

        try {
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Files.copy(srcFile.toPath(), destination);
            System.out.println("Screenshot saved: " + destination.toAbsolutePath());
            return destination.toAbsolutePath().toString();
        } catch (IOException e) {
            System.err.println("Failed to save screenshot for [" + testName + "]: " + e.getMessage());
            return "";
        }
    }

    /**
     * Returns screenshot as Base64 string (useful for embedding directly in reports).
     */
    public static String captureScreenshotAsBase64(WebDriver driver) {
        try {
            return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
        } catch (Exception e) {
            System.err.println("Failed to capture Base64 screenshot: " + e.getMessage());
            return "";
        }
    }
}
