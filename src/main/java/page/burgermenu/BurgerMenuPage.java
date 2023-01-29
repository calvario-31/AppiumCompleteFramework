package page.burgermenu;

import base.BasePage;
import element.$;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;
import utilities.Logs;

import static element.$.LocatorType.ACCESSIBILITY_ID;

public class BurgerMenuPage extends BasePage {
    private final $ allItemsOption = $(ACCESSIBILITY_ID, "test-DRAWING");
    private final $ webViewOption = $(ACCESSIBILITY_ID, "test-WEBVIEW");
    private final $ qrCodeScannerOption = $(ACCESSIBILITY_ID, "test-QR CODE SCANNER");
    private final $ geoLocationOption = $(ACCESSIBILITY_ID, "test-GEO LOCATION");
    private final $ drawingOption = $(ACCESSIBILITY_ID, "test-DRAWING");
    private final $ aboutOption = $(ACCESSIBILITY_ID, "test-ABOUT");
    private final $ LogsoutOption = $(ACCESSIBILITY_ID, "test-LogsOUT");
    private final $ resetAppStateOption = $(ACCESSIBILITY_ID, "test-RESET APP STATE");

    public BurgerMenuPage(AndroidDriver driver) {
        super(driver);
    }

    @Override
    @Step("Waiting Menu Burger Page to Load")
    public void waitPageToLoad() {
        waitPage(drawingOption, "Burger Menu Page");
    }

    @Override
    @Step("Verifying Menu Burger Page")
    public void verifyPage() {
        softAssert.assertTrue(allItemsOption.isDisplayed());
        softAssert.assertTrue(webViewOption.isDisplayed());
        softAssert.assertTrue(qrCodeScannerOption.isDisplayed());
        softAssert.assertTrue(geoLocationOption.isDisplayed());
        softAssert.assertTrue(aboutOption.isDisplayed());
        softAssert.assertTrue(LogsoutOption.isDisplayed());
        softAssert.assertTrue(resetAppStateOption.isDisplayed());
        softAssert.assertAll();
    }

    @Step("Selecting logout option")
    public void selectLogoutOption() {
        Logs.info("Selecting Logout option");
        LogsoutOption.click();
    }

    @Step("Selecting drawing option")
    public void selectDrawingOption() {
        Logs.info("Selecting drawing option");
        drawingOption.click();
    }

    @Step("Selecting webview option")
    public void selectWebViewOption() {
        Logs.info("Selecting webview option");
        webViewOption.click();
    }
}
