package shopping;

import base.BaseTest;
import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.bars.TopBarPage;
import page.shopping.ItemDetailPage;
import page.shopping.ShoppingPage;

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
    protected void initPages(AndroidDriver driver) {
        shoppingPage = new ShoppingPage(driver);
        itemDetailPage = new ItemDetailPage(driver);
        topBarPage = new TopBarPage(driver);
    }
}
