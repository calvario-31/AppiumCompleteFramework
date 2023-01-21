package shopping;

import base.BaseTest;
import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.LoginPage;
import page.ShoppingPage;

public class ShoppingTests extends BaseTest {
    private ShoppingPage shoppingPage;
    private LoginPage loginPage;

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

    @Override
    protected void initPages(AndroidDriver webDriver) {
        loginPage = new LoginPage(webDriver);
        shoppingPage = new ShoppingPage(webDriver);
    }
}
