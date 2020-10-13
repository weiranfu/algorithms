package OOD.DeckOfCards;

import java.util.ArrayList;
import java.util.List;

public class BlackJackHand extends Hand<BlackJackCard> {

    @Override
    public int scores() {
        List<Integer> scores = getPossibleScores();
        int maxUnder = Integer.MIN_VALUE;
        int minOver = Integer.MAX_VALUE;
        for (int score : scores) {
            if (score > 21 && score < minOver) {
                minOver = score;
            } else if (score <= 21 && score > maxUnder) {
                maxUnder = score;
            }
        }
        return maxUnder != Integer.MIN_VALUE ? maxUnder : minOver;
    }

    private List<Integer> getPossibleScores() {
        List<Integer> scores = new ArrayList<>();
        if (cards.size() == 0) return scores;
        scores.add(0);
        for (BlackJackCard c : cards) {
            int length = scores.size();  // modify list during iteration
            for (int i = 0; i < length; i++) {
                scores.set(i, scores.get(i) + c.value());
                if (c.isAce()) {
                    scores.add(scores.get(i) + c.maxAceValue());
                }
            }
        }
        return scores;
    }

    public boolean isBusted() {
        return scores() > 21;
    }

    public boolean is21() {
        return scores() == 21;
    }

    public boolean isBlackJack() {
        if (cards.size() != 2) return false;
        BlackJackCard first = cards.get(0);
        BlackJackCard second = cards.get(1);
        return (first.isAce() && second.isFaceCard()) || (first.isFaceCard() && second.isAce());
    }
}
