package com.thinkst.test;

import com.thinkst.common.Base;
import com.thinkst.common.TestData;
import com.thinkst.pages.BlogPage;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;

@Parameters("browser")
public class blogTests extends Base {
    TestData data = new TestData();

    public void setupTest(String browser) throws IOException {
        super.setup(browser);
    }

    @Test
    @Description("A passing positive test - As a logged out user, I should be able to launch and view the website successfully")
    public void launchWebpageSuccessfully() {
        BlogPage blogPage = new BlogPage(getDriver());
        Assert.assertTrue(blogPage.verifyElementIsDisplayed(blogPage.homeButton));
        Assert.assertTrue(blogPage.verifyElementIsDisplayed(blogPage.signInLink));
        Assert.assertTrue(blogPage.verifyElementIsDisplayed(blogPage.signUpLink));
    }

    @Test
    @Description("A passing positive test- Verify that users can successfully sign up")
    public void signUpSuccessfully()  {
        BlogPage blogPage = new BlogPage(getDriver());
        blogPage.clickSignUp(blogPage.signUpLink);
        blogPage.enterUsername(data.username);
        blogPage.enterEmailAddress(data.emailaddress, blogPage.signUpEmailAddressTextBox);
        blogPage.enterPassword(data.password, blogPage.passwordTextbox);
        blogPage.clickSignUp(blogPage.signUpButton);
        Assert.assertTrue(blogPage.verifyElementIsDisplayed(blogPage.settingsButton));
    }

    @Test
    @Description("A passing positive test- Verify registered users can successfully login")
    public void loginSuccessfully()  {
        BlogPage blogPage = new BlogPage(getDriver());
        blogPage.clickSignIn();
        blogPage.enterEmailAddress(data.existingUser, blogPage.signInEmailAddressTextbox); //Fix Element
        blogPage.enterPassword(data.existingPassword, blogPage.signInPasswordTextBox);
        blogPage.clickSignInButton();
        Assert.assertTrue(blogPage.verifyElementIsDisplayed(blogPage.settingsButton));
    }

    @Test
    @Description("A passing positive test- Verify logged-in users can successfully publish a article")
    public void publishNewArticleSuccessfully() throws InterruptedException {
        loginSuccessfully();
        BlogPage blogPage = new BlogPage(getDriver());
        blogPage.clickNewArticle();
        blogPage.enterArticleTitle();
        blogPage.enterArticleAbout();
        blogPage.enterArticleBody();
        blogPage.enterArticleTag();
        blogPage.clickPublishArticleButton();
        Assert.assertTrue(blogPage.verifyElementIsDisplayed(blogPage.deleteArticleButton));
    }

    @Test
    @Description("A passing positive test- Verify logged-in users can successfully post a comment on an article")
    public void postCommentSuccessfully() {
        loginSuccessfully();
        BlogPage blogPage = new BlogPage(getDriver());
        blogPage.clickArticleLink();
        blogPage.enterComment();
        blogPage.clickPostComment();
        Assert.assertTrue(blogPage.isCommentPosted());
        blogPage.deleteComment();
    }

    @Test
    @Description("Passing negative test- Display validation message when user enters incorrect password")
    public void validateIncorrectPassword() {
        BlogPage blogPage = new BlogPage(getDriver());
        blogPage.clickSignIn();
        blogPage.enterEmailAddress(data.existingUser, blogPage.signInEmailAddressTextbox); //Fix Element
        blogPage.enterPassword(data.existingPassword+"123", blogPage.signInPasswordTextBox);
        blogPage.clickSignInButton();
        Assert.assertTrue(blogPage.isValidationMessageDisplayed());
    }

    @Test
    @Description("Passing negative test- Display validation message when user enters incorrect username")
    public void validateIncorrectUsername() {
        BlogPage blogPage = new BlogPage(getDriver());
        blogPage.clickSignIn();
        blogPage.enterEmailAddress(data.existingUser+"123", blogPage.signInEmailAddressTextbox); //Fix Element
        blogPage.enterPassword(data.existingPassword, blogPage.signInPasswordTextBox);
        blogPage.clickSignInButton();
        Assert.assertTrue(blogPage.isValidationMessageDisplayed());
    }

    //TODO: Users should only see favorited articles shown under 'Favorited Articles'
    @Test(enabled = false)
    @Description("A failing negative- Do not Display any articles under 'Favourited Articles' if I have not favorited an article")
    public void DoNotDisplayArticlesOnFavoritesTab()  {
        BlogPage blogPage = new BlogPage(getDriver());
        blogPage.clickSignIn();
        blogPage.enterEmailAddress(data.existingUser, blogPage.signInEmailAddressTextbox);
        blogPage.enterPassword(data.existingPassword, blogPage.signInPasswordTextBox);
        blogPage.clickSignInButton();
        blogPage.clickUsername();
        blogPage.clickFavorites();
        Assert.assertFalse(blogPage.verifyElementIsDisplayed(blogPage.articleLink));
    }

    //TODO: BUG: Users cannot filter by Popular Tags, enable test once bug is fixed!
    @Test(enabled = false)
    @Description("Failing positive test -- Verify that users are able to filter posts by clicking on 'Popular Tags'")
    public void filterByPopularTags() {
        loginSuccessfully();
        BlogPage blogPage = new BlogPage(getDriver());
        blogPage.enterTagTitle();
    }

    @Test
    @Description("A security test- Verify that users are redirected to login page when attempting to like a post")
    public void redirectUserToLoginWhenLikingPost() {
        BlogPage blogPage = new BlogPage(getDriver());
        blogPage.clickLikeButton();
        Assert.assertTrue(getDriver().getCurrentUrl().contains(url +"#/login"));
    }
}