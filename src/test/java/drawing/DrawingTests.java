package drawing;

import base.BaseTest;
import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.burgermenu.DrawingPage;

import static element.$.Orientation.HORIZONTAL;
import static element.$.Orientation.VERTICAL;

public class DrawingTests extends BaseTest {
    private DrawingPage drawingPage;

    @BeforeMethod(alwaysRun = true, description = setup)
    public void setUp() {
        commonFlows.goToDrawingPage();
    }

    @Test
    public void sumSymbolTest() {
        drawingPage.swipe(30, 70, HORIZONTAL);
        drawingPage.swipe(30, 70, VERTICAL);
    }

    @Test
    public void squareTest() {
        drawingPage.swipe(30, 70, 10, VERTICAL);
        drawingPage.swipe(10, 70, 70, HORIZONTAL);
        drawingPage.swipe(70, 30, 70, VERTICAL);
        drawingPage.swipe(70, 10, 30, HORIZONTAL);
    }

    @Override
    protected void initPages(AndroidDriver driver) {
        drawingPage = new DrawingPage(driver);
    }
}
