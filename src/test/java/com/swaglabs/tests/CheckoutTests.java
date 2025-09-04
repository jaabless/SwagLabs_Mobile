package com.swaglabs.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.swaglabs.pages.*;

public class CheckoutTests extends BaseTest {

    @BeforeMethod
    public void setupToCheckout() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(testData.getProperty("valid_username"), testData.getProperty("valid_password"));

        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.addFirstProductToCart();
        productsPage.clickCartIcon();

        CartPage cartPage = new CartPage(driver);
        cartPage.clickCheckout();
    }

    @DataProvider(name = "checkoutData")
    public Object[][] checkoutData() {
        return new Object[][]{
                {testData.getProperty("first_name"), testData.getProperty("last_name"), testData.getProperty("zip_code"), true, ""},
                {testData.getProperty("empty"), testData.getProperty("last_name"), testData.getProperty("zip_code"), false, "First Name is required"},
                {testData.getProperty("first_name"), testData.getProperty("empty"), testData.getProperty("zip_code"), false, "Last Name is required"},
                {testData.getProperty("first_name"), testData.getProperty("last_name"), testData.getProperty("empty"), false, "Zip/Postal Code is required"},
                {testData.getProperty("special_chars"), testData.getProperty("last_name"), testData.getProperty("zip_code"), false, "First Name is required"}
        };
    }

    @Test(dataProvider = "checkoutData")
    public void testCheckoutProcess(String firstName, String lastName, String zipCode, boolean expectedSuccess, String expectedError) {
        CheckoutInformationPage infoPage = new CheckoutInformationPage(driver);
        Assert.assertTrue(infoPage.isCheckoutInfoPageDisplayed(), "Checkout Information Page not displayed");
        infoPage.enterUserInformation(firstName, lastName, zipCode);
        infoPage.clickContinue();

        if (expectedSuccess) {
            CheckoutOverviewPage overviewPage = new CheckoutOverviewPage(driver);
            OrderConfirmationPage confirmationPage = overviewPage.clickFinish();
            Assert.assertTrue(confirmationPage.isConfirmationPageDisplayed(), "Confirmation Page not displayed");
            System.out.println("test: "+ confirmationPage.getConfirmationMessage());
            Assert.assertEquals(confirmationPage.getConfirmationMessage(), "THANK YOU FOR YOU ORDER", "Order not confirmed");
        } else {
            System.out.println("Error Message: " + infoPage.getErrorMessage());
            Assert.assertEquals(infoPage.getErrorMessage(), expectedError, "Incorrect error message");
        }
    }

    // Edge: Max length for fields, special chars in names
    @Test
    public void testEdgeCaseSpecialChars() {
        CheckoutInformationPage infoPage = new CheckoutInformationPage(driver);
        infoPage.enterUserInformation(testData.getProperty("special_chars"), testData.getProperty("last_name"), testData.getProperty("zip_code"));
        infoPage.clickContinue();
        // Assume success or specific validation
    }
}
