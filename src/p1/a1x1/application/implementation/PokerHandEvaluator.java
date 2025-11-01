package p1.a1x1.application.implementation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import application.cards.Card;
import application.cards.Card.Rank;
import application.cards.Card.Suit;
import supportStuff.applicationSupport.HandRanking;

public class PokerHandEvaluator {
    
    private List<Card[]> cardCombinations;
    
    public PokerHandEvaluator() {
        
    }
    
    public HandRanking evaluate(Card[] playerCards, Card[] communityCards) {
        cardCombinations = new ArrayList<Card[]>();
        createCombinations(playerCards, communityCards);
        
        if (isStrightFlush() == true) { 
            return HandRanking.STRAIGHT_FLUSH;
        }
        if (isStrightFlush() == true) { 
            return HandRanking.QUADS;
        }
        if (isStrightFlush() == true) { 
            return HandRanking.FULL_HOUSE;
        }
        if (isStrightFlush() == true) { 
            return HandRanking.FLUSH;
        }
        if (isStrightFlush() == true) { 
            return HandRanking.STRAIGHT;
        }
        if (isStrightFlush() == true) { 
            return HandRanking.TRIPS;
        }
        if (isStrightFlush() == true) { 
            return HandRanking.TWO_PAIR;
        }
        if (isStrightFlush() == true) { 
            return HandRanking.ONE_PAIR;
        }

        return HandRanking.HIGH_CARD;
        
    }
    
    private void createCombinations (Card[] playerCards, Card[] communityCards) {
        
        cardCombinations.add(communityCards);
        
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 5; j++) {
                Card[] newCards = Arrays.copyOf(communityCards,communityCards.length);
                newCards[j] = playerCards[i];
                cardCombinations.add(newCards);
            }
        }
        
        for (int i = 0; i < 3; i++) {
            for (int j = i+1; j < 5; j++) {
                Card[] newCards = Arrays.copyOf(communityCards,communityCards.length);
                newCards[i] = playerCards[0];
                newCards[j] = playerCards[1];
                cardCombinations.add(newCards);
            }
        }
        
        /*
        for (int i = 0; i < 4; i++) {
            Card[] newCards = Arrays.copyOf(communityCards,communityCards.length);
            newCards[i] = playerCards[0];
            newCards[i+1] = playerCards[1];
            cardCombinations.add(newCards);
        }
        */
        
    }
    
    
    private boolean isStrightFlush() {
        

        for (Card[] cards : cardCombinations) {
             
            
            
            
            
            if(true/*proverka на то стрейт*/)
                    {
                        
                if (flushChecker(cards)) {
                    return true;
                }
                    }
                    
        }
        
        return false;

    }
    
    private boolean isQuads() {
        
        for (Card[] cards : cardCombinations) {
            
        }
        
        return false;
        
    }
    
    private boolean isFullHouse() {
        
        for (Card[] cards : cardCombinations) {
            
        }
        
        return false;
        
    }
    
    private boolean isFlush() {
        
        for (Card[] cards : cardCombinations) {
            
            if (flushChecker(cards)) {
                return true;
            }
            
        }
        
        return false;
        
    }
    
    private boolean isStraight() {
        
        for (Card[] cards : cardCombinations) {
            
        }
        return false;
        
    }
    
    private boolean isTrips() {
        
        for (Card[] cards : cardCombinations) {
            
        }
        return false;
        
    }
    
    
    private boolean isTwoPair() {
        
        
        for (Card[] cards : cardCombinations) {
            
        }
        
        return false;
        
    }
    
    
    private boolean isOnePair() {
        
        for (Card[] cards : cardCombinations) {
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (cards[i] == cards[j]) {
                        return true;
                    }
                }
            }
        }
        
        return false;
        
    }
    
    
    private boolean isHighCard() {
        
        
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
        
        int firstRankAsValue = cards[0].getRank().value();
        
        
        
        
        return false;
    }
    
    

}
