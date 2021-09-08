Feature: Partial Update Booking Scenarios

  Background:
    When Success Login

  #I updated firtname field. My expectation is value must be updated and other fields are same
  Scenario: Update firstname of Booking
    Given User Prepare Booking Request Body
      | firstname  | lastname       |
      | UpdateTest | UpdateLastname |
    When User Send Booking Request Method "PATCH" with "/booking/1" path
    Then Expected to see 200 status code
    Then Check "firstname" Is Equal "UpdateTest"
    Then Check "lastname" Is Equal "UpdateLastname"

