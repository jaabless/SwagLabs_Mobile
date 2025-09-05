package com.swaglabs.tests;

import io.appium.java_client.AppiumBy;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.swaglabs.pages.LoginPage;
import com.swaglabs.pages.ProductsPage;

public class LogoutTests extends BaseTest {

    @BeforeMethod
    public void loginSetup() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(testData.getProperty("valid_username"), testData.getProperty("valid_password"));
    }

    @Test
    @Description("Verify logout functionality after successful login")
    @Story("Logout Functionality")
    @Severity(SeverityLevel.NORMAL)
    public void testLogout() {
        ProductsPage productsPage = new ProductsPage(driver);
        Assert.assertTrue(productsPage.isFirstProductDisplayed(), "Login failed, products page not displayed");
         // Perform logout
        productsPage.openMenu();  // Open menu
        LoginPage loginPage = productsPage.clickLogout();
        Assert.assertTrue(loginPage.isLoginButtonDisplayed(), "Not back to login page after logout");
    }
}
