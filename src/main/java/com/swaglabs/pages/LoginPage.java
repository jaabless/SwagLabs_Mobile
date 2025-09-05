package com.swaglabs.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @AndroidFindBy(accessibility = "test-Username")
    private WebElement usernameField;

    @AndroidFindBy(accessibility = "test-Password")
    private WebElement passwordField;

    @AndroidFindBy(accessibility = "test-LOGIN")
    private WebElement loginButton;

//    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Sorry, this user has been locked out.\"]")
//    private WebElement errorMessage;
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='test-Error message']//android.widget.TextView")
    private WebElement errorMessage;


    public LoginPage(AppiumDriver driver) {
        super(driver);
    }

    public void enterUsername(String username) {
        sendKeysToElement(usernameField, username);
    }

    public void enterPassword(String password) {
        sendKeysToElement(passwordField, password);
    }

    public void clickLogin() {
        clickElement(loginButton);
    }

    public String getErrorMessage() {
        return getTextFromElement(errorMessage).trim();
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }

    public boolean isLoginButtonDisplayed() {
        return loginButton.isDisplayed();
    }
}
