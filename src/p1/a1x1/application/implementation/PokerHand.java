package p1.a1x1.application.implementation;

import application.cards.Card;
import supportStuff.applicationSupport.HandRanking;

public class PokerHand {
    
    public HandRanking ranking;
    public Card[] playerCards;
    
    public PokerHand(HandRanking ranking, Card[] playerCards) {
        this.playerCards = playerCards;
        this.ranking = ranking;
        
    }

    public HandRanking getRanking() {
        return ranking;
    }

    public Card[] getPlayerCards() {
        return playerCards;
    }
    
}
