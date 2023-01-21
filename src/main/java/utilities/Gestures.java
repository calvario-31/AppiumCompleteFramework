package utilities;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.time.Duration;
import java.util.List;

public class Gestures {
    private final AndroidDriver driver;

    public Gestures(AndroidDriver driver) {
        this.driver = driver;
    }

    public WebElement scrollInto(String locatorString) {
        final var uiAutomatorText =
                String.format("UiScrollable(scrollable(true)).scrollIntoView(%s)", locatorString);
        return driver.findElement(new AppiumBy.ByAndroidUIAutomator(uiAutomatorText));
    }

    public void longTap(WebElement element, int duration) {
        final var source = element.getLocation();
        final var finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        final var sequence = new Sequence(finger, 1);

        //move the finger down to the element or starting position
        sequence.addAction(finger.createPointerMove(Duration.ZERO,
                PointerInput.Origin.viewport(), source.x, source.y));

        //tap the element
        sequence.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));

        //wait duration
        sequence.addAction(finger.createPointerMove(Duration.ofMillis(duration * 1000L),
                PointerInput.Origin.viewport(), source.x, source.y));

        //move the finger up of the screen
        sequence.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(List.of(sequence));
    }

    public void generalSwipeByPercentages(int x1, int y1, int x2, int y2) {
        final var finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        final var sequence = new Sequence(finger, 1);

        double firstXPercentage = x1 / 100.0;
        double firstYPercentage = y1 / 100.0;
        double secondXPercentage = x2 / 100.0;
        double secondYPercentage = y2 / 100.0;

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
        final var size = driver.manage().window().getSize();
        final var x = size.width / 2;
        generalSwipeByPercentages(x, y1, x, y2);
    }

    public void generalHorizontalSwipeByPercentages(int x1, int x2) {
        final var size = driver.manage().window().getSize();
        final var y = size.height / 2;
        generalSwipeByPercentages(x1, y, x2, y);
    }

    public void scrollToTop() {
        final var uiAutomatorText = "new UiScrollable(scrollable(true)).scrollToBeginning(10)";
        driver.findElement(new AppiumBy.ByAndroidUIAutomator(uiAutomatorText));
    }

    public void pressBack() {
        driver.pressKey(new KeyEvent(AndroidKey.BACK));
    }
}
