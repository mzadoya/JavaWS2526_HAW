package p1.a1x1.application.implementation;

import p1.a1x1.application.cards.Card;
import p1.a1x1.supportStuff.applicationSupport.HandRanking;

/**
 * Repraesentiert eine bewertete Pokerhand eines Spielers.
 * 
 * Enthaelt die beste 5-Karten-Kombination, das Ranking dieser Hand (z.B. Pair,
 * Flush) und die Position des Spielers im Spiel.
 * 
 * @author Maksym Zadoya
 * @version 2025/11/6 #1
 */
public class PokerHand implements Comparable<PokerHand> {

    private HandRanking ranking;
    private Card[] playerCards;
    private int playerIndex;
    protected int playerRank;

    public PokerHand(HandRanking ranking, Card[] playerCards, int playerIndex, int playerRank) {
        this.playerCards = playerCards;
        this.ranking = ranking;
        this.playerIndex = playerIndex;
        this.playerRank = playerRank;
    }

    /**
     * Gibt das Ranking dieser Hand zurueck.
     * 
     * @return das HandRanking (z.B. ONE_PAIR, FLUSH)
     */
    public HandRanking getRanking() {
        return ranking;
    }

    /**
     * Gibt die 5 besten Karten dieser Hand zurueck.
     * 
     * @return Array mit den 5 Pokerkarten
     */
    public Card[] getPlayerCards() {
        return playerCards;
    }

    /**
     * Gibt den Spielerindex zurueck (welcher Spieler diese Hand hat).
     * 
     * @return der Index des Spielers (0-4)
     */
    public int getPlayerIndex() {
        return playerIndex;
    }

    /**
     * Gibt die Position/den Rang des Spielers zurueck. 1 = beste Hand, 2 = zweite
     * beste, etc.
     * 
     * @return die Position im Ranking (1-5)
     */
    public int getPlayerRank() {
        return playerRank;
    }

    /**
     * Setzt die Position/den Rang des Spielers. Wird verwendet, wenn zwei Haende
     * verglichen werden muessen.
     * 
     * @param playerRank die neue Position (1 = beste, hoeher = schlechter)
     */
    public void setPlayerRank(int playerRank) {
        this.playerRank = playerRank;
    }

    /**
     * Setzt das Ranking dieser Hand.
     * 
     * @param ranking das neue HandRanking
     */
    public void setRanking(HandRanking ranking) {
        this.ranking = ranking;
    }

    @Override
    public int compareTo(PokerHand other) {
        return Integer.compare(this.playerRank, other.playerRank);
    }

}
