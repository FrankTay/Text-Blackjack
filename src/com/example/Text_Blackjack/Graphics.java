package com.example.Text_Blackjack;

import java.util.ArrayList;
import java.util.HashMap;

public class Graphics {
    public static HashMap<String, String> suitsMap(){
        HashMap<java.lang.String, java.lang.String> suitSymbols = new HashMap<java.lang.String, java.lang.String>();
        suitSymbols.put("C","♣"); // Club
        suitSymbols.put("D","♦"); // Diamond
        suitSymbols.put("H","♥"); // Heart
        suitSymbols.put("S","♠"); // Spade
        return suitSymbols;
    }

    public static void cardTopRow(ArrayList<String> hand){
        for (String card: hand) {
            String val = (card.length() == 3) ? card.substring(0,2): card.substring(0,1);
            String space = (card.length() == 3) ? "": " ";
            System.out.print(String.format("│%s%s       │ ",val,space));
        }
        System.out.print("\n");
    }
    public static void cardSuitRow(ArrayList<String> hand){
        HashMap<String, String> suits = suitsMap();
        for (String card: hand) {
            String suit = (card.length() == 3) ? card.substring(2,3): card.substring(1,2);
            System.out.print(String.format("│    %s    │ ",suits.get(suit)));
        }
        System.out.print("\n");
    }
    public static void cardBottomRow(ArrayList<String> hand){
        for (String card: hand) {
            String val = (card.length() == 3) ? card.substring(0,2): card.substring(0,1);
            String space = (card.length() == 3) ? "": " ";
            System.out.print(String.format("│       %s%s│ ",space,val));
        }
        System.out.print("\n");
    }
    public static HashMap<String,String> getValuesForSingleVisualCard(String card){
        HashMap<String,String> output = new HashMap<String,String>();
        String value = (card.length() == 3) ? card.substring(0,2): card.substring(0,1);
        String space = (card.length() == 3) ? "": " ";
        String suit = (card.length() == 3) ? card.substring(2,3): card.substring(1,2);
        output.put("value",value);
        output.put("space",space);
        output.put("suit",suit);
        return output;
    }
    public static void showVisualHand(ArrayList<String> hand){

        ArrayList<String> visualCard = new ArrayList<String>();
        int handLen = hand.size();
        System.out.println(  "┌─────────┐ ".repeat(handLen));
        cardTopRow(hand);   //│value    │
        System.out.println(  "│         │ ".repeat(handLen));
        cardSuitRow(hand);  //│  suit   │
        System.out.println(  "│         │ ".repeat(handLen));
        cardBottomRow(hand);//│    value│
        System.out.println(  "└─────────┘ ".repeat(handLen));

    }
    public static void showDealerHiddenHand(ArrayList<String> hand){
        HashMap<String,String> values = getValuesForSingleVisualCard(hand.get(1));
        System.out.println(            "┌─────────┐ ┌─────────┐ ");
        System.out.print(String.format("│░░░░░░░░░│ │%s%s       │\n",values.get("value"),values.get("space")));
        System.out.println(            "│░░░░░░░░░│ │         │  ");
        System.out.print(String.format("│░░░░░░░░░│ │    %s    │\n",suitsMap().get( values.get("suit"))) );
        System.out.println(            "│░░░░░░░░░│ │         │  ");
        System.out.print(String.format("│░░░░░░░░░│ │       %s%s│\n",values.get("space"),values.get("value")));
        System.out.println(            "└─────────┘ └─────────┘ ");
    }
}
