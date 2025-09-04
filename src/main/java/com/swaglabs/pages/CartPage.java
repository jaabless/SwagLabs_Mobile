package com.swaglabs.pages;


import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage extends BasePage {

    @AndroidFindBy(accessibility = "test-REMOVE")
    private List<WebElement> removeButtons;

    @AndroidFindBy(accessibility = "test-CHECKOUT")
    private WebElement checkoutButton;

    @AndroidFindBy(accessibility = "test-Cart Empty")
    private WebElement emptyCartMessage;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Cart\"]/android.view.ViewGroup/android.widget.ImageView")
    private WebElement cartIcon;

    public CartPage(AppiumDriver driver) {
        super(driver);
    }

    public void removeFirstItem() {
        clickElement(removeButtons.get(0));
    }

    public CheckoutInformationPage clickCheckout() {
        clickElement(checkoutButton);
        return new CheckoutInformationPage(driver);
    }

    public boolean isCartEmpty() {
        return emptyCartMessage.isDisplayed();
    }

    public String getCartItemCount() {
        try {
            return getTextFromElement(cartIcon.findElement(org.openqa.selenium.By.xpath("//android.view.ViewGroup[@content-desc=\"test-Cart\"]/android.view.ViewGroup/android.view.ViewGroup")));
        } catch (Exception e) {
            return "0";
        }
    }
}