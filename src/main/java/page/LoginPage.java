package page;

import base.BasePage;
import element.$;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;
import org.testng.Assert;

import static element.$.LocatorType.ACCESSIBILITY_ID;

public class LoginPage extends BasePage {
    private final $ usernameInput = $(ACCESSIBILITY_ID, "test-Username");
    private final $ passwordInput = $(ACCESSIBILITY_ID, "test-Password");
    private final $ loginButton = $(ACCESSIBILITY_ID, "test-LOGIN");
    private final $ correctUsername = $(ACCESSIBILITY_ID, "test-standard_user");
    private final $ lockedOutUsername = $(ACCESSIBILITY_ID, "test-locked_out_user");
    private final $ errorMessageLabel = $(ACCESSIBILITY_ID, "test-Error message");

    public LoginPage(AndroidDriver driver) {
        super(driver);
    }

    @Override
    @Step("Waiting Login Page to Load")
    public void waitPageToLoad() {
        waitPage(usernameInput, "Login Page");
    }

    @Override
    @Step("Verifying Login Page")
    public void verifyPage() {
        softAssert.assertTrue(usernameInput.isDisplayed());
        softAssert.assertTrue(passwordInput.isDisplayed());
        softAssert.assertTrue(loginButton.isDisplayed());
        softAssert.assertAll();
    }

    @Step("Tapping on correct username")
    public void correctTapLogin() {
        log.info("Tapping on correct username");
        correctUsername.scrollTo().click();
        loginButton.click();
    }

    @Step("Tapping on locked username")
    public void incorrectTapLogin() {
        log.info("Tapping on locked username");
        lockedOutUsername.scrollTo().click();
        loginButton.click();
    }

    @Step("Verifying error message is displayed")
    public void verifyErrorMessage() {
        log.info("Verifying error message is displayed");
        Assert.assertTrue(errorMessageLabel.isDisplayed());
    }
}
