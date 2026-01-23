Feature: Calculator Operations

  Scenario Outline: Perform calculator operations
    Given I open the calculator app
    When I enter "<num1>"
    And I perform "<operation>" with "<num2>"
    Then I should see "<result>" on the screen
    
    
    
Examples:

  | num1 | operation | num2 | result |
  | 2    | add       | 3    | 5      |
  | 9    | subtract  | 4    | 5      |
  | 60   | multiply  | 5    | 300    |
  | 8    | divide    | 2    | 4      |
  | 50   | percent   | NA   | 0.5    |