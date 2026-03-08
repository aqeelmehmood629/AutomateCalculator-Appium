package pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CalculatorPage {

    private AndroidDriver driver;

    public CalculatorPage(AndroidDriver driver) {
        this.driver = driver;
    }

    // Elements / Locators
    private By clearButton = By.id("com.miui.calculator:id/btn_c_s");
    private By resultField = By.id("com.miui.calculator:id/result");

    private By addBtn = By.id("com.miui.calculator:id/btn_plus_s");
    private By subBtn = By.id("com.miui.calculator:id/btn_minus_s");
    private By mulBtn = By.id("com.miui.calculator:id/btn_mul_s");
    private By divBtn = By.id("com.miui.calculator:id/btn_div_s");
    private By percentBtn = By.id("com.miui.calculator:id/btn_percent_s");
    private By equalBtn = By.id("com.miui.calculator:id/btn_equal_s");

    // Actions
    public void clearCalculator() {
        try {
            driver.findElement(clearButton).click();
            Thread.sleep(300);
        } catch (Exception ignored) {}
    }

    public void tapNumber(String number) {
        if (number == null || number.equalsIgnoreCase("NA")) return;

        for (char digit : number.toCharArray()) {
            driver.findElement(By.id("com.miui.calculator:id/btn_" + digit + "_s")).click();
            try { Thread.sleep(250); } catch (InterruptedException ignored) {}
        }
    }

    public void tapOperator(String operator) {
        switch (operator.toLowerCase()) {
            case "add": tap(addBtn); break;
            case "subtract": tap(subBtn); break;
            case "multiply": tap(mulBtn); break;
            case "divide": tap(divBtn); break;
            case "percent": tap(percentBtn); break;
            default: throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }

    public void tapEqual() {
        tap(equalBtn);
    }

    public String getResult() {
        WebElement result = driver.findElement(resultField);
        return result.getText().replace("=", "").replace(",", "").trim();
    }

    private void tap(By locator) {
        driver.findElement(locator).click();
        try { Thread.sleep(250); } catch (InterruptedException ignored) {}
    }
}