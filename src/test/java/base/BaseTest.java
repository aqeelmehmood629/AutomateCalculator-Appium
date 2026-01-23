package base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class BaseTest {

    public static AppiumDriver driver;

    @BeforeAll
    public static void setUp() throws Exception {

        if (driver != null) {
            return;
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

        

        driver = new AndroidDriver(
                new URL("http://127.0.0.1:4723"),
                caps
        );
    }

    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
