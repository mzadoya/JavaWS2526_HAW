package p1.a1x1.application.implementation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import p1.a1x1.application.implementation.PokerHand;
import p1.a1x1.application.cards.Card;
import p1.a1x1.application.cards.Card.Rank;
import p1.a1x1.application.cards.Card.Suit;
import p1.a1x1.supportStuff.applicationSupport.HandRanking;

public class PokerHandEvaluator {

    private List<Card[]> cardCombinations;
    private Card[] bestCards;
    public PokerHandEvaluator() {

    }

    public PokerHand evaluate(Card[] playerCards, Card[] communityCards) {
        cardCombinations = new ArrayList<Card[]>();
        createCombinations(playerCards, communityCards);
        bestCards = new Card[5];

        if (isStrightFlush() == true) {
            return new PokerHand(HandRanking.STRAIGHT_FLUSH, bestCards);
        }
        if (isQuads() == true) {
            return new PokerHand(HandRanking.QUADS, bestCards);
        }
        if (isFullHouse() == true) {
            return new PokerHand(HandRanking.FULL_HOUSE, bestCards);
        }
        if (isFlush() == true) {
            return new PokerHand(HandRanking.FLUSH, bestCards);
        }
        if (isStraight() == true) {
            return new PokerHand(HandRanking.STRAIGHT, bestCards);
        }
        if (isTrips() == true) {
            return new PokerHand(HandRanking.TRIPS, bestCards);
        }
        if (isTwoPair() == true) {
            return new PokerHand(HandRanking.TWO_PAIR, bestCards);
        }
        if (isOnePair() == true) {
            return new PokerHand(HandRanking.ONE_PAIR, bestCards);
        }
        
        highCard();
        return new PokerHand(HandRanking.HIGH_CARD, bestCards);

    }

    private void createCombinations(Card[] playerCards, Card[] communityCards) {

        cardCombinations.add(communityCards);

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 5; j++) {
                Card[] newCards = Arrays.copyOf(communityCards, communityCards.length);
                newCards[j] = playerCards[i];
                cardCombinations.add(newCards);
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = i + 1; j < 5; j++) {
                Card[] newCards = Arrays.copyOf(communityCards, communityCards.length);
                newCards[i] = playerCards[0];
                newCards[j] = playerCards[1];
                cardCombinations.add(newCards);
            }
        }

        /*
         * for (int i = 0; i < 4; i++) { Card[] newCards =
         * Arrays.copyOf(communityCards,communityCards.length); newCards[i] =
         * playerCards[0]; newCards[i+1] = playerCards[1];
         * cardCombinations.add(newCards); }
         */

    }

    private boolean isStrightFlush() {

        for (Card[] cards : cardCombinations) {

            if (flushChecker(cards)) {
                if (straigtChecker(cards)) {
                    bestCards = cards;
                    return true;
                }

            }

        }

        return false;

    }

    private boolean isQuads() {

        for (Card[] cards : cardCombinations) {
            if (rankChecker(cards, 4, false)) {
                bestCards = cards;
                return true;
            }
        }

        return false;

    }

    private boolean isFullHouse() {

        for (Card[] cards : cardCombinations) {
            if (rankChecker(cards, 3, true)) {
                bestCards = cards;
                return true;
            }
        }

        return false;

    }

    private boolean isFlush() {

        for (Card[] cards : cardCombinations) {

            if (flushChecker(cards)) {
                bestCards = cards;
                return true;
            }

        }

        return false;

    }

    private boolean isStraight() {

        for (Card[] cards : cardCombinations) {
            if (flushChecker(cards)) {
                bestCards = cards;
                return true;
            }
        }
        return false;

    }

    private boolean isTrips() {

        for (Card[] cards : cardCombinations) {
            if(rankChecker(cards,  3, false)) {
                bestCards = cards;
                return true;
            }
        }
        
        return false;

    }

    private boolean isTwoPair() {

        for (Card[] cards : cardCombinations) {
            if (rankChecker(cards,2, true)) {
                bestCards = cards;
                return true;
            }
        }

        return false;

    }

    private boolean isOnePair() {

        for (Card[] cards : cardCombinations) {
            if (rankChecker(cards, 2, false)) {
                bestCards = cards;
                return true;
            }
        }

        return false;

    }

    private boolean flushChecker(Card[] cards) {

        Suit suit = cards[0].getSuit();
        if (suit == cards[1].getSuit() && suit == cards[2].getSuit() && suit == cards[2].getSuit()
                && suit == cards[3].getSuit() && suit == cards[4].getSuit()) {  
            return true;
        }

        return false;
    }

    private boolean straigtChecker(Card[] cards) { 

        Arrays.sort(cards);
        
        if (cards[0].getRank().value() == 2 && cards[1].getRank().value() == 3 && cards[2].getRank().value() == 4 &&
                cards[3].getRank().value() == 5 && cards[4].getRank().value() == 14)  {
            return true;
        }
        
        for (int i = 0; i < cards.length - 1; i++) {
            if (cards[i].getRank().value() + 1 != cards[i+1].getRank().value()) {
                return false;
            }
        }

        return true;
    }
    
    private void highCard() {  
        
        bestCards = cardCombinations.get(0);
        Arrays.sort(bestCards);
        
        for (Card[] cards : cardCombinations) {
            Arrays.sort(cards);
            if (isBetterHighCard(bestCards, cards)) {
                bestCards = cards;
            }
        }
    }
    
    private boolean isBetterHighCard (Card[] bestCards, Card[] cards) {
        
        for (int i = cards.length - 1; i >= 0; i--) {
            if (bestCards[i].getRank().value() < cards[i].getRank().value()) {
               return true;
            }
            else if (bestCards[i].getRank().value() > cards[i].getRank().value()) {
               return false;
            }
        }
        return false;
        
    }

    private boolean rankChecker(Card[] cards, int matches, boolean checkSecond) {  //TODO: vereinchen 

        for (int i = 0; i < cards.length + 1 - matches; i++) {
            Rank rank = cards[i].getRank();
            int counter = 1;
            for (int j = 0; j < cards.length; j++) {
                if (i != j && cards[j].getRank() == rank) {
                    counter++;
                }
                
                if (counter == matches) {
                    if (checkSecond == false) {
                        return true;
                    } else {
                        Card[] secondCheck = new Card[cards.length-matches];
                        int pos = 0;
                        for (int k = 0; k < cards.length; k++) {
                            if (cards[i].getRank()!=cards[k].getRank()) {
                
                                secondCheck[pos++] = cards[k];
                            }
                        }
                        return rankChecker(secondCheck, 2, false);
                    }
                }
            }
        }

        return false;
    }
    
    //TODO: Maybe Varagrs 
    
    private int compareHighCard(Card[] player1, Card[] player2) {
        
    }
    
    private int compareOnePair(Card[] player1, Card[] player2) {
        
    }
    
    private int compareTwoPair(Card[] player1, Card[] player2) {
     
    }
    
    private int compareTrips(Card[] player1, Card[] player2) {
        
    }
    
    private int compareStright(Card[] player1, Card[] player2) {
        
    }
    
    private int compareFlush(Card[] player1, Card[] player2) {
        
    }
    
    private int compareFullHouse(Card[] player1, Card[] player2) {
        
    }
    
    private int compareQuads(Card[] player1, Card[] player2) {
        
    }
    
    private int compareStrightFlush(Card[] player1, Card[] player2) {
     
    }

}

