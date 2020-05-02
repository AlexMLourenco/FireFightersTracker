Feature: Firefighter Alarm

  Scenario: Firefighter Heart Rate Alert
    Given The firefighter number "a1"
    When The firefighter heart rate is 110 bpm
    Then The alert should be 1