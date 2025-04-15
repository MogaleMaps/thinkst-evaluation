package com.thinkst.test;

import com.thinkst.common.Base;
import com.thinkst.pages.LandingPage;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;

@Parameters("browser")
public class landingTests extends Base {

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
    @Description("A security test- Verify that users are redirected to login page when attempting to like a post")
    public void redirectUserToLoginWhenLikingPost() {
        LandingPage landingPage = new LandingPage(getDriver());
        landingPage.clickLikeButton();
        Assert.assertTrue(getDriver().getCurrentUrl().contains(url +"#/login"));
    }

    //TODO: BUG: Users cannot filter by Popular Tags, enable test once bug is fixed!
    @Test(enabled = true)
    @Description("Failing positive test -- Verify that users are able to filter posts by clicking on 'Popular Tags'")
    public void filterByPopularTags() {
        loginSuccessfully();
        LandingPage landingPage = new LandingPage(getDriver());
        landingPage.enterTagTitle();
    }
}