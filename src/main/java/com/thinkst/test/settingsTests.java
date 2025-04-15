package com.thinkst.test;

import com.thinkst.common.Base;
import com.thinkst.common.TestData;
import com.thinkst.pages.SettingsPage;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;

@Parameters("browser")
public class settingsTests extends Base {

    TestData data = new TestData();
    public void setupTest(String browser) throws IOException {
        super.setup(browser);
    }

    //TODO: Users should only see favorited articles shown under 'Favorited Articles'
    @Test(enabled = false)
    @Description("A failing negative- Do not Display any articles under 'Favourited Articles' if I have not favorited an article")
    public void DoNotDisplayArticlesOnFavoritesTab()  {
        SettingsPage settingsPage = new SettingsPage(getDriver());
        settingsPage.clickSignIn();
        settingsPage.enterEmailAddress(data.existingUser, settingsPage.signInEmailAddressTextbox);
        settingsPage.enterPassword(data.existingPassword, settingsPage.signInPasswordTextBox);
        settingsPage.clickSignInButton();
        settingsPage.clickUsername();
        settingsPage.clickFavorites();
        Assert.assertFalse(settingsPage.verifyElementIsDisplayed(settingsPage.articleLink));
    }
}