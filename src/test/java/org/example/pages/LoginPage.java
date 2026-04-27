package org.example.pages;

import org.example.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[@class='lgn-sc c-p txt-l pl-30 pr-30 googleSignIn']")
    private WebElement googleButton;

    @FindBy(xpath = "//input[@id='identifierId']")
    private WebElement emailField;

    @FindBy(xpath = "//span[normalize-space()='Next']")
    private WebElement nextButton;

    @FindBy(xpath = "//div[@class='Ekjuhf Jj6Lae']")
    private WebElement errorMessage;

    public void clickGoogleButton() {
        clickElement(googleButton);
    }

    public void enterEmail(String email) {
        typeText(emailField, email);
    }

    public void clickNext() {
        clickElement(nextButton);
    }

    public String getErrorMessage() {
        return getElementText(errorMessage);
    }

    public WebElement getEmailField() {
        return emailField;
    }

    public WebElement getErrorElement() {
        return errorMessage;
    }
}