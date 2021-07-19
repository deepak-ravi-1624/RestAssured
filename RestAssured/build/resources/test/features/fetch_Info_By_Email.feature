
Feature: This feature is to fetch the identity details of a customer by providing the email ID using identity/v1/identity/EMAIL/{email)/details


  Scenario Outline: fetch the identity details of a customer by providing the  a valid email ID
    Given I want to execute service of method "get"
    When the relative url is "1" and "0"
    And the given test data is in  "1" and "4"
    And the query parameter is passed "active=false"
    Then execute the URL
    And I validate status code "200" and validate msisdn "+447800901004"


  Scenario Outline: fetch the identity details of a customer by providing the  a invalid email ID
    Given I want to execute service of method "get"
    When  the relative url is "2" and "0"
    And the given test data is in  "2" and "4"
    And the query parameter is passed "active=false"
    Then execute the URL
    And I validate status code "404"

  Scenario Outline: fetch the identity details of a customer by providing the  a invalid email ID
    Given I want to execute service of method "get"
    When  the relative url is "2" and "0"
    And the given test data is in  "2" and "4"
    And the query parameter is passed "active=false"
    Then execute the URL
    And I validate status code "405"










