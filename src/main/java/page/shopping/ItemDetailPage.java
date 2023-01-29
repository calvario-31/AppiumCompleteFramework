package page.shopping;

import base.BasePage;
import element.$;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;

import static element.$.LocatorType.ACCESSIBILITY_ID;
import static element.$.LocatorType.UIAUTOMATOR2;

public class ItemDetailPage extends BasePage {
    private final $ backToProductsButton = $(ACCESSIBILITY_ID, "test-BACK TO PRODUCTS");
    private final $ productImage = $(UIAUTOMATOR2,
            "description(\"test-Image Container\").childSelector(className(\"android.widget.ImageView\"))");
    private final $ productTitleLabel = $(UIAUTOMATOR2,
            "description(\"test-Description\").childSelector(index(0))");
    private final $ productDescriptionLabel = $(UIAUTOMATOR2,
            "description(\"test-Description\").childSelector(index(1))");
    private final $ productPriceLabel = $(ACCESSIBILITY_ID, "test-Price");
    private final $ addToCartButton = $(ACCESSIBILITY_ID, "test-ADD TO CART");

    public ItemDetailPage(AndroidDriver driver) {
        super(driver);
    }

    @Override
    @Step("Waiting Item Detail Page to load")
    public void waitPageToLoad() {
        waitPage(productImage, "Item Detail Page");
    }

    @Override
    @Step("Verifying Item Detail Page")
    public void verifyPage() {
        softAssert.assertTrue(backToProductsButton.isDisplayed());
        softAssert.assertTrue(productImage.isDisplayed());
        softAssert.assertTrue(productTitleLabel.isDisplayed());
        softAssert.assertTrue(productDescriptionLabel.isDisplayed());
        softAssert.assertTrue(addToCartButton.scrollTo().isDisplayed()); //scroll
        softAssert.assertTrue(productPriceLabel.isDisplayed());
        softAssert.assertAll();
    }

    @Step("Clicking on back to products")
    public void clickOnBackToProducts() {
        logs.info("Clicking on back to products");
        backToProductsButton.click();
    }

    @Step("Clicking on add to cart")
    public void clickOnAddToCart() {
        logs.info("Clicking on add to cart");
        addToCartButton.scrollTo().click();
    }
}
