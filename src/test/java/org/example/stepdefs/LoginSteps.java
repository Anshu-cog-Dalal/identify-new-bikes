package org.example.stepdefs;
import io.cucumber.java.en.*;
import org.example.Hook.Hooks;
import org.example.base.BasePage;
import org.example.pages.HomePage;
import org.example.pages.LoginPage;
import org.example.utils.ExcelReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;
import java.util.ArrayList;

public class LoginSteps {
    private WebDriver driver;
    private LoginPage loginPage;
    private HomePage homePage;

    @Given("the user is on the home page")
    public void the_user_is_on_the_home_page() {
        BasePage.getLogger().info("the user is on the home page");
        this.driver = Hooks.driver;
        homePage = new HomePage(driver);
        System.out.println("User is on ZigWheels home page");
    }

    @When("the user clicks the login icon")
    public void the_user_clicks_the_login_icon() {
        BasePage.getLogger().info("the user clicks the login icon");
        homePage.clickLoginIcon();
    }

    @When("the user clicks the Google sign-in button")
    public void the_user_clicks_the_google_sign_in_button() throws InterruptedException{
        BasePage.getLogger().info("the user clicks the Google sign-in button");
        loginPage=new LoginPage(driver);
        Thread.sleep(2000);
        loginPage.clickGoogleButton();
    }

    @When("the user enters an invalid email from excel by row {string}")
    public void the_user_enters_an_invalid_email(String row) {
        BasePage.getLogger().info("the user enters an invalid email from excel");
        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
        ArrayList<String> windows=new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(windows.get(1));
        int rowNum=Integer.parseInt(row);
        String email=ExcelReader.getEmail(rowNum);
        wait.until(ExpectedConditions.visibilityOf(loginPage.getEmailField()));
        loginPage.enterEmail(email);
        System.out.println("Entered invalid email: "+email);
    }

    @When("the user clicks Next")
    public void the_user_clicks_next() {
        BasePage.getLogger().info("the user clicks Next");
        loginPage.clickNext();
    }

    @Then("an error message should be displayed")
    public void an_error_message_containing_should_be_displayed() {
        BasePage.getLogger().info("error message should be displayed");
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(loginPage.getErrorElement()));
        String actualError=loginPage.getErrorMessage();
        System.out.println("Error displayed: "+actualError);
        ArrayList<String> windows=new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(windows.get(0));
    }
}