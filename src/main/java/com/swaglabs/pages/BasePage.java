package com.swaglabs.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected AppiumDriver driver;
    protected WebDriverWait wait;

    public BasePage(AppiumDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    // Common methods, e.g., wait for visibility, click, send keys
    protected void clickElement(org.openqa.selenium.WebElement element) {
        element.click();
    }

    protected void sendKeysToElement(org.openqa.selenium.WebElement element, String text) {
        element.sendKeys(text);
    }

    protected String getTextFromElement(org.openqa.selenium.WebElement element) {
        return element.getText();
    }
}
