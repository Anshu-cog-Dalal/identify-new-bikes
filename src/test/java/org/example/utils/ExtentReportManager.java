package org.example.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.WebDriver;

import java.io.File;

public class ExtentReportManager {

    private static ExtentReports extent;
    private static final ThreadLocal<ExtentTest> testThread = new ThreadLocal<>();
    private static final String REPORT_PATH = "output/reports/ExtentReport.html";

    private ExtentReportManager() {}

    public static ExtentReports getInstance() {
        if (extent == null) {
            new File("output/reports").mkdirs();
            ExtentSparkReporter spark = new ExtentSparkReporter(REPORT_PATH);
            spark.config().setTheme(Theme.DARK);
            spark.config().setDocumentTitle("Identify New Bikes — Test Report");
            spark.config().setReportName("ZigWheels Automation Suite");
            spark.config().setEncoding("UTF-8");

            extent = new ExtentReports();
            extent.attachReporter(spark);
            extent.setSystemInfo("Application", "ZigWheels");
            extent.setSystemInfo("Browser", "Chrome");
            extent.setSystemInfo("OS", System.getProperty("os.name"));
            extent.setSystemInfo("Author", "Automation Team");
        }
        return extent;
    }

    public static ExtentTest createTest(String testName) {
        ExtentTest test = getInstance().createTest(testName);
        testThread.set(test);
        return test;
    }

    public static ExtentTest createTest(String testName, String description) {
        ExtentTest test = getInstance().createTest(testName, description);
        testThread.set(test);
        return test;
    }

    public static ExtentTest getTest() {
        return testThread.get();
    }

    public static void logInfo(String message) {
        if (testThread.get() != null) {
            testThread.get().info(message);
        }
    }

    public static void logPass(String message) {
        if (testThread.get() != null) {
            testThread.get().pass(message);
        }
    }

    public static void logFail(String message) {
        if (testThread.get() != null) {
            testThread.get().fail(message);
        }
    }

    public static void logPassWithScreenshot(WebDriver driver, String message) {
        if (testThread.get() != null) {
            try {
                String screenshotPath = ScreenshotUtil.captureScreenshot(driver, "pass_" + System.currentTimeMillis());
                testThread.get().pass(message,
                        MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
            } catch (Exception e) {
                testThread.get().pass(message + " [screenshot failed: " + e.getMessage() + "]");
            }
        }
    }

    public static void logFailWithScreenshot(WebDriver driver, String message) {
        if (testThread.get() != null) {
            try {
                String screenshotPath = ScreenshotUtil.captureScreenshot(driver, "fail_" + System.currentTimeMillis());
                testThread.get().fail(message,
                        MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
            } catch (Exception e) {
                testThread.get().fail(message + " [screenshot failed: " + e.getMessage() + "]");
            }
        }
    }

    public static void logSkip(String message) {
        if (testThread.get() != null) {
            testThread.get().skip(message);
        }
    }

    public static void flush() {
        if (extent != null) {
            extent.flush();
        }
    }

    public static void removeTest() {
        testThread.remove();
    }
}
