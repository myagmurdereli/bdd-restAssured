Feature: Get Booking Scenarios
  Background:
    Given User Prepare Booking Request Body
      | firstname | lastname | totalprice | depositpaid | additionalneeds | checkin    | checkout   |
      | Yagmur    | Dereli   | 150        | true        | Breakfast       | 2018-01-01 | 2019-01-01 |
    When User Send Booking Request Method "POST" with "/booking" path

  Scenario: GET Without Any Filter
    When User Send Booking Request Method "GET" with "/booking" path
    Then Expected to see 200 status code
    Then Array Response "bookingid" Fields Are Not Null

    #I have created 3 data with using the post method.
    # Then I get with firstname filter.But DB is not stable
  Scenario: GET With Firstname Filter
    When User Send Booking Request Method "GET" with "/booking?firstname=Yagmur" path
    Then Expected to see 200 status code
    Then Response Size Is Equal 1

