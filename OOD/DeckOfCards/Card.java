package OOD.DeckOfCards;

public abstract class Card {

    /* number or face that's on card - a number 2 through 10,
     * or 11 for Jack, 12 for Queen, 13 for King, or 1 for Ace
     */
    protected int value;
    protected Suit suit;

    public Card(int v, Suit s) {
        value = v;
        suit = s;
    }

    public abstract int value();

    public Suit suit() {
        return suit;
    }

    public void print() {
        String[] s = {"", "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        System.out.print(s[value]+","+ suit + " ");
    }
}
