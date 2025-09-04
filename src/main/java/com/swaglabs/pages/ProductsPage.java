package com.swaglabs.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ProductsPage extends BasePage {

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Menu\"]/android.view.ViewGroup/android.widget.ImageView")
    private WebElement menuButton;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Modal Selector Button\"]/android.view.ViewGroup/android.view.ViewGroup/android.widget.ImageView")
    private WebElement sortButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text, 'Name - A to Z')]")  // Example for sort options
    private WebElement sortAToZ;

    @AndroidFindBy(xpath = "(//android.widget.TextView[@text=\"ADD TO CART\"])[1]")
    private List<WebElement> addToCartButtons;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Cart\"]/android.view.ViewGroup/android.widget.ImageView")
    private WebElement cartIcon;

    @AndroidFindBy(xpath = "//android.widget.TextView[@content-desc='test-Item title']")
    private List<WebElement> productTitles;

    @AndroidFindBy (xpath = "//android.widget.TextView[@text='LOGOUT']")
    private WebElement logoutLink;

    @AndroidFindBy (xpath = "(//android.view.ViewGroup[@content-desc=\"test-Item\"])[1]/android.view.ViewGroup")
    private WebElement firstProduct;

    public ProductsPage(AppiumDriver driver) {
        super(driver);
    }

    public void sortProductsAToZ() {
        clickElement(sortButton);
        clickElement(sortAToZ);
    }

    public void addFirstProductToCart() {
        clickElement(addToCartButtons.get(0));
    }



    public CartPage clickCartIcon() {
        clickElement(cartIcon);
        return new CartPage(driver);
    }

//    public List<String> getProductTitles() {
//        // Collect texts
////        return productTitles.stream().map(this::getTextFromElement).toList();
//        return productTitles.stream().map(this::getTextFromElement).toList();
//    }
    public boolean isMenuButtonDisplayed() {
        menuButton.isDisplayed();
        return true;
    }

    public void openMenu() {
        clickElement(menuButton);
    }

    public LoginPage clickLogout() {
        openMenu();
        clickElement(logoutLink);
        return new LoginPage(driver);
    }

    public boolean isFirstProductDisplayed() {
        return firstProduct.isDisplayed();
    }

    // Add more sorting options as needed (e.g., price low-high)
}
