Feature: A2 Game

  Scenario: 0_winner_quest
    Given a 2-stage quest is drawn
    And P1 decides to sponsor the quest
    When P2, P3, P4 participate in stage 1
    And all players lose stage 1
    Then the quest ends with no winner
    And the sponsor discards and draws cards

    Scenario: 1winner_game_with_events
      Given a 4-stage quest is drawn
      And P1 decides to sponsor the quest
      When P2, P3, P4 participate in stage 1
      And all players win
      Then P2, P3, P4 all earn 4 shields
      When P2 draws plague and loses two shields
      When P3 draws prosperity and everyone gets two cards
      When P4 draws Queens favor and draws 2 adventure cards
      When P1 draws a 3-stage quest
      And P1 decides to sponsor the quest
      When P2, P3, P4 participate in stage 1
      And P2, P3 wins, P4 loses
      When P2 and P3 participate again
      And P2 and P3 win quest
      Then P2 and P3 earn 3 shields
      Then P3 is declared the winner

