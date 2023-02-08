package page.credentials;

import base.BasePage;
import element.$;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;
import org.testng.Assert;
import utilities.Logs;

import static element.$.LocatorType.ACCESSIBILITY_ID;
import static element.$.LocatorType.UIAUTOMATOR2;
import static element.$.Orientation.VERTICAL;

public class LoginPage extends BasePage {
    private final $ usernameInput = $(ACCESSIBILITY_ID, "test-Username");
    private final $ passwordInput = $(ACCESSIBILITY_ID, "test-Password");
    private final $ loginButton = $(ACCESSIBILITY_ID, "test-LOGIN");
    private final $ correctUsername = $(ACCESSIBILITY_ID, "test-standard_user");
    private final $ lockedOutUsername = $(ACCESSIBILITY_ID, "test-locked_out_user");
    private final $ errorMessageLabel = $(ACCESSIBILITY_ID, "test-Error message");
    private final $ errorMessage = $(ACCESSIBILITY_ID, "test-Error message");


    private $ getErrorMessage(String messageText) {
        final var uiautomator2Locator = String.format("text(\"%s\")", messageText);
        return $(UIAUTOMATOR2, uiautomator2Locator);
    }

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

    @Step("Filling username with {0} and password {1}")
    public void fillForm(String username, String password) {
        Logs.info("Filling username %s", username);
        usernameInput.sendKeys(username);

        Logs.info("Filling password %s", password);
        passwordInput.sendKeys(password);

        clickOnLogin();
    }

    @Step("Verifying error message is displayed")
    public void verifyErrorMessage(String message) {
        Logs.info("Verifying error message is displayed");
        Assert.assertTrue(errorMessage.isDisplayed());
        Assert.assertTrue(getErrorMessage(message).isDisplayed());
    }

    @Step("Tapping on correct username")
    public void correctTapLogin() {
        Logs.info("Tapping on correct username");
        correctUsername.scrollTo(VERTICAL).click();
        clickOnLogin();
    }

    @Step("Tapping on locked username")
    public void incorrectTapLogin() {
        Logs.info("Tapping on locked username");
        lockedOutUsername.scrollTo(VERTICAL).click();
        clickOnLogin();
    }

    @Step("Verifying error message is displayed")
    public void verifyErrorMessage() {
        Logs.info("Verifying error message is displayed");
        Assert.assertTrue(errorMessageLabel.isDisplayed());
    }

    @Step("Clicking on login")
    public void clickOnLogin() {
        Logs.info("Clicking on login");
        loginButton.click();
    }
}
