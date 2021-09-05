Feature: Update Booking Scenarios

  #I updated firtname field. My expectation is value must be updated and other fields are same
  Scenario: Update Booking With All Fields
    Given User Prepare Booking Request Body
      | firstname   | lastname | totalprice | depositpaid | additionalneeds | checkin    | checkout   |
      | Update Test | Dereli   | 150        | true        | Breakfast       | 2018-01-01 | 2019-01-01 |
    When User Send Booking Request Method "PUT" with "/booking/11" path
    Then Expected to see 200 status code
    Then Check "booking.firstname" Is Equal "Update Test "
    Then Check "booking.totalprice" Is Equal 150
    Then Check "booking.depositpaid" Is Equals "true"
    Then Check "booking.lastname" Is Equal "Dereli"
    Then Check "booking.additionalneeds" Is Equal "Breakfast"
    Then Check "booking.bookingdates.checkin" Is Equal "2018-01-01"
    Then Check "booking.bookingdates.checkout" Is Equal "2019-01-01"
