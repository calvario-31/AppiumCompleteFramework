package shopping;

import base.BaseTest;
import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.ItemDetailPage;
import page.ShoppingPage;
import page.TopBarPage;

public class ItemDetailTests extends BaseTest {
    private ItemDetailPage itemDetailPage;
    private ShoppingPage shoppingPage;
    private TopBarPage topBarPage;

    @BeforeMethod(alwaysRun = true, description = setup)
    public void setUp() {
        commonFlows.goToItemDetail("Sauce Labs Onesie");
    }

    @Test(groups = {regression})
    public void backToProductsTest() {
        itemDetailPage.verifyPage();
        itemDetailPage.clickOnBackToProducts();
        shoppingPage.waitPageToLoad();
        shoppingPage.verifyPage();
    }

    @Test(groups = {smoke, regression})
    public void itemDetailNavigationTest() {
        itemDetailPage.verifyPage();
        itemDetailPage.pressBack();
        shoppingPage.waitPageToLoad();
        shoppingPage.verifyPage();
    }

    @Test(groups = {smoke, regression})
    public void addToCartTest() {
        itemDetailPage.verifyPage();
        itemDetailPage.clickOnAddToCart();
        itemDetailPage.scrollToTop();
        itemDetailPage.clickOnBackToProducts();
        shoppingPage.waitPageToLoad();
        shoppingPage.verifyPage();
        topBarPage.verifyItemCount(1);
    }

    @Override
    protected void initPages(AndroidDriver webDriver) {
        shoppingPage = new ShoppingPage(webDriver);
        itemDetailPage = new ItemDetailPage(webDriver);
        topBarPage = new TopBarPage(webDriver);
    }
}
