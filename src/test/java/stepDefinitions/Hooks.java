package stepDefinitions;

import base.BaseTest;
import com.aventstack.extentreports.*;
import com.aventstack.extentreports.Status;

import io.cucumber.java.*;
import utils.ExtentManager;
import utils.ScreenshotUtil;

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

		String status = scenario.isFailed() ? "FAIL" : "PASS";

		String screenshotPath = ScreenshotUtil.captureScreenshot(scenario.getName().replace(" ", "_"), status);

		try {

			if (scenario.isFailed()) {

				testThread.get().fail("Scenario Failed").addScreenCaptureFromPath(screenshotPath, "Failure Screenshot");

			} else {

				testThread.get().pass("Scenario Passed").addScreenCaptureFromPath(screenshotPath, "Passed Screenshot");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		extent.flush();

	}

	public static ExtentTest getTest() {
		return testThread.get();
	}

}