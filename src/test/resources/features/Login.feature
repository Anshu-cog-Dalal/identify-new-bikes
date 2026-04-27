Feature: Google Login Validation

  Scenario Outline: Show error for invalid Google account email
    Given the user is on the home page
    When the user clicks the login icon
    And the user clicks the Google sign-in button
    And the user enters an invalid email "<email>"
    And the user clicks Next
    Then an error message containing "Couldn't find your Google Account" should be displayed

    Examples:
      | email               |
      | abc123@random.net   |
      | test@nomail.io      |
      | zzz999@nowhere.com  |