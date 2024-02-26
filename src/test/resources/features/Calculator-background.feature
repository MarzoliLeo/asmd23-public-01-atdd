Feature:  Adding numbers with a Calculator
  In order to not learn math
  As someone who is bad at math
  I want to be able to add numbers using a Calculator

  Background: Start with a Calculator
    Given I have a Calculator

  Scenario:  Add two positive numbers
    When I add 1 and 1
    Then the sum should be 2

  Scenario:  Add a positive and negative number
    When I add 1 and -1
    Then the sum should be 0

  Scenario:  Add two negative numbers
    When I add -1 and -1
    Then the sum should be -2

  Scenario: Multiply two numbers
    When I multiply 2 and 2
    Then the product should be 4