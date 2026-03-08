package utils;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.ExtentTest;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import static base.BaseTest.getDriver;

public class TestListener {

    private static ThreadLocal<ExtentTest> testThread = new ThreadLocal<>();

    public static void startScenario(Scenario scenario) {
        ExtentTest test = ExtentManager.getExtentReports().createTest(scenario.getName());
        testThread.set(test);
    }

    public static ExtentTest getTest() {
        return testThread.get();
    }

    public static void logStep(String step, boolean passed) {
        if (passed) {
            getTest().log(Status.PASS, step);
        } else {
            getTest().log(Status.FAIL, step);
            try {
                byte[] screenshot = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.BYTES);
                getTest().addScreenCaptureFromBase64String(java.util.Base64.getEncoder().encodeToString(screenshot));
            } catch (Exception e) {
                getTest().log(Status.WARNING, "Screenshot failed: " + e.getMessage());
            }
        }
    }

    public static void flush() {
        ExtentManager.getExtentReports().flush();
    }
}