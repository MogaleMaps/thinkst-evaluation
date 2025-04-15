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

public class SettingsPage extends Base {

    WebDriverWait wait;

    public SettingsPage(RemoteWebDriver driver) {
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    TestData data = new TestData();
    @FindBy(how = How.CSS, using = "a[href='#/login']")
    public WebElement signInLink;
    @FindBy(how = How.CSS, using = "a[href='#/']")
    public WebElement homeButton;
    @FindBy(how = How.CSS, using = "a[href='#/register']")
    public WebElement signUpLink;

    @FindBy(how = How.CSS, using = "a[href='#/settings']")
    public WebElement settingsButton;

    @FindBy(how = How.CSS, using = "a[href='#/editor']")
    public WebElement newArticleLink;

    @FindBy(how = How.CSS, using = "a[href='#/articles/test']")
    public WebElement articleLink;

    @FindBy(how = How.XPATH, using = "//*[@id='app']/nav/div/ul/li[4]/a")
    public WebElement usernameLink;

    @FindBy(how = How.XPATH, using = "//*[@id='app']/div/div[2]/div/div/div[1]/ul/li[2]/a")
    public WebElement favoritesLink;

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

    @FindBy(how = How.XPATH, using = "//*[@id='app']/div/div/div/div/form/fieldset/fieldset[1]/input")
    public WebElement articleTitleTextbox;

    @FindBy(how = How.XPATH, using = "//*[@id='app']/div/div/div/div/form/fieldset/fieldset[2]/input")
    public WebElement articleAboutTextbox;
    @FindBy(how = How.XPATH, using = "//*[@id='app']/div/div/div/div/form/fieldset/fieldset[3]/textarea")
    public WebElement articleBodyTextArea;

    @FindBy(how = How.XPATH, using = "//*[@id='app']/div/div/div/div/form/fieldset/fieldset[4]/input")
    public WebElement articleTagTextbox;

    @FindBy(how = How.XPATH, using = "//*[@id='app']/div/div/div/div/form/button")
    public WebElement publishButton;

    @FindBy(how = How.XPATH, using = "//*[@id='app']/div/div[2]/div[2]/div/span/button")
    public WebElement deleteArticleButton;

    @FindBy(how = How.XPATH, using = "//*[@id='app']/div/div[2]/div[3]/div/div/form/div[1]/textarea")
    public WebElement commentTextArea;

    @FindBy(how = How.XPATH, using = "//*[@id='app']/div/div[2]/div[3]/div/div/form/div[2]/button")
    public WebElement postCommentButton;

    @FindBy(how = How.XPATH, using = "//*[@id='app']/div/div[2]/div[3]/div/div[2]/div[1]/p")
    public WebElement commentFooter;

    @FindBy(how = How.XPATH, using = "//*[@id='app']/div/div/div/div/ul")
    public WebElement errorLabel;

    @FindBy(how = How.XPATH, using = "//*[@id='app']/div/div[2]/div[3]/div/div[2]/div[2]/span[2]/i")
    public WebElement deleteCommentIcon;

    @FindBy(how = How.XPATH, using = "//*[@id='app']/div/div/div/div/button")
    public WebElement logoutButton;

    @FindBy(how = How.XPATH, using = "//*[@id='app']/div/div[2]/div/div[1]/div[2]/div/div/div[1]/div/button")
    public WebElement likeButton;

    @FindBy(how = How.XPATH, using = "//*[@id='app']/div/div[2]/div/div[2]/div/p")
    public WebElement popularTagsSearchTextBox;

    public boolean verifyElementIsDisplayed(WebElement element) {
        try {
            return wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickSignInButton() {
        wait.until(ExpectedConditions.elementToBeClickable(signInButton)).click();
    }

    public void clickSignIn() {
        wait.until(ExpectedConditions.elementToBeClickable(signInLink)).click();
    }

    public void enterEmailAddress(String email, WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).sendKeys(email);
    }

    public void enterPassword(String password, WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).sendKeys(password);
    }
    public void clickUsername() {
        wait.until(ExpectedConditions.visibilityOf(usernameLink)).click();
    }

    public void clickFavorites() {
        wait.until(ExpectedConditions.visibilityOf(favoritesLink)).click();
    }

}