Feature: Google Login Validation

  Scenario Outline: Show error for invalid Google account email
    Given the user is on the home page
    When the user clicks the login icon
    And the user clicks the Google sign-in button
    And the user enters an invalid email from excel by row "<row_index>"
    And the user clicks Next
    Then an error message should be displayed

    Examples:
      | row_index |
      |         1 |
      |         2 |
      |         3 |
      |         4 |
      |         5 |