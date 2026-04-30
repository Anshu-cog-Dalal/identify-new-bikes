package org.example.pages;
import org.example.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.*;

public class UsedCarsPage extends BasePage {
    public UsedCarsPage(WebDriver driver){
        super(driver);
    }
    @FindBy(xpath="//*[@id=\"popularCityList\"]/li[7]/a")
    private WebElement chennaiCity;

    @FindBy(xpath="//div[@class='gsc_thin_scroll']/ul/li/label")
    private List<WebElement> popularCarList;

    public void selectCity(){
        clickElement(chennaiCity);
        System.out.println("City Clicked");
    }
    public List<String> getPopularList(){
        List<String> carNames=new ArrayList<String>();
        for(WebElement car:popularCarList){
            String name=car.getText().trim();
            carNames.add(name);
        }
        return carNames;
    }
    public void displayPopularUsedCars(){
        List<String> cars=getPopularList();
        System.out.println("Popular used cars in Chennai:");
        for(String car:cars){
            System.out.println(car);
        }
    }


}
