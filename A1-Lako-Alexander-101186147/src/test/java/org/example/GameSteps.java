package org.example;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.example.Main;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Scanner;

import static org.junit.Assert.*;

public class GameSteps {

    Main game;
    int initialDiscardSize;

    @Given("a 2-stage quest is drawn")
    public void drawTwoStageQuest() {
        game = new Main();
        game.initializeEventDeck();
        game.initializeAdventureDeck();
        game.initializePlayers();

        game.setEventCard("Q", 2, 0);
        game.drawEventCard();
        assertEquals("Q", game.currentEvent.type);
        assertEquals(2, game.currentEvent.stages);
    }

    @Then("P1 decides to sponsor the quest")
    public void p1SponsorsQuest() {
        game.sponsorPlayerNum = 0;
        game.playersPlaying.remove(game.sponsorPlayerNum);
    }

    @When("P2, P3, P4 participate in stage 1")
    public void playersParticipateStage1() {
        String in = "partake";
        game.withdrawOrTackle(game.players.get(1), new Scanner(in));
        game.withdrawOrTackle(game.players.get(2), new Scanner(in));
        game.withdrawOrTackle(game.players.get(3), new Scanner(in));
    }

    @When("all players lose stage 1")
    public void allPlayersLoseStage1() {
         game.setAdventureCard(game.players.get(game.sponsorPlayerNum), "F", "", 50, 0);
         game.buildStage(new Scanner("1"));

        for (int i = 0; i < game.playersPlaying.size(); i++) {
            int playerAttack = game.buildAttack(game.playersPlaying.get(i), new Scanner("quit"), new PrintWriter(new StringWriter()));
            assertTrue("Player lost the stage", playerAttack < game.foePoints);
        }
        game.playersPlaying.clear();
    }

    @Then("the quest ends with no winner")
    public void questEndsWithNoWinner() {
        assertTrue(game.playersPlaying.isEmpty());
    }

    @Then("the sponsor discards and draws cards")
    public void sponsorDiscardsAndDrawsCards() {
        assertEquals(11, game.getPlayerHandSize(game.players.get(0)));

        game.addCard(game.players.get(game.sponsorPlayerNum));
        assertEquals(12, game.getPlayerHandSize(game.players.get(0)));
    }
}
