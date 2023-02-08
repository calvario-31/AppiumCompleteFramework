package page.bars;

import base.BasePage;
import element.$;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;
import utilities.Logs;

import static element.$.LocatorType.ACCESSIBILITY_ID;

public class TitleBarPage extends BasePage {
    private final $ dropZone = $(ACCESSIBILITY_ID, "test-Cart drop zone");
    private final $ toggleIcon = $(ACCESSIBILITY_ID, "test-Toggle");
    private final $ sortIcon = $(ACCESSIBILITY_ID, "test-Modal Selector Button");

    public TitleBarPage(AndroidDriver driver) {
        super(driver);
    }

    @Override
    @Step("Waiting Title Bar Page to Load")
    public void waitPageToLoad() {
        waitPage(dropZone, this.getClass().getSimpleName());
    }

    @Override
    @Step("Verifying title Bar Page")
    public void verifyPage() {
        softAssert.assertTrue(dropZone.isDisplayed());
        softAssert.assertTrue(toggleIcon.isDisplayed());
        softAssert.assertTrue(sortIcon.isDisplayed());
        softAssert.assertAll();
    }

    @Step("Clicking on toggleView option")
    public void clickToggleView() {
        Logs.info("Clicking on toggleView option");
        toggleIcon.click();
    }

    public $ getDropZone() {
        return dropZone;
    }
}
