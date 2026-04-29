Feature: Verify Popular Used Cars

  Scenario: Verify popular used cars are displayed after selecting Chennai
    Given the user is on Home page
    When the user navigates to the Used Cars page
    And the user selects Chennai
    Then the popular used cars should be displayed