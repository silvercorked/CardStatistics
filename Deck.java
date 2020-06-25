import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Deck {

	private List<Card> cards;
	private Random rand;

	public Deck() {
		this.setCards(new ArrayList<Card>());
		this.rand = new Random();
	}

	public void setCards(List<Card> cards) {
		this.cards = cards;
	}
	public List<Card> getCards() {
		return this.cards;
	}

	public Card drawCardWithoutReplacement() {
		List<Card> currentCards = this.getCards();
		int index = this.rand.nextInt(currentCards.size());
		Card card = currentCards.remove(index);
		return card;
	}

	public int addCard(Card add, boolean duplicates) {
		if (duplicates) {
			for (Card card : this.getCards()) {
				if (add.equals(card)) {
					return -1; // no dups
				}
			}
			this.getCards().add(add);
			return this.getCards().size() - 1;
		}
		else {
			this.getCards().add(add);
			return this.getCards().size() - 1;
		}
	}

	public void shuffle() {
		Collections.shuffle(this.getCards());
	}

	public int addCard(Card add) {
		return this.addCard(add, true);
	}

	public Card getCard(int index) {
		return this.getCards().get(index);
	}
}