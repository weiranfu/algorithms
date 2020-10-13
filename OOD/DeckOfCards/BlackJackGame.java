package OOD.DeckOfCards;

import java.util.ArrayList;
import java.util.List;

public class BlackJackGame {
    private int numberOfPlayer;
    private BlackJackHand[] hands;
    private Deck<BlackJackCard> deck;
    private static final int HIT_UNTIL = 16;

    public BlackJackGame(int number) {
        numberOfPlayer = number;
        init();
    }

    public void init() {
        initHands();
        initDeck();
    }

    private void initHands() {
        hands = new BlackJackHand[numberOfPlayer];
        for (int i = 0; i < numberOfPlayer; i++) hands[i] = new BlackJackHand();
    }

    private void initDeck() {
        ArrayList<BlackJackCard> cards = new ArrayList<>();
        for (int i = 1; i <= 13; i++) {
            for (Suit s : Suit.values()) {
                cards.add(new BlackJackCard(i, s));
            }
        }
        deck = new Deck<>(cards);
        deck.shuffle();          // shuffle the cards
    }

    public boolean dealInitPlay() {
        for (BlackJackHand hand : hands) {
            BlackJackCard card1 = deck.dealCard();
            BlackJackCard card2 = deck.dealCard();
            if (card1 == null || card2 == null) return false;
            hand.addCard(card1); hand.addCard(card2);
        }
        return true;
    }

    public List<Integer> getBlackJacks() {
        List<Integer> winners = new ArrayList<>();
        for (int i = 0; i < numberOfPlayer; i++) {
            if (hands[i].isBlackJack()) {
                winners.add(i);
            }
        }
        return winners;
    }

    public boolean playAllHands() {
        for (BlackJackHand hand : hands) {
            if (!playHand(hand)) return false;
        }
        return true;
    }
    private boolean playHand(BlackJackHand hand) {
        while (hand.scores() < HIT_UNTIL) {
            BlackJackCard card = deck.dealCard();
            if (card == null) return false;
            hand.addCard(card);
        }
        return true;
    }

    public List<Integer> getWinners() {
        List<Integer> winners = new ArrayList<>();
        int winingScore = 0;
        for (int i = 0; i < numberOfPlayer; i++) {
            BlackJackHand hand = hands[i];
            if (hand.isBusted()) continue;
            if (hand.scores() > winingScore) {
                winingScore = hand.scores();
                winners.clear();
                winners.add(i);
            } else if (hand.scores() == winingScore) {
                winners.add(i);
            }
        }
        return winners;
    }

    public void printHandsAndScore() {
        for (BlackJackHand hand : hands) {
            hand.print();
            System.out.println(hand.scores());
            System.out.println();
        }
    }
}
