package OOD.DeckOfCards;

import java.util.ArrayList;
import java.util.List;

/*
 * Design the data structures for a generic deck of cards.
 * Explain how you would subclass the data structures to implement blackjack.
 *
 *  BlackJackGame
 *  -------------------
 *  - numberOfPlayer: int
 *  - hands: BlackJackHand[]
 *  - deck: BlackJackDeck
 *  -------------------
 *  + init(): void
 *  + dealInitPlay(): boolean
 *  + getBlackJacks(): List<Integer>
 *  + playAllHands(): boolean
 *  + getWinners(): List<Integer>
 *  + printHandsAndScore(): void
 *
 *  Abstract Hand <T extends Card>
 *  -------------------
 *  # cards: List<T>
 *  -------------------
 *  + scores(): int
 *  + addCard(card: T): void
 *  + print(): void
 *
 *  BlackJackHand extends Hand:
 *  -------------------
 *  -------------------
 *  + scores(): int
 *  + isBusted(): boolean
 *  + is21(): boolean
 *  + isBlackJack(): boolean
 *
 *  Enum Suit
 *  -------------------
 *  Club, Diamond, Spade, Heart
 *
 *  Abstract Card
 *  -------------------
 *  # value: int
 *  # suit: Suit
 *  -------------------
 *  + value(): int
 *  + suit(): Suit
 *  + print(): void
 *
 *  BlackJackCard extends Card
 *  -------------------
 *  - maxAceValue: int
 *  -------------------
 *  + value(): int
 *  + isAce(): boolean
 *  + isFaceCard(): boolean
 *
 *  Deck <T extends Card>
 *  -------------------
 *  - cards: List<T>
 *  - dealtIndex: int
 *  -------------------
 *  + shuffle(): void
 *  + dealCard(): T
 *  + print(): void
 */
public class Question {

    public static void main(String[] args) {
        int numHands = 5;

        BlackJackGame game = new BlackJackGame(numHands);
        game.init();
        boolean success = game.dealInitPlay();
        if (!success) {
            System.out.println("Error. Out of cards.");
        } else {
            System.out.println("-- Initial --");
            game.printHandsAndScore();
            List<Integer> blackjacks = game.getBlackJacks();
            if (blackjacks.size() > 0) {
                System.out.print("Blackjack at ");
                for (int i : blackjacks) {
                    System.out.print(i + ", ");
                }
                System.out.println();
            } else {
                success = game.playAllHands();
                if (!success) {
                    System.out.println("Error. Out of cards.");
                } else {
                    System.out.println("\n-- Completed Game --");
                    game.printHandsAndScore();
                    List<Integer> winners = game.getWinners();
                    if (winners.size() > 0) {
                        System.out.print("Winners: ");
                        for (int i : winners) {
                            System.out.print(i + ", ");
                        }
                        System.out.println("");
                    } else {
                        System.out.println("Draw. All players have busted.");
                    }
                }
            }
        }
    }
}
