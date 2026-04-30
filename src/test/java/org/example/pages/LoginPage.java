package org.example.pages;
import org.example.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver){
        super(driver);
    }

    //Locate Google button
    @FindBy(xpath="//div[@class='lgn-sc c-p txt-l pl-30 pr-30 googleSignIn']")
    private WebElement googleButton;

    //Locate email field
    @FindBy(xpath="//input[@id='identifierId']")
    private WebElement emailField;

    //Locate Next button
    @FindBy(xpath="//span[normalize-space()='Next']")
    private WebElement nextButton;

    //Locate Error message
    @FindBy(xpath="//div[@class='Ekjuhf Jj6Lae']")
    private WebElement errorMessage;

    //Click the google button
    public void clickGoogleButton(){
        clickElement(googleButton);
    }

    //fill the email
    public void enterEmail(String email){
        typeText(emailField,email);
    }

    //Click the next Button
    public void clickNext(){
        clickElement(nextButton);
    }

    public String getErrorMessage(){
        return getElementText(errorMessage);
    }

    public WebElement getEmailField(){
        return emailField;
    }

    public WebElement getErrorElement(){
        return errorMessage;
    }
}