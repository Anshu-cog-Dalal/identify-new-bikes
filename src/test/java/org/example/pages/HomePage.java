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
   //Locate new Bikes section
    @FindBy(xpath="//span[normalize-space()='NEW BIKES']")
    private WebElement newBike;
    //Locate upcoming bikes section
    @FindBy(xpath="//a[normalize-space()='Upcoming Bikes']")
    private WebElement upComingBike;
    //Locate More section
    @FindBy(xpath="//span[normalize-space()='MORE']")
    private WebElement more;
    //Locate used car section
    @FindBy(xpath="//a[normalize-space()='Used Cars']")
    private WebElement usedCar;
    //Locate Login section
    @FindBy(xpath="//div[@id='des_lIcon']")
    private WebElement loginIcon;

    //Hover new Bike and click upcoming bike section
    public void cilckUpComingBike(){
        Actions ac=new Actions(driver);
        ac.moveToElement(newBike).perform();
        upComingBike.click();
    }

   //Hover More and Click Used Car section
    public void cilckUsedCar(){
        Actions ac=new Actions(driver);
        ac.moveToElement(more).perform();
        usedCar.click();
    }

    //Click login button
    public void clickLoginIcon(){
        loginIcon.click();
    }
}
