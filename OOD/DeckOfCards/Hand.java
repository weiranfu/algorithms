package OOD.DeckOfCards;

import java.util.ArrayList;
import java.util.List;

public abstract class Hand <T extends Card> {
    protected List<T> cards = new ArrayList<>();

    public abstract int scores();

    public void addCard(T card) {
        cards.add(card);
    }

    public void print() {
        for (T card : cards) {
            card.print();
        }
        System.out.println();
    }

}
