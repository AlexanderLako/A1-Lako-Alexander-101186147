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
      Then P2 draws plague and loses two shields
      Then P3 draws prosperity and everyone gets two cards
      Then P4 draws Queens favor and draws 2 adventure cards
      Then P1 draws a 3-stage quest
      And P1 decides to sponsor the quest
      And P2, P3, P4 participate in stage 1
      And P2, P3 wins, P4 loses
      Then P2 and P3 participate again
      And P2 and P3 win quest
      Then P2 and P3 earn 3 shields
      Then P3 is declared the winner


    Scenario: 2winner_game_2winner_quest
      Given a 4-stage quest is drawn
      And P1 decides to sponsor the quest
      When P2, P3, P4 participate in stage 1
      And P2 and P4 wins, P3 loses
      And P2 and P3 participate again
      And P2 and P4 win quest
      Then P2 and P4 earn 4 shields
      Then P2 draws a 3-stage quest
      And P2 declines to sponsor quest
      And P3 decides to sponsor the quest
      Then P1 declines to participate
      And P2, P4 participate
      And P2 and P4 win the quest
      Then P2 and P4 earn 3 shields
      Then P2 and P4 are declared the winners

    Scenario: A1_scenario
      Given a 4-stage quest is drawn
      And P1 declines to sponsor
      Then P2 sponsors the quest
      When P1, P3, P4 participate
      And P1, P3, P4 win stage 1
      Then P1, P3, P4 participate
      And P1 loses, P3, and P4 win stage 2
      Then P3, P4 participate
      And P3, P4 win stage 3
      Then P3, P4 participate
      And P4 wins, P3 loses
      Then P4 gains 4 shields






