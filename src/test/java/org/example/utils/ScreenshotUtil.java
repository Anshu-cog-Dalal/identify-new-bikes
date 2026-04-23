package org.example.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {

    private static final String SCREENSHOT_DIR = "screenshots/";

    /**
     * Captures a screenshot and saves it to the screenshots/ folder.
     * Called from BaseTest @AfterMethod on test failure.
     *
     * @param driver   the WebDriver instance for the current thread
     * @param testName the name of the failed test (used in filename)
     * @return absolute path of the saved screenshot (attached to ExtentReport)
     */
    public static String captureScreenshot(WebDriver driver, String testName) {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String fileName  = testName + "_" + timestamp + ".png";
        String filePath  = SCREENSHOT_DIR + fileName;

        try {
            // Ensure the screenshots directory exists
            File dir = new File(SCREENSHOT_DIR);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            // Cast driver to TakesScreenshot and capture
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File destFile = new File(filePath);
            FileUtils.copyFile(srcFile, destFile);

            System.out.println("[Screenshot] Saved: " + destFile.getAbsolutePath());
            return destFile.getAbsolutePath();

        } catch (IOException e) {
            System.err.println("[Screenshot] Failed to save screenshot: " + e.getMessage());
            return "";
        }
    }

    /**
     * Captures screenshot as Base64 string.
     * Useful for embedding directly into ExtentReports HTML without a file path.
     *
     * @param driver the WebDriver instance
     * @return Base64-encoded screenshot string
     */
    public static String captureScreenshotAsBase64(WebDriver driver) {
        try {
            return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
        } catch (Exception e) {
            System.err.println("[Screenshot] Base64 capture failed: " + e.getMessage());
            return "";
        }
    }
}