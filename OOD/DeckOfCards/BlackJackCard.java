package OOD.DeckOfCards;

public class BlackJackCard extends Card {

    public BlackJackCard(int v, Suit s) {
        super(v, s);
    }

    @Override
    public int value() {
        if (isAce()) {
            return 1;
        } else if (isFaceCard()) {
            return 10;
        } else {
            return value;
        }
    }

    protected int minValue() {
        if (isAce()) {
            return 1;
        } else {
            return value();
        }
    }

    protected int maxValue() {
        if (isAce()) {
            return 11;
        } else {
            return value();
        }
    }

    public boolean isAce() {
        return value == 1;
    }

    public boolean isFaceCard() {
        return value >= 11 && value <= 13;
    }
}
