package com.swaglabs.tests;


import io.appium.java_client.AppiumDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import com.swaglabs.utils.DriverUtils;
import org.testng.annotations.BeforeSuite;

import java.net.MalformedURLException;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;

public class BaseTest {
    protected AppiumDriver driver;
    protected static Properties testData;

    @BeforeSuite
    public void loadTestData() throws IOException {
        testData = new Properties();
        testData.load(new FileInputStream("src/test/resources/testdata.properties"));
    }

    @BeforeMethod
    public void setup() throws MalformedURLException {
        driver = DriverUtils.getDriver();
    }

    @AfterMethod
    public void teardown() {
        DriverUtils.quitDriver();
    }
}
