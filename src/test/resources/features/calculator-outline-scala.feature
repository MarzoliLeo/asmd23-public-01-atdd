Feature:  Adding numbers with a Calculator
  In order to not learn math
  As someone who is bad at math
  I want to be able to add numbers using a Calculator

  Scenario Outline: Add two numbers
    Given I have a Scala Calculator
    When In scala I add <arg0> and <arg1>
    Then In scala the sum should be <res>
    Examples:
      | arg0 | arg1 | res |
      | 1    | 1    | 2   |
      | 1    | -1   | 0   |
      | -5   | -6   | -11 |

  Scenario Outline: Multiply two numbers
    Given I have a Scala Calculator
    When In scala I multiply <arg0> and <arg1>
    Then In scala the product should be <prod>
    Examples:
      | arg0 | arg1 | prod |
      | 2    | 2    | 4    |

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