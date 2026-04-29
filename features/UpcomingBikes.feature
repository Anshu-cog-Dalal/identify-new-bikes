Feature: Verify Upcoming Honda Bikes under 4 Lakh

  Scenario: Verify upcoming Honda bikes under 4 lakh in India
    Given the user is on the Home page
    When the user navigates to the Upcoming Bikes page
    And the user applies the Honda brand filter
    Then the user should see a list of upcoming Honda bikes under 4 lakh
    And each bike should have a non empty name
    And each bike should have a non empty price
    And each bike should have a non empty expected launch date
