Feature: Firefighter Alarm

  Scenario: Firefighter Heart Rate Alert ON
    When The firefighter heart rate is 110 bpm
    Then The alert should be 1

  Scenario: Firefighter Heart Rate Alert OFF
    When The firefighter heart rate is 80 bpm
    Then The alert should be 0