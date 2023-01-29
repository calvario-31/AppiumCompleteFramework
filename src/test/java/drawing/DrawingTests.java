package drawing;

import base.BaseTest;
import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.burgermenu.DrawingPage;

public class DrawingTests extends BaseTest {
    private DrawingPage drawingPage;

    @BeforeMethod(alwaysRun = true, description = setup)
    public void setUp() {
        commonFlows.goToDrawingPage();
    }

    @Test
    public void sumSymbolTest() {
        drawingPage.horizontalSwipe(30, 70);
        drawingPage.verticalSwipe(30, 70);
    }

    @Test
    public void squareTest() {
        drawingPage.verticalSwipe(30, 70, 10);
        drawingPage.horizontalSwipe(10, 70, 70);
        drawingPage.verticalSwipe(70, 30, 70);
        drawingPage.horizontalSwipe(70, 10, 30);
    }

    @Override
    protected void initPages(AndroidDriver driver) {
        drawingPage = new DrawingPage(driver);
    }
}
