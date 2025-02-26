package calculator;

import io.cucumber.java.en.*;

public class CalculatorSteps {
    private int res = 0;

    private Calculator calculator;

    @Given("I have a Calculator")
    public void iHaveACalculator() {
        this.calculator = new Calculator();
    }

    @When("I add {int} and {int}")
    public void iAddAnd(int arg0, int arg1) {
        this.calculator.enter(arg0);
        this.calculator.enter(arg1);
    }

    @Then("the sum should be {int}")
    public void theSumShouldBe(int arg0) {
        this.calculator.add();
        if (arg0 != this.calculator.getResult()) { // or using Junit's asserts
            throw new IllegalStateException();
        }
    }

    /* New steps for multiply */
    @When("I multiply {int} and {int}")
    public void iMultiplyAnd(int arg0, int arg1) {
        this.calculator.enter(arg0);
        this.calculator.enter(arg1);
    }

    @Then("the product should be {int}")
    public void theProductShouldBe(int arg0) {
        this.calculator.multiply();
        if (arg0 != this.calculator.getResult()) { // or using Junit's asserts
            throw new IllegalStateException();
        }
    }

    /* More advanced Steps */

    @When("I provide a first number {int} and a second number {int}")
    public void iProvideAFirstNumberN(int arg0, int arg1) {
        this.calculator.enter(arg0);
        this.calculator.enter(arg1);
    }

    @Then("the operation evaluates to {int} with the operator {string}")
    public void theOperationEvaluatesToResult(int arg0, String arg1) {
        switch (arg1) {
            case "+"	->	this.calculator.add();
            case "-"	->	this.calculator.subtract();
            case "*"	->	this.calculator.multiply();
            case "/"	->	this.calculator.divide();
            default		->	throw new IllegalStateException();
        }
        if (arg0 != this.calculator.getResult()) {
            throw new IllegalStateException();
        }
    }


}
