package credentials;

import base.BaseTest;
import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.Test;
import page.credentials.LoginPage;
import page.shopping.ShoppingPage;

public class LoginTests extends BaseTest {
    private LoginPage loginPage;
    private ShoppingPage shoppingPage;

    @Test(groups = {smoke})
    public void correctLoginTapTest() {
        loginPage.correctTapLogin();
        shoppingPage.waitPageToLoad();
        shoppingPage.verifyPage();
    }

    @Test(groups = {regression})
    public void lockedOutUserTapTest() {
        loginPage.incorrectTapLogin();
        loginPage.verifyErrorMessage();
    }

    @Override
    protected void initPages(AndroidDriver driver) {
        loginPage = new LoginPage(driver);
        shoppingPage = new ShoppingPage(driver);
    }
}
