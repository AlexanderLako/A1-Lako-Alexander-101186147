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


}