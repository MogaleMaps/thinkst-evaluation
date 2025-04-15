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

public class NewArticlesPage extends Base {

    WebDriverWait wait;

    public NewArticlesPage(RemoteWebDriver driver) {
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    TestData data = new TestData();
    @FindBy(how = How.CSS, using = "a[href='#/editor']")
    public WebElement newArticleLink;

    @FindBy(how = How.CSS, using = "a[href='#/articles/test']")
    public WebElement articleLink;

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
    @FindBy(how = How.XPATH, using = "//*[@id='app']/div/div[2]/div[3]/div/div[2]/div[2]/span[2]/i")
    public WebElement deleteCommentIcon;

    public boolean verifyElementIsDisplayed(WebElement element) {
        try {
            return wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickNewArticle() {
        wait.until(ExpectedConditions.elementToBeClickable(newArticleLink)).click();
    }

    public void enterArticleTitle() {
        wait.until(ExpectedConditions.elementToBeClickable(articleTitleTextbox)).sendKeys(data.articleTitle);
    }

    public void enterArticleAbout() {
        wait.until(ExpectedConditions.elementToBeClickable(articleAboutTextbox)).sendKeys(data.articleAbout);
    }

    public void enterArticleBody() {
        wait.until(ExpectedConditions.elementToBeClickable(articleBodyTextArea)).sendKeys(data.articleBody);
    }

    public void enterArticleTag() {
        wait.until(ExpectedConditions.elementToBeClickable(articleTagTextbox)).sendKeys(data.articleTag);
    }

    public void clickPublishArticleButton() {
        wait.until(ExpectedConditions.elementToBeClickable(publishButton)).click();
    }

    public void clickArticleLink() {
        wait.until(ExpectedConditions.elementToBeClickable(articleLink)).click();
    }

    public void enterComment() {
        wait.until(ExpectedConditions.elementToBeClickable(commentTextArea)).sendKeys(data.comment);
    }

    public void clickPostComment() {
        wait.until(ExpectedConditions.elementToBeClickable(postCommentButton)).click();
    }

    public boolean isCommentPosted() {
        return wait.until(ExpectedConditions.visibilityOf(commentFooter)).getText().contains(data.comment);
    }

    public void deleteComment() {
        wait.until(ExpectedConditions.visibilityOf(deleteCommentIcon)).click();
    }

}