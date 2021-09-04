Feature: Create Booking Scenarios

  Scenario: Create Booking With All Fields
    Given User Prepare Booking Request Body
      | firstname | lastname | totalprice | depositpaid | additionalneeds | checkin    | checkout   |
      | Yagmur    | Dereli   | 150        | true        | Breakfast       | 2018-01-01 | 2019-01-01 |
    When User Send Create Booking Request
    Then Expected to see 200 status code
