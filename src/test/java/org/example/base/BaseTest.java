package org.example.base;
import org.example.utils.ConfigReader;
import org.example.utils.ExtentReportManager;
import org.example.utils.ScreenshotUtil;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import java.lang.reflect.Method;

public class BaseTest{
    protected WebDriver driver;
    @BeforeClass
    public void setUp(){
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        System.out.println("Setup complete—browser is ready!");
    }


    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() {
        ExtentReportManager.getInstance(); // initialize report once
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite() {
        ExtentReportManager.flushReport(); // Writes the HTML report to disk
    }


    @AfterMethod
    public void tearDown(){
        DriverFactory.quitDriver();
        System.out.println("Teardown complete—browser is closed!");
    }

}