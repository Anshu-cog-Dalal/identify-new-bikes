package org.example.tests;
import org.example.base.BaseTest;
import org.example.pages.HomePage;
import org.example.pages.UpcomingBikesPage;
import org.example.pages.UpcomingBikesPage.BikeDetails;
import org.example.utils.ExcelReader;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;
public class UpcomingBikesTest extends BaseTest{
    @Test
    public void verifyHondaBikesUnder4Lakh() throws InterruptedException {
        HomePage homePage = new HomePage(driver);

        //navigate upcoming bike page
        homePage.cilckUpComingBike();

        //changed by me
        Object[][] bikesData =
                ExcelReader.getSheetData("UpcomingHondaBikes");

        //fetch the honda bike in range
        UpcomingBikesPage u=new UpcomingBikesPage(driver);
        u.hondaFilter();
        List<BikeDetails> bikes = u.getHondaBikes();

        //print bike deatails
        System.out.println("  Upcoming Honda Bikes Under 4 Lakh in India — Total: " + bikes.size());
        for (int i = 0; i < bikes.size(); i++) {
            BikeDetails bike = bikes.get(i);
            System.out.println("\nBike " + (i + 1) + " :");
            System.out.println("Bike Name: " + bike.name);
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
