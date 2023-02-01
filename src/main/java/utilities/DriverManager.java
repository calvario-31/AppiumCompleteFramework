package utilities;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class DriverManager {
    public static String deviceName;
    private static AndroidDriver staticDriver;

    public AndroidDriver buildRemoteDriver() {
        //TO-DO
        return null;
    }

    public AndroidDriver buildLocalDriver() {
        try {
            final var appiumUrl = "http://localhost:4723/wd/hub";

            final var fileAPK = new File("src/main/resources/apk/sauceLabs.apk");
            final var desiredCapabilities = new DesiredCapabilities();

            desiredCapabilities.setCapability("appium:chromeOptions", ImmutableMap.of("w3c", false));
            desiredCapabilities.setCapability("autoGrantPermissions", true);
            desiredCapabilities.setCapability("appWaitActivity", "com.swaglabsmobileapp.MainActivity");
            desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
            desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
            desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
            desiredCapabilities.setCapability(MobileCapabilityType.APP, fileAPK.getAbsolutePath());

            final var driver = new AndroidDriver(new URL(appiumUrl), desiredCapabilities);
            staticDriver = driver;
            return driver;
        } catch (MalformedURLException malformedURLException) {
            malformedURLException.printStackTrace();
            Logs.error("Failed building local driver");
            return null;
        }
    }
}
