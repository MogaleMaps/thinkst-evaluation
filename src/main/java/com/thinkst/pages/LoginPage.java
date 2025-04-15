package com.thinkst.pages;

import com.thinkst.common.Base;
import com.thinkst.common.TestData;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends Base {

    WebDriverWait wait;

    public LoginPage(RemoteWebDriver driver) {
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    TestData data = new TestData();
    @FindBy(how = How.CSS, using = "a[href='#/login']")
    public WebElement signInLink;
    @FindBy(how = How.CSS, using = "a[href='#/register']")
    public WebElement signUpLink;

    @FindBy(how = How.CSS, using = "a[href='#/settings']")
    public WebElement settingsButton;

    @FindBy(how = How.XPATH, using = "//*[@id='app']/div/div/div/div/form/fieldset[1]/input")
    public WebElement usernameTextBox;

    @FindBy(how = How.XPATH, using = "//*[@id='app']/div/div/div/div/form/fieldset[2]/input")
    public WebElement signUpEmailAddressTextBox;

    @FindBy(how = How.XPATH, using = "//*[@id='app']/div/div/div/div/form/fieldset[1]/input")
    public WebElement signInEmailAddressTextbox;

    @FindBy(how = How.XPATH, using = "//*[@id='app']/div/div/div/div/form/fieldset[2]/input")
    public WebElement signInPasswordTextBox;

    @FindBy(how = How.XPATH, using = "//*[@id='app']/div/div/div/div/form/fieldset[3]/input")
    public WebElement passwordTextbox;

    @FindBy(how = How.XPATH, using = "//*[@id='app']/div/div/div/div/form/button")
    public WebElement signUpButton;

    @FindBy(how = How.XPATH, using = "//*[@id='app']/div/div/div/div/form/button")
    public WebElement signInButton;

    @FindBy(how = How.XPATH, using = "//*[@id='app']/div/div/div/div/ul")
    public WebElement errorLabel;


    public boolean verifyElementIsDisplayed(WebElement element) {
        try {
            return wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickSignUp(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    public void clickSignInButton() {
        wait.until(ExpectedConditions.elementToBeClickable(signInButton)).click();
    }

    public void clickSignIn() {
        wait.until(ExpectedConditions.elementToBeClickable(signInLink)).click();
    }

    public void enterUsername(String username) {
        wait.until(ExpectedConditions.elementToBeClickable(usernameTextBox)).sendKeys(username);
    }

    public void enterEmailAddress(String email, WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).sendKeys(email);
    }

    public void enterPassword(String password, WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).sendKeys(password);
    }

    public boolean isValidationMessageDisplayed() {
        return wait.until(ExpectedConditions.visibilityOf(errorLabel)).getText().contains("body User not found");
    }
}