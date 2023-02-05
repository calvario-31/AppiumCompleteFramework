package base;

import data.DataProviders;
import io.appium.java_client.android.AndroidDriver;
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
    protected final DataProviders dataProviders = new DataProviders();

    private void initDriver() {
        final var driverManager = new DriverManager();

        if (runOnServer) {
            driver = driverManager.buildRemoteDriver();
        } else {
            driver = driverManager.buildLocalDriver();
        }
    }

    @BeforeMethod(alwaysRun = true, description = "Setting up the driver and going to index")
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

    protected abstract void initPages(AndroidDriver driver);
}
