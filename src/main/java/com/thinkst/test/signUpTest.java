package com.thinkst.test;

import com.thinkst.common.Base;
import com.thinkst.common.TestData;
import com.thinkst.pages.SignUpPage;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;

@Parameters("browser")
public class signUpTest extends Base {
    TestData data = new TestData();

    public void setupTest(String browser) throws IOException {
        super.setup(browser);
    }

    @Test
    @Description("A passing positive test- Verify that users can successfully sign up")
    public void signUpSuccessfully()  {
        SignUpPage SignUpPage = new SignUpPage(getDriver());
        SignUpPage.clickSignUp(SignUpPage.signUpLink);
        SignUpPage.enterUsername(data.username);
        SignUpPage.enterEmailAddress(data.emailaddress, SignUpPage.signUpEmailAddressTextBox);
        SignUpPage.enterPassword(data.password, SignUpPage.passwordTextbox);
        SignUpPage.clickSignUp(SignUpPage.signUpButton);
        Assert.assertTrue(SignUpPage.verifyElementIsDisplayed(SignUpPage.settingsButton));
    }
}