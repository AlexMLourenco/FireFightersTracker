# Created by alexandrelourenco at 08/05/2020
Feature: Firefighter Information
  # Enter feature description here

  Scenario: Firefighter Information
    Given The firefighter number "a1"
    When I want to check the firefighter global information
    Then The firefighter information about GPS, Environment and Heart Rate will appear on the dashboard