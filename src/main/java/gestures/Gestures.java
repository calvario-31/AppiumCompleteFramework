package gestures;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import utilities.Logs;

import java.time.Duration;
import java.util.List;

public class Gestures {
    private final Actions actions;
    protected final AndroidDriver driver;

    public Gestures(AndroidDriver driver) {
        this.driver = driver;
        actions = new Actions(driver);
    }

    public WebElement verticalScrollInto(String locatorString) {
        Logs.debug(String.format("Vertical scrolling into %s", locatorString));
        final var uiAutomatorText =
                String.format("UiScrollable(scrollable(true)).setAsVerticalList().scrollIntoView(%s)", locatorString);
        return driver.findElement(new AppiumBy.ByAndroidUIAutomator(uiAutomatorText));
    }

    private WebElement horizontalScrollInto(String locatorString) {
        Logs.debug(String.format("Horizontal scrolling into %s", locatorString));
        final var uiAutomatorText =
                String.format("UiScrollable(scrollable(true)).setAsHorizontalList().scrollIntoView(%s)", locatorString);
        return driver.findElement(new AppiumBy.ByAndroidUIAutomator(uiAutomatorText));
    }

    public void generalSwipeByPercentages(int x1, int y1, int x2, int y2) {
        swipe(
                getPointUsingPercentages(x1, y1),
                getPointUsingPercentages(x2, y2),
                0,
                1500
        );
    }

    public void generalVerticalSwipeByPercentages(int y1, int y2) {
        generalSwipeByPercentages(50, y1, 50, y2);
    }

    public void generalHorizontalSwipeByPercentages(int x1, int x2) {
        generalSwipeByPercentages(x1, 50, x2, 50);
    }

    public void generalVerticalSwipeByPercentages(int y1, int y2, int x) {
        generalSwipeByPercentages(x, y1, x, y2);
    }

    public void generalHorizontalSwipeByPercentages(int x1, int x2, int y) {
        generalSwipeByPercentages(x1, y, x2, y);
    }

    public void dragOneItemToAnother(WebElement originElement, WebElement destinyElement) {
        final var originMiddlePoint = getMiddlePoint(originElement);
        final var destinyMiddlePoint = getMiddlePoint(destinyElement);

        swipe(originMiddlePoint, destinyMiddlePoint, 500, 1500);
    }

    public void scrollToTop() {
        final var uiAutomatorText = "new UiScrollable(scrollable(true)).scrollToBeginning(10)";
        driver.findElement(new AppiumBy.ByAndroidUIAutomator(uiAutomatorText));
    }

    public void pressBack() {
        driver.pressKey(new KeyEvent(AndroidKey.BACK));
    }

    public void pressHome() {
        driver.pressKey(new KeyEvent(AndroidKey.HOME));
    }

    public void doubleClick(WebElement webElement) {
        actions.moveToElement(webElement);
        actions.doubleClick();
        actions.perform();
    }

    public void longTap(WebElement webElement, int duration) {
        actions.moveToElement(webElement);
        actions.clickAndHold();
        actions.pause(Duration.ofSeconds(duration));
        actions.release();
        actions.perform();
    }

    private void swipe(Point originPoint, Point destinyPoint, int secondsPauseMillis, int secondsMoveMillis) {
        Logs.debug(String.format("Swiping from %s to %s", originPoint, destinyPoint));

        final var finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        final var sequence = new Sequence(finger, 1);

        finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg());
        finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg());

        //move the finger down to the element or starting position
        sequence.addAction(finger.createPointerMove(Duration.ZERO,
                PointerInput.Origin.viewport(), originPoint));

        //tap the element
        sequence.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));

        if (secondsPauseMillis != 0) {
            //wait to make it long press
            sequence.addAction(new Pause(finger, Duration.ofMillis(secondsPauseMillis)));
        }

        //move to the element
        sequence.addAction(finger.createPointerMove(Duration.ofMillis(secondsMoveMillis),
                PointerInput.Origin.viewport(), destinyPoint));

        //move the finger up of the screen
        sequence.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(List.of(sequence));
    }

    private Point getMiddlePoint(WebElement webElement) {
        final var location = webElement.getLocation();
        final var rect = webElement.getRect();
        final var halfWidth = rect.width / 2;
        final var halfHeight = rect.height / 2;

        final var x = location.getX() + halfWidth;
        final var y = location.getY() + halfHeight;

        return new Point(x, y);
    }

    private Point getPointUsingPercentages(int percentageX, int percentageY) {
        final var size = driver.manage().window().getSize();

        final var x = (int) (size.width * (percentageX / 100.0)); //transform from % to number according to the width
        final var y = (int) (size.height * (percentageY / 100.0)); //transform from % to number according to the height

        return new Point(x, y);
    }
}