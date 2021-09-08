Feature: Create Booking Scenarios

#This scenario for the checking all paramethers in response
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

# This scenario is about the sending request without additionalneeds.
# Because if dont not send the additionalneeds, response returns without this parameter
  Scenario: Create Booking Without additionalneeds
    Given User Prepare Booking Request Body
      | firstname | lastname | totalprice | depositpaid | checkin    | checkout   |
      | Yagmur    | Dereli   | 150        | true        | 2018-01-01 | 2019-01-01 |
    When User Send Booking Request Method "POST" with "/booking" path
    Then Expected to see 200 status code
    Then Check Response Not Include "booking.additionalneeds"

#Checkout must be greater than Checkin.
# In this test my expectation was the seeing 400 Bad Request Error
# and a message like which include "Checkin canat be
# greater than checkout date" because of REST standarts.
  Scenario: Checkin Is Greater Than Checkout
    Given User Prepare Booking Request Body
      | firstname | lastname | totalprice | depositpaid | additionalneeds | checkin    | checkout   |
      | Yagmur    | Dereli   | 150        | true        | Breakfast       | 2020-01-01 | 2019-01-01 |
    When User Send Booking Request Method "POST" with "/booking" path
    Then Expected to see 400 status code
