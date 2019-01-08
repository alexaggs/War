import java.awt.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;

public class Player {
	
	private Stack<Card> cards;
	private ArrayList<Card> cardsWon; // Used to represent the cards won on a draw, aka the 'face up' cards
	
	public Player() {
		cards = new Stack<Card>();
		cardsWon = new ArrayList<Card>();
	}
	
	public Stack<Card> getCards() {
		return cards;
	}
	
	public void addCard(Card c) {
		cardsWon.add(c);
	}
	
	public ArrayList<Card> getCardsWon() {
		return cardsWon;
	}
	
	public void printCards() {
		ArrayList<Card> temp = new ArrayList<Card>(cards);
		for(Card c: temp) {
			System.out.println(c.getValue() + " " + c.getSuit());
		}
	}
} 
