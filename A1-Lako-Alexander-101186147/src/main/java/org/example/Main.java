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
        StringWriter output = new StringWriter();
        Scanner input = new Scanner(System.in);

        game.setEventCard("Q",2,0);
        game.drawEventCard();
        game.displayEventCard(game.currentEvent, new PrintWriter(output));

       //game.playEvent();
        game.startQ();

//        if(game.getNumFoe(game.currentPlayer) < game.currentEvent.stages){
//            for(int i = 0; i < 4; i++){
//                if(game.getNumFoe(game.players.get(i)) >= game.currentEvent.stages){
//                    System.out.print(game.players.get(i).name + " has decided to sponsor the quest");
//                    game.sponsorPlayerNum = i;
//                    break;
//                }
//            }
//        }
//        //System.out.println("The current player has decided to sponsor the quest");
//
//
//        for(int i = 0; i < 2; i++){
//            StringWriter output0 = new StringWriter();
//            game.displayAdventureHand(game.currentPlayer, new PrintWriter(output0));
//            System.out.println(output0);
//            System.out.println("Which Foe would you like to choose?");
//            int cardNum = Integer.parseInt(input.nextLine());
//            adventureCard questCard = game.players.get(game.sponsorPlayerNum).playersHand.get(cardNum);
//            game.adventureDiscardPile.add(game.players.get(game.sponsorPlayerNum).playersHand.remove(cardNum));
//            System.out.println("Quest has begun (all players are playing)");
//
//            System.out.println("P1 turn");
//            game.addCard(game.players.get(0));
//            System.out.println("Which card would you like to use?");
//            StringWriter output1 = new StringWriter();
//            game.displayAdventureHand(game.players.get(0), new PrintWriter(output1));
//            System.out.println(output1);
//            cardNum = Integer.parseInt(input.nextLine());
//            if(game.players.get(0).playersHand.get(cardNum).value >= questCard.value){
//                System.out.println("Attack was strong enough");
//                game.players.get(0).numShields++;
//            }
//            else{
//                System.out.println("Attack was too weak");
//            }
//            game.adventureDiscardPile.add(game.players.get(3).playersHand.remove(cardNum));
//
//            System.out.println("P2 turn");
//            game.addCard(game.players.get(1));
//            System.out.println("Which card would you like to use?");
//            StringWriter output2 = new StringWriter();
//            game.displayAdventureHand(game.players.get(1), new PrintWriter(output2));
//            System.out.println(output2);
//            cardNum = Integer.parseInt(input.nextLine());
//            if(game.players.get(1).playersHand.get(cardNum).value >= questCard.value){
//                System.out.println("Attack was strong enough");
//                game.players.get(1).numShields++;
//            }
//            else{
//                System.out.println("Attack was too weak");
//            }
//            game.adventureDiscardPile.add(game.players.get(3).playersHand.remove(cardNum));
//
//            System.out.println("P3 turn");
//            game.addCard(game.players.get(2));
//            System.out.println("Which card would you like to use?");
//            StringWriter output3 = new StringWriter();
//            game.displayAdventureHand(game.players.get(2), new PrintWriter(output3));
//            System.out.println(output3);
//            cardNum = Integer.parseInt(input.nextLine());
//            if(game.players.get(2).playersHand.get(cardNum).value >= questCard.value){
//                System.out.println("Attack was strong enough");
//                game.players.get(2).numShields++;
//            }
//            else{
//                System.out.println("Attack was too weak");
//            }
//            game.adventureDiscardPile.add(game.players.get(3).playersHand.remove(cardNum));
//
//            System.out.println("P4 turn");
//            game.addCard(game.players.get(3));
//            System.out.println("Which card would you like to use?");
//            StringWriter output4 = new StringWriter();
//            game.displayAdventureHand(game.players.get(3), new PrintWriter(output4));
//            System.out.println(output4);
//            cardNum = Integer.parseInt(input.nextLine());
//            if(game.players.get(3).playersHand.get(cardNum).value >= questCard.value){
//                System.out.println("Attack was strong enough");
//                game.players.get(3).numShields++;
//            }
//            else{
//                System.out.println("Attack was too weak");
//            }
//            game.adventureDiscardPile.add(game.players.get(3).playersHand.remove(cardNum));
//        }
//
//
//        if(game.checkWinner()){
//            StringWriter outputWinner = new StringWriter();
//            game.displayWinners(new PrintWriter(outputWinner));
//            System.out.println(outputWinner);
//            System.exit(0);
//        }
//        else{
//            System.out.println("No one has enough shields to win yet!");
//        }




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
    player currentPlayer = new player("");

    eventCard currentEvent = new eventCard("");
    int sponsorPlayerNum = 0;

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
            addCard(currentPlayer);
            addCard(currentPlayer);
        }
        else if(currentEvent.type.equals("Prosperity")){
            addCard(players.get(0));
            addCard(players.get(0));
            addCard(players.get(1));
            addCard(players.get(1));
            addCard(players.get(2));
            addCard(players.get(2));
            addCard(players.get(3));
            addCard(players.get(3));
        }
        else if(currentEvent.type.equals("Q")){
            String input = "";
            StringWriter output = new StringWriter();
            startQuest(new Scanner(input), new PrintWriter(output));
        }
    }

    void startQ(){
        String inputStr = "yes";
        ArrayList<player> playersPlaying = new ArrayList<player>();
        Scanner s = new Scanner(System.in);
        System.out.println(currentPlayer.name + " Would you like to sponsor the current quest?");
        //System.out.println("Would you like to sponsor the current quest?");
        inputStr = s.nextLine();

        if(inputStr.toLowerCase().equals("yes")){
            System.out.println(currentPlayer.name + " has decided to sponsor the quest");
            sponsorPlayerNum = players.indexOf(currentPlayer);
        }

        System.out.println(" P2 Would you like to withdraw from the quest?");
        inputStr = s.nextLine();
        if(inputStr.toLowerCase().equals("yes")){

        }
        else{
            playersPlaying.add(players.get(1));
            addCard(players.get(1));
            if(getTrimAmount(players.get(1)) != 0){
                String input = "1";
                StringWriter output = new StringWriter();
                trimHand(players.get(1), new Scanner(input), new PrintWriter(output));
                //players.get(1).playersHand.removeFirst();
            }
        }

        System.out.println(" P3 Would you like to withdraw from the quest?");
        inputStr = s.nextLine();
        if(inputStr.toLowerCase().equals("yes")){

        }
        else{
            playersPlaying.add(players.get(2));
            addCard(players.get(2));
            if(getTrimAmount(players.get(2)) != 0){
                String input = "1";
                StringWriter output = new StringWriter();
                trimHand(players.get(2), new Scanner(input), new PrintWriter(output));
            }
        }

        System.out.println(" P4 Would you like to withdraw from the quest?");
        inputStr = s.nextLine();
        if(inputStr.toLowerCase().equals("yes")){

        }
        else{
            playersPlaying.add(players.get(2));
            addCard(players.get(3));
            if(getTrimAmount(players.get(3)) != 0){
                String input = "1";
                StringWriter output = new StringWriter();
                trimHand(players.get(3), new Scanner(input), new PrintWriter(output));
            }
        }

        System.out.println("Select the foe you would like to use for the quest");
        StringWriter output = new StringWriter();
        displayAdventureHand(currentPlayer, new PrintWriter(output));
        System.out.println(output);
        inputStr = s.nextLine();

        int foe1Points = currentPlayer.playersHand.get(Integer.parseInt(inputStr)-1).value;

        String input = "quit";
        StringWriter output1 = new StringWriter();
        buildAttack(players.get(1), new Scanner(input), new PrintWriter(output1));
        System.out.println(output1);
        inputStr = s.nextLine();
        int attack = buildAttack(players.get(1), new Scanner(inputStr), new PrintWriter(output1));

        if(attack < foe1Points){
            System.out.println("Attack failed");
            playersPlaying.remove(0);
        }
        else{
            System.out.println("Attack Worked!");
        }

        input = "quit";
        StringWriter output2 = new StringWriter();
        buildAttack(players.get(1), new Scanner(input), new PrintWriter(output2));
        System.out.println(output2);
        inputStr = s.nextLine();
        int attack2 = buildAttack(players.get(2), new Scanner(inputStr), new PrintWriter(output2));

        if(attack2 < foe1Points){
            System.out.println("Attack failed");
            playersPlaying.remove(0);
        }
        else{
            System.out.println("Attack Worked!");
        }

        input = "quit";
        StringWriter output3 = new StringWriter();
        buildAttack(players.get(3), new Scanner(input), new PrintWriter(output3));
        System.out.println(output3);
        inputStr = s.nextLine();
        int attack3 = buildAttack(players.get(3), new Scanner(inputStr), new PrintWriter(output3));

        if(attack2 < foe1Points){
            System.out.println("Attack failed");
            playersPlaying.remove(0);
        }
        else{
            System.out.println("Attack Worked!");
        }

        System.out.println("Select the foe you would like to use for the quest");
        StringWriter output4 = new StringWriter();
        displayAdventureHand(currentPlayer, new PrintWriter(output4));
        System.out.println(output4);
        inputStr = s.nextLine();
        int foe2Points = currentPlayer.playersHand.get(Integer.parseInt(inputStr)-1).value;

        input = "quit";
        StringWriter output11 = new StringWriter();
        buildAttack(players.get(1), new Scanner(input), new PrintWriter(output11));
        System.out.println(output11);
        inputStr = s.nextLine();
        int attack11 = buildAttack(players.get(1), new Scanner(inputStr), new PrintWriter(output11));

        if(attack11 < foe2Points){
            System.out.println("Attack failed");
            //playersPlaying.remove(0);
        }
        else{
            System.out.println("Attack Worked!");
        }

        input = "quit";
        StringWriter output22 = new StringWriter();
        buildAttack(players.get(1), new Scanner(input), new PrintWriter(output22));
        System.out.println(output22);
        inputStr = s.nextLine();
        int attack22 = buildAttack(players.get(2), new Scanner(inputStr), new PrintWriter(output22));

        if(attack22 < foe2Points){
            System.out.println("Attack failed");
            //playersPlaying.remove(0);
        }
        else{
            System.out.println("Attack Worked!");
        }

        input = "quit";
        StringWriter output33 = new StringWriter();
        buildAttack(players.get(3), new Scanner(input), new PrintWriter(output33));
        System.out.println(output33);
        inputStr = s.nextLine();
        int attack33= buildAttack(players.get(3), new Scanner(inputStr), new PrintWriter(output33));

        if(attack33 < foe2Points){
            System.out.println("Attack failed");
            //playersPlaying.remove(0);
        }
        else{
            System.out.println("Attack Worked!");
        }

        if(checkWinner()){
            StringWriter win = new StringWriter();
            displayWinners(new PrintWriter(win));
        }
        else{
            String in = "<return>";
            StringWriter out = new StringWriter();
            endPlayerTurn(new Scanner(in), new PrintWriter(out));
        }

    }


    void startQuest(Scanner input, PrintWriter output){
        output.println("Would you like to sponsor the current quest?"); output.flush();
        String inputStr = input.nextLine();
    }

    void endPlayerTurn(Scanner input, PrintWriter output){
        output.println("Please enter the <return> key to end your turn"); output.flush();
        String inputStr = input.nextLine();
        if(inputStr.isEmpty() || inputStr.equals("<return>")){
            output.println(currentPlayer.name + " turn has ended"); output.flush();
        }

        if(checkWinner()){
            displayWinners(output);
        }
        else{
            if(currentPlayer.name.equals("P1")){
                currentPlayer = players.get(1);
            }
            else if(currentPlayer.name.equals("P2")){
                currentPlayer = players.get(2);
            }
            else if(currentPlayer.name.equals("P3")){
                currentPlayer = players.get(3);
            }
            else if(currentPlayer.name.equals("P4")){
                currentPlayer = players.get(0);
            }

            output.println("It is now " + currentPlayer.name + " turn"); output.flush();
            displayAdventureHand(currentPlayer, output);
        }
    }

    void displayAdventureHand(player p, PrintWriter output){
        output.print(p.name + " Hand:");
        for(int i = 0; i < getPlayerHandSize(p); i++){
            output.print(" ("+(i+1)+"):" + p.playersHand.get(i).type + p.playersHand.get(i).category + p.playersHand.get(i).value);
        }
        output.flush();
    }

    void trimHand(player p, Scanner input, PrintWriter output){
        output.println("Enter the number of the card you would to delete: ");
        String inputStr = input.nextLine();

        int inputNum = -1;
        try {
            inputNum = Integer.parseInt(inputStr);
        } catch(NumberFormatException e){
            output.println("invalid input"); output.flush();
            return;
        }

        if(inputNum > getPlayerHandSize(p)){
            output.println("invalid input"); output.flush();
            return;
        }

        adventureDiscardPile.add(p.playersHand.remove(inputNum-1));
        displayAdventureHand(p, output);
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

    void sponsor(){

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
        String inputStr = "";

        displayAdventureHand(p, output);

        output.println("Enter the card number you would like to use for your attack OR enter 'quit'"); //output.flush();
        inputStr = input.nextLine();

        if(inputStr.toLowerCase().equals("quit")){
            return attackPower;
        }

        int inputNum = Integer.parseInt(inputStr);
        attackPower += p.playersHand.get(inputNum-1).value;
        adventureDiscardPile.add(p.playersHand.remove(inputNum-1));

        return attackPower;
    }

}