package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {

	private static ExtentReports extent;

	public synchronized static ExtentReports getExtentReports() {

		if (extent == null) {

			ExtentSparkReporter spark = new ExtentSparkReporter("reports/ExtentReport.html");

			spark.config().setDocumentTitle("Calculator Test Report");
			spark.config().setReportName("Automation Test Results");
			spark.config().setTheme(Theme.STANDARD);

			extent = new ExtentReports();
			extent.attachReporter(spark);

			/*
			 * spark.setSystemInfo("Tester", "Aqeel Mehmood"); spark.setSystemInfo("Device",
			 * "Android Device"); spark.setSystemInfo("Platform", "Android 13");
			 * spark.setSystemInfo("Automation Tool", "Appium");
			 */
		}

		return extent;
	}
}