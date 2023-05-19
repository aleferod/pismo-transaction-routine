Feature: Transaction Feature

  Scenario: Validating insufficient credit limit
    Given amount "123.45" and operation type id 1 and account id 1
    When client call create transaction endpoint to create a transaction
    Then return status code 400
    And  there is 0 transaction created on database

  Scenario: Creating transaction
    Given amount "123.45" and operation type id 4 and account id 1
    When client call create transaction endpoint to create a transaction
    Then return status code 201
    And  there is 1 transaction created on database

  Scenario: Validating sufficient credit limit
    Given amount "123.45" and operation type id 1 and account id 1
    When client call create transaction endpoint to create a transaction
    Then return status code 201
    And  there is 2 transaction created on database

