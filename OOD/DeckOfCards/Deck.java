package OOD.DeckOfCards;

import java.util.ArrayList;
import java.util.Random;

public class Deck <T extends Card> {

    private ArrayList<T> cards;
    private int dealtIndex = 0;

    public Deck(ArrayList<T> cards) {
        this.cards = cards;
    }

    public void shuffle() {
        Random random = new Random();
        for (int i = cards.size() - 1; i >= 0; i--) {
            int rand = random.nextInt(i + 1);
            swap(i, rand);
        }
    }
    private void swap(int a, int b) {
        T tmp = cards.get(a);
        cards.set(a, cards.get(b));
        cards.set(b, tmp);
    }

    public T dealCard() {
        if (dealtIndex >= cards.size()) return null;
        return cards.get(dealtIndex++);
    }

    public void print() {
        for (T card : cards) {
            card.print();
        }
        System.out.println();
    }
}
