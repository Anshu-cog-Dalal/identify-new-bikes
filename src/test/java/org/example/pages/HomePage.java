package org.example.pages;

import org.example.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;



public class HomePage extends BasePage {
    public HomePage(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath = "//span[normalize-space()='NEW BIKES']")
    private WebElement newBike;
    @FindBy(xpath = "//a[normalize-space()='Upcoming Bikes']")
    private WebElement upComingBike;

    @FindBy(xpath = "//span[normalize-space()='MORE']")
    private WebElement more;
    @FindBy(xpath = "//a[normalize-space()='Used Cars']")
    private WebElement usedCar;

    @FindBy(xpath = "//div[@id='des_lIcon']")
    private WebElement loginIcon;

    public void cilckUpComingBike(){
        Actions ac = new Actions(driver);
        ac.moveToElement(newBike).perform();
        upComingBike.click();
    }

    public void cilckUsedCar(){
        Actions ac = new Actions(driver);
        ac.moveToElement(more).perform();
        usedCar.click();
    }

    public void clickLoginIcon(){
        loginIcon.click();
    }
}
