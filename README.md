# Laboratorio 1 - Report
## Lavoro svolto:
### Task 1: Calculator.
Specification: the repo has a calculator example (with Gherkin specification, step implementation, and production code). Play with it. Add examples
and operations, use all Gherkin features. Strictly use ATDD. Does everything work as expected?

#### Task 1: Esecuzione.
Installando correttamente i plugin, Gherking e Cucumber for Java, tutti i test eseguono correttamente. 

Ho voluto testare le funzionalità del linguaggio inserendo una nuova operazione di moltiplicazione nella calcolatrice e ho definito dei nuovi scenarios in Gherkin, seguendo le varie modalità equivalenti tra di loro.
```
Scenario: Multiply two numbers
    Given I have a Calculator
    When I multiply 2 and 2
    Then the product should be 4
```
```
Scenario: Multiply two numbers
    When I multiply 2 and 2
    Then the product should be 4
```
```
Scenario Outline: Multiply two numbers
  Given I have a Calculator
  When I multiply <arg0> and <arg1>
  Then the product should be <prod>
  Examples:
    | arg0 | arg1 | prod |
    | 2    | 2    | 4    |
```
Definendo due nuovi step, nel seguente modo: 
```
@When("I multiply {int} and {int}")
    public void iMultiplyAnd(int arg0, int arg1) {
        this.calculator.enter(arg0);
        this.calculator.enter(arg1);
    }
```
```
@Then("the product should be {int}")
    public void theProductShouldBe(int arg0) {
        this.calculator.multiply();
        if (arg0 != this.calculator.getResult()) { // or using Junit's asserts
            throw new IllegalStateException();
        }
    }
```

**Ho creato un sistema che riconosce in automatico l'operazione da testare con il valore risultate: **
```
Scenario Outline: Evaluating arithmetic operations with two integer parameters
    Given I have a Calculator
    When I provide a first number <n1> and a second number <n2>
    Then the operation evaluates to <result> with the operator <op>
    Examples:
      | op  |n1|n2|result|
      | "+" | 4| 5|     9|
      | "-" | 8| 5|     3|
      | "*" | 7| 2|    14|
      | "/" | 6| 2|     3|
```

Evidenziando lo step che implementa la logica: 
```
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
```

Facendo ciò si può simulare a pieno il comportamento della calcolatrice e volendo aggiungere e testare una qualsiasi operazione facilmente.
