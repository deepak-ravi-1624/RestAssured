
Feature: This feature is to send the OTAC for the given  customer by providing the Phonenumber.


  Scenario Outline: Test condition to verify the functionality of the api to send otac(one time auth code) is successful when valid phone number is passed in the request.

    Given I want to execute service of method "post" and in environment "messaging"
    When the relative url is "messaging/v1/otac/phone/issue"
    And the given test data is in  "2" and "5"
    And the query parameter is passed "null"
    Then execute the URL
    And I validate status code "200" and validate response parameter "447789654389"



  Scenario Outline: Test condition to verify functionality of api by passing invalid otac template .

    Given I want to execute service of method "post" and in environment "messaging"
    When the relative url is "messaging/v1/otac/phone/issue"
    And the given test data is in  "2" and "7"
    And the query parameter is passed "null"
    Then execute the URL
    And I validate status code "400" and validate response parameter "Invalid template format. Requires {OTAC} token in template."












