package page.burgermenu;

import base.BasePage;
import element.$;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;
import utilities.Logs;

import static element.$.LocatorType.ACCESSIBILITY_ID;

public class WebViewPage extends BasePage {
    private final $ webInput = $(ACCESSIBILITY_ID, "test-enter a https url here...");
    private final $ goToSiteButton = $(ACCESSIBILITY_ID, "test-GO TO SITE");

    public WebViewPage(AndroidDriver driver) {
        super(driver);
    }

    @Override
    @Step("Waiting WebViewPage to Load")
    public void waitPageToLoad() {
        waitPage(webInput, "WebViewPage");
    }

    @Override
    @Step("Verifying WebViewPage")
    public void verifyPage() {
        softAssert.assertTrue(webInput.isDisplayed());
        softAssert.assertTrue(goToSiteButton.isDisplayed());
        softAssert.assertAll();
    }

    @Step("Navigating to web view with url {0}")
    public void goToWebView(String url) {
        Logs.info("Going to webview %s", url);
        webInput.sendKeys(url);
        goToSiteButton.click();
    }
}
