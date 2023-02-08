package deeplinks;

import base.BaseTest;
import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.Test;
import page.shopping.ItemDetailPage;

public class DeepLinkItemDetailTests extends BaseTest {
    private ItemDetailPage itemDetailPage;

    @Test(groups = {regression})
    public void deeplinkTest() {
        triggerDeeplink("swaglabs://swag-item/2");
        itemDetailPage.waitPageToLoad();
        itemDetailPage.verifyPage();
    }

    @Override
    protected void initPages(AndroidDriver driver) {
        itemDetailPage = new ItemDetailPage(driver);
    }
}
