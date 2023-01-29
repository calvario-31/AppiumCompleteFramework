package listeners;

import base.BaseListeners;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utilities.DriverManager;

public class TestListeners extends BaseListeners implements ITestListener {
    @Override
    public void onTestStart(ITestResult result) {
        logs.testSteps();
        setDriver(result, DriverManager.getStaticDriver());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        printSuccess(result.getInstanceName(), result.getName());
        fileManager.getScreenshot(driver, result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        printFailed(result.getInstanceName(), result.getName());
        fileManager.getScreenshot(driver, result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        printSkipped(result.getInstanceName(), result.getName());
    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println(context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println();
    }
}
