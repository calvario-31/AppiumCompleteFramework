package utilities;

import io.appium.java_client.android.AndroidDriver;
import page.bars.TopBarPage;
import page.burgermenu.BurgerMenuPage;
import page.burgermenu.DrawingPage;
import page.credentials.LoginPage;
import page.shopping.ItemDetailPage;
import page.shopping.ShoppingPage;

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

        shoppingPage.goToItemDetail(productName);
        itemDetailPage.waitPageToLoad();
    }

    public void goToDrawingPage() {
        final var burgerMenuPage = new BurgerMenuPage(driver);
        final var drawingPage = new DrawingPage(driver);

        goToShoppingPage();
        openBurgerMenu();

        burgerMenuPage.selectDrawingOption();
        drawingPage.waitPageToLoad();
    }

    private void openBurgerMenu() {
        final var topBarPage = new TopBarPage(driver);
        final var burgerMenuPage = new BurgerMenuPage(driver);

        topBarPage.clickMenuBurger();
        burgerMenuPage.waitPageToLoad();
    }
}
