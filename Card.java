
public class Card {

	private String color;
	private int suit;
	private int value;

	public Card(String color, int suit, int value) {
		this.setColor(color);
		this.setSuit(suit);
		this.setValue(value);
	}

	public void setColor(String color) {
		this.color = color;
	}
	public void setSuit(int suit) {
		this.suit = suit;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public String getColor() {
		return this.color;
	}
	public int getSuit() {
		return this.suit;
	}
	public int getValue() {
		return this.value;
	}

	@Override
	public String toString() {
		return "" + this.getColor() + " " + this.getSuit() + " " + this.getValue();
	}

	@Override
	public boolean equals(Object card) {
		if (card instanceof Card) {
			Card o = (Card) card;
			return o.getSuit() == this.getSuit() && o.getColor() == this.getColor() && o.getValue() == this.getValue();
		}
		return false;
	}
}