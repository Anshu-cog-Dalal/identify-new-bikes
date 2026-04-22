package org.example.tests;
import org.example.base.BaseTest;
import org.example.pages.HomePage;
import org.example.pages.UpcomingBikesPage;
import org.example.pages.UpcomingBikesPage.BikeDetails;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;
public class UpcomingBikesTest extends BaseTest{
    @Test
    public void verifyHondaBikesUnder4Lakh(){
        //open the url
        driver.get("https://www.zigwheels.com");
        //navigate upcoming bike page
        HomePage homePage = new HomePage(driver);
        homePage.cilckUpComingBike();

        //fetch the honda bike in range
        UpcomingBikesPage u=new UpcomingBikesPage(driver);
        List<BikeDetails> bikes = u.getHondaBikes();

        //check bike display
        boolean isDisplayed = u.isHonda();
        System.out.println("INFO : Honda bikes under 4 Lakh displayed = " + isDisplayed);
        Assert.assertTrue(isDisplayed, "FAIL : No Honda bikes under 4 Lakh are displayed on the page.");
        //verify count
        int count = u.countHonda();
        System.out.println("INFO : Total Honda bikes under 4 Lakh = " + count);
        Assert.assertTrue(count > 0, "FAIL : Count should be > 0 but was: " + count);
        //fetch details
        List<BikeDetails> bikes1 = u.getHondaBikes();
        //print bike deatails
        System.out.println("  Upcoming Honda Bikes Under 4 Lakh in India — Total: " + bikes.size());
        for (int i = 0; i < bikes.size(); i++) {
            BikeDetails bike = bikes.get(i);
            System.out.println("\n  Bike " + (i + 1) + " :");
            System.out.println("  Bike Name: " + bike.name);
            System.out.println("Price: " + bike.price);
            System.out.println("Expected Launch:" + bike.launchDate);
        }
        for (BikeDetails bike : bikes) {
            Assert.assertFalse(bike.name.isEmpty(),
                    "FAIL : Bike name should not be empty.");
            Assert.assertFalse(bike.price.isEmpty(),
                    "FAIL : Bike price should not be empty for: " + bike.name);
            Assert.assertFalse(bike.launchDate.isEmpty(),
                    "FAIL : Launch date should not be empty for: " + bike.name);
        }

        System.out.println("\nINFO : All assertions passed successfully.");
    }


}
