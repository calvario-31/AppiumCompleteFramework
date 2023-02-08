package shopping;

import base.BaseTest;
import data.DataGiver;
import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.bars.TitleBarPage;
import page.bars.TopBarPage;
import page.credentials.LoginPage;
import page.shopping.ShoppingPage;

public class ShoppingTests extends BaseTest {
    private ShoppingPage shoppingPage;
    private TitleBarPage titleBarPage;
    private TopBarPage topBarPage;
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

    @Test(groups = regression)
    public void dragTest() {
        final var list = DataGiver.getItemTitleList();

        shoppingPage.dragToCart(list, titleBarPage.getDropZone());
        topBarPage.verifyItemCount(list.size());
    }

    @Override
    public void initPages(AndroidDriver driver) {
        loginPage = new LoginPage(driver);
        topBarPage = new TopBarPage(driver);
        shoppingPage = new ShoppingPage(driver);
        titleBarPage = new TitleBarPage(driver);
    }
}
