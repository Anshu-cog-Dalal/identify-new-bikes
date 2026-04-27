package org.example.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;

public class DriverFactory {
    public static WebDriver driver;

    public static void initDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        options.setExperimentalOption("useAutomationExtension",false);

        driver = new ChromeDriver(options);
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