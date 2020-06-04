Feature: the message can be retrieved
  Scenario: client makes call to POST /baeldung
    When the client calls /baeldung
    Then the client receives status code of 200
    And the client receives server version hello
 
