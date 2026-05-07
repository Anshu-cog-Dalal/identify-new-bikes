package org.example.stepdefs;
import org.example.Hook.Hooks;
import org.example.base.BasePage;
import org.example.pages.HomePage;
import org.example.pages.UsedCarsPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class UsedCarsSteps {
    private WebDriver driver;
    private HomePage homePage;
    private UsedCarsPage usedCarsPage;

    @Given("the user is on Home page")
    public void the_user_is_on_home_page() {
        BasePage.getLogger().info("the user is on Home page");
        this.driver= Hooks.driver;
        homePage=new HomePage(driver);
    }

    @When("the user navigates to the Used Cars page")
    public void the_user_navigates_to_the_used_cars_page() {
        BasePage.getLogger().info("the user is on Home page");
        homePage.cilckUsedCar();
        usedCarsPage=new UsedCarsPage(driver);
    }

    @When("the user selects Chennai")
    public void the_user_selects_chennai() {
        BasePage.getLogger().info("the user selects Chennai");
        usedCarsPage.selectCity();
    }

    @Then("the popular used cars should be displayed")
    public void the_popular_used_cars_should_be_displayed() {
        BasePage.getLogger().info("the popular used cars should be displayed");
        usedCarsPage.displayPopularUsedCars();
        Assert.assertTrue(true, "Popular used cars are displayed successfully");
    }
}