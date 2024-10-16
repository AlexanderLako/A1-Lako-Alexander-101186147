package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {


    @Test
    @DisplayName("RESP 1.1: Test to check adventure deck size")
    void RESP_1_test_1(){
        Main game = new Main();
        game.initializeAdventureDeck();

        //Should be 100 cards in the adventure deck
        int adventureDeckSize = game.getAdventureDeckSize();
        assertEquals(100, adventureDeckSize);
    }

    @Test
    @DisplayName("RESP 1.2: Test to check event deck size")
    void RESP_1_test_2(){
        Main game = new Main();
        game.initializeEventDeck();

        //Should be 17 cards in the event deck
        int eventDeckSize = game.getEventDeckSize();
        assertEquals(17, eventDeckSize);
    }

    @Test
    @DisplayName("RESP 1.3: Test to check adventure discard pile is empty")
    void RESP_1_test_3(){
        Main game = new Main();

        int adventureDiscardSize = game.getAdventureDiscardSize();
        assertEquals(0, adventureDiscardSize);
    }

    @Test
    @DisplayName("RESP 1.4 Test to check event discard pile is empty")
    void RESP_1_test_4(){
        Main game = new Main();

        int eventDiscardSize = game.getEventDiscardSize();
        assertEquals(0, eventDiscardSize);
    }

    @Test
    @DisplayName("RESP 2.1: Test to check that each player has 12 cards")
    void RESP_2_test_1(){
        Main game = new Main();
        game.initializeAdventureDeck();

        //Initialize players and give them cards
        game.initializePlayers();

        int correctHands = 0;

        // Check that each player has 12 cards
        for(int i = 0; i < game.numPlayers; i++){
            if(game.getPlayerHandSize(game.players.get(i)) == 12) {correctHands++;}
        }

        assertEquals(game.numPlayers, correctHands);
    }

    @Test
    @DisplayName("RESP 2.2: Test to check that the adventure deck was updated")
    void RESP_2_test_2(){
        Main game = new Main();
        game.initializeAdventureDeck();

        //Initialize players and give them cards
        game.initializePlayers();

        int adventureDeckSize = game.getAdventureDeckSize();

        assertEquals(100 - (game.numPlayers * 12), adventureDeckSize);
    }

    @Test
    @DisplayName("RESP 3.1: Test to check if a player has 7 shields")
    void RESP_3_test_1(){
        Main game = new Main();
        game.initializeAdventureDeck();
        game.initializePlayers();

        //Rig the game to set player1 with 7 shields
        Main.player testPlayer = game.players.get(0);
        testPlayer.numShields = 7;

        int playerShields = game.getPlayerNumShields(testPlayer);

        assertEquals(7, playerShields);
    }

    @Test
    @DisplayName("RESP 3.2: Test to check if one or more players have 7 shields and won the game")
    void RESP_3_test_2(){
        Main game = new Main();
        game.initializeAdventureDeck();
        game.initializePlayers();

        //Rig the game to set players with 7 shields
        Main.player testPlayer1 = game.players.get(1);
        Main.player testPlayer2 = game.players.get(2);
        testPlayer1.numShields = 7;
        testPlayer2.numShields = 7;

        boolean winner = game.checkWinner();

        assertEquals(true, winner);
    }

    @Test
    @DisplayName("RESP 3.3: Test to check that if a player has 7 OR MORE shields and wins the game")
    void RESP_3_test_3(){
        Main game = new Main();
        game.initializeAdventureDeck();
        game.initializePlayers();

        //Rig the game to set player1 with 8 shields
        Main.player testPlayer = game.players.get(0);
        testPlayer.numShields = 8;

        boolean winner = game.checkWinner();

        assertEquals(true, winner);
    }

    @Test
    @DisplayName("RESP 3.4: Test to check that if no player has 7 or more shields, there is no winner")
    void RESP_3_test_4(){
        Main game = new Main();
        game.initializeAdventureDeck();
        game.initializePlayers();

        boolean winner = game.checkWinner();

        assertEquals(false, winner);
    }

    @Test
    @DisplayName("RESP 4.1: Test to check that if a player has won, their name is displayed")
    void RESP_4_test_1(){
        Main game = new Main();
        game.initializeAdventureDeck();
        game.initializePlayers();

        Main.player testPlayer = game.players.get(0);
        testPlayer.numShields = 7;

        StringWriter output = new StringWriter();
        game.displayWinners(new PrintWriter(output));

        assertTrue(output.toString().contains("Winner(s)!: P1 "));
    }

    @Test
    @DisplayName("RESP 4.2: Test to check that if multiple players have won, all their names are displayed")
    void RESP_4_test_2(){
        Main game = new Main();
        game.initializeAdventureDeck();
        game.initializePlayers();

        Main.player testPlayer1 = game.players.get(1);
        Main.player testPlayer2 = game.players.get(2);
        testPlayer1.numShields = 7;
        testPlayer2.numShields = 7;

        StringWriter output = new StringWriter();
        game.displayWinners(new PrintWriter(output));

        assertTrue(output.toString().contains("Winner(s)!: P2 P3 "));
    }

    @Test
    @DisplayName("RESP 5.1: Test to check that when an event card is drawn, the event deck is updated correctly")
    void RESP_5_test_1(){
        Main game = new Main();
        game.initializeEventDeck();
        game.drawEventCard();

        int currEventDeckSize = game.getEventDeckSize();
        assertEquals(16, currEventDeckSize);
    }

    @Test
    @DisplayName("RESP 5.2: Test to check that event card is displayed")
    void RESP_5_test_2(){
        Main game = new Main();
        game.initializeEventDeck();

        //Set the first card in the deck to be Q4
        game.setEventCard("Q", 4, 0);
        game.drawEventCard();

        StringWriter output = new StringWriter();
        game.displayEventCard(game.currentEvent, new PrintWriter(output));

        assertTrue(output.toString().contains("Current Event Card: Q4"));
    }

    @Test
    @DisplayName("RESP 5.3: Test to check that when an event card is drawn, the current event card in play is correct")
    void RESP_5_test_3(){
        Main game = new Main();
        game.initializeEventDeck();

        Main.eventCard eCard = game.eventDeck.get(0);
        game.drawEventCard();

        assertEquals(eCard, game.currentEvent);
    }

    @Test
    @DisplayName("RESP 6.1: Test to check if player loses 2 shields based on event card")
    void RESP_6_test_1(){
        Main game = new Main();
        game.initializeEventDeck();
        game.initializeAdventureDeck();
        game.initializePlayers();

        Main.player currPlayer = game.getCurrentPlayer();

        game.setEventCard("Plague", -1, 0);
        currPlayer.numShields = 4;
        game.drawEventCard();

        game.playEvent();

        assertEquals(2, game.getPlayerNumShields(currPlayer));
    }

    @Test
    @DisplayName("RESP 6.2: Test to check if player loses 2 shields they cant drop below 0")
    void RESP_6_test_2() {
        Main game = new Main();
        game.initializeEventDeck();
        game.initializeAdventureDeck();
        game.initializePlayers();

        Main.player currPlayer = game.getCurrentPlayer();

        game.setEventCard("Plague", -1, 0);
        currPlayer.numShields = 1;
        game.drawEventCard();

        game.playEvent();

        assertEquals(0, game.getPlayerNumShields(currPlayer));
    }

    @Test
    @DisplayName("RESP 7.1: Test to check that when queens favour is played, that it adds two cards to the users hand")
    void RESP_7_test_1() {
        Main game = new Main();
        game.initializeEventDeck();
        game.initializeAdventureDeck();
        game.initializePlayers();

        Main.player currPlayer = game.getCurrentPlayer();

        game.setEventCard("Queens favor", -1, 0);
        game.drawEventCard();

        game.playEvent();

        assertEquals(14, game.getPlayerHandSize(currPlayer));
    }

    @Test
    @DisplayName("RESP 8.1: Test to check that game correctly identifies how many cards the current player needs to discard")
    void RESP_8_test_1() {
        Main game = new Main();
        game.initializeEventDeck();
        game.initializeAdventureDeck();
        game.initializePlayers();

        Main.player currPlayer = game.getCurrentPlayer();

        game.setEventCard("Queens favor", -1, 0);
        game.drawEventCard();

        game.playEvent();

        assertEquals(2, game.getTrimAmount(currPlayer));
    }

    @Test
    @DisplayName("RESP 8.2: Test to check that players hand is correctly displayed")
    void RESP_8_test_2() {
        Main game = new Main();
        game.initializeEventDeck();
        game.initializeAdventureDeck();
        game.initializePlayers();

        Main.player currPlayer = game.getCurrentPlayer();

        game.setAdventureCard(currPlayer,"F", "", 5, 0);
        game.setAdventureCard(currPlayer,"F", "", 5, 1);
        game.setAdventureCard(currPlayer,"F", "", 5, 2);
        game.setAdventureCard(currPlayer,"F", "", 5, 3);
        game.setAdventureCard(currPlayer,"F", "", 5, 4);
        game.setAdventureCard(currPlayer,"F", "", 5, 5);
        game.setAdventureCard(currPlayer,"W", "S", 10, 6);
        game.setAdventureCard(currPlayer,"W", "S", 10, 7);
        game.setAdventureCard(currPlayer,"W", "S", 10, 8);
        game.setAdventureCard(currPlayer,"W", "S", 10, 9);
        game.setAdventureCard(currPlayer,"W", "S", 10, 10);
        game.setAdventureCard(currPlayer,"W", "B", 15, 11);
        game.setAdventureCard(currPlayer,"W", "E", 30, 12);
        game.setAdventureCard(currPlayer,"W", "E", 30, 13);

        StringWriter output = new StringWriter();
        game.displayAdventureHand(currPlayer, new PrintWriter(output));

        assertTrue(output.toString().contains("P1 Hand: (1):F5 (2):F5 (3):F5 (4):F5 (5):F5 (6):F5 (7):WS10 (8):WS10 (9):WS10 (10):WS10 (11):WS10 (12):WB15 (13):WE30 (14):WE30"));
    }

    @Test
    @DisplayName("RESP 9.1: Test to make sure that the player enters a valid position")
    void RESP_9_test_1() {
        Main game = new Main();
        game.initializeEventDeck();
        game.initializeAdventureDeck();
        game.initializePlayers();

        Main.player currPlayer = game.getCurrentPlayer();

        String input = "1000";
        StringWriter output = new StringWriter();
        game.trimHand(currPlayer, new Scanner(input), new PrintWriter(output));
        assertTrue(output.toString().contains("invalid input"));
    }

    @Test
    @DisplayName("RESP 9.2: Test to make sure player is asked what card they want to delete")
    void RESP_9_test_2() {
        Main game = new Main();
        game.initializeEventDeck();
        game.initializeAdventureDeck();
        game.initializePlayers();

        Main.player currPlayer = game.getCurrentPlayer();

        String input = "4";
        StringWriter output = new StringWriter();
        game.trimHand(currPlayer, new Scanner(input), new PrintWriter(output));
        assertTrue(output.toString().contains("Enter the number of the card you would to delete: "));
    }

    @Test
    @DisplayName("RESP 10.1: Test to make sure the card is deleted")
    void RESP_10_test_1() {
        Main game = new Main();
        game.initializeEventDeck();
        game.initializeAdventureDeck();
        game.initializePlayers();

        Main.player currPlayer = game.getCurrentPlayer();

        game.setAdventureCard(currPlayer,"F", "", 5, 0);
        game.setAdventureCard(currPlayer,"F", "", 5, 1);
        game.setAdventureCard(currPlayer,"F", "", 5, 2);
        game.setAdventureCard(currPlayer,"F", "", 5, 3);
        game.setAdventureCard(currPlayer,"F", "", 5, 4);
        game.setAdventureCard(currPlayer,"F", "", 5, 5);
        game.setAdventureCard(currPlayer,"W", "S", 10, 6);
        game.setAdventureCard(currPlayer,"W", "S", 10, 7);
        game.setAdventureCard(currPlayer,"W", "S", 10, 8);
        game.setAdventureCard(currPlayer,"W", "S", 10, 9);
        game.setAdventureCard(currPlayer,"W", "S", 10, 10);
        game.setAdventureCard(currPlayer,"W", "B", 15, 11);
        game.setAdventureCard(currPlayer,"W", "E", 30, 12);
        game.setAdventureCard(currPlayer,"W", "E", 30, 13);

        String input = "4";
        StringWriter output = new StringWriter();
        game.trimHand(currPlayer, new Scanner(input), new PrintWriter(output));
        //assertTrue(output.toString().contains("Enter the number of the card you would to delete: "));
        assertEquals(13, game.getPlayerHandSize(currPlayer));
    }

    @Test
    @DisplayName("RESP 10.2: Test to make sure deleted card is added to the discard pile")
    void RESP_10_test_2() {
        Main game = new Main();
        game.initializeEventDeck();
        game.initializeAdventureDeck();
        game.initializePlayers();

        Main.player currPlayer = game.getCurrentPlayer();

        game.setAdventureCard(currPlayer,"F", "", 5, 0);
        game.setAdventureCard(currPlayer,"F", "", 5, 1);
        game.setAdventureCard(currPlayer,"F", "", 5, 2);
        game.setAdventureCard(currPlayer,"F", "", 5, 3);
        game.setAdventureCard(currPlayer,"F", "", 5, 4);
        game.setAdventureCard(currPlayer,"F", "", 5, 5);
        game.setAdventureCard(currPlayer,"W", "S", 10, 6);
        game.setAdventureCard(currPlayer,"W", "S", 10, 7);
        game.setAdventureCard(currPlayer,"W", "S", 10, 8);
        game.setAdventureCard(currPlayer,"W", "S", 10, 9);
        game.setAdventureCard(currPlayer,"W", "S", 10, 10);
        game.setAdventureCard(currPlayer,"W", "B", 15, 11);
        game.setAdventureCard(currPlayer,"W", "E", 30, 12);
        game.setAdventureCard(currPlayer,"W", "E", 30, 13);

        String input = "4";
        StringWriter output = new StringWriter();
        game.trimHand(currPlayer, new Scanner(input), new PrintWriter(output));
        assertEquals(1, game.getAdventureDiscardSize());
    }

    @Test
    @DisplayName("RESP 11.1: Test to make hand is displayed correctly after deleting one card")
    void RESP_11_test_1() {
        Main game = new Main();
        game.initializeEventDeck();
        game.initializeAdventureDeck();
        game.initializePlayers();

        Main.player currPlayer = game.getCurrentPlayer();

        game.setAdventureCard(currPlayer,"F", "", 5, 0);
        game.setAdventureCard(currPlayer,"F", "", 5, 1);
        game.setAdventureCard(currPlayer,"F", "", 5, 2);
        game.setAdventureCard(currPlayer,"F", "", 5, 3);
        game.setAdventureCard(currPlayer,"F", "", 5, 4);
        game.setAdventureCard(currPlayer,"F", "", 5, 5);
        game.setAdventureCard(currPlayer,"W", "S", 10, 6);
        game.setAdventureCard(currPlayer,"W", "S", 10, 7);
        game.setAdventureCard(currPlayer,"W", "S", 10, 8);
        game.setAdventureCard(currPlayer,"W", "S", 10, 9);
        game.setAdventureCard(currPlayer,"W", "S", 10, 10);
        game.setAdventureCard(currPlayer,"W", "B", 15, 11);
        game.setAdventureCard(currPlayer,"W", "E", 30, 12);
        game.setAdventureCard(currPlayer,"W", "E", 30, 13);

        String input = "4";
        StringWriter output = new StringWriter();
        game.trimHand(currPlayer, new Scanner(input), new PrintWriter(output));
        assertTrue(output.toString().contains("P1 Hand: (1):F5 (2):F5 (3):F5 (4):F5 (5):F5 (6):WS10 (7):WS10 (8):WS10 (9):WS10 (10):WS10 (11):WB15 (12):WE30 (13):WE30"));
    }

    @Test
    @DisplayName("RESP 11.2: Test to make hand is displayed correctly after deleting two cards")
    void RESP_11_test_2() {
        Main game = new Main();
        game.initializeEventDeck();
        game.initializeAdventureDeck();
        game.initializePlayers();

        Main.player currPlayer = game.getCurrentPlayer();

        game.setAdventureCard(currPlayer,"F", "", 5, 0);
        game.setAdventureCard(currPlayer,"F", "", 5, 1);
        game.setAdventureCard(currPlayer,"F", "", 5, 2);
        game.setAdventureCard(currPlayer,"F", "", 5, 3);
        game.setAdventureCard(currPlayer,"F", "", 5, 4);
        game.setAdventureCard(currPlayer,"F", "", 5, 5);
        game.setAdventureCard(currPlayer,"W", "S", 10, 6);
        game.setAdventureCard(currPlayer,"W", "S", 10, 7);
        game.setAdventureCard(currPlayer,"W", "S", 10, 8);
        game.setAdventureCard(currPlayer,"W", "S", 10, 9);
        game.setAdventureCard(currPlayer,"W", "S", 10, 10);
        game.setAdventureCard(currPlayer,"W", "B", 15, 11);
        game.setAdventureCard(currPlayer,"W", "E", 30, 12);
        game.setAdventureCard(currPlayer,"W", "E", 30, 13);

        String input1 = "4";
        StringWriter output = new StringWriter();
        game.trimHand(currPlayer, new Scanner(input1), new PrintWriter(output));
        String input2 = "10";
        game.trimHand(currPlayer, new Scanner(input2), new PrintWriter(output));
        assertTrue(output.toString().contains("P1 Hand: (1):F5 (2):F5 (3):F5 (4):F5 (5):F5 (6):WS10 (7):WS10 (8):WS10 (9):WS10 (10):WB15 (11):WE30 (12):WE30"));
    }

    @Test
    @DisplayName("RESP 12.1: Test to check that when prosperity is played, that it adds two cards to all users")
    void RESP_12_test_1() {
        Main game = new Main();
        game.initializeEventDeck();
        game.initializeAdventureDeck();
        game.initializePlayers();

        Main.player currPlayer = game.getCurrentPlayer();

        //Remove 4 cards from each player leaving them with 8
        for(int i = 0; i < 4; i++){
            game.players.get(0).playersHand.remove(0);
            game.players.get(1).playersHand.remove(0);
            game.players.get(2).playersHand.remove(0);
            game.players.get(3).playersHand.remove(0);
        }

        game.setEventCard("Prosperity", -1, 0);
        game.drawEventCard();

        game.playEvent();

        boolean p110 = game.getPlayerHandSize(game.players.get(0)) == 10;
        boolean p210 = game.getPlayerHandSize(game.players.get(1)) == 10;
        boolean p310 = game.getPlayerHandSize(game.players.get(2)) == 10;
        boolean p410 = game.getPlayerHandSize(game.players.get(3)) == 10;
        boolean eachPlayer10 = p110 && p210 && p310 && p410;

        assertEquals(true, eachPlayer10);
    }

    @Test
    @DisplayName("RESP 13.1: Test to check that the game has drawn a Q card and asks the user if they would like to sponsor the quest")
    void RESP_13_test_1(){
        Main game = new Main();
        game.initializeEventDeck();
        game.initializeAdventureDeck();
        game.initializePlayers();

        game.setEventCard("Q", 4, 0);
        game.drawEventCard();

        String input = "yes";
        StringWriter output = new StringWriter();

        game.startQuest(new Scanner(input), new PrintWriter(output));

        assertTrue(output.toString().contains("Would you like to sponsor the current quest?"));
    }

    @Test
    @DisplayName("RESP 14.1: Test to end the current users turn and move onto the next player")
    void RESP_14_test_1(){
        Main game = new Main();
        game.initializeEventDeck();
        game.initializeAdventureDeck();
        game.initializePlayers();

        //Game starts off with the current player being P1
        String input = "<return>";
        StringWriter output = new StringWriter();
        game.endPlayerTurn(new Scanner(input), new PrintWriter(output));

        assertEquals(game.players.get(1), game.getCurrentPlayer());
    }

    @Test
    @DisplayName("RESP 14.2: Test to check that when P4 turn is over, it goes back to P1")
    void RESP_14_test_2(){
        Main game = new Main();
        game.initializeEventDeck();
        game.initializeAdventureDeck();
        game.initializePlayers();

        game.currentPlayer = game.players.get(3);

        String input = "<return>";
        StringWriter output = new StringWriter();
        game.endPlayerTurn(new Scanner(input), new PrintWriter(output));

        assertEquals(game.players.get(0), game.getCurrentPlayer());
    }

    @Test
    @DisplayName("RESP 14.3: Test that hotseat display is cleared once <return> is entered")
    void RESP_14_test_3(){
        Main game = new Main();
        game.initializeEventDeck();
        game.initializeAdventureDeck();
        game.initializePlayers();

        String input = "<return>";
        StringWriter output = new StringWriter();
        game.endPlayerTurn(new Scanner(input), new PrintWriter(output));

        assertTrue(output.toString().contains("P1 turn has ended"));
    }

    @Test
    @DisplayName("RESP 15.1: Test that if someone has won at the end of the turn, display the winners")
    void RESP_15_test_1(){
        Main game = new Main();
        game.initializeEventDeck();
        game.initializeAdventureDeck();
        game.initializePlayers();

        game.players.get(3).numShields = 7;

        String input = "<return>";
        StringWriter output = new StringWriter();
        game.endPlayerTurn(new Scanner(input), new PrintWriter(output));

        assertTrue(output.toString().contains("Winner(s)!: P4"));
    }

    @Test
    @DisplayName("A-TEST JP-Scenario")
    void A_TEST_JP_SCENARIO(){
        Main game = new Main();
        game.initializeEventDeck();
        game.initializeAdventureDeck();
        game.initializePlayers();

        game.setAdventureCard(game.players.get(0),"F", "", 5, 0);
        game.setAdventureCard(game.players.get(0),"F", "", 5, 1);
        game.setAdventureCard(game.players.get(0),"F", "", 15, 2);
        game.setAdventureCard(game.players.get(0),"F", "", 15, 3);
        game.setAdventureCard(game.players.get(0),"W", "D", 5, 4);
        game.setAdventureCard(game.players.get(0),"W", "S", 10, 5);
        game.setAdventureCard(game.players.get(0),"W", "S", 10, 6);
        game.setAdventureCard(game.players.get(0),"W", "H", 10, 7);
        game.setAdventureCard(game.players.get(0),"W", "H", 10, 8);
        game.setAdventureCard(game.players.get(0),"W", "B", 15, 9);
        game.setAdventureCard(game.players.get(0),"W", "B", 15, 10);
        game.setAdventureCard(game.players.get(0),"W", "L", 20, 11);

        game.setAdventureCard(game.players.get(1),"F", "", 5, 0);
        game.setAdventureCard(game.players.get(1),"F", "", 5, 1);
        game.setAdventureCard(game.players.get(1),"F", "", 15, 2);
        game.setAdventureCard(game.players.get(1),"F", "", 15, 3);
        game.setAdventureCard(game.players.get(1),"F", "", 40, 4);
        game.setAdventureCard(game.players.get(1),"W", "D", 5, 5);
        game.setAdventureCard(game.players.get(1),"W", "S", 10, 6);
        game.setAdventureCard(game.players.get(1),"W", "H", 10, 7);
        game.setAdventureCard(game.players.get(1),"W", "H", 10, 8);
        game.setAdventureCard(game.players.get(1),"W", "B", 15, 9);
        game.setAdventureCard(game.players.get(1),"W", "B", 15, 10);
        game.setAdventureCard(game.players.get(1),"W", "E", 30, 11);

        game.setAdventureCard(game.players.get(2),"F", "", 5, 0);
        game.setAdventureCard(game.players.get(2),"F", "", 5, 1);
        game.setAdventureCard(game.players.get(2),"F", "", 5, 2);
        game.setAdventureCard(game.players.get(2),"F", "", 15, 3);
        game.setAdventureCard(game.players.get(2),"W", "D", 5, 4);
        game.setAdventureCard(game.players.get(2),"W", "S", 10, 5);
        game.setAdventureCard(game.players.get(2),"W", "S", 10, 6);
        game.setAdventureCard(game.players.get(2),"W", "S", 10, 7);
        game.setAdventureCard(game.players.get(2),"W", "H", 10, 8);
        game.setAdventureCard(game.players.get(2),"W", "H", 10, 9);
        game.setAdventureCard(game.players.get(2),"W", "B", 15, 10);
        game.setAdventureCard(game.players.get(2),"W", "L", 20, 11);

        game.setAdventureCard(game.players.get(3),"F", "", 5, 0);
        game.setAdventureCard(game.players.get(3),"F", "", 15, 1);
        game.setAdventureCard(game.players.get(3),"F", "", 15, 2);
        game.setAdventureCard(game.players.get(3),"F", "", 40, 3);
        game.setAdventureCard(game.players.get(3),"W", "D", 5, 4);
        game.setAdventureCard(game.players.get(3),"W", "D", 5, 5);
        game.setAdventureCard(game.players.get(3),"W", "S", 10, 6);
        game.setAdventureCard(game.players.get(3),"W", "H", 10, 7);
        game.setAdventureCard(game.players.get(3),"W", "H", 10, 8);
        game.setAdventureCard(game.players.get(3),"W", "B", 15, 9);
        game.setAdventureCard(game.players.get(3),"W", "L", 20, 10);
        game.setAdventureCard(game.players.get(3),"W", "E", 30, 11);

        game.setEventCard("Q",4,0);
        game.drawEventCard();
        game.sponsorPlayerNum = 1; //P2

        String input = "1";
        StringWriter output = new StringWriter();

        game.setAdventureCard(game.players.get(0),"F", "", 30, 12);
        game.trimHand(game.players.get(0), new Scanner(input), new PrintWriter(output));

        game.setAdventureCard(game.players.get(2),"W", "S", 5, 12);
        game.trimHand(game.players.get(2), new Scanner(input), new PrintWriter(output));

        game.setAdventureCard(game.players.get(3),"W", "B", 15, 12);
        game.trimHand(game.players.get(3), new Scanner(input), new PrintWriter(output));

    }

}