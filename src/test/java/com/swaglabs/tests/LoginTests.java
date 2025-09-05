package com.swaglabs.tests;

import io.appium.java_client.AppiumBy;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.swaglabs.pages.LoginPage;
import com.swaglabs.pages.ProductsPage;

import java.time.Duration;

public class LoginTests extends BaseTest {

    @Test
    public void test() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        var loginBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("test-LOGIN")));
        Assert.assertTrue(loginBtn.isDisplayed(), "Login button is not displayed!");
    }

    @DataProvider(name = "loginData")
    public Object[][] loginData() {
        return new Object[][]{
                {testData.getProperty("valid_username"), testData.getProperty("valid_password"), true, ""},
                {testData.getProperty("invalid_username"), testData.getProperty("invalid_password"), false, "Username and password do not match any user in this service."},
//                {testData.getProperty("locked_username"), testData.getProperty("valid_password"), false, "Sorry, this user has been locked out."},
//                {testData.getProperty("empty"), testData.getProperty("valid_password"), false, "Username is required"},
//                {testData.getProperty("valid_username"), testData.getProperty("empty"), false, "Password is required"},
//                {testData.getProperty("valid_username"), testData.getProperty("special_chars"), false, "Username and password do not match any user in this service."}  // Edge: special chars
        };
    }

    @Test
    @Story("Login Functionality")
    @Description("Verify login page displays form correctly")
    @Severity(SeverityLevel.MINOR)
    public void testLoginPageFormDisplay() {
        LoginPage loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.isLoginPageDisplayed(), "Login page form not displayed correctly");
    }

    @Test(dataProvider = "loginData")
    @Story("Login Functionality")
    @Description("Verify login functionality with various credentials")
    @Severity(SeverityLevel.CRITICAL)
    public void testLogin(String username, String password, boolean expectedSuccess, String expectedError) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);

        if (expectedSuccess) {
            ProductsPage productsPage = new ProductsPage(driver);
            Assert.assertTrue(productsPage.isMenuButtonDisplayed(), "Products page not displayed after valid login");
        } else {
            Assert.assertEquals(loginPage.getErrorMessage(), expectedError, "Incorrect error message");
        }
    }
}
