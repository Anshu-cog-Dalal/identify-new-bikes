package org.example.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.example.base.DriverFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ExtentReportManager implements ITestListener {

    private static ExtentReports extent;
    private static final ThreadLocal<ExtentTest> testThread = new ThreadLocal<>();

    // ── Singleton report instance ─────────────────────────────────────────
    public static synchronized ExtentReports getInstance() {
        if (extent == null) {
            ExtentSparkReporter spark = new ExtentSparkReporter("reports/ExtentReport.html");
            spark.config().setTheme(Theme.DARK);
            spark.config().setDocumentTitle("ZigWheels Hackathon Report");
            spark.config().setReportName("Bike Automation Test Results");
            spark.config().setTimeStampFormat("dd-MM-yyyy HH:mm:ss");

            extent = new ExtentReports();
            extent.attachReporter(spark);
            extent.setSystemInfo("Project",     "identify-new-bikes");
            extent.setSystemInfo("Tester",      System.getProperty("user.name"));
            extent.setSystemInfo("Environment", "QA");
            extent.setSystemInfo("Browser",     ConfigReader.get("browser"));
            extent.setSystemInfo("Base URL",    ConfigReader.get("base.url"));
        }
        return extent;
    }

    // ── ITestListener overrides ───────────────────────────────────────────

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = getInstance()
                .createTest(result.getMethod().getMethodName());
        testThread.set(test);
        logInfo("Test started: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logPass("PASSED: " + result.getName());
        testThread.remove();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        // Capture screenshot and attach on failure
        String path = ScreenshotUtil.captureScreenshot(
                DriverFactory.getDriver(), result.getName());
        addScreenshot(path);
        logFail("FAILED: " + result.getThrowable().getMessage());
        testThread.remove();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        logSkip("SKIPPED: " + result.getName());
        testThread.remove();
    }

    @Override
    public void onFinish(ITestContext context) {
        flushReport(); // writes HTML to disk after all tests done
    }

    // ── Static helpers (called from test classes for step logging) ────────

    public static ExtentTest getTest() {
        return testThread.get();
    }

    public static void startTest(String testName) {
        ExtentTest test = getInstance().createTest(testName);
        testThread.set(test);
    }

    public static void logPass(String message) {
        if (getTest() != null) getTest().pass(message);
    }

    public static void logFail(String message) {
        if (getTest() != null) getTest().fail(message);
    }

    public static void logSkip(String message) {
        if (getTest() != null) getTest().skip(message);
    }

    public static void logInfo(String message) {
        if (getTest() != null) getTest().info(message);
    }

    public static void addScreenshot(String screenshotPath) {
        try {
            if (screenshotPath != null && !screenshotPath.isEmpty()) {
                getTest().fail("Screenshot:",
                        MediaEntityBuilder
                                .createScreenCaptureFromPath(screenshotPath)
                                .build());
            }
        } catch (Exception e) {
            System.err.println("[ExtentReport] Screenshot attach failed: "
                    + e.getMessage());
        }
    }

    public static void addScreenshotBase64(String base64) {
        try {
            if (base64 != null && !base64.isEmpty()) {
                getTest().fail("Screenshot:",
                        MediaEntityBuilder
                                .createScreenCaptureFromBase64String(base64)
                                .build());
            }
        } catch (Exception e) {
            System.err.println("[ExtentReport] Base64 screenshot failed: "
                    + e.getMessage());
        }
    }

    public static void flushReport() {
        if (extent != null) extent.flush();
    }

    public static void endTest() {
        testThread.remove();
    }
}