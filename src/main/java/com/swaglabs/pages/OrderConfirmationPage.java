package com.swaglabs.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OrderConfirmationPage extends BasePage {

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"THANK YOU FOR YOU ORDER\"]")
    private WebElement thankYouMessage;

    public OrderConfirmationPage(AppiumDriver driver) {
        super(driver);
    }

    public String getConfirmationMessage() {
        return getTextFromElement(thankYouMessage);
    }

    public boolean isConfirmationPageDisplayed() {
        return thankYouMessage.isDisplayed();
    }
}