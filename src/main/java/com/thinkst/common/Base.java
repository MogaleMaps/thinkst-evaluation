package com.thinkst.common;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Base {

    public static RemoteWebDriver driver;

    FileReader reader;
    Properties properties;
    protected String url;


    public static RemoteWebDriver getDriver() {
        return driver;
    }


    @Parameters("browser")
    @BeforeMethod
    public void setup(@Optional("chrome") String browser) throws IOException {
        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            //options.addArguments("--headless=new");
            options.addArguments("--start-maximized");
            options.addArguments("--window-size=1920,1080");
            driver = new ChromeDriver(options);

        } else if (browser.equalsIgnoreCase("firefox")) {
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--headless");
            options.addArguments("--width=1920");
            options.addArguments("--height=1080");
            driver = new FirefoxDriver(options);

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
        driver.get(url);
    }

    @AfterMethod(alwaysRun = true)
    public void quitDriver() {
        driver.quit();
    }
}