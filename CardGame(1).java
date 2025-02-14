// Oscar Munoz and Henry Sychanthavong
// Assignment title: Making a Card Game with OOD Group Activity
// collections utility library, added code to make the output more readable, fixed the deals from 4 cards to 5, and then used collections.shuffle method for better shuffles
// Fixed card class and merged into this file/code


package cardGame;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
//Introduced the util collections for better shuffling
import java.util.Collections;

//Fixed the card class
public class CardGame {

	private static ArrayList<Card> deckOfCards = new ArrayList<Card>();
	private static ArrayList<Card> playerCards = new ArrayList<Card>();
	public static class Card {

		//instance variables
		private String suit;
		private String rank;
		private int value;
		private String picture;
	
		//constructors
		public Card(String suit, String rank, int value, String picture) {
			this.suit = suit;
			this.rank = rank;
			this.value = value;
			this.picture = picture;
		}
		public String getSuit() {
			return suit;
		}
	
		public String getRank() {
			return rank;
		}
	
		public int getValue() {
			return value;
		}
	
		public String getPicture() {
			return picture;
		}
	
		@Override
		public String toString() {
			return "Card{suit='" + suit + "', rank='" + rank + "', value=" + value + ", picture='" + picture + "'}";
		}
		
	}


	public static void main(String[] args) {

		Scanner input = null;
		try {
			input = new Scanner(new File("cards.txt"));
		} catch (FileNotFoundException e) {
			// error
			System.out.println("error");
			e.printStackTrace();
		}

		while(input.hasNext()) {
			String[] fields  = input.nextLine().split(",");
			Card newCard = new Card(fields[0], fields[1].trim(),
					Integer.parseInt(fields[2].trim()), fields[3]);
			deckOfCards.add(newCard);	
		}

		shuffle();

		//fixed the deals from 4 to 5 cards
		for(int i = 0; i < 5; i++) {
			playerCards.add(deckOfCards.remove(0));
		}
		
		System.out.println("players cards");
		for(Card c: playerCards)
			//Fixed the output to make the output more readable
			System.out.println(c.rank + " " + c.value + " " + c.suit + " " + c.picture);

		System.out.println("pairs is " + checkFor2Kind());

	}

    public static void shuffle() {
		// Use Collections.shuffle method for more efficient shuffling
        Collections.shuffle(deckOfCards);
    }

    //check for 2 of a kind in the players hand
    public static boolean checkFor2Kind() {
        int count = 0;
        for (int i = 0; i < playerCards.size() - 1; i++) {
            Card current = playerCards.get(i);
            for (int j = i + 1; j < playerCards.size(); j++) {
                Card next = playerCards.get(j);
                if (current.getValue() == next.getValue()) {
                    count++;
                }
            }
            if (count == 1)
                return true;
			// Reset count for the next card
            count = 0; 

        }
        return false;
    }
}//end class