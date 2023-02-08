package base;

import element.$;
import gestures.Gestures;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;
import org.testng.asserts.SoftAssert;
import utilities.Logs;

public abstract class BasePage {
    public static final int DEFAULT_TIME_OUT = 5;
    private final AndroidDriver driver;
    protected final SoftAssert softAssert;
    private int timeOut;
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
        Logs.info("Waiting %s to load", pageName);
        mobileElement.waitForVisibility(timeOut);
        Logs.info("%s loaded successfully", pageName);
    }

    protected $ $($.LocatorType locatorType, String locator) {
        return new $(locatorType, locator, driver, DEFAULT_TIME_OUT);
    }

    protected $ $($.LocatorType locatorType, String locator, int timeOut) {
        return new $(locatorType, locator, driver, timeOut);
    }

    @Step("Pressing back")
    public void pressBack() {
        Logs.info("Pressing back");
        gestures.pressBack();
    }

    @Step("Pressing home")
    public void pressHome() {
        Logs.info("Pressing home");
        gestures.pressHome();
    }

    @Step("Scrolling to top")
    public void scrollToTop() {
        Logs.info("Pressing back");
        gestures.scrollToTop();
    }

    @Step("Swipe x1:{0} y1:{1} x2:{2} y2:{3}")
    public void swipe(int x1, int y1, int x2, int y2) {
        Logs.info("Swiping using x1: %d y1: %d x2: %d y2: %d", x1, y1, x2, y2);
        gestures.generalSwipeByPercentages(x1, y1, x2, y2);
    }

    @Step("Swipe init:{0} end:{1}")
    public void swipe(int init, int end, $.Orientation orientation) {
        Logs.info("Swipe using init: %d end: %d", init, end);
        gestures.generalSwipeByPercentages(init, end, orientation);
    }

    @Step("Swipe init:{0} end:{1} aux:{2}")
    public void swipe(int init, int end, int aux, $.Orientation orientation) {
        Logs.info("Swipe using x1: %d x2: %d aux: %d", init, end, aux);
        gestures.generalSwipeByPercentages(init, end, aux, orientation);
    }

    public abstract void waitPageToLoad();

    public abstract void verifyPage();
}
