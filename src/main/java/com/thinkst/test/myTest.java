package com.thinkst.test;

import com.thinkst.common.Base;
import com.thinkst.common.TestData;
import com.thinkst.pages.LandingPage;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;

@Parameters("browser")
public class myTest extends Base {
    TestData data = new TestData();

    public void setupTest(String browser) throws IOException {
        super.setup(browser);
    }

    @Test
    @Description("A passing positive test - As a logged out user, I should be able to launch and view the website successfully")
    public void launchWebpageSuccessfully() {
        LandingPage landingPage = new LandingPage(getDriver());
        Assert.assertTrue(landingPage.verifyElementIsDisplayed(landingPage.homeButton));
        Assert.assertTrue(landingPage.verifyElementIsDisplayed(landingPage.signInLink));
        Assert.assertTrue(landingPage.verifyElementIsDisplayed(landingPage.signUpLink));
    }

    @Test
    @Description("A passing positive test- Verify that users can successfully sign up")
    public void signUpSuccessfully()  {
        LandingPage landingPage = new LandingPage(getDriver());
        landingPage.clickSignUp(landingPage.signUpLink);
        landingPage.enterUsername(data.username);
        landingPage.enterEmailAddress(data.emailaddress, landingPage.signUpEmailAddressTextBox);
        landingPage.enterPassword(data.password,landingPage.passwordTextbox);
        landingPage.clickSignUp(landingPage.signUpButton);
        Assert.assertTrue(landingPage.verifyElementIsDisplayed(landingPage.settingsButton));
    }

    @Test
    @Description("A passing positive test- Verify registered users can successfully login")
    public void loginSuccessfully()  {
        LandingPage landingPage = new LandingPage(getDriver());
        landingPage.clickSignIn();
        landingPage.enterEmailAddress(data.existingUser, landingPage.signInEmailAddressTextbox); //Fix Element
        landingPage.enterPassword(data.existingPassword,landingPage.signInPasswordTextBox);
        landingPage.clickSignInButton();
        Assert.assertTrue(landingPage.verifyElementIsDisplayed(landingPage.settingsButton));
    }

    @Test
    @Description("A passing positive test- Verify logged-in users can successfully publish a article")
    public void publishNewArticleSuccessfully() throws InterruptedException {
        loginSuccessfully();
        LandingPage landingPage = new LandingPage(getDriver());
        landingPage.clickNewArticle();
        landingPage.enterArticleTitle();
        landingPage.enterArticleAbout();
        landingPage.enterArticleBody();
        landingPage.enterArticleTag();
        landingPage.clickPublishArticleButton();
        Assert.assertTrue(landingPage.verifyElementIsDisplayed(landingPage.deleteArticleButton));
    }

    @Test
    @Description("A passing positive test- Verify logged-in users can successfully post a comment on an article")
    public void postCommentSuccessfully() {
        loginSuccessfully();
        LandingPage landingPage = new LandingPage(getDriver());
        landingPage.clickArticleLink();
        landingPage.enterComment();
        landingPage.clickPostComment();
        Assert.assertTrue(landingPage.isCommentPosted());
        landingPage.deleteComment();
    }

    @Test
    @Description("A passing positive test- Verify that users can successfully logout")
    public void logoutSuccessfully() {
        loginSuccessfully();
        LandingPage landingPage = new LandingPage(getDriver());
        landingPage.clickSettings();
        landingPage.clickLogout();
        Assert.assertFalse(landingPage.verifyElementIsDisplayed(landingPage.settingsButton));
    }

    @Description("Passing negative test- Display validation message when user enters incorrect password")
    public void validateIncorrectPassword() {
        LandingPage landingPage = new LandingPage(getDriver());
        landingPage.clickSignIn();
        landingPage.enterEmailAddress(data.existingUser, landingPage.signInEmailAddressTextbox); //Fix Element
        landingPage.enterPassword(data.existingPassword+"123",landingPage.signInPasswordTextBox);
        landingPage.clickSignInButton();
        Assert.assertTrue(landingPage.isValidationMessageDisplayed());
    }

    @Test(enabled = false)
    @Description("Passing negative test- Display validation message when user enters incorrect username")
    public void validateIncorrectUsername() {
        LandingPage landingPage = new LandingPage(getDriver());
        landingPage.clickSignIn();
        landingPage.enterEmailAddress(data.existingUser+"123", landingPage.signInEmailAddressTextbox); //Fix Element
        landingPage.enterPassword(data.existingPassword,landingPage.signInPasswordTextBox);
        landingPage.clickSignInButton();
        Assert.assertTrue(landingPage.isValidationMessageDisplayed());
    }

    //TODO: Fix the below ASAP
    @Test(enabled = false)
    @Description("A failing negative- Display validation message when logging in without username and password ")
    public void signUpWithoutUsername()  {
        LandingPage landingPage = new LandingPage(getDriver());
        landingPage.clickSignIn();
        landingPage.enterEmailAddress("", landingPage.signInEmailAddressTextbox); //Fix Element
        landingPage.enterPassword("",landingPage.signInPasswordTextBox);
        landingPage.clickSignInButton();
        Assert.assertTrue(landingPage.isValidationMessageDisplayed());
    }

    @Test(enabled = false)
    @Description("Failing positive test -- Verify that users are able to filter posts by clicking on 'Popular Tags'")
    public void filterByPopularTags() {
        loginSuccessfully();
        LandingPage landingPage = new LandingPage(getDriver());
        landingPage.enterTagTitle();
    }

    @Test(enabled = false)
    @Description("A security test- Verify that users are redirected to login page when attempting to like a post")
    public void redirectUserToLoginWhenLikingPost() {
        LandingPage landingPage = new LandingPage(getDriver());
        landingPage.clickLikeButton();
        Assert.assertTrue(getDriver().getCurrentUrl().contains(data.baseUrl+"#/login"));
    }
}