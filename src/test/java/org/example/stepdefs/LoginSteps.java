package org.example.stepdefs;

import io.cucumber.java.en.*;
import org.example.pages.HomePage;
import org.example.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.Assert;

import java.time.Duration;
import java.util.ArrayList;

public class LoginSteps {

    private WebDriver driver;
    private LoginPage loginPage;
    private HomePage homePage;

    @Given("the user is on the home page")
    public void the_user_is_on_the_home_page() {
        this.driver = Hooks.driver;
        homePage = new HomePage(driver);
        System.out.println("User is on ZigWheels home page");
    }

    @When("the user clicks the login icon")
    public void the_user_clicks_the_login_icon() {
        homePage.clickLoginIcon();
    }

    @When("the user clicks the Google sign-in button")
    public void the_user_clicks_the_google_sign_in_button() throws InterruptedException {
        loginPage = new LoginPage(driver);
        Thread.sleep(2000);
        loginPage.clickGoogleButton();
    }

    @When("the user enters an invalid email {string}")
    public void the_user_enters_an_invalid_email(String email) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        ArrayList<String> windows = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(windows.get(1));

        wait.until(ExpectedConditions.visibilityOf(loginPage.getEmailField()));
        loginPage.enterEmail(email);

        System.out.println("Entered invalid email: " + email);
    }

    @When("the user clicks Next")
    public void the_user_clicks_next() {
        loginPage.clickNext();
    }

    @Then("an error message containing {string} should be displayed")
    public void an_error_message_containing_should_be_displayed(String expectedError) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(loginPage.getErrorElement()));

        String actualError = loginPage.getErrorMessage();
        System.out.println("Error displayed: " + actualError);


        String normalizedActual = actualError.replaceAll("[''`]", "'");
        String normalizedExpected = expectedError.replaceAll("[''`]", "'");

        Assert.assertTrue(
                "Expected error not shown! Actual: " + actualError,
                normalizedActual.contains(normalizedExpected)
        );

        ArrayList<String> windows = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(windows.get(0));
    }
}