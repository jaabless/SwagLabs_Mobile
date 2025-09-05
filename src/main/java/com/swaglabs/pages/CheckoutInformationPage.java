package com.swaglabs.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutInformationPage extends BasePage {

    @AndroidFindBy(accessibility = "test-First Name")
    private WebElement firstNameField;

    @AndroidFindBy(accessibility = "test-Last Name")
    private WebElement lastNameField;

    @AndroidFindBy(accessibility = "test-Zip/Postal Code")
    private WebElement zipCodeField;

    @AndroidFindBy(accessibility = "test-CONTINUE")
    private WebElement continueButton;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='test-Error message']//android.widget.TextView")
    private WebElement errorMessage;

    public CheckoutInformationPage(AppiumDriver driver) {
        super(driver);
    }

    public void enterUserInformation(String firstName, String lastName, String zipCode) {
        sendKeysToElement(firstNameField, firstName);
        sendKeysToElement(lastNameField, lastName);
        sendKeysToElement(zipCodeField, zipCode);
    }

    public void clickContinue() {
        clickElement(continueButton);
    }

    public String getErrorMessage() {
        return errorMessage.getText();
    }

    public boolean isCheckoutInfoPageDisplayed() {
        return firstNameField.isDisplayed() && lastNameField.isDisplayed() && zipCodeField.isDisplayed();
    }
}
