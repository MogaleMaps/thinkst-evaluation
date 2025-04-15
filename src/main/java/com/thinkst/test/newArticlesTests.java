package com.thinkst.test;

import com.thinkst.common.Base;
import com.thinkst.pages.NewArticlesPage;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;

@Parameters("browser")
public class newArticlesTests extends Base {

    public void setupTest(String browser) throws IOException {
        super.setup(browser);
    }

    @Test
    @Description("A passing positive test- Verify logged-in users can successfully publish a article")
    public void publishNewArticleSuccessfully() throws InterruptedException {
        loginSuccessfully();
        NewArticlesPage newArticlesPage = new NewArticlesPage(getDriver());
        newArticlesPage.clickNewArticle();
        newArticlesPage.enterArticleTitle();
        newArticlesPage.enterArticleAbout();
        newArticlesPage.enterArticleBody();
        newArticlesPage.enterArticleTag();
        newArticlesPage.clickPublishArticleButton();
        Assert.assertTrue(newArticlesPage.verifyElementIsDisplayed(newArticlesPage.deleteArticleButton));
    }

    @Test
    @Description("A passing positive test- Verify logged-in users can successfully post a comment on an article")
    public void postCommentSuccessfully() {
        loginSuccessfully();
        NewArticlesPage newArticlesPage = new NewArticlesPage(getDriver());
        newArticlesPage.clickArticleLink();
        newArticlesPage.enterComment();
        newArticlesPage.clickPostComment();
        Assert.assertTrue(newArticlesPage.isCommentPosted());
        newArticlesPage.deleteComment();
    }
}