package org.example.base;

import org.example.utils.ExtentReportManager;
import org.example.utils.ScreenshotUtil;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

public class BaseTest {

    protected WebDriver driver;

    @BeforeSuite
    public void initReport() {
        ExtentReportManager.getInstance();
    }

    @BeforeClass
    public void setUp() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        System.out.println("Setup complete — browser is ready!");
    }

    @BeforeMethod
    public void startTest(java.lang.reflect.Method method) {
        ExtentReportManager.createTest(method.getName());
        ExtentReportManager.logInfo("Test started: " + method.getName());
    }

    @AfterMethod
    public void logResult(ITestResult result) {
        switch (result.getStatus()) {
            case ITestResult.SUCCESS:
                ExtentReportManager.logPassWithScreenshot(driver, "Test PASSED: " + result.getName());
                break;
            case ITestResult.FAILURE:
                ExtentReportManager.logFailWithScreenshot(driver,
                        "Test FAILED: " + result.getName() + "\n" + result.getThrowable());
                break;
            case ITestResult.SKIP:
                ExtentReportManager.logSkip("Test SKIPPED: " + result.getName());
                break;
        }
        ExtentReportManager.removeTest();
    }

    @AfterClass
    public void tearDown() {
        DriverFactory.quitDriver();
        System.out.println("Teardown complete — browser is closed!");
    }

    @AfterSuite
    public void flushReport() {
        ExtentReportManager.flush();
        System.out.println("Extent Report flushed to output/reports/ExtentReport.html");
    }
}
