package com.thinkst.common;

import com.thinkst.pages.LoginPage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Base {

    private static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();
    FileReader reader;
    Properties properties;
    protected String url;

    TestData data = new TestData();

    public static RemoteWebDriver getDriver() {
        return driver.get();
    }

    @Parameters("browser")
    @BeforeMethod
    public void setup(String browser) throws IOException {
        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
            options.addArguments("--headless=new");
            driver.set(new ChromeDriver(options));

        } else if (browser.equalsIgnoreCase("firefox")) {
            FirefoxOptions options = new FirefoxOptions();
            options.setBinary("/usr/bin/firefox");
            options.addArguments("--start-maximized");
            options.addArguments("--headless");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            driver.set(new FirefoxDriver(options));

        } else {
            throw new IllegalArgumentException("Unsupported browser: " + browser);
        }

        readProperties();
        goToUrl();
    }

    public void readProperties() throws IOException {
        reader = new FileReader("config.properties");
        properties = new Properties();
        properties.load(reader);
        url = properties.getProperty("url");
        reader.close();
    }

    public void goToUrl() {
        getDriver().get(url);
    }

    public void loginSuccessfully() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.clickSignIn();
        loginPage.enterEmailAddress(data.existingUser, loginPage.signInEmailAddressTextbox);
        loginPage.enterPassword(data.existingPassword, loginPage.signInPasswordTextBox);
        loginPage.clickSignInButton();
        Assert.assertTrue(loginPage.verifyElementIsDisplayed(loginPage.settingsButton));
    }

    @AfterMethod(alwaysRun = true)
    public void quitDriver() {
        getDriver().quit();
        driver.remove();
    }
}