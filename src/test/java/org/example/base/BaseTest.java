package org.example.base;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
public class BaseTest{
    protected WebDriver driver;
    @BeforeClass
    public void setUp(){
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        System.out.println("Setup complete—browser is ready!");
    }
    @AfterClass
    public void tearDown(){
        DriverFactory.quitDriver();
        System.out.println("Teardown complete—browser is closed!");
    }
}