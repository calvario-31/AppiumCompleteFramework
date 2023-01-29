package element;

import base.BasePage;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.Gestures;

import java.time.Duration;

public class $ {
    private WebElement webElement;
    private final AndroidDriver driver;
    private final By locator;
    private String locatorString;
    private final LocatorType locatorType;
    private final int timeOut;
    private WebDriverWait wait;
    private final Gestures gestures;

    public $(LocatorType locatorType, String locator, AndroidDriver driver, int timeOut) {
        this.driver = driver;
        this.locatorType = locatorType;
        this.timeOut = timeOut;
        this.locator = buildLocator(locator);
        this.gestures = new Gestures(driver);
    }

    public $(LocatorType locatorType, String locator, AndroidDriver driver) {
        this.driver = driver;
        this.locatorType = locatorType;
        this.timeOut = BasePage.DEFAULT_TIME_OUT;
        this.locator = buildLocator(locator);
        this.gestures = new Gestures(driver);
    }

    public $ click() {
        findElement();
        webElement.click();
        return this;
    }

    public $ sendKeys(String text) {
        findElement();
        webElement.sendKeys(text);
        return this;
    }

    public $ scrollTo() {
        gestures.scrollInto(locatorString);
        return this;
    }

    public $ dragTo($ destiny) {
        gestures.dragOneItemToAnother(getWebElement(), destiny.getWebElement());
        return this;
    }

    public $ doubleClick() {
        findElement();
        gestures.doubleClick(webElement);
        return this;
    }

    public $ longTap(int duration) {
        findElement();
        gestures.longTap(webElement, duration);
        return this;
    }

    public $ waitForVisibility(int timeOut) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator)); //explicit wait per se
        return this;
    }

    public $ waitForVisibility() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));  //explicit wait per se
        return this;
    }

    public boolean isDisplayed() {
        findElement();
        return webElement.isDisplayed();
    }

    public String getText() {
        findElement();
        return webElement.getText();
    }

    public enum LocatorType {
        ID,
        ACCESSIBILITY_ID,
        CLASSNAME,
        UIAUTOMATOR2,
    }

    private By buildLocator(String locator) {
        switch (locatorType) {
            case ID:
                this.locatorString = String.format("resourceId(\"%s\")", locator);
                return By.id(locator);
            case ACCESSIBILITY_ID:
                this.locatorString = String.format("description(\"%s\")", locator);
                return AppiumBy.accessibilityId(locator);
            case CLASSNAME:
                this.locatorString = String.format("className(\"%s\")", locator);
                return By.className(locator);
            case UIAUTOMATOR2:
                this.locatorString = locator;
                return AppiumBy.androidUIAutomator(locator);
        }
        return null;
    }

    private void findElement() {
        webElement = driver.findElement(locator);
    }

    public WebElement getWebElement() {
        findElement(); //refresh web element
        return webElement;
    }
}
