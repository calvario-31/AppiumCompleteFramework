package utilities;

import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;

public class FileManager {
    private final String allureReportsPath = "target/allure-results";
    private final String debugEvidenceFolder = "src/test/resources/debugEvidence";
    public static AndroidDriver staticDriver;

    public FileManager deleteTestEvidence() {
        try {
            Logs.debug("Deleting debug evidence directory");
            FileUtils.deleteDirectory(new File(debugEvidenceFolder));

            Logs.debug("Deleting allure reports directory");
            FileUtils.deleteDirectory(new File(allureReportsPath));
        } catch (IOException ioException) {
            Logs.error(String.format("Failed to delete directory: %s%n", ioException.getLocalizedMessage()));
        }
        return this;
    }

    public FileManager redirectStdErr() {
        Logs.debug("Redirecting stderr");
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

    @Attachment(value = "failureScreenshot", type = "image/png")
    public byte[] takeAllureScreenshot() {
        Logs.debug("Taking allure screenshot");
        return ((TakesScreenshot) staticDriver).getScreenshotAs(OutputType.BYTES);
    }

    public void saveTestEvidence(AndroidDriver driver, String testName) {
        getPageSourceXML(driver, testName).takeScreenshot(driver, testName);
    }

    private FileManager getPageSourceXML(AndroidDriver driver, String fileName) {
        Logs.debug("Taking page source");
        final var path = String.format("%s/view-hierarchy-%s.xml", debugEvidenceFolder, fileName);
        Logs.debug(path);
        try {
            Logs.debug("Creating xml file");
            final var file = new File(path);
            Logs.debug("Creating file parents");
            if (file.getParentFile().mkdirs()) {
                Logs.debug("Writing to xml file");
                final var fileWriter = new FileWriter(file);
                fileWriter.write(driver.getPageSource());
                Logs.debug("Closing filewriter");
                fileWriter.close();
            }
        } catch (IOException ioException) {
            Logs.error(String.format("Failed to write xml: %s%n", ioException.getLocalizedMessage()));
        }
        return this;
    }

    private FileManager takeScreenshot(AndroidDriver driver, String screenshotName) {
        Logs.debug("Taking screenshot");
        final var screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        final var path = String.format("%s/screenshot-%s.png", debugEvidenceFolder, screenshotName);
        try {
            FileUtils.copyFile(screenshotFile, new File(path));
        } catch (IOException ioException) {
            Logs.error(String.format("Failed creating screenshot: %s", ioException.getLocalizedMessage()));
        }
        return this;
    }
}