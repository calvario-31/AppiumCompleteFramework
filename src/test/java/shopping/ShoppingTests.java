package shopping;

import base.BaseTest;
import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.bars.TopBarPage;
import page.credentials.LoginPage;
import page.shopping.ShoppingPage;

public class ShoppingTests extends BaseTest {
    private ShoppingPage shoppingPage;
    private LoginPage loginPage;
    private TopBarPage topBarPage;

    @BeforeMethod(alwaysRun = true, description = setup)
    public void setUp() {
        commonFlows.goToShoppingPage();
    }

    @Test(groups = {smoke})
    public void verifyPageTest() {
        shoppingPage.verifyPage();
        shoppingPage.pressBack();
        loginPage.waitPageToLoad();
        loginPage.verifyPage();
    }

    @Test(groups = {smoke})
    public void dragTest() {
        shoppingPage.addItemByDragging("Sauce Labs Backpack", topBarPage);
        shoppingPage.addItemByDragging("Sauce Labs Bike Light", topBarPage);
        topBarPage.verifyItemCount(2);
    }

    @Override
    protected void initPages(AndroidDriver driver) {
        loginPage = new LoginPage(driver);
        shoppingPage = new ShoppingPage(driver);
        topBarPage = new TopBarPage(driver);
    }
}
