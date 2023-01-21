package listeners;

import base.BaseListeners;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

public class InvokeMethodListeners extends BaseListeners implements IInvokedMethodListener {
    private final String preConditionMethodName = "setupDriver";
    private final String postConditionMethodName = "teardownDriver";

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.getTestResult().getName().equals(preConditionMethodName)) {
            logs.startTest(testResult.getInstanceName());
            logs.preconditionStart();
        }

        if (method.getTestResult().getName().equals(postConditionMethodName)) {
            logs.postConditionStart();
        }
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.getTestResult().getName().equals(postConditionMethodName)) {
            logs.postConditionFinish();
        }
    }
}
