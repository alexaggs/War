import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class EventHandler {

	public static void handleEvent(Card one, Card two, Player playerOne, Player playerTwo) {
		int tieValOne = 0, tieValTwo = 0;
		ArrayList<Card> playerOnePops, playerTwoPops, playerPopsCombined;
		boolean tieAgain = true;
		if(one.getValue() > two.getValue()) {
			System.out.println("Player One won the draw with a "
					+ one.getValue() + " " + one.getSuit() + " as opposed to a "
							+ two.getValue() + " " + two.getSuit() + "."
					);
			// Give Player One both cards back, face up
			playerOne.addCard(one); playerOne.addCard(two);
			
			displayInfo(playerOne, playerTwo);

		
		} else if(two.getValue() > one.getValue()) {
			System.out.println("Player Two won the draw with a "
					+ two.getValue() + " " + two.getSuit() + " as opposed to a "
							+ one.getValue() + " " + one.getSuit() + "."
					);
			// Give Player Two both cards back, face up
			playerTwo.addCard(one); playerTwo.addCard(two);
			
			displayInfo(playerOne, playerTwo);
			
		} else {
			//Redistribute the 'face up' cards back into the playing deck
			if(playerOne.getCards().size() == 0) {
				reDistribute(playerOne);
			} 
			// This is to avoid an issue where if a user has one/two cards left
			// and a tie happens, we avoid the scenario of having 0 or negative cards
			else if(playerOne.getCards().size() - 3 <= 0) {
				reDistribute(playerOne);
			}
			if(playerTwo.getCards().size() == 0) {
				reDistribute(playerTwo);
			} else if(playerTwo.getCards().size() - 3 <= 0) {
				reDistribute(playerTwo);
			}
			//Here, we keep track of the popped cards for a tie
			while(tieAgain) {
				playerOnePops = popTie(playerOne.getCards());
				playerTwoPops = popTie(playerTwo.getCards());
				playerPopsCombined = new ArrayList<Card>(playerOnePops);
				playerPopsCombined.addAll(playerTwoPops);
				//Get the value of the last popped card to compare for each person
				tieValOne = playerOnePops.get(playerOnePops.size() - 1).getValue();
				tieValTwo = playerTwoPops.get(playerTwoPops.size() - 1).getValue();
				
				if(tieValOne > tieValTwo) {
					System.out.println("Player one wins the tie!");
					playerOne.addCard(one); playerOne.addCard(two);
					addTieCards(playerPopsCombined, playerOne);
					
					displayInfo(playerOne, playerTwo);
					tieAgain = false;
					
				} else if(tieValTwo > tieValOne) {
					System.out.println("Player two wins the tie!");
					playerTwo.addCard(one); playerTwo.addCard(two);
					addTieCards(playerPopsCombined, playerTwo);
					
					displayInfo(playerOne, playerTwo);
					tieAgain = false;
	
				} else {
					// For if theres another tie directly after. Don't
					// think this is totally necessary, but keeping for now
					tieAgain = true;
				}
			}
		}
	}
	
	// Returns the winner
	public static void returnWinner(Player playerOne, Player playerTwo) {
		int playerOneTotal = playerOne.getCards().size() + playerOne.getCardsWon().size();
		int playerTwoTotal = playerTwo.getCards().size() + playerTwo.getCardsWon().size();
		if(playerTwo.getCards().size() == 0 && playerTwo.getCardsWon().size() == 0) {
			System.out.println("Player One Wins! They have all your cards");
		} else if(playerOne.getCards().size() == 0 && playerOne.getCardsWon().size() == 0) {
			System.out.println("Player Two Wins! They have all your cards");
		} else {
			if(playerOneTotal > playerTwoTotal) {
				System.out.println("Player One wins having " + playerOneTotal + " cards");
			} else if(playerTwoTotal > playerOneTotal) {
				System.out.println("Player Two wins having " + playerTwoTotal + " cards");
			} else {
				System.out.println("Ended in a tie.");
			}
		}
	}
	
	//pops three times per deck on a tie, returns last value
	public static ArrayList<Card> popTie(Stack<Card> cards) {
		Card one, two;
		//Keeping track of what cards we popped
		ArrayList<Card> temp = new ArrayList<Card>();
		one = cards.pop();
		two = cards.pop();
		temp.add(one);
		temp.add(two);
		return temp;
		
	}
	
	public static void rePopulateDeck(ArrayList<Card> temp, Stack<Card> deck) {
		for(Card c: temp) {
			deck.push(c);
		}
	}
	
	//Adds all the cards when we have a tie
	public static void addTieCards(ArrayList<Card> cards, Player p) {
		for(Card c: cards) {
			p.addCard(c);
		}
	}
	
	//Display player card status after every draw
	public static void displayInfo(Player playerOne, Player playerTwo) {
		System.out.println("PLAYER ONE HAS " + (playerOne.getCards().size() + playerOne.getCardsWon().size()) + " cards total");
		System.out.println("PLAYER TWO HAS " + (playerTwo.getCards().size() + playerTwo.getCardsWon().size()) + " cards total");
		
		System.out.println("Player one has " + playerOne.getCards().size()
				+ " cards face down and " + playerOne.getCardsWon().size() + " face up");
		System.out.println("Player two has " + playerTwo.getCards().size() 
				+ " cards face down and " + playerTwo.getCardsWon().size() + " face up");
	}
	
	//Re distributing cards on a tie
	public static void reDistribute(Player p) {
		rePopulateDeck(p.getCardsWon(), p.getCards());
		p.getCardsWon().clear();
		Collections.shuffle(p.getCards());
	}	
}
