import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;

public class Deck {
	
	private final String[] suits = {"Spades", "Hearts", "Diamonds", "Clubs"};
	private final int[] values = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
	
	private Stack<Card> cards;
	
	public Deck() {
		cards = new Stack<>();
		populateDeck();
	}
	
	public Stack<Card> getDeck() {
		return cards;
	}
	
	public void populateDeck() {
		for(int j = 0; j < values.length; j++) {
			for(int x = 0; x < suits.length; x++) {
				cards.push(new Card(values[j], suits[x]));
			}
		}
	}
	
	public void disributeCards(Player one, Player two) {
		ArrayList<Card> temp = new ArrayList<Card>(cards);
		for(int i = 0; i < temp.size(); i++) {
			if(i % 2 == 0) {
				one.getCards().push((temp.get(i)));
			} else {
				two.getCards().push((temp.get(i)));
			}
		}
	}
	
	public void shuffleDeck() {
		Collections.shuffle(cards);
	}
}
