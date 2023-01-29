package page.burgermenu;

import base.BasePage;
import element.$;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;

import static element.$.LocatorType.ACCESSIBILITY_ID;
import static element.$.LocatorType.UIAUTOMATOR2;

public class DrawingPage extends BasePage {
    private final $ clearButton = $(ACCESSIBILITY_ID, "test-CLEAR");
    private final $ saveButton = $(ACCESSIBILITY_ID, "test-SAVE");
    private final $ canvas = $(UIAUTOMATOR2,
            "description(\"test-DRAWING-SCREEN\").childSelector(className(\"android.widget.Image\"))");

    public DrawingPage(AndroidDriver driver) {
        super(driver);
    }

    @Override
    @Step("Waiting Drawing Page to Load")
    public void waitPageToLoad() {
        waitPage(canvas, "Drawing Page");
    }

    @Override
    @Step("Verifying Drawing Page")
    public void verifyPage() {
        softAssert.assertTrue(clearButton.isDisplayed());
        softAssert.assertTrue(saveButton.isDisplayed());
        softAssert.assertTrue(canvas.isDisplayed());
        softAssert.assertAll();
    }
}
