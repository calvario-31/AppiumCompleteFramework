package page;

import base.BasePage;
import element.$;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;

import static element.$.LocatorType.ACCESSIBILITY_ID;
import static element.$.LocatorType.UIAUTOMATOR2;

public class TopBarPage extends BasePage {
    private final $ menuOption = $(ACCESSIBILITY_ID, "test-Menu");
    private final $ cartOption = $(ACCESSIBILITY_ID, "test-Cart");
    private final $ itemCountLabel = $(UIAUTOMATOR2,
            "description(\"test-Cart\").childSelector(className(\"android.widget.TextView\"))");

    public TopBarPage(AndroidDriver driver) {
        super(driver);
    }

    @Override
    @Step("Waiting Top Bar to Load")
    public void waitPageToLoad() {
        waitPage(menuOption, "Top Bar Page");
    }

    @Override
    @Step("Verifying Top Bar")
    public void verifyPage() {
        softAssert.assertTrue(menuOption.isDisplayed());
        softAssert.assertTrue(cartOption.isDisplayed());
        softAssert.assertAll();
    }

    @Step("Verifying item count")
    public void verifyItemCount(int expectedCount) {
        final var actualItemCount = Integer.parseInt(itemCountLabel.getText());
        log.info("Verifying item count");
        softAssert.assertEquals(actualItemCount, expectedCount);
    }
}
