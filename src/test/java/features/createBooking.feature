Feature: Create Booking Scenarios

#This scenario bloa blaa
  @Regression
  Scenario: Create Booking With All Fields
    Given User Prepare Booking Request Body
      | firstname | lastname | totalprice | depositpaid | additionalneeds | checkin    | checkout   |
      | Yagmur    | Dereli   | 150        | true        | Breakfast       | 2018-01-01 | 2019-01-01 |
    When User Send Booking Request Method "POST" with "/booking" path
    Then Expected to see 200 status code
    Then Check "booking.firstname" Is Equal "Yagmur"
    Then Check "booking.totalprice" Is Equal 150
    Then Check "booking.depositpaid" Is Equals "true"
    Then Check "booking.lastname" Is Equal "Dereli"
    Then Check "booking.additionalneeds" Is Equal "Breakfast"
    Then Check "booking.bookingdates.checkin" Is Equal "2018-01-01"
    Then Check "booking.bookingdates.checkout" Is Equal "2019-01-01"

# This scenario bla bla
  @Regression
  Scenario: Create Booking Without additionalneeds
    Given User Prepare Booking Request Body
      | firstname | lastname | totalprice | depositpaid | checkin    | checkout   |
      | Yagmur    | Dereli   | 150        | true        | 2018-01-01 | 2019-01-01 |
    When User Send Booking Request Method "POST" with "/booking" path
    Then Expected to see 200 status code
    Then Check Response Not Include "booking.additionalneeds"

    #This scenario




