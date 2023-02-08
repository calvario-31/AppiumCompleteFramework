package credentials;

import base.BaseTest;
import data.DataGiver;
import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.Test;
import page.bars.TopBarPage;
import page.credentials.LoginPage;
import page.shopping.ShoppingPage;

public class LoginTests extends BaseTest {
    private LoginPage loginPage;
    private ShoppingPage shoppingPage;
    private TopBarPage topBarPage;

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

    @Test(groups = {smoke, regression})
    public void validCredentialsTest() {
        final var validCredential = DataGiver.getValidCredential();
        loginPage.fillForm(validCredential.getUsername(), validCredential.getPassword());
        topBarPage.waitPageToLoad();
        topBarPage.verifyPage();
    }

    @Test(groups = {regression})
    public void invalidCredentialsTest() {
        final var invalidCredentials = DataGiver.getInvalidCredential();
        loginPage.fillForm(invalidCredentials.getUsername(), invalidCredentials.getPassword());
        loginPage.verifyErrorMessage(DataGiver.getInvalidErrorMessage());
    }

    @Test(groups = {regression})
    public void lockedUserTest() {
        final var lockedCredentials = DataGiver.getLockedCredential();
        loginPage.fillForm(lockedCredentials.getUsername(), lockedCredentials.getPassword());
        loginPage.verifyErrorMessage(DataGiver.getLockedErrorMessage());
    }

    @Override
    protected void initPages(AndroidDriver driver) {
        topBarPage = new TopBarPage(driver);
        loginPage = new LoginPage(driver);
        shoppingPage = new ShoppingPage(driver);
    }
}
