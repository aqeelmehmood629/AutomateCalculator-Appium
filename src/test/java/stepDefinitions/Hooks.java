package stepDefinitions;

import base.BaseTest;
import com.aventstack.extentreports.*;
import com.aventstack.extentreports.Status;

import io.cucumber.java.*;
import utils.ExtentManager;

public class Hooks {

    private static ExtentReports extent = ExtentManager.getExtentReports();
    private static ThreadLocal<ExtentTest> testThread = new ThreadLocal<>();

    @Before
    public void beforeScenario(Scenario scenario) throws Exception {

        BaseTest.initDriver();

        ExtentTest test = extent.createTest(scenario.getName());
        testThread.set(test);
    }

    @After
    public void afterScenario(Scenario scenario) {

        if (scenario.isFailed()) {
            testThread.get().log(Status.FAIL, "Scenario Failed");
        } else {
            testThread.get().log(Status.PASS, "Scenario Passed");
        }

        extent.flush();
    }

    public static ExtentTest getTest() {
        return testThread.get();
    }
}