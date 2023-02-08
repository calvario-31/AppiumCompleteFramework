package page.shopping;

import base.BasePage;
import element.$;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;
import utilities.Logs;

import java.util.List;

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

    private $ getDragHandler(String title) {
        final var uiautomator2String =
                String.format(
                        "description(\"test-Item title\").text(\"%s\")." +
                                "fromParent(description(\"test-Drag Handle\")." +
                                "childSelector(className(\"android.widget.TextView\")))", title);
        return $(UIAUTOMATOR2, uiautomator2String);
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

    @Step("Drag {0} to cart")
    public void dragToCart(List<String> listItems, $ dropZone) {
        for (var element : listItems) {
            getDragHandler(element).scrollTo(VERTICAL).dragTo(dropZone);
        }
    }
}
