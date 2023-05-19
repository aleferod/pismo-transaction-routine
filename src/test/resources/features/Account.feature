Feature: Account Feature

Scenario: Creating account

  When client call create account endpoint with document number "12345678900"
  Then return status code 201
  And  there is one account created on database

  Scenario: Getting Account

    When client call get account endpoint with id "1"
    Then return status code 200
    And  account with document number "12345678900" is returned