package org.example.base;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
public class DriverFactory{
    public static WebDriver driver;
    public static void initDriver(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.zigwheels.com");
        System.out.println("Browser opened and ZigWheels loaded!");
    }
    public static WebDriver getDriver() {

        return driver;
    }
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
            System.out.println("Browser closed!");
        }
    }
}

