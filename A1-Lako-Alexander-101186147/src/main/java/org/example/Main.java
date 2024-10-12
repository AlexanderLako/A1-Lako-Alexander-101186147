package org.example;

import java.util.ArrayList;
import java.util.Collections;


public class Main {

    public static void main(String[] args) {

    }

    class adventureCard{
        String type; // Foe, Weapon
        String category; // Daggers, Horses, Swords, Battle-Axes, Lances, Excaliburs
        int value;

        public adventureCard(String t, String c, int v){
            this.type = t;
            this.category = c;
            this.value = v;
        }
    }

    class eventCard{
        String type; // Quest, Event
        int stages;

        public eventCard(String t){
            this.type = t;
            this.stages = -1;
        }

        public eventCard(String t, int s){
            this.type = t;
            this.stages = s;
        }
    }

    class player{
        String name;
        ArrayList<adventureCard> playersHand = new ArrayList<adventureCard>();
        int numShields;

        public player(String n){
            this.name = n;
            numShields = 0;
        }
    }

    ArrayList<adventureCard> adventureDeck = new ArrayList<adventureCard>();
    ArrayList<eventCard> eventDeck = new ArrayList<eventCard>();

    ArrayList<adventureCard> adventureDiscardPile = new ArrayList<adventureCard>();
    ArrayList<eventCard> eventDiscardPile = new ArrayList<eventCard>();

    ArrayList<player> players = new ArrayList<player>();

    int numPlayers = 4;
    int handSize = 12;

    //Initializes an adventure card array with all foe and weapon cards. Shuffle Deck
    void initializeAdventureDeck(){
        adventureDeck = new ArrayList<adventureCard>();

        //Add all foes to the adventure deck
        for(int i = 0; i < 8; i++){
            adventureCard aCard5 = new adventureCard("F", "", 5);
            adventureCard aCard15 = new adventureCard("F", "", 15);
            adventureDeck.add(aCard5);
            adventureDeck.add(aCard15);
        }

        for(int i = 0; i < 7; i++){
            adventureCard aCard10 = new adventureCard("F", "", 10);
            adventureCard aCard20 = new adventureCard("F", "", 20);
            adventureCard aCard25 = new adventureCard("F", "", 25);
            adventureDeck.add(aCard10);
            adventureDeck.add(aCard20);
            adventureDeck.add(aCard25);
        }

        for(int i = 0; i < 4; i++){
            adventureCard aCard30 = new adventureCard("F", "", 30);
            adventureCard aCard35 = new adventureCard("F", "", 35);
            adventureDeck.add(aCard30);
            adventureDeck.add(aCard35);
        }

        adventureCard aCard401 = new adventureCard("F", "", 40);
        adventureCard aCard402 = new adventureCard("F", "", 40);
        adventureDeck.add(aCard401);
        adventureDeck.add(aCard402);

        adventureCard aCard501 = new adventureCard("F", "", 50);
        adventureCard aCard502 = new adventureCard("F", "", 50);
        adventureDeck.add(aCard501);
        adventureDeck.add(aCard502);

        adventureCard aCard70 = new adventureCard("F", "", 70);
        adventureDeck.add(aCard70);

        //Add all the weapons to the adventure deck
        for(int i = 0; i < 6; i++){
            adventureCard dagger = new adventureCard("W", "D", 5);
            adventureCard lance = new adventureCard("W", "L", 20);
            adventureDeck.add(dagger);
            adventureDeck.add(lance);
        }

        for(int i = 0; i < 12; i++){
            adventureCard horse = new adventureCard("W", "H", 10);
            adventureDeck.add(horse);
        }

        for(int i = 0; i < 16; i++){
            adventureCard sword = new adventureCard("W", "S", 10);
            adventureDeck.add(sword);
        }

        for(int i = 0; i < 8; i++){
            adventureCard battleaxe = new adventureCard("W", "B", 15);
            adventureDeck.add(battleaxe);
        }

        adventureCard excalibur1 = new adventureCard("W", "E", 30);
        adventureCard excalibur2 = new adventureCard("W", "E", 30);
        adventureDeck.add(excalibur1);
        adventureDeck.add(excalibur2);

        //Shuffle adventure deck
        Collections.shuffle(adventureDeck);


    }

    //Initializes an event card array with all quest and event cards. Shuffle Deck
    void initializeEventDeck(){
        eventDeck = new ArrayList<eventCard>();

        eventCard ePlague = new eventCard("Plague");
        eventCard eQueen1 = new eventCard("Queens favor");
        eventCard eQueen2 = new eventCard("Queens favor");
        eventCard eProsperity1 = new eventCard("Prosperity");
        eventCard eProsperity2 = new eventCard("Prosperity");
        eventDeck.add(ePlague);
        eventDeck.add(eQueen1);
        eventDeck.add(eQueen2);
        eventDeck.add(eProsperity1);
        eventDeck.add(eProsperity2);

        for(int i = 0; i < 3; i++){
            eventCard q2 = new eventCard("Q", 2);
            eventCard q4 = new eventCard("Q", 4);
            eventDeck.add(q2);
            eventDeck.add(q4);
        }

        for(int i = 0; i < 4; i++){
            eventCard q3 = new eventCard("Q", 3);
            eventDeck.add(q3);
        }

        eventCard q51 = new eventCard("Q", 5);
        eventCard q52 = new eventCard("Q", 5);
        eventDeck.add(q51);
        eventDeck.add(q52);

        Collections.shuffle(eventDeck);
    }

    void initializePlayers(){

        for(int i = 0; i < numPlayers; i++){
            player p = new player("P" + Integer.toString(i+1));
            players.add(p);

            while (getPlayerHandSize(p) < handSize) {
                addCard(p);
            }
        }
    }
    
    void addCard(player p){
        adventureCard card = adventureDeck.removeLast();
        p.playersHand.add(card);
    }

    int getAdventureDeckSize(){
        return adventureDeck.size();
    }

    int getEventDeckSize(){
        return eventDeck.size();
    }

    int getAdventureDiscardSize(){
        return adventureDiscardPile.size();
    }

    int getEventDiscardSize(){
        return eventDiscardPile.size();
    }

    int getPlayerHandSize(player p){
        return p.playersHand.size();
    }


}