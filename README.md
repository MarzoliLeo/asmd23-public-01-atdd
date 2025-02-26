# Laboratorio 01 - 01Lab Requirements, Acceptance Testing, and BDD/ATDD - Report
## Lavoro svolto:
### **Task 1: Calculator.**
Specification: the repo has a calculator example (with Gherkin specification, step implementation, and production code). Play with it. Add examples
and operations, use all Gherkin features. Strictly use ATDD. Does everything work as expected?

#### __Task 1: Implementazione.__
Installando correttamente i plugin, Gherking e Cucumber for Java, tutti i test eseguono correttamente. 

**Ho voluto testare le funzionalità del linguaggio** inserendo una nuova operazione di moltiplicazione nella calcolatrice e ho definito dei nuovi scenarios in Gherkin, seguendo le varie modalità equivalenti tra di loro.
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

**Ho creato un sistema che riconosce in automatico l'operazione da testare con il valore risultate:**

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

### **Task 2: Tooling.**
Specification: Experiment with installing/using Cucumber with Scala and/or in VSCode. Is VSCode better at all here? Does Cucumber play well with
Scala 3?

Per poter utilizzare cucumber in Scala bisogna integrare un nuovo plugin "Cucumber for Scala" se lo si vuole utilizzare in IntelliJ.

#### __Task 2: Implementazione.__

All'interno del progetto si è implementata una versione differente della calcolatrice in Scala3 nel file [CalculatorScala](src/main/java/calculator/CalculatorScala.scala). Questa appare nel seguente modo:
```
trait CalculatorScala {
  def enter(i: Int): Unit
  def add(): Unit
  def multiply(): Unit
  def subtract(): Unit
  def divide(): Unit
  def getResult: Int
  def binaryOperation(operation: (Int, Int) => Int): Unit

}

object CalculatorScala {
  private class CalculatorScalaImpl() extends CalculatorScala{
    private var numbers = List.empty[Int]
    override def enter(i: Int): Unit =
      numbers = numbers :+ i
      if (numbers.length > 2)
        throw new IllegalStateException

    override def add(): Unit = binaryOperation(_ + _)

    override def multiply(): Unit = binaryOperation(_ * _)

    override def subtract(): Unit =
      if (numbers.head >= numbers(1))
        binaryOperation(_ - _)
      else
        throw new ArithmeticException

    override def divide(): Unit =
      if (numbers(1) != 0 && (numbers.head != 0 && numbers(1) != 0))
        binaryOperation(_ / _)
      else
        throw new ArithmeticException

    override def getResult: Int = {
      if (numbers.length != 1)
        throw new IllegalStateException
      numbers.head
    }

    override def binaryOperation(operation: (Int, Int) => Int): Unit = {
      if (numbers.length != 2)
        throw new IllegalStateException
      numbers = List(operation(numbers.head, numbers(1)))
    }
  }

  def apply() : CalculatorScala = new CalculatorScalaImpl
}
```
si è poi definito un nuovo [CalculatorStepsScala](src/test/java/calculator/CalculatorStepsScala.java) che utilizza i metodi offerti da questa nuova implementazione :
```
@Given("I have a Scala Calculator")
    public void iHaveAScalaCalculator(){ this.calculatorScala = CalculatorScala.apply(); }
```
e si è modificato opportunamente tutte le stringhe degli acceptance test dentro al file apposito [Calculator-outline-Scala](src/test/resources/features/calculator-outline-scala.feature) per fare riferimento all'utilizzo del nuovo calcolatore Scala, ecco un esempio:
```
Scenario Outline: Evaluating arithmetic operations with two integer parameters
    Given I have a Scala Calculator
    When In scala I provide a first number <n1> and a second number <n2>
    Then In scala the operation evaluates to <result> with the operator <op>
    Examples:
      | op  |n1|n2|result|
      | "+" | 4| 5|     9|
      | "-" | 8| 5|     3|
      | "*" | 7| 2|    14|
      | "/" | 6| 2|     3|
```

Facendo così gli acceptance test eseguono tutti correttamente e il sistema ATDD è perfettamente integrato in Scala3. 

**Visual Studio Code** la migrazione del progetto su quest'ultimo non è facile. Il compilatore migliore per utilizzare Cucumber è IntelliJ IDEA, in quanto la configurazione è molto più rapida.
In visual studio code bisogna installare due plugin: 
 - Cucumber (Gherkin) Full Support
 - Cucumber Quick

Fatto ciò ancora non è sufficiente e bisogna creare una cartella .vscode/ nella root di progetto e inserire al suo interno due file. Uno di settings.json (quindi .vscode/settings.json) che abiliterà l'esecuzione da parte di Cucumber Quick, ma solo per alcuni framework supportati:
 * Protractor Cucumber
 * WebDriverIo Cucumber
 * Cypress Cucumber Pre-processor
 * Native CucumberJS
 * Serenity-JS

E l'altro sarà un file launch.json (quindi .vscode/launch.json) per la configurazione di uno dei framework che supporta cucumber.


### **Task 3: Reengineer.**
Specification: Take an existing implemented small app with GUI, e.g. an OOP exam. Write Gherkin specifications explaining what the system is
expected to do, and make acceptance tests pass. Does the system need a refactor of implementation? What does it tell us about how
an application has to be designed to be easily acceptance tested?
Search here: https://bitbucket.org/mviroli/oop2023-esami (2023, 2022,. . . )

#### __Task 3: implementazione__.

Scelto uno dei progetti all'interno del repo bitbucket (ho preferito il a01b.sol2) e dopo averlo importato nel mio progetto, ho analizzato il comportamento del singolo definendo una serie di Acceptance Tests.

