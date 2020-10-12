package OOD.DeckOfCards;

/*
 * Design the data structures for a generic deck of cards.
 * Explain how you would subclass the data structures to implement blackjack.
 *
 *  BlackJackAutomator
 *  -------------------
 *  - hands: List<BlackJackHand>[]
 *  - deck: BlackJackDeck
 *  -------------------
 *  + init(): void
 *  + dealInitPlay(): boolean
 *  + getBlackJack(): List<Integer>
 *  + playAllHands(): boolean
 *  + getWinners(): List<Integer>
 *
 *  Abstract Hand <T extends Card>
 *  -------------------
 *  - cards: List<T>
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
 *  - value: int
 *  - suit: Suit
 *  -------------------
 *  + value(): int
 *  + suit(): Suit
 *  + print(): void
 *
 *  BlackJackCard extends Card
 *  -------------------
 *  -------------------
 *  + value(): int
 *  + isAce(): boolean
 *  + isFaceCard(): boolean
 *
 *  Deck <T extends Card>
 *  -------------------
 *  - cards: List<T>[]
 *  - dealtIndex: int
 *  -------------------
 *  + shuffle(): void
 *  + dealCard(): T
 */
public class Question {

    public static void main(String[] args) {
        BlackJackCard c = new BlackJackCard(2, Suit.Diamond);
        c.print();
    }
}
