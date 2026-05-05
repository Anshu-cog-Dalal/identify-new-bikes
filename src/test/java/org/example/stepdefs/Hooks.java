package org.example.stepdefs;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.example.base.DriverFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Hooks {
    public static WebDriver driver;

    @Before
    public void setUp(){
        DriverFactory.initDriver();
        driver=DriverFactory.getDriver();
        System.out.println("Setup complete,browser is ready!");
    }
    @After
    public void tearDown(){
        DriverFactory.quitDriver();
        System.out.println("Teardown complete,browser is closed!");
    }
    @AfterStep
    public void  addScreenShot(Scenario scenario){
        if(scenario.isFailed()){
            byte[] screenshot=((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot,"image/png","Failure Screenshot");
            System.out.println("Screenshot captured: "+scenario.getName());
        }
    }
}