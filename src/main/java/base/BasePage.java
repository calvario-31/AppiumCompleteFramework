package base;

import element.$;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;
import org.testng.asserts.SoftAssert;
import utilities.Gestures;
import utilities.Logs;

public abstract class BasePage {
    public static final int DEFAULT_TIME_OUT = 5;
    private final AndroidDriver driver;
    protected final SoftAssert softAssert;
    private int timeOut;
    protected final Logs log = new Logs();
    protected final Gestures gestures;

    protected BasePage(AndroidDriver driver) {
        this.driver = driver;
        softAssert = new SoftAssert();
        gestures = new Gestures(driver);
        timeOut = DEFAULT_TIME_OUT;
    }

    protected BasePage(AndroidDriver driver, int timeOut) {
        this(driver);
        this.timeOut = timeOut;
    }

    protected void waitPage($ mobileElement, String pageName) {
        log.info("Waiting " + pageName + " to load");
        mobileElement.waitForVisibility(timeOut);
        log.info(pageName + " loaded successfully");
    }

    protected $ $($.LocatorType locatorType, String locator) {
        return new $(locatorType, locator, driver, DEFAULT_TIME_OUT);
    }

    protected $ $($.LocatorType locatorType, String locator, int timeOut) {
        return new $(locatorType, locator, driver, timeOut);
    }

    @Step("Pressing back")
    public void pressBack() {
        log.info("Pressing back");
        gestures.pressBack();
    }

    @Step("Scrolling to top")
    public void scrollToTop() {
        log.info("Pressing back");
        gestures.scrollToTop();
    }

    public abstract void waitPageToLoad();

    public abstract void verifyPage();
}
