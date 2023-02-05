package listeners;

import base.BaseListeners;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import utilities.Logs;

public class SuiteListeners extends BaseListeners implements ISuiteListener {
    @Override
    public void onStart(ISuite suite) {
        Logs.startSuite(suite.getName());
        fileManager.deleteTestEvidence().redirectStdErr();
    }

    @Override
    public void onFinish(ISuite suite) {
        ISuiteListener.super.onFinish(suite);
    }
}
