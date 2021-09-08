Feature: Delete Scenarios

  #Delete process can not return 201 status code
  Scenario: Delete Booking
    When User Send Booking Request Method "DELETE" with "/booking/1" path
    Then Expected to see 200 status code
