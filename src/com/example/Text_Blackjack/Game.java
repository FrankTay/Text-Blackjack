package com.example.Text_Blackjack;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    public static void startGame() {
        Scanner userInp = new Scanner(System.in);
        Blackjack blackjack = new Blackjack();
        String letterTyped;
        int totalDecks = 2;
        ArrayList<String> dealerHand = new ArrayList<String>();
        ArrayList<String> playerHand = new ArrayList<String>();
        ArrayList<String> newDeck = blackjack.shuffleArray(blackjack.createDeck(totalDecks)); //create new shuffled deck

        System.out.println("D for Deal or Q to quit - (Deck count: " + newDeck.size() + ")");
        letterTyped = userInp.nextLine();

        while (!letterTyped.equalsIgnoreCase("q")) {

            if (letterTyped.equalsIgnoreCase("d")) {
                boolean playerTurn = true;
                blackjack.firstDeal(newDeck, dealerHand, playerHand); //deal
                blackjack.showHands(dealerHand, playerHand, playerTurn);

                while (playerTurn) { // during the player's turn

                    if (blackjack.handValue(playerHand) >= 21) { // if player has blackjack, 21, or busts, automatically end turn
                        playerTurn = false;
                        break;
                    }

                    System.out.println();
                    System.out.println("H for hit, S for Stand, Q for quit");
                    letterTyped = userInp.nextLine();
                    if (letterTyped.equalsIgnoreCase("q")) { // quit game
                        System.exit(0);
                    } else if (letterTyped.equalsIgnoreCase("h")) { //player hits
                        blackjack.hit(newDeck, playerHand);
                        blackjack.showHands(dealerHand, playerHand, playerTurn);
                    } else if (letterTyped.equalsIgnoreCase("s")) { //player stands
                        playerTurn = false; // move on to dealer turn
                    }
                }
                //runs when player stands or busts
                blackjack.gameOutcome(newDeck, dealerHand, playerHand, playerTurn); //check dealer hand.
            }

            // process clearing hands and new deck
            playerHand.clear();
            dealerHand.clear();
            if (newDeck.size() < 60) // declare new shuffled deck if less than 60 cards are available
                newDeck = blackjack.shuffleArray(blackjack.createDeck(totalDecks));

            System.out.println();
            System.out.println("New Game? D for Deal or Q to quit - (Deck count: " + newDeck.size() + ")");
            letterTyped = userInp.nextLine();
        }
        System.out.println("GAME WAS ENDED!!!!!!");
    }
}
