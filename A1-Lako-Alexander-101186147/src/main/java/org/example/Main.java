package org.example;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        Main game = new Main();
        game.initializeEventDeck();
        game.initializeAdventureDeck();
        game.initializePlayers();

//        game.setEventCard("Q",2,0);
//
//        while(!game.checkWinner()){
//            StringWriter output = new StringWriter();
//            Scanner input = new Scanner(System.in);
//
//            game.drawEventCard();
//            game.displayEventCard(game.currentEvent, new PrintWriter(output));
//            System.out.println(output);
//
//            game.playEvent();
//            if(game.checkWinner()){break;}
//
//            while(game.players.get(game.sponsorPlayerNum).playersHand.size() < 12){
//                game.addCard(game.players.get(game.sponsorPlayerNum));
//
//            }
//
//            String in = "<return>";
//            StringWriter out = new StringWriter();
//            game.endPlayerTurn(new Scanner(in), new PrintWriter(out));
//            System.out.println(out);
//
//        }
//
//        StringWriter win = new StringWriter();
//        game.displayWinners(new PrintWriter(win));
//        System.out.println(win);



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
    ArrayList<player> playersPlaying = new ArrayList<>();
    player currentPlayer = new player("");
    ArrayList<player> temp = new ArrayList<>();

    eventCard currentEvent = new eventCard("");
    int sponsorPlayerNum = 0;
    int foePoints = 0;

    int numPlayers = 4;
    int handSize = 12;
    boolean terminate = false;

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
            playersPlaying.add(p);

            while (getPlayerHandSize(p) < handSize) {
                addCard(p);
            }
        }
        currentPlayer = players.getFirst();
    }
    
    void addCard(player p){
        adventureCard card = adventureDeck.removeLast();
        p.playersHand.add(card);
    }

    boolean checkWinner(){
        for(int i = 0; i < numPlayers; i++){
            if(getPlayerNumShields(players.get(i)) >= 7){
                return true;
            }
        }
        return false;
    }

    void displayWinners(PrintWriter output){
        String winnerNames = "";
        for(int i = 0; i < numPlayers; i++){
            if(getPlayerNumShields(players.get(i)) >= 7){
                winnerNames += players.get(i).name + " ";
            }
        }
        output.println("Winner(s)!: " + winnerNames); output.flush();
        terminate = true;
    }

    void drawEventCard(){
        currentEvent = eventDeck.removeFirst();
    }

    void displayEventCard(eventCard eCard, PrintWriter output){
        if(eCard.stages == -1){
            output.println("Current Event Card: " + eCard.type); output.flush();
        }
        else{
            output.println("Current Event Card: " + eCard.type + eCard.stages); output.flush();
        }
    }

    void playEvent(){
        if(currentEvent.type.equals("Plague")){
            currentPlayer.numShields -= 2;
            if(getPlayerNumShields(currentPlayer) <= 0){
                currentPlayer.numShields = 0;
            }
        }
        else if(currentEvent.type.equals("Queens favor")){
            StringWriter output = new StringWriter();
            String input = "";
            addCard(currentPlayer);
            addCard(currentPlayer);
            trimHand(currentPlayer, new Scanner(input), new PrintWriter(output));
        }
        else if(currentEvent.type.equals("Prosperity")){
            StringWriter output0 = new StringWriter();
            StringWriter output1 = new StringWriter();
            StringWriter output2 = new StringWriter();
            StringWriter output3 = new StringWriter();
            String input = "";

            addCard(players.get(0));
            addCard(players.get(0));
            trimHand(playersPlaying.get(0), new Scanner(input), new PrintWriter(output0));

            addCard(players.get(1));
            addCard(players.get(1));
            trimHand(playersPlaying.get(1), new Scanner(input), new PrintWriter(output1));

            addCard(players.get(2));
            addCard(players.get(2));
            trimHand(playersPlaying.get(2), new Scanner(input), new PrintWriter(output2));

            addCard(players.get(3));
            addCard(players.get(3));
            trimHand(playersPlaying.get(3), new Scanner(input), new PrintWriter(output3));
        }
        else if(currentEvent.type.equals("Q")){
            startQ();
        }
    }

    void nextPlayer(){
        if(players.indexOf(currentPlayer) == 0){
            currentPlayer = players.get(1);
            return;
        }
        else if(players.indexOf(currentPlayer) == 1){
            currentPlayer = players.get(2);
            return;
        }
        else if(players.indexOf(currentPlayer) == 2){
            currentPlayer = players.get(3);
            return;
        }
        else if(players.indexOf(currentPlayer) == 3){
            currentPlayer = players.get(0);
            return;
        }
    }

    int findSponsor(){
        String inputStr = "yes";
        Scanner s = new Scanner(System.in);

        for(int i = 0; i < 4; i++) {

            System.out.println(currentPlayer.name + " Would you like to sponsor the current quest?");
            inputStr = s.nextLine();

            if (inputStr.toLowerCase().equals("yes")) {
                System.out.println(currentPlayer.name + " has decided to sponsor the quest");
                sponsorPlayerNum = players.indexOf(currentPlayer);
                return 1;
            } else {
                nextPlayer();
            }
        }
        return 0;
    }

    void withdrawOrTackle(player p, Scanner input){
        Scanner s = new Scanner(System.in);
        String inputStr = input.nextLine();

        if(inputStr.equals("withdraw")){
            temp.add(playersPlaying.get(playersPlaying.indexOf(p)));
            return;
        }
        else if(inputStr.equals("partake")){
            addCard(p);
            return;
            //String in = "1";
            //StringWriter output = new StringWriter();
            //trimHand(p, new Scanner(in), new PrintWriter(output));
        }


            System.out.println(p.name + " Would you like to withdraw from the quest?");
            inputStr = s.nextLine();
            if(inputStr.toLowerCase().equals("yes")){
                temp.add(playersPlaying.get(playersPlaying.indexOf(p)));
            }
            else {
                addCard(p);
                String in = "1";
                StringWriter output = new StringWriter();
                trimHand(p, new Scanner(in), new PrintWriter(output));
            }

    }

    void buildStage(Scanner input){
        Scanner s = new Scanner(System.in);
        String inputStr = input.nextLine();

        if(!inputStr.equals("-1")){
            foePoints = players.get(sponsorPlayerNum).playersHand.get(Integer.parseInt(inputStr)-1).value;
            players.get(sponsorPlayerNum).playersHand.remove(Integer.parseInt(inputStr)-1);
            return;
        }

        StringWriter out = new StringWriter();
        displayAdventureHand(players.get(sponsorPlayerNum), new PrintWriter(out));
        System.out.println(out);

        System.out.println("Select the foe you would like to use for this stage");
        inputStr = s.nextLine();
        foePoints = players.get(sponsorPlayerNum).playersHand.get(Integer.parseInt(inputStr)-1).value;
        System.out.println(foePoints);

        players.get(sponsorPlayerNum).playersHand.remove(Integer.parseInt(inputStr)-1);
    }



    void startQ(){
        String inputStr = "yes";
        Scanner s = new Scanner(System.in);
        player startingPlayer = currentPlayer;

        int foundSponsor = findSponsor();
        if(foundSponsor == 0){
            System.out.println("No one has decided to sponsor the quest!");
            return;
        }

        playersPlaying.remove(sponsorPlayerNum);

        String input = "";
        for(int i = 0; i < playersPlaying.size(); i++){
            withdrawOrTackle(playersPlaying.get(i), new Scanner(input));
        }
        for(int k = 0; k < temp.size(); k++){
            playersPlaying.remove(playersPlaying.indexOf(temp.get(k)));
        }
        temp.clear();


        for(int i = 0; i < currentEvent.stages; i++){
            if(playersPlaying.isEmpty()){break;}

            buildStage(new Scanner("-1"));

            ArrayList<player> temp = new ArrayList<>();

            for(int j = 0; j < playersPlaying.size(); j++){
                StringWriter out2 = new StringWriter();
                String input1 = "0";
                int attackPower = buildAttack(playersPlaying.get(j), new Scanner(input1), new PrintWriter(out2));

                if(attackPower < foePoints){
                    System.out.println("Attack failed!");
                    player tempPlayer = playersPlaying.get(i);
                    temp.add(tempPlayer);
                }
                else{
                    System.out.println("Attack worked!");
                }
            }

            for(int l = 0; l < temp.size(); l++){
                playersPlaying.remove(temp.get(l));
            }
        }

        System.out.println("Quest over!");
        for(int i = 0; i < playersPlaying.size(); i++){
            players.get(players.indexOf(playersPlaying.get(i))).numShields += currentEvent.stages;
        }

        currentPlayer = startingPlayer;
        playersPlaying.clear();
        for(int j = 0; j < 4; j++){
            playersPlaying.add(players.get(j));
        }


    }

    void endPlayerTurn(Scanner input, PrintWriter output){
        Scanner s = new Scanner(System.in);
        output.println("Please enter the <return> key to end your turn"); output.flush();
        System.out.println("Please enter the <return> key to end your turn");
        String inputStr = s.nextLine();

        if(inputStr.isEmpty() || inputStr.equals("<return>")){
            output.println(currentPlayer.name + " turn has ended"); output.flush();
        }

        nextPlayer();

        output.println("It is now " + currentPlayer.name + " turn"); output.flush();
    }

    void displayAdventureHand(player p, PrintWriter output){
        output.print(p.name + " Hand:"); output.flush();
        for(int i = 0; i < getPlayerHandSize(p); i++){
            output.print(" ("+(i+1)+"):" + p.playersHand.get(i).type + p.playersHand.get(i).category + p.playersHand.get(i).value);
        }
        output.flush();
    }

    void trimHand(player p, Scanner input, PrintWriter output){

        Scanner s = new Scanner(System.in);

        StringWriter out = new StringWriter();
        displayAdventureHand(p, new PrintWriter(out));
        System.out.println(out);

        while(getPlayerHandSize(p) > 12){
            //output.println("Enter the number of the card you would to delete: ");
            System.out.println("Enter the number of the card you would to delete: ");
            String inputStr = s.nextLine();

            int inputNum = -1;
            try {
                inputNum = Integer.parseInt(inputStr);
            } catch(NumberFormatException e){
                output.println("invalid input"); output.flush();
                return;
            }

            if(inputNum > getPlayerHandSize(p)){
                output.println("invalid input"); output.flush();
            }
            else{
                adventureDiscardPile.add(p.playersHand.remove(inputNum-1));
                StringWriter out1 = new StringWriter();
                displayAdventureHand(p, new PrintWriter(out1));
                System.out.println(out1);
            }
        }
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

    int getPlayerNumShields(player p){
        return p.numShields;
    }

    player getCurrentPlayer(){
        return currentPlayer;
    }

    int getTrimAmount(player p){
        if(getPlayerHandSize(p) - 12 <= 0){
            return 0;
        }
        else{
            return getPlayerHandSize(p) - 12;
        }
    }

    void setEventCard(String t, int s, int position){
        int index = -1;

        for(int i = 0; i < getEventDeckSize(); i++){
            if(eventDeck.get(i).type.equals(t) && eventDeck.get(i).stages == s){
                index = i;
                break;
            }
        }
        Collections.swap(eventDeck, position, index);
    }

    void setAdventureCard(player p, String t, String c, int v, int position){
        adventureCard aCard = new adventureCard(t, c, v);
        if(position > getPlayerHandSize(p)-1){
            p.playersHand.add(aCard);
        }
        else{
            p.playersHand.remove(position);
            p.playersHand.add(position, aCard);
        }
    }

    int getNumFoe(player p){
        int numFoes = 0;
        for(int i = 0; i < getPlayerHandSize(p); i++){
            if(p.playersHand.get(i).type.equals("F")){
                numFoes++;
            }
        }
        return numFoes;
    }

    int buildAttack(player p, Scanner input, PrintWriter output){
        int attackPower = 0;
        String inputStr = input.nextLine();
        Scanner s = new Scanner(System.in);

        //output.println("Enter the card number you would like to use for your attack OR enter 'quit'"); //output.flush();
        while(!inputStr.equals("quit")){

            StringWriter out = new StringWriter();
            displayAdventureHand(p, new PrintWriter(out));
            System.out.println(out);

            System.out.println("Enter the card number you would like to use for your attack OR enter 'quit'");
            inputStr = s.nextLine();

            if(inputStr.toLowerCase().equals("quit")){
                return attackPower;
            }

            int inputNum = Integer.parseInt(inputStr);
            attackPower += p.playersHand.get(inputNum-1).value;
            adventureDiscardPile.add(p.playersHand.remove(inputNum-1));
        }


        return attackPower;
    }

}