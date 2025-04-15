package com.thinkst.test;

import com.thinkst.common.Base;
import com.thinkst.common.TestData;
import com.thinkst.pages.LoginPage;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;

@Parameters("browser")
public class loginTests extends Base {
    TestData data = new TestData();

    public void setupTest(String browser) throws IOException {
        super.setup(browser);
    }

    @Test
    @Description("A passing positive test- Verify that users can successfully sign up")
    public void signUpSuccessfully()  {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.clickSignUp(loginPage.signUpLink);
        loginPage.enterUsername(data.username);
        loginPage.enterEmailAddress(data.emailaddress, loginPage.signUpEmailAddressTextBox);
        loginPage.enterPassword(data.password, loginPage.passwordTextbox);
        loginPage.clickSignUp(loginPage.signUpButton);
        Assert.assertTrue(loginPage.verifyElementIsDisplayed(loginPage.settingsButton));
    }

    @Test
    @Description("A passing positive test- Verify registered users can successfully login")
    public void loginSuccessfully()  {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.clickSignIn();
        loginPage.enterEmailAddress(data.existingUser, loginPage.signInEmailAddressTextbox); //Fix Element
        loginPage.enterPassword(data.existingPassword, loginPage.signInPasswordTextBox);
        loginPage.clickSignInButton();
        Assert.assertTrue(loginPage.verifyElementIsDisplayed(loginPage.settingsButton));
    }

    @Test
    @Description("Passing negative test- Display validation message when user enters incorrect password")
    public void validateIncorrectPassword() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.clickSignIn();
        loginPage.enterEmailAddress(data.existingUser, loginPage.signInEmailAddressTextbox);
        loginPage.enterPassword(data.existingPassword+"123", loginPage.signInPasswordTextBox);
        loginPage.clickSignInButton();
        Assert.assertTrue(loginPage.isValidationMessageDisplayed());
    }

    @Test
    @Description("Passing negative test- Display validation message when user enters incorrect username")
    public void validateIncorrectUsername() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.clickSignIn();
        loginPage.enterEmailAddress(data.existingUser+"123", loginPage.signInEmailAddressTextbox); //Fix Element
        loginPage.enterPassword(data.existingPassword, loginPage.signInPasswordTextBox);
        loginPage.clickSignInButton();
        Assert.assertTrue(loginPage.isValidationMessageDisplayed());
    }
}