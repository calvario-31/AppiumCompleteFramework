package listeners;

import base.BaseListeners;
import io.qameta.allure.listener.TestLifecycleListener;
import io.qameta.allure.model.TestResult;
import utilities.Logs;

public class AllureListeners extends BaseListeners implements TestLifecycleListener {
    @Override
    public void beforeTestStop(TestResult result) {
        Logs.debug("beforeTestStop: " + result.getStatus().name());

        if (result.getStatus().name().equalsIgnoreCase("FAILED") ||
                result.getStatus().name().equalsIgnoreCase("BROKEN")) {
            Logs.debug("failed");
            fileManager.getAllureScreenshot(staticDriver);
        }
    }
}
