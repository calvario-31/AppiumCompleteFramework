package base;

import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;
import listeners.InvokeMethodListeners;
import listeners.SuiteListeners;
import listeners.TestListeners;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import utilities.CommonFlows;
import utilities.DriverManager;
import utilities.Logs;

@Listeners({TestListeners.class, SuiteListeners.class, InvokeMethodListeners.class})
public abstract class BaseTest {
    private final boolean runOnServer = System.getenv("JOB_NAME") != null;
    protected AndroidDriver driver;
    protected CommonFlows commonFlows;
    protected final String regression = "Regression";
    protected final String smoke = "Smoke";
    protected final String setup = "Setup";

    private void initDriver() {
        final var driverManager = new DriverManager();

        if (runOnServer) {
            driver = driverManager.buildRemoteDriver();
        } else {
            driver = driverManager.buildLocalDriver();
        }
    }

    @BeforeMethod(alwaysRun = true, description = "Setting up the driver and going to main activity")
    protected void setupDriver() {
        initDriver();
        commonFlows = new CommonFlows(driver);

        initPages(driver);
    }

    @AfterMethod(alwaysRun = true, description = "Killing the driver")
    protected void teardownDriver() {
        Logs.debug("Killing the driver");
        driver.quit();
    }

    protected void triggerDeeplink(String url) {
        Logs.info("Triggering deeplink %s", url);
        driver.get(url);
    }

    public AndroidDriver getDriver() {
        return driver;
    }

    @Step("Switching to web context")
    protected void switchWebContext() {
        Logs.debug("Switching to web context");
        final var contextNames = driver.getContextHandles();
        driver.context((String) contextNames.toArray()[1]); // set context to WEBVIEW
    }

    @Step("Switching to native app context")
    protected void switchNativeAppContext() {
        Logs.debug("Switching to native app context");
        final var contextNames = driver.getContextHandles();
        driver.context((String) contextNames.toArray()[0]); // set context to NATIVE_APP
    }

    protected abstract void initPages(AndroidDriver driver);
}
