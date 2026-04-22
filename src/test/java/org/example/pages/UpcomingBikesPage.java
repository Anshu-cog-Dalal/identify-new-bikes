package org.example.pages;
import org.example.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UpcomingBikesPage extends BasePage {
    public UpcomingBikesPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }


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

        @Override
        public String toString(){
            return String.format(
                    "Bike Name      : %s%n  Price          : %s%n  Expected Launch: %s",
                    name,price,launchDate
            );
        }
    }
//        //locate all bike cards
        @FindBy(xpath="//li[contains(@class,'modelItem')]")
        private List<WebElement> bikeCards;
//        //locate bike name
        @FindBy(xpath = "//li[contains(@class,'modelItem')]")
        private List<WebElement> allBikeCards;
        //locate the
        private final By BikeName=By.xpath(".//strong[contains(@class,'lnk-hvr')]");
        private final By BikePrice=By.xpath(".//div[contains(@class,'b fnt-15')]");
        private final By BikelaunchDate=By.xpath(".//div[contains(@class,'clr-try fnt-14')]");
        private final By alertBtn=By.xpath(".//span[contains(@data-cta-type,'awl')]");
        //convert the string type price in integer
        private int parsePrice(String priceText){
            if(priceText==null||priceText.trim().isEmpty()||priceText.contains("Announced")) {
                return Integer.MAX_VALUE;
            }
            priceText=priceText.replace("Rs.","").trim();
            if(priceText.toLowerCase().contains("lakh")){
                double lakhs=Double.parseDouble(
                        priceText.toLowerCase().replace("lakh", "").trim());
                return (int)(lakhs * 100_000);
            }
            return Integer.parseInt(priceText.replaceAll(",","").trim());
        }
        //Filter Honda bike under 4 lakh

        public List<BikeDetails> getHondaBikes(){
            List<BikeDetails> result=new ArrayList<>();

            for (WebElement card : bikeCards) {
                try {
                    //manufacture attribute
                    String makeName=card.findElement(alertBtn).getAttribute("data-cta-makename");
                    String bikeName=card.findElement(BikeName).getText().trim();
                    String bikePrice=card.findElement(BikePrice).getText().trim();
                    String launchDate=card.findElement(BikelaunchDate).getText().trim();
                    if ("Honda".equalsIgnoreCase(makeName) && parsePrice(bikePrice) < 400000){
                        result.add(new BikeDetails(bikeName,bikePrice,launchDate));
                    }
                } catch (Exception e) {
                   System.out.println("Can not add the bike Details");
                }
            }
            return result;
        }
    public int countHonda() {
        int count = 0;
        for (WebElement card : allBikeCards) {
            try {
                String makeName   = card.findElement(alertBtn).getAttribute("data-cta-makename");
                String priceText  = card.findElement(BikePrice).getText().trim();

                if ("Honda".equalsIgnoreCase(makeName) && parsePrice(priceText) < 400000) {
                    count++;
                }
            } catch (Exception e) {
                System.out.println("Cannot read card for count: " + e.getMessage());
            }
        }
        return count;
    }
    public boolean isHonda() {
        for (WebElement card : allBikeCards) {
            try {
                String makeName  = card.findElement(alertBtn).getAttribute("data-cta-makename");
                String priceText = card.findElement(BikePrice).getText().trim();

                if ("Honda".equalsIgnoreCase(makeName) && parsePrice(priceText) < 400_000) {
                    return true;
                }
            } catch (Exception e) {
                System.out.println("Cannot read card for display check: " + e.getMessage());
            }
        }
        return false;
    }

}
