package com.example.Text_Blackjack;

import java.util.*;
import java.util.Random;
import java.util.Arrays;

public class Blackjack {
    final private String[] suits = {"C", "D", "H", "S"};
    final private String[] cards = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
    final private String[] faceCards = {"J", "Q", "K"};


    public ArrayList<String> createDeck(int totalDecks){ //ArrayList
        ArrayList<String> deck = new ArrayList<String>();
        for (int i = 0; i < totalDecks; i++) {
            for (String suit : suits) {
                for (String card : cards) {
                    deck.add(card + suit);
                }
            }
        }
        return deck;
    }

    public static String[] shuffleArray(String[] deck){ //Classic Array
        Random rand = new Random();
        int arrLen = deck.length;

        for (int i = arrLen - 1; i >= 0; i--) {
            if (i <= 0) {
                break;
            }
            String placehold = deck[i];
            int randNum = rand.nextInt(i);
            deck[i] = deck[randNum];
            deck[randNum] = placehold;
        }
        return deck;
    }

    public static ArrayList<String> shuffleArray(ArrayList<String> deck){ // ArrayList
        Random rand = new Random();
        int arrLen = deck.size();

        for (int i = arrLen - 1; i >= 0; i--) {
            if (i <= 0) {
                break;
            }
            String placehold = deck.get(i);
            int randNum = rand.nextInt(i);
            deck.set(i, deck.get(randNum));
            deck.set(randNum, placehold);
        }
        return deck;
    }

    //algorithm to calculate value of hand if an array
    public int handValue(ArrayList<String> hand){
        int total = 0;
        int aceCount = 0;
        for (String i : hand){
            boolean faceCard = Arrays.asList(this.faceCards).contains(i.substring(0,1)); // is face card in faceCards array?
            if ((i.length() == 3) || faceCard ){ // if face or 10 card
                total += 10;
            } else if (!i.substring(0,1).equals("A")){ // if not and Ace
                total += Integer.parseInt(i.substring(0,1));
            } else {
                aceCount++;
            }
        }
        if (aceCount > 0) {
            total = aceParse(total,aceCount);
        }
        return total;
    }

    //algorithm to calculate value of hand if a string
    public int handValue(String[] hand){
        int total = 0;
        int aceCount = 0;
        for (String i : hand){
            boolean faceCard = Arrays.asList(this.faceCards).contains(i.substring(0,1)); // is face card in faceCards array?

            if ((i.length() == 3) || faceCard ){ // if face or 10 card
                total += 10;
            } else if (!i.substring(0,1).equals("A")){ // if not and Ace
                total += Integer.parseInt(i.substring(0,1));
            } else {
                aceCount++;
            }
        }
            if (aceCount > 0) {
                total = aceParse(total,aceCount);
            }
        return total;
    }

    //algorithm to calculate value of hand when aces are included
    private static int aceParse(int handTotal, int aceCount){
        int uBound = ((aceCount * 1)-1)+11+handTotal; //highest amount hand can be with Aces
        if (uBound > 21){
            return (aceCount * 1) + handTotal; //lowest amount hand can be with aces
        }
        return uBound;
    }

    public static void firstDeal(ArrayList<String> deck,ArrayList<String>  dealerHand,ArrayList<String>  playerHand){
        //alternates dealing each card for the starting hands for dealer and player
        for (int i = 0; i < 4; i++) {
            if (i % 2 == 0 ){
                playerHand.add(deck.get(0));
            } else {
                dealerHand.add(deck.get(0));
            }
            deck.remove(0);
        }
    }

    public void showHands(ArrayList<String>  dealerHand, ArrayList<String>  playerHand, boolean playerTurn){
        String[] dealerHidden = {dealerHand.get(1)};
        if (playerTurn){  // if it is the player's turn, only show dealer's second card and high value of second card
            Graphics.showDealerHiddenHand(dealerHand);
            System.out.println("Dealer shows ?, " + dealerHand.get(1) + " - total of " + this.handValue(dealerHidden));
        } else {
            Graphics.showVisualHand(dealerHand);
            System.out.println("Dealer shows, " + dealerHand + " - total of " +  this.handValue(dealerHand));

        }
        Graphics.showVisualHand(playerHand);
        System.out.println("Player shows " + playerHand + " - total of "+ this.handValue(playerHand)+"\n");
    }

    public void hit(ArrayList<String> deck, ArrayList<String> hand){
        hand.add(deck.get(0));
        deck.remove(0);
    }

    public ArrayList<String> gameOutcome(ArrayList<String> deck, ArrayList<String>  dealerHand, ArrayList<String>  playerHand, boolean playerTurn) {
        if (handValue(playerHand) > 21) { // if player busts
            showHands( dealerHand, playerHand, playerTurn);
            System.out.println("PLAYER BUSTS, DEALER WINS :(");
            return deck;
        }

        if (playerHand.size() == 2 && handValue(playerHand) == 21) { //if player has Blackjack
            showHands(dealerHand, playerHand, playerTurn);
            if (dealerHand.size() == 2 && handValue(dealerHand) == 21) {
                System.out.println("BLACKJACK PUSH!!! TIE GAME");
                return deck;
            } else {
                System.out.println("PLAYER BLACKJACK!!!! YOU WIN");
                return deck;
            }
        }

        if (dealerHand.size() == 2 && handValue(dealerHand) == 21) { // if dealer has blackjack
            showHands(dealerHand, playerHand, playerTurn);

            System.out.println("DEALER BLACKJACK!!!! DEALER WINS");
            return deck;
        }

        while (this.handValue(dealerHand) < 17) { //have dealer continue to hit while hand is less than 17
            hit(deck, dealerHand);
        }
        showHands(dealerHand, playerHand, playerTurn);

        if (handValue(dealerHand) > 21){
            System.out.println("DEALER BUSTS, YOU WIN :(");

        } else if (handValue(dealerHand) == handValue(playerHand)) {
            System.out.println("PUSH, TIE GAME");
        } else if (handValue(dealerHand) > handValue(playerHand)){
            System.out.println("DEALER WINS");
        } else {
            System.out.println("YOU WIN");
        }
        return deck;
    }
}
