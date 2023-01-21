package base;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import utilities.FileManager;
import utilities.Logs;

public class BaseListeners {
    protected WebDriver driver;
    protected static WebDriver staticDriver;
    protected final FileManager fileManager = new FileManager();
    protected final Logs logs = new Logs();

    protected void setDriver(ITestResult result) {
        var currentClass = result.getInstance();
        driver = ((BaseTest) currentClass).getDriver(); //for the test listeners
        staticDriver = driver; //for allure listeners
    }

    protected void printSuccess(String className, String testName) {
        var status = "PASSED";
        logs.endTest(status);
        var message =
                String.format("\t %s.%s ... \u001B[32m%s\u001B[0m", className, testName, status);
        System.out.println(message);
    }

    protected void printFailed(String className, String testName) {
        var status = "FAILED";
        logs.endTest(status);
        var message =
                String.format("\t %s.%s ... \u001B[31m%s\u001B[0m", className, testName, status);
        System.out.println(message);
    }

    protected void printSkipped(String className, String testName) {
        var status = "SKIPPED";
        logs.endTest(status);
        var message =
                String.format("\t %s.%s ... \u001B[33m%s\u001B[0m", className, testName, status);
        System.out.println(message);
    }
}
