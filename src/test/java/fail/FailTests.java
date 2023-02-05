package fail;

import base.BaseTest;
import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.Test;
import page.shopping.ShoppingPage;

public class FailTests extends BaseTest {
    private ShoppingPage shoppingPage;

    @Test
    public void failTest() {
        shoppingPage.verifyPage();
    }

    @Override
    protected void initPages(AndroidDriver driver) {
        shoppingPage = new ShoppingPage(driver);
    }
}
