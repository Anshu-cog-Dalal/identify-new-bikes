package org.example.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScreenshotUtil {


    public static String captureScreenshot(WebDriver driver, String testName) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
        String fileName  = testName + "_" + timestamp + ".png";
        String filePath  = System.getProperty("user.dir") +
                "\\screenshots\\" + fileName;

        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File srcFile = ts.getScreenshotAs(OutputType.FILE);

            File destFile = new File(filePath);
            srcFile.renameTo(destFile);

            System.out.println("[Screenshot] Saved: " + destFile.getAbsolutePath());
            return destFile.getAbsolutePath();

        } catch (Exception e) {
            System.err.println("[Screenshot] Failed to save screenshot: " + e.getMessage());
            return "";
        }
    }

}