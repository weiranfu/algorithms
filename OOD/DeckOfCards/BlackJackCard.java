package OOD.DeckOfCards;

public class BlackJackCard extends Card {

    private static final int MAX_ACE_VALUE = 11;

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

    public boolean isAce() {
        return value == 1;
    }

    public boolean isFaceCard() {
        return value >= 11 && value <= 13;
    }

    public int maxAceValue() {
        return MAX_ACE_VALUE;
    }
}
