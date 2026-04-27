package org.example.base;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

public class BaseTest{
    protected WebDriver driver;
    @BeforeClass
    public void setUp(){
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        System.out.println("Setup complete—browser is ready!");
    }


    @AfterMethod
    public void tearDown(){
        DriverFactory.quitDriver();
        System.out.println("Teardown complete—browser is closed!");
    }

}