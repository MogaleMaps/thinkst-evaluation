package com.thinkst.pages;

import com.thinkst.common.Base;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SignUpPage extends Base {

    WebDriverWait wait;

    public SignUpPage(RemoteWebDriver driver) {
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @FindBy(how = How.CSS, using = "a[href='#/register']")
    public WebElement signUpLink;

    @FindBy(how = How.CSS, using = "a[href='#/settings']")
    public WebElement settingsButton;

    @FindBy(how = How.XPATH, using = "//*[@id='app']/div/div/div/div/form/fieldset[1]/input")
    public WebElement usernameTextBox;

    @FindBy(how = How.XPATH, using = "//*[@id='app']/div/div/div/div/form/fieldset[2]/input")
    public WebElement signUpEmailAddressTextBox;

    @FindBy(how = How.XPATH, using = "//*[@id='app']/div/div/div/div/form/fieldset[3]/input")
    public WebElement passwordTextbox;

    @FindBy(how = How.XPATH, using = "//*[@id='app']/div/div/div/div/form/button")
    public WebElement signUpButton;

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
    public void enterUsername(String username) {
        wait.until(ExpectedConditions.elementToBeClickable(usernameTextBox)).sendKeys(username);
    }

    public void enterEmailAddress(String email, WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).sendKeys(email);
    }

    public void enterPassword(String password, WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).sendKeys(password);
    }
}