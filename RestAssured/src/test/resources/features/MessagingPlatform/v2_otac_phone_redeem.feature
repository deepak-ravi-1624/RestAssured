
Feature: This feature is used to redeem the OTAC for the given phone Number.


  Scenario Outline: Test condition to redeem the OTAC for the given phone Number.


    Given I want to execute service of method "post" and in environment "messaging"
    When the relative url is "messaging/v1/otac/phone/issue"
    And the given test data is in  "2" and "5"
    And the query parameter is passed "null"
    Then execute the URL
    And the relative url is "messaging/v2/otac/phone/redeem" and method is "post"
    And the given test data is in  "4" and "5"
    And execute the URL
    And I validate status code "204"


  Scenario Outline: Test condition to verify functionality of the api by passing invalid template.

    Given I want to execute service of method "post" and in environment "messaging"
    When the relative url is "messaging/v1/otac/phone/issue"
    And the given test data is in  "2" and "5"
    And the query parameter is passed "null"
    Then execute the URL
    And the relative url is "messaging/v2/otac/phone/redeem" and method is "post"
    And the given test data is in  "4" and "7"
    And execute the URL
    And I validate status code "400"











