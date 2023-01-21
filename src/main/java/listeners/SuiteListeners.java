package listeners;

import base.BaseListeners;
import org.testng.ISuite;
import org.testng.ISuiteListener;

public class SuiteListeners extends BaseListeners implements ISuiteListener {
    @Override
    public void onStart(ISuite suite) {
        logs.startSuite(suite.getName());
        fileManager.deleteTestEvidence().deleteAllureReports().redirectStdErr();
    }

    @Override
    public void onFinish(ISuite suite) {
        ISuiteListener.super.onFinish(suite);
    }
}
