package page;

import base.BasePage;
import element.$;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;

import static element.$.LocatorType.ACCESSIBILITY_ID;
import static element.$.LocatorType.UIAUTOMATOR2;

public class ShoppingPage extends BasePage {
    private final $ itemContainer = $(ACCESSIBILITY_ID, "test-PRODUCTS");

    private $ getTitle(String title) {
        final var dynamicLocator =
                String.format("description(\"test-Item title\").text(\"%s\")", title);
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
        log.info(String.format("Navigating to item detail %s", title));
        getTitle(title).scrollTo().click();
    }
}
