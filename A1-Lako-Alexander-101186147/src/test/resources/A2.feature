Feature: A2 Game

  Scenario: 0_winner_quest
    Given P1 draws 2-stage quest
    And P1 sponsors the quest
    And P2, P3, P4 all decide to participate

    When P2, P3, P4 attempt stage 1
    Then P2, P3, P4 all lose
    And Quest ends with no winner

    And P1 discards
    And P1 draws new card