package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
}