package stepDefinitions;

import base.BaseTest;
import com.aventstack.extentreports.Status;
import io.cucumber.java.en.*;
import org.testng.Assert;
import pages.CalculatorPage;

public class CalculatorSteps {

    CalculatorPage calculator;

    @Given("I open the calculator app")
    public void openCalculatorApp() {

        calculator = new CalculatorPage(BaseTest.getDriver());

        Hooks.getTest().log(Status.INFO, "Given I open the calculator app");

        calculator.clearCalculator();
    }

    @When("I enter {string}")
    public void enterFirstNumber(String number) {

        Hooks.getTest().log(Status.INFO, "When I enter \"" + number + "\"");

        calculator.tapNumber(number);
    }

    @And("I perform {string} with {string}")
    public void pressOperator(String operator, String secondNumber) {

        Hooks.getTest().log(Status.INFO,
                "And I perform \"" + operator + "\" with \"" + secondNumber + "\"");

        calculator.tapOperator(operator);

        if (!secondNumber.equalsIgnoreCase("NA")) {
            calculator.tapNumber(secondNumber);
        }

        calculator.tapEqual();
    }

    @Then("I should see {string} on the screen")
    public void verifyResult(String expected) {

        Hooks.getTest().log(Status.INFO,
                "Then I should see \"" + expected + "\" on the screen");

        String actual = calculator.getResult();

        Assert.assertEquals(actual, expected);
    }
}