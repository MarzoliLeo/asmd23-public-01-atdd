package calculator;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CalculatorStepsScala {
    private int res = 0;

    private CalculatorScala calculatorScala;

    @Given("I have a Scala Calculator")
    public void iHaveAScalaCalculator(){ this.calculatorScala = CalculatorScala.apply(); }

    @When("In scala I add {int} and {int}")
    public void iAddAnd(int arg0, int arg1) {
        this.calculatorScala.enter(arg0);
        this.calculatorScala.enter(arg1);
    }

    @Then("In scala the sum should be {int}")
    public void theSumShouldBe(int arg0) {
        this.calculatorScala.add();
        if (arg0 != this.calculatorScala.getResult()) { // or using Junit's asserts
            throw new IllegalStateException();
        }
    }

    /* New steps for multiply */
    @When("In scala I multiply {int} and {int}")
    public void iMultiplyAnd(int arg0, int arg1) {
        this.calculatorScala.enter(arg0);
        this.calculatorScala.enter(arg1);
    }

    @Then("In scala the product should be {int}")
    public void theProductShouldBe(int arg0) {
        this.calculatorScala.multiply();
        if (arg0 != this.calculatorScala.getResult()) { // or using Junit's asserts
            throw new IllegalStateException();
        }
    }

    /* More advanced Steps */

    @When("In scala I provide a first number {int} and a second number {int}")
    public void iProvideAFirstNumberN(int arg0, int arg1) {
        this.calculatorScala.enter(arg0);
        this.calculatorScala.enter(arg1);
    }

    @Then("In scala the operation evaluates to {int} with the operator {string}")
    public void theOperationEvaluatesToResult(int arg0, String arg1) {
        switch (arg1) {
            case "+"	->	this.calculatorScala.add();
            case "-"	->	this.calculatorScala.subtract();
            case "*"	->	this.calculatorScala.multiply();
            case "/"	->	this.calculatorScala.divide();
            default		->	throw new IllegalStateException();
        }
        if (arg0 != this.calculatorScala.getResult()) {
            throw new IllegalStateException();
        }
    }


}
