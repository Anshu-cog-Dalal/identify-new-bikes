package org.example.tests;

import org.example.base.BaseTest;
import org.example.pages.HomePage;
import org.example.pages.LoginPage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;

public class GoogleLoginTest extends BaseTest{

    @Test
    public void testInvalidGoogleLogin() throws InterruptedException{

        // Click account logo on Home Page
        HomePage homePage = new HomePage(driver);
        homePage.clickLoginIcon();
        Thread.sleep(2000); // wait for popup

        // Click Google button in popup
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickGoogleButton();

        //Switch to new Google window
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        ArrayList<String> windows = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(windows.get(1));


        loginPage.enterEmail("shubham@gil.com");
        loginPage.clickNext();
        // wait for error message
        Thread.sleep(2000);

        // Capture and print error message
        String error = loginPage.getErrorMessage();
        System.out.println("Error Message Displayed: " + error);

        //Assert error message
        Assert.assertTrue(error.contains("find your Google Account"),
                "Error message not displayed!");

        //Switch back to main window
        driver.switchTo().window(windows.get(0));
    }
}