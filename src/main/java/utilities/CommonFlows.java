package utilities;

import io.appium.java_client.android.AndroidDriver;
import page.ItemDetailPage;
import page.LoginPage;
import page.ShoppingPage;

public class CommonFlows {
    private final AndroidDriver driver;

    public CommonFlows(AndroidDriver driver) {
        this.driver = driver;
    }

    public void goToShoppingPage() {
        final var loginPage = new LoginPage(driver);
        final var shoppingPage = new ShoppingPage(driver);

        loginPage.correctTapLogin();
        shoppingPage.waitPageToLoad();
    }

    public void goToItemDetail(String productName) {
        final var shoppingPage = new ShoppingPage(driver);
        final var itemDetailPage = new ItemDetailPage(driver);

        goToShoppingPage();
        shoppingPage.goToItemDetail(productName);
        itemDetailPage.waitPageToLoad();
    }
}
