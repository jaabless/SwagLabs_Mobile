package com.swaglabs.tests;


import com.swaglabs.pages.CheckoutInformationPage;
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
    public void testAddItemToCart() {
        CartPage cartPage = new CartPage(driver);
        Assert.assertFalse(cartPage.getCartItemCount().isEmpty());
    }

    @Test
    public void testAddAndRemoveFromCart() {
        CartPage cartPage = new CartPage(driver);
        Assert.assertFalse(cartPage.getCartItemCount().isEmpty());

        // Verify item in cart (assume at least one remove button)
//        Assert.assertFalse(cartPage.isCartEmpty(), "No items in cart");

        // Remove item
        cartPage.removeFirstItem();
//        Assert.assertTrue(cartPage.isCartEmpty(), "Cart not empty after remove");
        Assert.assertEquals(cartPage.getCartItemCount(), "0");

        // Negative: Try remove when empty (no action, but verify no error)
    }

    @Test
    public void testProceedToCheckout() {
        CartPage cartPage = new CartPage(driver);
        CheckoutInformationPage checkoutInformationPage = cartPage.clickCheckout();
        Assert.assertTrue(checkoutInformationPage.isCheckoutInfoPageDisplayed(), "Checkout Information Page not displayed");
        // Verify next page (e.g., check element on CheckoutInformationPage)
    }
}
