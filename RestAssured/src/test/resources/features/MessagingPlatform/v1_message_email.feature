
Feature: This feature is used to send the message for the given email ID


  Scenario Outline: To verify the functionality of the api to send message for given email

    Given I want to execute service of method "post" and in environment "messaging"
    When the relative url is "messaging/v1/message/email"
    And the given test data is in  "5" and "5"
    And the query parameter is passed "null"
    Then execute the URL
    And I validate status code "200" and validate response parameter "idtest153@o2.com"



  Scenario Outline: To verify the functionality of the api to send message by passing invalid OTAC template

    Given I want to execute service of method "post" and in environment "messaging"
    When the relative url is "messaging/v1/message/email"
    And the given test data is in  "5" and "7"
    And the query parameter is passed "null"
    Then execute the URL
    And I validate status code "400" and validate response parameter "template must be supplied"












