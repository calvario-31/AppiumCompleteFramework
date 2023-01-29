package utilities;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.time.Duration;
import java.util.List;

public class Gestures {
    private final AndroidDriver driver;
    private final Actions actions;
    private final Logs logs = new Logs();

    public Gestures(AndroidDriver driver) {
        this.driver = driver;
        actions = new Actions(driver);
    }

    public WebElement scrollInto(String locatorString) {
        final var uiAutomatorText =
                String.format("UiScrollable(scrollable(true)).scrollIntoView(%s)", locatorString);
        return driver.findElement(new AppiumBy.ByAndroidUIAutomator(uiAutomatorText));
    }

    public void generalSwipeByPercentages(int x1, int y1, int x2, int y2) {
        final var finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        final var sequence = new Sequence(finger, 1);

        final var firstXPercentage = x1 / 100.0;
        final var firstYPercentage = y1 / 100.0;
        final var secondXPercentage = x2 / 100.0;
        final var secondYPercentage = y2 / 100.0;

        final var size = driver.manage().window().getSize();

        final var xFirstPoint = (int) (size.width * firstXPercentage);
        final var yFirstPoint = (int) (size.height * firstYPercentage);
        final var xSecondPoint = (int) (size.width * secondXPercentage);
        final var ySecondPoint = (int) (size.height * secondYPercentage);

        //move the finger down to the element or starting position
        sequence.addAction(finger.createPointerMove(Duration.ZERO,
                PointerInput.Origin.viewport(), xFirstPoint, yFirstPoint));

        //tap the element
        sequence.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));

        //move to the element
        sequence.addAction(finger.createPointerMove(Duration.ofMillis(1000L),
                PointerInput.Origin.viewport(), xSecondPoint, ySecondPoint));

        //move the finger up of the screen
        sequence.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(List.of(sequence));
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
        final var finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        final var sequence = new Sequence(finger, 1);

        final var originLocation = originElement.getLocation();
        final var originRect = originElement.getRect();
        final var originHalfWidth = originRect.width / 2;
        final var originHalfHeight = originRect.height / 2;
        final var originX = originLocation.getX() + originHalfWidth;
        final var originY = originLocation.getY() + originHalfHeight;

        final var destinyLocation = destinyElement.getLocation();
        final var destinyRect = destinyElement.getRect();
        final var destinyHalfWidth = destinyRect.width / 2;
        final var destinyHalfHeight = destinyRect.height / 2;
        final var destinyX = destinyLocation.getX() + destinyHalfWidth;
        final var destinyY = destinyLocation.getY() + destinyHalfHeight;

        logs.debug(String.format("originX: %d originY: %d height: %s width %s",
                originX, originY, originRect.getHeight(), originRect.getWidth()));
        logs.debug(String.format("destinyX: %d destinyY: %d height: %s width %s",
                destinyX, destinyY, destinyRect.getHeight(), destinyRect.getWidth()));

        //move the finger down to the center of the origin element
        sequence.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), originX, originY));

        //tap the element
        sequence.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));

        //wait to make it long press
        sequence.addAction(new Pause(finger, Duration.ofMillis(500)));

        //move to the element center of the destiny element
        sequence.addAction(finger.createPointerMove(Duration.ofMillis(1000L),
                PointerInput.Origin.viewport(), destinyX, destinyY));

        //move the finger up of the screen
        sequence.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(List.of(sequence));
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
}
