package org.example.tests;

import org.example.base.BaseTest;
import org.example.pages.HomePage;
import org.example.pages.UsedCarsPage;
import org.testng.annotations.Test;

public class UsedCarsTest extends BaseTest {
    @Test
    public void verifyUsedCars(){
        HomePage homePage = new HomePage(driver);
        homePage.cilckUsedCar();
        UsedCarsPage usedCarsP = new UsedCarsPage(driver);
        usedCarsP.selectCity();
        usedCarsP.displayPopularUsedCars();

    }

}
