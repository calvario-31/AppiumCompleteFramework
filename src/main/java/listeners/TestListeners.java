package listeners;

import base.BaseListeners;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utilities.Logs;

public class TestListeners extends BaseListeners implements ITestListener {
    @Override
    public void onTestStart(ITestResult result) {
        Logs.testSteps();
        setDriver(result);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        printSuccess(result.getInstanceName(), result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        printFailed(result.getInstanceName(), result.getName());
        fileManager.saveTestEvidence(driver, result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        printSkipped(result.getInstanceName(), result.getName());
    }
}