Ho preso quelle che erano i requirements dell'applicazione e li ho trasformati in Acceptance Tests all'interno della classe: [GUI_exam.feature](src/test/resources/features/GUI_exam.feature) __(src/test/resources/features/GUI_exam.feature)__
per fare ciò ho dovuto definire una serie di nuovi steps all'interno del file: [GUIExamSteps.java](src/test/java/GUI_exam/GUIExamSteps.java) __(src/test/java/GUI_exam/GUIExamSteps.java)__ ed inserire dentro ciascuno di essi la business logic dell'applicazione.
Le difficoltà riscontrate nel procedimento sono emerse durante lo sviluppo dei vari test ed hanno prodotto come risultato una leggera rivisitazione delle logiche implementative.
Infatti, siccome il progetto è strutturato per utilizzare una GUI e separa la logica implementativa, questo dopo aver effettuato la business logic, mappa le "Position" della "Grid" in una map di "<JButton,Position>" e l'incremento delle celle avveniva soltanto per una questione estetica, ciò che ho dovuto modificare è stato
simulare il comportamento incrementale da parte della business logic esponendo la visibilità della lista "marks" che tiene traccia di tutte le "Position". Un altro piccolo cambiamento è stato aggiungere un booleano per verificare che l'applicazione si chiudesse al momento del "Game Over". 

Dunque, in conclusione per rispondere alla domanda di partenza di questo task: un'applicazione ha bisogno di considerare all'interno del suo design, per poter definire facilmente al suo interno degli acceptance test, il concetto di visibilità per alcune delle sue funzionalità principali. Ovvero, ciò che prima era privato
della business logic, viene di conseguenza reso visibile quindi pubblico per poter essere manipolato e testato, in caso cui non si acceda direttamente ad un elemento privato, comunque lo si fa indirettamente tramite getter or setter o tramite l'introduzione di variabili utili a monitorare lo stato. Visto che in un linguaggio ad oggetti come Java,
le variabili di una classe dovrebbero essere privati, si può ovviare all'introduzione di public tramite un package protected.

### **Task 5: ATDD-LLM.**
Specification: LLMs/ChatGPT can arguably help in write/improve/complete/implement/reverse-engineer a Gherkin specification. Experiment with
this, based on the above tasks or in other cases. Is ChatGPT useful for all that?

#### __Task 5: implementazione__.
Sono una persona che utilizza regolarmente ChatGPT per velocizzare il mio lavoro. Non penso che sia sostitutivo (un po' scarso a mio parere), ma è sicuramente utilizzabile  per inquadrare meglio l'obbiettivo o avere una visione differente su una certa situazione, magari pure bloccante. 

Per i task sopra descritti, l'utilizzo di tool LLM è stato praticamente inutile. Perché per rendere capace ChatGPT di creare dei test Gherkin, ha bisogno di conoscere il contesto o quanto meno quella che è la core business logic, altrimenti genera dei test ipotizzando quello che dovrebbe essere il comportamento, ma spesso e volentieri questo porta all'implementazione di nuovi metodi che vanno ad aumentare la complessità di un progetto se non addirittura inserire delle funzionalità che non c'entrano con i requirements. Dunque di seguito elenco come è stato usato per ciascuna fase:
 * Write: Come già citato per generare dei test è utile solo se conosce il contesto. Nel Task 3 è stato utile per fornire una prima impostazione che comunque appariva un po' troppo banale rispetto i requirements, da cui poi ho sviluppato degli acceptance test più avanzati. Senza specificazioni predilige sempre gli Scenarios, chiedendo espressamente una certa feature è in grado di modificare la soluzione in base alle esigenze. Essendo che però riutilizza le soluzioni fornite precedentemente per tarare le nuove, senza spostarsi troppo da quello che è il contesto, si finisce sempre con il discutere delle stesse cose.
 * Complete: Sempre nel task 3 ho provato ad utilizzare ChatGPT per completare alcuni step senza dargli un contesto. Il problema è sempre lo stesso, non è capace di vedere da sè la business logic, perciò fornisce delle linee guida basandosi su supposizioni che fa in base alla firma del metodo o dal numero di parametri che questo prende o dalla descrizione e/o commenti. Ovviamente, fa un buon lavoro perché riconosce cosa andrebbe fatto. Ma completa la richiesta con una soluzione che al più delle volte definisce variabili che non esistono, metodi che assume ci siano, ma vanno implementati oppure introduce librerie anch'esse abbastanza complicate.
 * Implement: Non è stato utile in nessun caso di implementazione. Per definire un contesto di partenza sì, ma a livello implementativo ho risolto tutto manualmente, anzì mi ha rallentato.
 * Reverse-Engineer: Per quanto riguarda questo aspetto, ho notato che è pratico. Anche qui, sbaglia al più delle volte, però si avvicina molto a quella che è la soluzione, prendendo come caso d'esempio il task 1, ho chiesto a ChatGPT di fare reverse-engineer dello step
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
    
    ed ho ottenuto questo risultato
   
    ```
    Feature: Calculator Operation Evaluation
    
      Scenario: Perform a calculator operation and verify the result
        Given a calculator
        When the operator is set to "<operator>"
        And the calculator performs the operation
        Then the result should be <result>
    
      Examples:
        | operator | result |
        | +        | <int>   |
        | -        | <int>   |
        | *        | <int>   |
        | /        | <int>   |
    ```
    
    Come si può vedere nel task 1, è molto simile a come è stato impostato da me, però ho notato che in generale quando opera con acceptance test Gherkin tende ad essere quanto più testuale possibile, come si vede nell'esempio, invece di considerare due fattori su cui applicare una operazione, ChatGPT descrive il procedimento di operazione in maniera più astratta, lasciando libera interpretazione allo sviluppatore. Io personalmente, preferirei fosse più specifico.
