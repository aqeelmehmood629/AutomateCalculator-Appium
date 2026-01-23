package stepDefinitions;


import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import static base.BaseTest.driver;


public class CalculatorSteps {

 
    @Before
    public void resetCalculator() {
        try {
            driver.findElement(By.id("com.miui.calculator:id/btn_c_s")).click();
            Thread.sleep(300);
        } catch (Exception ignored) {}
    }

    @Given("I open the calculator app")
    public void opencalculator_app() {
        // already opened from BaseTest
    }

    @When("I enter {string}")
    public void enterfirst_number(String num1) {
        tapNumber(num1);
    }

    @And("I perform {string} with {string}")
    public void press_operator(String operator,String num2) {

        switch (operator.toLowerCase()) {
            case "add":
                tap(By.id("com.miui.calculator:id/btn_plus_s"));
                tapNumber(num2);
                break;
            case "subtract":
                tap(By.id("com.miui.calculator:id/btn_minus_s"));
                tapNumber(num2);
                break;
            case "multiply":
                tap(By.id("com.miui.calculator:id/btn_mul_s"));
                tapNumber(num2);
                break;
            case "divide":
                tap(By.id("com.miui.calculator:id/btn_div_s"));
                tapNumber(num2);
                break;
            case "percent":
            	tap(By.id("com.miui.calculator:id/btn_percent_s"));
            	break;
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }

      //  tapNumber(secondNumber);
    }

    @Then("I should see {string} on the screen")
    public void result(String expected) {

        tap(By.id("com.miui.calculator:id/btn_equal_s"));        
        WebElement result = driver.findElement(By.id("com.miui.calculator:id/result"));

        String actual = result.getText()
                .replace("=", "")
                .replace(",", "")
                .trim();

        if (!actual.equals(expected)) {
            throw new AssertionError("Expected: " + expected + " but got: " + actual);
        }
    }

    private void tapNumber(String number) {
        if (number == null || number.equalsIgnoreCase("NA")) {
            return;
        }
        for (char digit : number.toCharArray()) {
            tap(By.id("com.miui.calculator:id/btn_" + digit + "_s"));
        }
    }


    private void tap(By locator) {
        driver.findElement(locator).click();
        try {
        	Thread.sleep(250);
        	}
        catch (InterruptedException ignored) {}
    }
}
