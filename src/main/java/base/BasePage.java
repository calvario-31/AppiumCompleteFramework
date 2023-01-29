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
    protected final Logs logs = new Logs();
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
        logs.info("Waiting " + pageName + " to load");
        mobileElement.waitForVisibility(timeOut);
        logs.info(pageName + " loaded successfully");
    }

    protected $ $($.LocatorType locatorType, String locator) {
        return new $(locatorType, locator, driver, DEFAULT_TIME_OUT);
    }

    protected $ $($.LocatorType locatorType, String locator, int timeOut) {
        return new $(locatorType, locator, driver, timeOut);
    }

    @Step("Pressing back")
    public void pressBack() {
        logs.info("Pressing back");
        gestures.pressBack();
    }

    @Step("Pressing home")
    public void pressHome() {
        logs.info("Pressing home");
        gestures.pressHome();
    }

    @Step("Scrolling to top")
    public void scrollToTop() {
        logs.info("Pressing back");
        gestures.scrollToTop();
    }

    @Step("Swipe x1:{0} y1:{1} x2:{2} y2:{3}")
    public void swipe(int x1, int y1, int x2, int y2) {
        logs.info(String.format("Swiping using x1: %d y1: %d x2: %d y2: %d", x1, y1, x2, y2));
        gestures.generalSwipeByPercentages(x1, y1, x2, y2);
    }

    @Step("Horizontal Swipe x1:{0} x2:{1}")
    public void horizontalSwipe(int x1, int x2) {
        logs.info(String.format("Horizontal Swipe using x1: %d x2: %d", x1, x2));
        gestures.generalHorizontalSwipeByPercentages(x1, x2);
    }

    @Step("Vertical Swipe y1:{0} y2:{1}")
    public void verticalSwipe(int y1, int y2) {
        logs.info(String.format("Vertical Swipe using y1: %d y2: %d", y1, y2));
        gestures.generalVerticalSwipeByPercentages(y1, y2);
    }

    @Step("Horizontal Swipe x1:{0} x2:{1} y:{y}")
    public void horizontalSwipe(int x1, int x2, int y) {
        logs.info(String.format("Horizontal Swipe using x1: %d x2: %d y: %d", x1, x2, y));
        gestures.generalHorizontalSwipeByPercentages(x1, x2, y);
    }

    @Step("Vertical Swipe y1:{0} y2:{1} x:{x}")
    public void verticalSwipe(int y1, int y2, int x) {
        logs.info(String.format("Vertical Swipe using y1: %d y2: %d x:%d", y1, y2, x));
        gestures.generalVerticalSwipeByPercentages(y1, y2, x);
    }

    public abstract void waitPageToLoad();

    public abstract void verifyPage();
}
