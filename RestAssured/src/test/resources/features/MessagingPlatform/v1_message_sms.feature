
Feature: This feature is used to send the message for the given Phone number.


  Scenario Outline: To verify the functionality of the api to send message for given Phone number

    Given I want to execute service of method "post" and in environment "messaging"
    When the relative url is "messaging/v1/message/sms"
    And the given test data is in  "6" and "5"
    And the query parameter is passed "null"
    Then execute the URL
    And I validate status code "200" and validate response parameter "447765432190"



  Scenario Outline: To verify the functionality of the api to send message by passing invalid OTAC template

    Given I want to execute service of method "post" and in environment "messaging"
    When the relative url is "messaging/v1/message/sms"
    And the given test data is in  "6" and "7"
    And the query parameter is passed "null"
    Then execute the URL
    And I validate status code "400" and validate response parameter "template must be supplied"












