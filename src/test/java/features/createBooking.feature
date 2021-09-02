Feature: Create Booking Scenarios
  Scenario: Create Booking With All Fields
    Given User Prepare Booking Request Body
    When User Send Create Booking Request
    Then Expected to see 200 status code
