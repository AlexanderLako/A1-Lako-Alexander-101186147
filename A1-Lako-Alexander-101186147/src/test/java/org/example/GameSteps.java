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
        game.playersPlaying.remove(game.players.get(game.sponsorPlayerNum));
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

        assertEquals(0, game.getPlayerNumShields(game.players.get(0)));
        assertEquals(0, game.getPlayerNumShields(game.players.get(1)));
        assertEquals(0, game.getPlayerNumShields(game.players.get(2)));
        assertEquals(0, game.getPlayerNumShields(game.players.get(3)));
    }






    @Given("a 4-stage quest is drawn")
    public void draw4StageQuest() {
        game = new Main();
        game.initializeEventDeck();
        game.initializeAdventureDeck();
        game.initializePlayers();

        game.setEventCard("Q", 4, 0);
        game.drawEventCard();
        assertEquals("Q", game.currentEvent.type);
        assertEquals(4, game.currentEvent.stages);
    }


    @Then("all players win")
    public void playersWinAllStages() {

        for(int s = 0; s < game.currentEvent.stages; s++){
            game.setAdventureCard(game.players.get(game.sponsorPlayerNum), "F", "", 5, 0);
            game.buildStage(new Scanner("1"));

            for(int i = 0; i < game.playersPlaying.size(); i++){
                game.setAdventureCard(game.playersPlaying.get(i), "W", "B", 15, 0);
                int playerAttack = game.buildAttackOnce(game.playersPlaying.get(i), new Scanner("1"), new PrintWriter(new StringWriter()));
                assertTrue("Player won the stage", playerAttack >= game.foePoints);
            }
        }
        for(int i = 0; i < game.playersPlaying.size(); i++){
            game.players.get(game.players.indexOf(game.playersPlaying.get(i))).numShields += game.currentEvent.stages;
        }
    }

    @Then("P2, P3, P4 all earn 4 shields")
    public void playersEarnShields() {
        assertEquals(4, game.players.get(1).numShields);
        assertEquals(4, game.players.get(2).numShields);
        assertEquals(4, game.players.get(3).numShields);
    }

    @When("P2 draws plague and loses two shields")
    public void p2DrawsPlague() {
        game.currentPlayer = game.players.get(1);
        int initialShields = game.players.get(1).numShields;
        game.setEventCard("Plague", -1, 0);
        game.drawEventCard();
        game.playEvent();
        assertEquals(initialShields - 2, game.currentPlayer.numShields);
    }

    @When("P3 draws prosperity and everyone gets two cards")
    public void p3DrawsProsperity() {
        game.currentPlayer = game.players.get(2);
        int initialP1 = game.players.get(0).playersHand.size();
        int initialP2 = game.players.get(1).playersHand.size();
        int initialP3 = game.players.get(2).playersHand.size();
        int initialP4 = game.players.get(3).playersHand.size();

        game.setEventCard("Prosperity", -1, 0);
        game.drawEventCard();
        game.playEvent();

        assertEquals(initialP1 + 2, game.players.get(0).playersHand.size());
        assertEquals(initialP2 + 2, game.players.get(1).playersHand.size());
        assertEquals(initialP3 + 2, game.players.get(2).playersHand.size());
        assertEquals(initialP4 + 2, game.players.get(3).playersHand.size());
    }

    @When("P4 draws Queens favor and draws 2 adventure cards")
    public void p4DrawsQueensFavor() {
        int initialP4 = game.players.get(3).playersHand.size();
        game.currentPlayer = game.players.get(3);
        game.setEventCard("Queens favor", -1, 0);
        game.drawEventCard();
        game.playEvent();
        assertEquals(initialP4 + 2, game.players.get(3).playersHand.size());
    }

    @Given("P1 draws a 3-stage quest")
    public void p1DrawThreeStage() {
        game.currentPlayer = game.players.get(0);
        game.setEventCard("Q", 3, 0);
        game.drawEventCard();
        assertEquals("Q", game.currentEvent.type);
        assertEquals(3, game.currentEvent.stages);
    }

    @Then("P2, P3 wins, P4 loses")
    public void p2p3winp4lose() {
        for (int s = 0; s < game.currentEvent.stages; s++) {
            game.setAdventureCard(game.players.get(game.sponsorPlayerNum), "F", "", 15, 0);
            game.buildStage(new Scanner("1"));

            game.setAdventureCard(game.playersPlaying.get(0), "W", "B", 15, 0);
            game.setAdventureCard(game.playersPlaying.get(1), "W", "B", 15, 0);
            game.setAdventureCard(game.playersPlaying.get(2), "W", "S", 10, 0);

            int player2Attack = game.buildAttackOnce(game.playersPlaying.get(0), new Scanner("1"), new PrintWriter(new StringWriter()));
            int player3Attack = game.buildAttackOnce(game.playersPlaying.get(1), new Scanner("1"), new PrintWriter(new StringWriter()));
            int player4Attack = game.buildAttackOnce(game.playersPlaying.get(2), new Scanner("1"), new PrintWriter(new StringWriter()));

            assertTrue("Player won the stage", player2Attack >= game.foePoints);
            assertTrue("Player won the stage", player3Attack >= game.foePoints);
            assertTrue("Player lost the stage", player4Attack < game.foePoints);
        }
    }

    @When("P2 and P3 participate again")
    public void p2p3Participate(){
        String in = "partake";
        game.withdrawOrTackle(game.players.get(1), new Scanner(in));
        game.withdrawOrTackle(game.players.get(2), new Scanner(in));
    }


    @When("P2 and P3 win quest")
    public void p2AndP3WinStages2and3() {
        for (int s = 0; s < game.currentEvent.stages-1; s++) {
            game.setAdventureCard(game.players.get(game.sponsorPlayerNum), "F", "", 15, 0);
            game.buildStage(new Scanner("1"));

            game.setAdventureCard(game.playersPlaying.get(0), "W", "B", 15, 0);
            game.setAdventureCard(game.playersPlaying.get(1), "W", "B", 15, 0);

            int player2Attack = game.buildAttackOnce(game.playersPlaying.get(0), new Scanner("1"), new PrintWriter(new StringWriter()));
            int player3Attack = game.buildAttackOnce(game.playersPlaying.get(1), new Scanner("1"), new PrintWriter(new StringWriter()));

            assertTrue("Player won the stage", player2Attack >= game.foePoints);
            assertTrue("Player won the stage", player3Attack >= game.foePoints);
        }
    }

    @Then("P2 and P3 earn 3 shields")
    public void p2AndP3EarnShields() {
        int initShields1 = game.players.get(1).numShields;
        int initShields2 = game.players.get(2).numShields;
        game.players.get(1).numShields += game.currentEvent.stages;
        game.players.get(2).numShields += game.currentEvent.stages;
        assertEquals(initShields1 + game.currentEvent.stages, game.players.get(1).numShields);
        assertEquals(initShields2 + game.currentEvent.stages, game.players.get(2).numShields);
    }

    @Then("P3 is declared the winner")
    public void declareWinner() {
        assertTrue("P3 wins", game.players.get(2).numShields >= 7);
    }






    @Then("P2 and P4 wins, P3 loses")
    public void p2p4winp3loss(){
        for (int s = 0; s < game.currentEvent.stages; s++) {
            game.setAdventureCard(game.players.get(game.sponsorPlayerNum), "F", "", 15, 0);
            game.buildStage(new Scanner("1"));

            game.setAdventureCard(game.playersPlaying.get(0), "W", "B", 15, 0);
            game.setAdventureCard(game.playersPlaying.get(1), "W", "S", 10, 0);
            game.setAdventureCard(game.playersPlaying.get(2), "W", "B", 15, 0);

            int player2Attack = game.buildAttackOnce(game.playersPlaying.get(0), new Scanner("1"), new PrintWriter(new StringWriter()));
            int player3Attack = game.buildAttackOnce(game.playersPlaying.get(1), new Scanner("1"), new PrintWriter(new StringWriter()));
            int player4Attack = game.buildAttackOnce(game.playersPlaying.get(2), new Scanner("1"), new PrintWriter(new StringWriter()));

            assertTrue("Player won the stage", player2Attack >= game.foePoints);
            assertTrue("Player lost the stage", player3Attack < game.foePoints);
            assertTrue("Player won the stage", player4Attack >= game.foePoints);

        }
    }

    @When("P2 and P4 win quest")
    public void p2AndppWinStages() {
        for (int s = 0; s < game.currentEvent.stages-1; s++) {
            game.setAdventureCard(game.players.get(game.sponsorPlayerNum), "F", "", 15, 0);
            game.buildStage(new Scanner("1"));

            game.setAdventureCard(game.playersPlaying.get(0), "W", "B", 15, 0);
            game.setAdventureCard(game.playersPlaying.get(2), "W", "B", 15, 0);

            int player2Attack = game.buildAttackOnce(game.playersPlaying.get(0), new Scanner("1"), new PrintWriter(new StringWriter()));
            int player4Attack = game.buildAttackOnce(game.playersPlaying.get(2), new Scanner("1"), new PrintWriter(new StringWriter()));

            assertTrue("Player won the stage", player2Attack >= game.foePoints);
            assertTrue("Player won the stage", player4Attack >= game.foePoints);
        }
    }

    @Then("P2 and P4 earn 4 shields")
    public void p2AndP4Earn4Shields() {
        int initShields1 = game.players.get(1).numShields;
        int initShields2 = game.players.get(3).numShields;
        game.players.get(1).numShields += game.currentEvent.stages;
        game.players.get(3).numShields += game.currentEvent.stages;
        assertEquals(initShields1 + game.currentEvent.stages, game.players.get(1).numShields);
        assertEquals(initShields2 + game.currentEvent.stages, game.players.get(3).numShields);
    }

    @When("P2 draws a 3-stage quest")
    public void p2draws3stage(){
        game.currentPlayer = game.players.get(1);
        game.setEventCard("Q", 3, 0);
        game.drawEventCard();
        assertEquals("Q", game.currentEvent.type);
        assertEquals(3, game.currentEvent.stages);
    }

    @Then("P2 declines to sponsor quest")
    public void p2DeclineToSponsor(){
        game.nextPlayer();
    }

    @Then("P3 decides to sponsor the quest")
    public void p3Sponsor(){
        game.sponsorPlayerNum = 2;
        game.playersPlaying.remove(game.players.get(game.sponsorPlayerNum));
    }

    @Then("P1 declines to participate")
    public void p1DeclinesToParticipate(){
        String in = "withdraw";
        game.withdrawOrTackle(game.players.get(0), new Scanner(in));
        game.playersPlaying.remove(game.players.get(0));
        assertEquals(2, game.playersPlaying.size());
    }

    @Then("P2, P4 participate")
    public void p2p4Partake(){
        String in = "partake";
        game.withdrawOrTackle(game.players.get(1), new Scanner(in));
        game.withdrawOrTackle(game.players.get(3), new Scanner(in));
    }

    @When("P2 and P4 win the quest")
    public void p2AndP4WinQuest() {
        for (int s = 0; s < game.currentEvent.stages-1; s++) {
            game.setAdventureCard(game.players.get(game.sponsorPlayerNum), "F", "", 15, 0);
            game.buildStage(new Scanner("1"));

            game.setAdventureCard(game.playersPlaying.get(0), "W", "B", 15, 0);
            game.setAdventureCard(game.playersPlaying.get(1), "W", "B", 15, 0);

            int player2Attack = game.buildAttackOnce(game.playersPlaying.get(0), new Scanner("1"), new PrintWriter(new StringWriter()));
            int player4Attack = game.buildAttackOnce(game.playersPlaying.get(1), new Scanner("1"), new PrintWriter(new StringWriter()));

            assertTrue("Player won the stage", player2Attack >= game.foePoints);
            assertTrue("Player won the stage", player4Attack >= game.foePoints);
        }
    }

    @Then("P2 and P4 earn 3 shields")
    public void p2AndP4Earn3Shields() {
        int initShields1 = game.players.get(1).numShields;
        int initShields2 = game.players.get(3).numShields;
        game.players.get(1).numShields += game.currentEvent.stages;
        game.players.get(3).numShields += game.currentEvent.stages;
        assertEquals(initShields1 + game.currentEvent.stages, game.players.get(1).numShields);
        assertEquals(initShields2 + game.currentEvent.stages, game.players.get(3).numShields);
    }

    @Then("P2 and P4 are declared the winners")
    public void declareWinners() {
        assertTrue("P2 wins", game.players.get(1).numShields >= 7);
        assertTrue("P4 wins", game.players.get(3).numShields >= 7);
    }




}





