package driver;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class DriverFactory {

    private static AndroidDriver driver;

    // Driver initialize method
    public static AndroidDriver initializeDriver() throws Exception {

        if (driver != null) {
            return driver;
        }

        DesiredCapabilities caps = new DesiredCapabilities();

        caps.setCapability("platformName", "Android");
        caps.setCapability("automationName", "UiAutomator2");
        caps.setCapability("deviceName", "Android Device");
        caps.setCapability("udid", "au6h6lgyaalbzpdu");

        caps.setCapability("noReset", true);
        caps.setCapability("newCommandTimeout", 300);
        caps.setCapability("adbExecTimeout", 60000);
        caps.setCapability("uiautomator2ServerInstallTimeout", 60000);
        caps.setCapability("uiautomator2ServerLaunchTimeout", 60000);
        caps.setCapability("ignoreHiddenApiPolicyError", true);
        caps.setCapability("disableWindowAnimation", true);
        caps.setCapability("autoGrantPermissions", true);

        caps.setCapability("appPackage", "com.miui.calculator");
        caps.setCapability("appActivity","com.miui.calculator.cal.CalculatorActivity");
        
        caps.setCapability("unlockType", "pin");
        caps.setCapability("unlockKey", "7643");


        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), caps);
        return driver;
    }

    // Get driver
    public static AndroidDriver getDriver() {
        return driver;
    }

    // Quit driver
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}