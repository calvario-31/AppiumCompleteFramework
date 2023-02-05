package page.shopping;

import base.BasePage;
import element.$;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;
import page.bars.TopBarPage;
import utilities.Logs;

import static element.$.LocatorType.ACCESSIBILITY_ID;
import static element.$.LocatorType.UIAUTOMATOR2;
import static element.$.Orientation.VERTICAL;

public class ShoppingPage extends BasePage {
    private final $ itemContainer = $(ACCESSIBILITY_ID, "test-PRODUCTS");

    private $ getTitle(String title) {
        final var dynamicLocator =
                String.format("description(\"test-Item title\").text(\"%s\")", title);
        return $(UIAUTOMATOR2, dynamicLocator);
    }

    private $ getDragItem(String title) {
        final var dynamicLocator =
                String.format("text(\"%s\").fromParent(" +
                        "description(\"test-Drag Handle\")." +
                        "childSelector(className(\"android.widget.TextView\")))", title);
        return $(UIAUTOMATOR2, dynamicLocator);
    }

    public ShoppingPage(AndroidDriver driver) {
        super(driver);
    }

    @Override
    @Step("Waiting Shopping Page to Load")
    public void waitPageToLoad() {
        waitPage(itemContainer, "Shopping Page");
    }

    @Override
    @Step("Verifying Login Page")
    public void verifyPage() {
        softAssert.assertTrue(itemContainer.isDisplayed());
        softAssert.assertAll();
    }

    @Step("Navigation to item detail {0}")
    public void goToItemDetail(String title) {
        Logs.info("Navigating to item detail %s", title);
        getTitle(title).scrollTo(VERTICAL).click();
    }

    @Step("Dragging item {0}")
    public void addItemByDragging(String title, TopBarPage topBarPage) {
        Logs.info("Dragging item: %s", title);
        getDragItem(title).dragTo(topBarPage.getDropZone());
    }
}
