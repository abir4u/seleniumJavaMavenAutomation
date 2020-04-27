Feature: Check available brands in TradeMe

  @Smoke
  Scenario: Check the number of brands that are available in UsedCars category
    Given I navigate to TradeMe Sandbox
    And I go to Used cars category
    Then I verify that 35 brands are available

  @Smoke
  Scenario: Check that Kia brand is available with 1 car listed in UsedCars category
    Given I navigate to TradeMe Sandbox
    And I go to Used cars category
    Then I verify that Kia brand is available
    And I verify that 1 car is available in Kia brand

  @Smoke
  Scenario: Check Hispano Suiza brand is not available in UsedCars category
    Given I navigate to TradeMe Sandbox
    And I go to Used cars category
    Then I verify that Hispano Suiza brand is not available