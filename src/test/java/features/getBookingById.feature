Feature: Get Booking By Id Scenarios

  Scenario: Check Response Fields Not Null
    When User Send Booking Request Method "GET" with "/booking/11" path
    Then Expected to see 200 status code
    Then Check Response "firstname" Fields Not Null
    Then Check Response "lastname" Fields Not Null
    Then Check Response "totalprice" Fields Not Null
    Then Check Response "bookingdates.checkin" Fields Not Null
    Then Check Response "bookingdates.checkout" Fields Not Null
    Then Check Response "depositpaid" Fields Not Null
    #Then Check Response "additionalneeds" Fields Not Null


  Scenario: Non-Existing User Control
    When User Send Booking Request Method "GET" with "/booking/9999" path
    Then Expected to see 404 status code

