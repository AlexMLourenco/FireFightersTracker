# Created by alexandrelourenco at 08/05/2020
Feature: Firefighter Location
  # Enter feature description here

  Scenario: Firefighter Location
    Given The firefighterGPS number "a1"
    When I want to know where the firefighter is
    Then The firefighter appears on the map