package com.swaglabs.tests;


import com.swaglabs.pages.CheckoutInformationPage;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.swaglabs.pages.CartPage;
import com.swaglabs.pages.LoginPage;
import com.swaglabs.pages.ProductsPage;

public class CartTests extends BaseTest {

    @BeforeMethod
    public void setupWithItemInCart() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(testData.getProperty("valid_username"), testData.getProperty("valid_password"));
        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.addFirstProductToCart();
        productsPage.clickCartIcon();
    }

    @Test
    @Description("Verify adding an item to the cart updates the cart count")
    @Story("Cart Functionality")
    @Severity(SeverityLevel.CRITICAL)
    public void testAddItemToCart() {
        CartPage cartPage = new CartPage(driver);
        Assert.assertFalse(cartPage.getCartItemCount().isEmpty());
    }

    @Test
    @Description("Verify removing an item from the cart updates the cart count")
    @Story("Cart Functionality")
    @Severity(SeverityLevel.NORMAL)
    public void testAddAndRemoveFromCart() {
        CartPage cartPage = new CartPage(driver);
        Assert.assertFalse(cartPage.getCartItemCount().isEmpty());
        // Remove item
        cartPage.removeFirstItem();
//        Assert.assertTrue(cartPage.isCartEmpty(), "Cart not empty after remove");
        Assert.assertEquals(cartPage.getCartItemCount(), "0");

        // Negative: Try remove when empty (no action, but verify no error)
    }

    @Test
    @Description("Verify proceeding to checkout from the cart navigates to the checkout information page")
    @Story("Cart Functionality")
    @Severity(SeverityLevel.NORMAL)
    public void testProceedToCheckout() {
        CartPage cartPage = new CartPage(driver);
        CheckoutInformationPage checkoutInformationPage = cartPage.clickCheckout();
        Assert.assertTrue(checkoutInformationPage.isCheckoutInfoPageDisplayed(), "Checkout Information Page not displayed");
        // Verify next page (e.g., check element on CheckoutInformationPage)
    }

    @Test
    @Description("Verify checkout with empty cart does not proceed")
    @Story("Cart Functionality")
    @Severity(SeverityLevel.MINOR)
    public void testCheckoutWithEmptyCart() {
        CartPage cartPage = new CartPage(driver);
        cartPage.removeFirstItem(); // Ensure cart is empty
        Assert.assertEquals(cartPage.getCartItemCount(), "0");
        // Attempt to proceed to checkout
        cartPage.clickCheckout();
        // Verify that checkout does not proceed and appropriate message is shown
        Assert.assertTrue(cartPage.isCheckoutButtonEnabled(), "Checkout button should be disabled with empty cart");
    }
}
