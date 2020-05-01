Feature: Is there some alert?
  User wants to know if there is any firefighter heart rate alert.

  Scenario: Firefighter Heart Rate Alert
    Given the firefighter number a1
    When firefighter heart rate is 110 bpm
    Then a alert will be ON
    When firefighter heart rate is 80 bpm
    Then a alert will be OFF