package utilities;

import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class FileManager {
    private final String allureReportsPath = "target/allure-results";
    private final String screenshotPath = "src/test/resources/screenshots";
    public static WebDriver staticDriver;
    private final Logs logs = new Logs();

    public FileManager deleteTestEvidence() {
        try {
            logs.debug("Deleting screenshots directory");
            FileUtils.deleteDirectory(new File(screenshotPath));
        } catch (IOException ioException) {
            logs.error("Failed deleting folder");
            logs.error(ioException.getLocalizedMessage());
        }
        return this;
    }

    public FileManager deleteAllureReports() {
        try {
            logs.debug("Deleting previous allure results directory");
            FileUtils.deleteDirectory(new File(allureReportsPath));
        } catch (IOException ioException) {
            logs.error("Failed deleting folder");
            logs.error(ioException.getLocalizedMessage());
        }
        return this;
    }

    public FileManager redirectStdErr() {
        logs.debug("Redirecting stderr");
        final var file = new File("src/test/resources/logs/stderr.log");
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        final var ps = new PrintStream(fos);
        System.setErr(ps);
        return this;
    }

    public void getScreenshot(WebDriver driver, String screenshotName) {
        logs.debug("Taking screenshot");
        final var screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        final var path = String.format("%s/%s.png", screenshotPath, screenshotName);
        try {
            FileUtils.copyFile(screenshotFile, new File(path));
        } catch (IOException ioException) {
            logs.error("Failed creating screenshot");
            logs.error(ioException.getLocalizedMessage());
        }
    }

    @Attachment(value = "failureScreenshot", type = "image/png")
    public byte[] getAllureScreenshot(WebDriver driver) {
        logs.debug("Taking allure screenshot");
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}