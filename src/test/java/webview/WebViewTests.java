package webview;

import base.BaseTest;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.burgermenu.WebViewPage;

import java.time.Duration;

public class WebViewTests extends BaseTest {
    private WebViewPage webViewPage;

    @BeforeMethod(alwaysRun = true, description = setup)
    public void setUp() {
        commonFlows.goToWebView();
    }

    @Test(groups = regression)
    public void goToSauceDemoTest() {
        //appium
        webViewPage.goToWebView("https://www.saucedemo.com");
        switchWebContext();

        //selenium
        final var wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("user-name"))).sendKeys("standard_user");

        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inventory_container")));
        Assert.assertTrue(driver.findElement(By.id("react-burger-menu-btn")).isDisplayed());

        //appium again
        switchNativeAppContext();
        webViewPage.pressBack();
        webViewPage.waitPageToLoad();
        webViewPage.verifyPage();
    }

    @Override
    protected void initPages(AndroidDriver driver) {
        webViewPage = new WebViewPage(driver);
    }
}
