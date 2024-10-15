package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.PrintWriter;
import java.io.StringWriter;

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
}