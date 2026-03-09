package utils;

import base.BaseTest;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {

    public static String captureScreenshot(String testName, String status) {

        try {

            File src = ((TakesScreenshot) BaseTest.getDriver()).getScreenshotAs(OutputType.FILE);

            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

            String fileName = testName + "_" + status + "_" + timeStamp + ".png";

            String folderPath = System.getProperty("user.dir") + "/reports/screenshots/";

            File folder = new File(folderPath);
            if (!folder.exists()) {
                folder.mkdirs();
            }

            File dest = new File(folderPath + fileName);

            FileHandler.copy(src, dest);

            return dest.getAbsolutePath();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}