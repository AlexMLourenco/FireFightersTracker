
Feature: the version can be retrieved
  Scenario: client makes call to GET /location
    When the client calls /location
    Then the client receives status code of 200
    And the client receives fighter latitude 19.34563
    And the client receives fighter longitude 20.56211