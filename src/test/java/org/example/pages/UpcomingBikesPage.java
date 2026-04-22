package org.example.pages;
import org.example.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UpcomingBikesPage extends BasePage {
    public UpcomingBikesPage(WebDriver driver) {
        super(driver);
    }

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));


    //store the bike name,price,launch date
    public static class BikeDetails{
        public final String name;
        public final String price;
        public final String launchDate;

        public BikeDetails(String name,String price,String launchDate){
            this.name=name;
            this.price=price;
            this.launchDate=launchDate;
        }
    }
        //locate all bike cards
        @FindBy(xpath="//li[@class='col-lg-4 txt-c rel modelItem ']")
        private List<WebElement> bikeCards;

        //locate the
        private By honda = By.xpath("//a[normalize-space()='Honda']");
        private final By BikeName=By.xpath(".//strong");
        private final By BikelaunchDate=By.xpath(".//div[contains(text(),'Expected Launch : ')]");

        //Click on Honda Filter
        public void hondaFilter() throws InterruptedException {

            JavascriptExecutor js = (JavascriptExecutor)driver;
            js.executeScript("window.scrollBy(0,3000)");
            WebElement hondaBtn = wait.until(ExpectedConditions.elementToBeClickable(honda));
            Thread.sleep(1000);
            hondaBtn.click();
        }

        //Filter Honda bike under 4 lakh
        public List<BikeDetails> getHondaBikes(){
            List<BikeDetails> result=new ArrayList<>();

            for (WebElement card : bikeCards) {
                try {
                    String bikeName=card.findElement(BikeName).getText().trim();
                    String bikePrice=card.getAttribute("data-price");
                    String launchDate=card.findElement(BikelaunchDate).getText().trim();
                    if (Integer.parseInt(bikePrice) < 400000 && Integer.parseInt(bikePrice)!=0){
                        result.add(new BikeDetails(bikeName,bikePrice,launchDate));
                    }
                } catch (Exception e) {
                   System.out.println("Can not add the bike Details");
                }
            }
            return result;
        }

}
