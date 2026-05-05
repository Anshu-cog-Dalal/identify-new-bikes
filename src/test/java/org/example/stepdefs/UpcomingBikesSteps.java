package org.example.stepdefs;
import org.example.pages.HomePage;
import org.example.pages.UpcomingBikesPage;
import org.example.pages.UpcomingBikesPage.BikeDetails;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import java.util.List;

public class UpcomingBikesSteps {

    private WebDriver driver;
    private HomePage homePage;
    private UpcomingBikesPage upcomingBikesPage;
    private List<BikeDetails> bikes;

    @Given("the user is on the Home page")
    public void the_user_is_on_the_home_page() {
        this.driver=Hooks.driver;
        homePage=new HomePage(driver);
    }

    @When("the user navigates to the Upcoming Bikes page")
    public void the_user_navigates_to_the_upcoming_bikes_page() {
        homePage.cilckUpComingBike();
        upcomingBikesPage=new UpcomingBikesPage(driver);
    }

    @When("the user applies the Honda brand filter")
    public void the_user_applies_the_honda_brand_filter() throws InterruptedException{
        upcomingBikesPage.hondaFilter();

    }

    @Then("the user should see a list of upcoming Honda bikes under 4 lakh")
    public void the_user_should_see_a_list_of_upcoming_honda_bikes_under_4_lakh() {
        bikes = upcomingBikesPage.getHondaBikes();
        Assert.assertTrue(bikes.size()>0,
                "FAIL : No Honda bikes found under 4 lakh.");
        System.out.println("Upcoming Honda Bikes Under 4 Lakh — Total: " + bikes.size());
        for(int i=0;i<bikes.size();i++){
            BikeDetails bike=bikes.get(i);
            System.out.println("\nBike " + (i + 1) + " :");
            System.out.println("Bike Name: "+bike.name);
            System.out.println("Price: "+bike.price);
            System.out.println("Expected Launch:"+bike.launchDate);
        }
    }

    @Then("each bike should have a non empty name")
    public void each_bike_should_have_a_non_empty_name() {
        for(BikeDetails bike:bikes){
            Assert.assertFalse(bike.name.isEmpty(),
                    "FAIL : Bike name should not be empty.");
        }
    }

    @Then("each bike should have a non empty price")
    public void each_bike_should_have_a_non_empty_price() {
        for(BikeDetails bike:bikes){
            Assert.assertFalse(bike.price.isEmpty(),
                    "FAIL : Bike price should not be empty for: "+bike.name);
        }
    }

    @Then("each bike should have a non empty expected launch date")
    public void each_bike_should_have_a_non_empty_expected_launch_date() {
        for(BikeDetails bike:bikes){
            Assert.assertFalse(bike.launchDate.isEmpty(),
                    "FAIL : Launch date should not be empty for: "+bike.name);
        }
        System.out.println("INFO : All assertions passed successfully.");
    }
}


