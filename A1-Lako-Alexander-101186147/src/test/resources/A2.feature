Feature: A2 Game

  Scenario: 0_winner_quest
    Given a 2-stage quest is drawn
    And P1 decides to sponsor the quest
    When P2, P3, P4 participate in stage 1
    And all players lose stage 1
    Then the quest ends with no winner
    And the sponsor discards and draws cards


#    Given P1 draws 2-stage quest
#    And P1 sponsors the quest
#    And P2, P3, P4 all decide to participate
#
#    When P2, P3, P4 attempt stage 1
#    Then P2, P3, P4 all lose
#    And Quest ends with no winner
#
#    And P1 discards
#    And P1 draws new card



#  Scenario: 1winner_game_with_events
#    Given a new game is started with players P1, P2, P3, and P4
#
#    When P1 draws a 4-stage quest card
#    And P1 decides to sponsor the quest
#    And P1 builds 4 stages for the quest
#    And P2, P3, and P4 participate in the quest
#    Then P2, P3, and P4 win all 4 stages of the quest
#    And P2, P3, and P4 each earn 4 shields
#
#    When P2 draws the "Plague" event card
#    Then P2 loses 2 shields
#
#    When P3 draws the "Prosperity" event card
#    Then all players (P1, P2, P3, and P4) each receive 2 adventure cards
#
#    When P4 draws the "Queen’s Favor" event card
#    Then P4 receives 2 additional adventure cards
#
#    When P1 draws a 3-stage quest card
#    And P1 decides to sponsor the quest
#    And P1 builds 3 stages for the quest
#    And P2, P3, and P4 participate in stage 1 of the quest
#    Then P2 and P3 win stage 1
#    And P4 loses stage 1
#
#    When P2 and P3 participate in and win stages 2 and 3 of the quest
#    Then P2 and P3 each earn 3 additional shields
#
#    Then P3 is declared the winner
#    And P3’s victory is asserted