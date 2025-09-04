package com.swaglabs.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class CheckoutOverviewPage extends BasePage {

    @AndroidFindBy(accessibility = "test-FINISH")
    private WebElement finishButton;

    public CheckoutOverviewPage(AppiumDriver driver) {
        super(driver);
    }

    public OrderConfirmationPage clickFinish() {
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true))" +
                        ".scrollIntoView(new UiSelector().description(\"test-FINISH\"));"
        ));

        clickElement(finishButton);
        return new OrderConfirmationPage(driver);
    }

    private void scrollToElement(WebElement element) {
        // Using Appium's mobile: scrollGesture (W3C)
        driver.executeScript("mobile: scrollGesture", new java.util.HashMap<String, Object>() {{
            put("left", 100);
            put("top", 100);
            put("width", 200);
            put("height", 400);
            put("direction", "down");
            put("percent", 3.0);
        }});

        // You can loop until displayed
        while (!element.isDisplayed()) {
            driver.executeScript("mobile: scrollGesture", new java.util.HashMap<String, Object>() {{
                put("left", 100);
                put("top", 100);
                put("width", 200);
                put("height", 400);
                put("direction", "down");
                put("percent", 1.0);
            }});
        }
    }
}
