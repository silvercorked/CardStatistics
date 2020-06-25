import java.util.ArrayList;
import java.util.List;

public class Simulation {

	private List<Integer> stats;
	private int modifier;
	private Deck deck;
	private Deck firstDraw;
	private Deck secondDraw;
	private boolean done;

	public Simulation(Deck deck, int modifier) {
		this.deck = deck;
		this.deck.shuffle();
		this.modifier = modifier;
		this.stats = new ArrayList<Integer>();
		this.firstDraw = new Deck();
		this.secondDraw = new Deck();
		this.done = false;
	}

	public List<Integer> processStats() {
		if (done) {
			return this.stats;
		}
		generateSingleDraw(this.firstDraw);
		generateSingleDraw(this.secondDraw);
		this.done = true;
		this.stats = new ArrayList<Integer>();
		for (int i = 0; i < 6; i++) {
			Card a = this.firstDraw.getCard(i);
			Card b = this.secondDraw.getCard(i);
			this.stats.add(a.getValue() + b.getValue() + (a.getSuit() == b.getSuit() ? this.modifier : 0));
			//System.out.println("a: " + a.toString() + " b: " + b.toString() + " gave Score: " + this.stats.get(i));
		}
		return this.stats;
	}
	private Deck generateSingleDraw(Deck drawResult) {
		//drawResult = new Deck();
		for (int i = 0; i < 6; i++) {
			drawResult.addCard(this.deck.drawCardWithoutReplacement());
		}
		return drawResult;
	}

	public void setDeck(Deck deck) {
		this.deck = deck;
	}
	public void setModifier(int modifier) {
		this.modifier = modifier;
	}
	public Deck getDeck() {
		return this.deck;
	}
	public int getModifier() {
		return this.modifier;
	}
	public List<Integer> getStats() {
		return this.stats;
	}
}