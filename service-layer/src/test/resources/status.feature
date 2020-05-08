# Created by alexandrelourenco at 08/05/2020
Feature: Firefighter Status
  # Enter feature description here

  Scenario: Firefighter Status
    Given The firefighter number "a1"
    When I want to check the informations of the firefighter
    Then The firefighter status will appear on a window in the map