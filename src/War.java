import java.util.Collections;
import java.util.Scanner;
import java.util.Stack;

public class War {

	public static void main(String args[]) {
		Deck d = new Deck();
		
		Player one = new Player();
		Player two = new Player();
		
		String draw = "";
		
		Boolean isRunning = true;
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Welcome to WAR. Press d to draw after each turn.\n"
				+ "If you type anything else, the game will exit and determine a winner");
				
		try {
			d.shuffleDeck();
			d.disributeCards(one, two);
			while(isRunning) {
				Card valueOne, valueTwo;
				
				draw = scan.nextLine();
				
				if(draw.equals("d")) {
					valueOne = one.getCards().pop();
					valueTwo = two.getCards().pop();
					EventHandler.handleEvent(valueOne, valueTwo, one, two);
				} else {
					isRunning = false;
				}
				
				if(one.getCards().isEmpty()) {
					if(one.getCardsWon().isEmpty()) {
						break;
					} else {
						EventHandler.rePopulateDeck(one.getCardsWon(), one.getCards());
						one.getCardsWon().clear();
						Collections.shuffle(one.getCards());
						System.out.println("\nReshuffling... Player one has " + one.getCards().size() + " cards");
					}
				}
				
				if(two.getCards().isEmpty()) {
					if(two.getCardsWon().isEmpty()) {
						break;
					} else {
						EventHandler.rePopulateDeck(two.getCardsWon(), two.getCards());
						two.getCardsWon().clear();
						Collections.shuffle(two.getCards());
						System.out.println("Reshuffling... Player two has " + two.getCards().size() + " cards");
					}
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		EventHandler.returnWinner(one, two);
		scan.close();
	}
}

