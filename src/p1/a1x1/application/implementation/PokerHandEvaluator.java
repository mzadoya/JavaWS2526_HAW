package p1.a1x1.application.implementation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import p1.a1x1.application.cards.Card;
import p1.a1x1.application.cards.Card.Rank;
import p1.a1x1.application.cards.Card.Suit;
import p1.a1x1.supportStuff.applicationSupport.HandRanking;
/**
 * 
 * Wertet Pokerhaende aus und bestimmt die beste 5-Karten-Kombination aus 7
 * verfuegbaren Karten (2 Player Cards + 5 Community Cards).
 * 
 * Die Klasse generier alle moeglichen 5 Karten Kombinationen und evaluiert sie
 * systematisch von der hoechsten Hand (Straight Flush) bis zur niedrigsten
 * (High Card)
 * 
 * @author Maksym Zadoya
 * @version 2025/11/6 #1
 * 
 */
public class PokerHandEvaluator {

    private List<Card[]> cardCombinations;
    private Card[] bestCards;

    /**
     * 
     * Evaluiert die beste Pokerhand eines Spielers aus seinen Cards und den
     * Community Cards.
     * 
     * @param playerCards    die 2 Hole Cards des Spielers
     * @param communityCards die 5 gemeinsamen Karten auf dem Board
     * @param playerNumber   die Nummer des Spielers (0-4)
     * @return die beste PokerHand mit Ranking und Karten
     * 
     */
    public PokerHand evaluate(Card[] playerCards, Card[] communityCards, int playerNumber) {
        cardCombinations = new ArrayList<Card[]>();
        createCombinations(playerCards, communityCards);
        bestCards = new Card[5];
        if (isStrightFlush()) {
            return new PokerHand(HandRanking.STRAIGHT_FLUSH, Arrays.copyOf(bestCards, bestCards.length), playerNumber,
                    0);
        }
        if (isQuads()) {
            return new PokerHand(HandRanking.QUADS, Arrays.copyOf(bestCards, bestCards.length), playerNumber, 0);
        }
        if (isFullHouse()) {
            return new PokerHand(HandRanking.FULL_HOUSE, Arrays.copyOf(bestCards, bestCards.length), playerNumber, 0);
        }
        if (isFlush()) {
            return new PokerHand(HandRanking.FLUSH, Arrays.copyOf(bestCards, bestCards.length), playerNumber, 0);
        }
        if (isStraight()) {
            return new PokerHand(HandRanking.STRAIGHT, Arrays.copyOf(bestCards, bestCards.length), playerNumber, 0);
        }
        if (isTrips()) {
            return new PokerHand(HandRanking.TRIPS, Arrays.copyOf(bestCards, bestCards.length), playerNumber, 0);
        }
        if (isTwoPair()) {
            return new PokerHand(HandRanking.TWO_PAIR, Arrays.copyOf(bestCards, bestCards.length), playerNumber, 0);
        }
        if (isOnePair()) {
            return new PokerHand(HandRanking.ONE_PAIR, Arrays.copyOf(bestCards, bestCards.length), playerNumber, 0);
        }

        highCard();
        return new PokerHand(HandRanking.HIGH_CARD, Arrays.copyOf(bestCards, bestCards.length), playerNumber, 0);

    }

    /**
     * 
     * Generiert alle möglichen 5-Karten-Kombinationen aus den verfuegbaern 7 Karten
     * (2 Player + 5 Communitz). Kombinationen werden sortiert.
     * 
     * @param playerCards    die 2 Hole Cards
     * @param communityCards die 5 Community Cards
     * 
     */
    private void createCombinations(Card[] playerCards, Card[] communityCards) {

        cardCombinations.add(communityCards);

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 5; j++) {
                Card[] newCards = Arrays.copyOf(communityCards, communityCards.length);
                newCards[j] = playerCards[i];
                Arrays.sort(newCards);
                cardCombinations.add(newCards);
            }
        }

        for (int i = 0; i < 4; i++) {
            for (int j = i + 1; j < 5; j++) {
                Card[] newCards = Arrays.copyOf(communityCards, communityCards.length);
                newCards[i] = playerCards[0];
                newCards[j] = playerCards[1];
                Arrays.sort(newCards);
                cardCombinations.add(newCards);
            }
        }

    }

    /**
     * 
     * Prueft ob die 5 Karten einen Straight Flush bilden (5 aufeinanderfolgende
     * Karten derselben Farbe).
     * 
     * @return true wenn Straight Flush voehanden ist, sonst false
     * 
     */
    private boolean isStrightFlush() {
        List<Card[]> kandidaten = new ArrayList<>();
        for (Card[] cards : cardCombinations) {

            if (flushChecker(cards)) {
                if (straigtChecker(cards)) {
                    kandidaten.add(cards);
                }
            }
        }
        if (kandidaten.isEmpty()) {
            return false;
        }
        if (kandidaten.size() == 1) {
            bestCards = kandidaten.get(0);
            return true;
        }
        Card[] best = kandidaten.get(0);
        for (int i = 1; i < kandidaten.size(); i++) {
            int result = compareStrightFlush(best, kandidaten.get(i));
            if (result == -1) {
                best = kandidaten.get(i); 
            }
        }
        bestCards = best;
        return true;
    }

    /**
     * 
     * Prueft ob die 5 Karten einen Vierling (Quads) bilden ( 4 Karten gleichen
     * Rangs).
     * 
     * @return true wenn Vierling voehanden ist, sonst false
     * 
     */
    private boolean isQuads() {
        List<Card[]> kandidaten = new ArrayList<>();
        for (Card[] cards : cardCombinations) {
            if (rankChecker(cards, 4, false)) {
                kandidaten.add(cards);
            }
        }
        if (kandidaten.isEmpty()) {
            return false;
        }
        if (kandidaten.size() == 1) {
            bestCards = kandidaten.get(0);
            return true;
        }
        Card[] best = kandidaten.get(0);
        for (int i = 1; i < kandidaten.size(); i++) {
            int result = compareQuads(best, kandidaten.get(i));
            if (result == -1) {
                best = kandidaten.get(i); 
            }
        }
        bestCards = best;
        return true;
    }

    /**
     * 
     * Prueft ob die 5 Karten ein Full House bilden ( 3 Karten gleichen Rangs + 2
     * andere Karten gleichen Rangs).
     * 
     * @return true wenn Full House voehanden ist, sonst false
     * 
     */
    private boolean isFullHouse() {
        List<Card[]> kandidaten = new ArrayList<>();
        for (Card[] cards : cardCombinations) {
            if (rankChecker(cards, 3, true)) {
                kandidaten.add(cards);
            }
        }
        if (kandidaten.isEmpty()) {
            return false;
        }
        if (kandidaten.size() == 1) {
            bestCards = kandidaten.get(0);
            return true;
        }
        Card[] best = kandidaten.get(0);
        for (int i = 1; i < kandidaten.size(); i++) {
            int result = compareFullHouse(best, kandidaten.get(i));
            if (result == -1) {
                best = kandidaten.get(i); 
            }
        }
        bestCards = best;
        return true;
    }

    /**
     * 
     * Prueft ob die 5 Karten einen Flush bilden ( 5 Karten derselben Farbe).
     * 
     * @return true wenn Flush voehanden ist, sonst false
     * 
     */
    private boolean isFlush() {
        List<Card[]> kandidaten = new ArrayList<>();
        for (Card[] cards : cardCombinations) {

            if (flushChecker(cards)) {
                kandidaten.add(cards);
            }
        }
        if (kandidaten.isEmpty()) {
            return false;
        }
        if (kandidaten.size() == 1) {
            bestCards = kandidaten.get(0);
            return true;
        }
        Card[] best = kandidaten.get(0);
        for (int i = 1; i < kandidaten.size(); i++) {
            int result = compareFlush(best, kandidaten.get(i));
            if (result == -1) {
                best = kandidaten.get(i); 
            }
        }
        bestCards = best;
        return true;
    }

    /**
     * 
     * Prueft ob die 5 Karten einen Straight bilden (5 aufeinanderfolgende Karten).
     * 
     * @return true wenn Straight voehanden ist, sonst false
     * 
     */
    private boolean isStraight() {
        
        List<Card[]> kandidaten = new ArrayList<>();
        for (Card[] cards : cardCombinations) {
            if (straigtChecker(cards)) {
                kandidaten.add(cards);
               
            }
        }
        if (kandidaten.isEmpty()) {
            return false;
        }
        if (kandidaten.size() == 1) {
            bestCards = kandidaten.get(0);
            return true;
        }
        Card[] best = kandidaten.get(0);
        for (int i = 1; i < kandidaten.size(); i++) {
            int result = compareStright(best, kandidaten.get(i));
            if (result == -1) {
                best = kandidaten.get(i); 
            }
        }
        bestCards = best;
        return true;
    }

    /**
     * 
     * Prueft ob die 5 Karten einen Drilling bilden (3 Karten gleichen Rangs).
     * 
     * @return true wenn Drilling voehanden ist, sonst false
     * 
     */
    private boolean isTrips() {
        List<Card[]> kandidaten = new ArrayList<>();
        for (Card[] cards : cardCombinations) {
            if (rankChecker(cards, 3, false)) {
                kandidaten.add(cards);
            }
        }
        if (kandidaten.isEmpty()) {
            return false;
        }
        if (kandidaten.size() == 1) {
            bestCards = kandidaten.get(0);
            return true;
        }
        Card[] best = kandidaten.get(0);
        for (int i = 1; i < kandidaten.size(); i++) {
            int result = compareTrips(best, kandidaten.get(i));
            if (result == -1) {
                best = kandidaten.get(i); 
            }
        }
        bestCards = best;
        return true;
    }

    /**
     * 
     * Prueft ob die 5 Karten zwei Paare bilden (2 Karten gleichen Rangs + 2 weitere
     * Karten gleichen Rangs).
     * 
     * @return true wenn zwei Paare voehanden ist, sonst false
     * 
     */
    private boolean isTwoPair() {
        List<Card[]> kandidaten = new ArrayList<>();
        for (Card[] cards : cardCombinations) {
            if (rankChecker(cards, 2, true)) {
                kandidaten.add(cards);
            }
        }
        if (kandidaten.isEmpty()) {
            return false;
        }
        if (kandidaten.size() == 1) {
            bestCards = kandidaten.get(0);
            return true;
        }
        Card[] best = kandidaten.get(0);
        for (int i = 1; i < kandidaten.size(); i++) {
            int result = compareTwoPair(best, kandidaten.get(i));
            if (result == -1) {
                best = kandidaten.get(i); 
            }
        }
        bestCards = best;
        return true;
    }

    /**
     * 
     * Prueft ob die 5 Karten ein Paar bilden (2 Karten gleichen Rangs).
     * 
     * @return true wenn ein Paar voehanden ist, sonst false
     * 
     */
    private boolean isOnePair() {
        List<Card[]> kandidaten = new ArrayList<>();
        for (Card[] cards : cardCombinations) {
            if (rankChecker(cards, 2, false)) {
                kandidaten.add(cards);
            }
        }
        if (kandidaten.isEmpty()) {
            return false;
        }
        if (kandidaten.size() == 1) {
            bestCards = kandidaten.get(0);
            return true;
        }
        Card[] best = kandidaten.get(0);
        for (int i = 1; i < kandidaten.size(); i++) {
            int result = compareOnePair(best, kandidaten.get(i));
            if (result == -1) {
                best = kandidaten.get(i); 
            }
        }
        bestCards = best;
        return true;
        
    }

    /**
     * 
     * Prueft ob alle 5 Karten dieselbe Farbe haben.
     * 
     * @param cards die zu pruefenden Karten
     * @return true wenn alle Karten dieselbe Farbe haben, sonst false
     * 
     */
    private boolean flushChecker(Card[] cards) {

        Suit suit = cards[0].getSuit();
        if (suit == cards[1].getSuit() && suit == cards[2].getSuit() && suit == cards[3].getSuit()
                && suit == cards[4].getSuit()) {
            return true;
        }

        return false;
    }

    /**
     * 
     * Prueft ob die 5 Karten ein Straight bilden. (5 aufeinanderfolgende Karten
     * beliebiger Farbe). Behandelt auch die Special Case "Wheel" (2-3-4-5-A)
     * 
     * @param cards die zu pruefenden Karten
     * @return true wenn Straight vorhanden ist, sonst false
     * 
     */
    private boolean straigtChecker(Card[] cards) {

        // Arrays.sort(cards);

        if (cards[0].getRank().value() == 2 && cards[1].getRank().value() == 3 && cards[2].getRank().value() == 4
                && cards[3].getRank().value() == 5 && cards[4].getRank().value() == 14) {
            
            Card temp = cards[0];
            cards[0] = cards[4];
            cards[4] = temp;
            return true;
        }

        for (int i = 0; i < cards.length - 1; i++) {
            if (cards[i].getRank().value() + 1 != cards[i + 1].getRank().value()) {
                return false;
            }
        }

        return true;
    }

    /**
     * 
     * Bestimm die beste High Card aus den 5 Karten. Vergleicht alle moeglichen
     * Kombinationen um die hoechwertige zu findn.
     * 
     */
    private void highCard() {

        bestCards = cardCombinations.get(0);
        // Arrays.sort(bestCards);

        for (Card[] cards : cardCombinations) {
            Arrays.sort(cards);
            if (isBetterHighCard(bestCards, cards)) {
                bestCards = cards;
            }
        }
    }

    /**
     * 
     * Vergleicht zwei High Cards lexikographisch (von oben nach unten)
     * 
     * @param bestCards die bisherige beste Hand
     * @param cards     die neue zu vergleichende Hand
     * @return true wenn cards besser ist, sonst false
     * 
     */
    private boolean isBetterHighCard(Card[] bestCards, Card[] cards) {

        for (int i = cards.length - 1; i >= 0; i--) {
            if (bestCards[i].getRank().value() < cards[i].getRank().value()) {
                return true;
            } else if (bestCards[i].getRank().value() > cards[i].getRank().value()) {
                return false;
            }
        }
        return false;

    }

    /**
     * 
     * Prueft ob Karten gleiche Raenge besitzen (fuer Paare, Drillinge, etc.) Wenn
     * checkSecond true ist, wird rekursiv nach einer zweiten Gruppe gesucht (fuer
     * Full House und Two Pair)
     * 
     * @param cards        die zu pruefenden Karten
     * @param matches      die Anzahl der gesuchten Uebereinstimmungen (2,3 oder 4)
     * @param checkSeconde true wenn nach einer zweiten Gruppe gesucht werden soll,
     *                     sonst false
     * @return true wenn die gesuchte Kombination vorhanden ist, sonst false
     * 
     */
    private boolean rankChecker(Card[] cards, int matches, boolean checkSecond) {

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
                        Card[] secondCheck = new Card[cards.length - matches];
                        int pos = 0;
                        for (int k = 0; k < cards.length; k++) {
                            if (cards[i].getRank() != cards[k].getRank()) {

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

    /**
     * 
     * Vergleicht zwei High Cards lexikographisch von der hoechsten zur niedrigsten
     * Karte.
     * 
     * @param player1 die Karten des ersten Spielers (sortiert)
     * @param player2 die Karten des zweiten Spielers (sortiert)
     * @return 1 wenn player1 gewinnt, -1 wenn player2 gewinnt, 0 bei Gleichstand
     * 
     */
    private int compareHighCard(Card[] player1, Card[] player2) {
        int anzahlKartenP1 = player1.length;

        for (int i = anzahlKartenP1 - 1; i >= 0; i--) {
            if (player1[i].getRank().value() > player2[i].getRank().value()) {
                return 1;
            } else if (player1[i].getRank().value() < player2[i].getRank().value()) {
                return -1;
            }
        }
        return 0;
    }

    /**
     * 
     * Vergleicht zwei Paare. Zuerst wird das Paar verglichen, dann die Kicker.
     * 
     * @param player1 die Karten des ersten Spielers
     * @param player2 die Karten des zweiten Spielers
     * @return 1 wenn player1 gewinnt, -1 wenn player2 gewinnt, 0 bei Gleichstand
     * 
     */
    private int compareOnePair(Card[] player1, Card[] player2) {
        Card pairPlayer1 = getPaar(player1, 2);
        Card pairPlayer2 = getPaar(player2, 2);
        if (pairPlayer1.getRank().value() > pairPlayer2.getRank().value()) {
            return 1;
        } else if (pairPlayer1.getRank().value() < pairPlayer2.getRank().value()) {
            return -1;
        } else {
            Card[] kickersPlayer1 = getKickers(player1, pairPlayer1);
            Card[] kickersPlayer2 = getKickers(player2, pairPlayer2);
            for (int i = 0; i < kickersPlayer1.length; i++) {
                if (kickersPlayer1[i].getRank().value() > kickersPlayer2[i].getRank().value()) {
                    return 1;
                } else if (kickersPlayer1[i].getRank().value() < kickersPlayer2[i].getRank().value()) {
                    return -1;
                }
            }
        }
        return 0;
    }

    /**
     * 
     * Vergleicht zwei Zwei-Paar-Haende. Zuerst wird das hoehere Paar verglichen,
     * dann das niedrigere Paar, schliesslich der Kicker.
     * 
     * @param player1 die Karten des ersten Spielers
     * @param player2 die Karten des zweiten Spielers
     * @return 1 wenn player1 gewinnt, -1 wenn player2 gewinnt, 0 bei Gleichstand
     * 
     */
    private int compareTwoPair(Card[] player1, Card[] player2) {
        Card fistPairPlayer1 = getPaar(player1, 2);
        Card fistPairPlayer2 = getPaar(player2, 2);
        if (fistPairPlayer1.getRank().value() > fistPairPlayer2.getRank().value()) {
            return 1;
        } else if (fistPairPlayer1.getRank().value() < fistPairPlayer2.getRank().value()) {
            return -1;
        }

        else {
            Card secondPairPlayer1 = getPaar(player1, 2, fistPairPlayer1);
            Card secondPairPlayer2 = getPaar(player2, 2, fistPairPlayer2);
            if (secondPairPlayer1.getRank().value() > secondPairPlayer2.getRank().value()) {
                return 1;
            } else if (secondPairPlayer1.getRank().value() < secondPairPlayer2.getRank().value()) {
                return -1;
            } else {
                Card[] kickersPlayer1 = getKickers(player1, fistPairPlayer1, secondPairPlayer1);
                Card[] kickersPlayer2 = getKickers(player2, fistPairPlayer2, secondPairPlayer2);
                for (int i = 0; i < kickersPlayer1.length; i++) {
                    if (kickersPlayer1[i].getRank().value() > kickersPlayer2[i].getRank().value()) {
                        return 1;
                    } else if (kickersPlayer1[i].getRank().value() < kickersPlayer2[i].getRank().value()) {
                        return -1;
                    }
                }

            }
        }
        return 0;
    }

    /**
     * 
     * Vergleicht zwei Drillinge. Zuerst wird der Drilling verglichen, dann die
     * Kicker in absteigender Reihenfolge.
     * 
     * @param player1 die Karten des ersten Spielers
     * @param player2 die Karten des zweiten Spielers
     * @return 1 wenn player1 gewinnt, -1 wenn player2 gewinnt, 0 bei Gleichstand
     * 
     */
    private int compareTrips(Card[] player1, Card[] player2) {
        Card tripsPlayer1 = getPaar(player1, 3);
        Card tripsPlayer2 = getPaar(player2, 3);
        if (tripsPlayer1.getRank().value() > tripsPlayer2.getRank().value()) {
            return 1;
        } else if (tripsPlayer1.getRank().value() < tripsPlayer2.getRank().value()) {
            return -1;
        } else {
            Card[] kickersPlayer1 = getKickers(player1, tripsPlayer1);
            Card[] kickersPlayer2 = getKickers(player2, tripsPlayer2);
            for (int i = 0; i < kickersPlayer1.length; i++) {
                if (kickersPlayer1[i].getRank().value() > kickersPlayer2[i].getRank().value()) {
                    return 1;
                } else if (kickersPlayer1[i].getRank().value() < kickersPlayer2[i].getRank().value()) {
                    return -1;
                }
            }

        }
        return 0;
    }

    /**
     * 
     * Vergleicht zwei Straights. Die hoechste Karte der jeweiligen Straße
     * entscheidet. Beachtet auch das "Wheel" (A-2-3-4-5), wo das Ace als 1 gilt.
     * 
     * @param player1 die Karten des ersten Spielers
     * @param player2 die Karten des zweiten Spielers
     * @return 1 wenn player1 gewinnt, -1 wenn player2 gewinnt, 0 bei Gleichstand
     * 
     */
    private int compareStright(Card[] player1, Card[] player2) {
        if (player1[player1.length - 1].getRank().value() > player2[player2.length - 1].getRank().value()) {
            return 1;
        } else if (player1[player1.length - 1].getRank().value() < player2[player2.length - 1].getRank().value()) {
            return -1;
        }
        return 0;
    }

    /**
     * 
     * Vergleicht zwei Flushes. Die hoechste Karte wird zuerst verglichen, dann die
     * naechsten in absteigender Reihenfolge.
     * 
     * @param player1 die Karten des ersten Spielers
     * @param player2 die Karten des zweiten Spielers
     * @return 1 wenn player1 gewinnt, -1 wenn player2 gewinnt, 0 bei Gleichstand
     * 
     */
    private int compareFlush(Card[] player1, Card[] player2) {
        int anzahlKartenP1 = player1.length;

        for (int i = anzahlKartenP1 - 1; i >= 0; i--) {
            if (player1[i].getRank().value() > player2[i].getRank().value()) {
                return 1;
            } else if (player1[i].getRank().value() < player2[i].getRank().value()) {
                return -1;
            }
        }
        return 0;
    }

    /**
     * 
     * Vergleicht zwei Full Houses. Zuerst wird der Drilling verglichen, dann das
     * Paar.
     * 
     * @param player1 die Karten des ersten Spielers
     * @param player2 die Karten des zweiten Spielers
     * @return 1 wenn player1 gewinnt, -1 wenn player2 gewinnt, 0 bei Gleichstand
     * 
     */
    private int compareFullHouse(Card[] player1, Card[] player2) {
        Card tripsPlayer1 = getPaar(player1, 3);
        Card tripsPlayer2 = getPaar(player2, 3);
        if (tripsPlayer1.getRank().value() > tripsPlayer2.getRank().value()) {
            return 1;
        } else if (tripsPlayer1.getRank().value() < tripsPlayer2.getRank().value()) {
            return -1;
        }

        else {
            Card pairPlayer1 = getPaar(player1, 2, tripsPlayer1);
            Card pairPlayer2 = getPaar(player2, 2, tripsPlayer2);
            if (pairPlayer1.getRank().value() > pairPlayer2.getRank().value()) {
                return 1;
            } else if (pairPlayer1.getRank().value() < pairPlayer2.getRank().value()) {
                return -1;
            }
        }
        return 0;
    }

    /**
     * 
     * Vergleicht zwei Vierlinge. Zuerst wird der Vierling verglichen, dann der
     * Kicker.
     * 
     * @param player1 die Karten des ersten Spielers
     * @param player2 die Karten des zweiten Spielers
     * @return 1 wenn player1 gewinnt, -1 wenn player2 gewinnt, 0 bei Gleichstand
     * 
     */
    private int compareQuads(Card[] player1, Card[] player2) {
        Card vierlingPlayer1 = getPaar(player1, 4);
        Card vierlingPlayer2 = getPaar(player2, 4);
        if (vierlingPlayer1.getRank().value() > vierlingPlayer2.getRank().value()) {
            return 1;
        } else if (vierlingPlayer1.getRank().value() < vierlingPlayer2.getRank().value()) {
            return -1;
        } else {
            Card[] kickersPlayer1 = getKickers(player1, vierlingPlayer1);
            Card[] kickersPlayer2 = getKickers(player2, vierlingPlayer2);
            for (int i = 0; i < kickersPlayer1.length; i++) {
                if (kickersPlayer1[i].getRank().value() > kickersPlayer2[i].getRank().value()) {
                    return 1;
                } else if (kickersPlayer1[i].getRank().value() < kickersPlayer2[i].getRank().value()) {
                    return -1;
                }
            }
        }
        return 0;
    }

    /**
     * 
     * Vergleicht zwei Straight Flushes. Die hoechste Karte entscheidet.
     * 
     * @param player1 die Karten des ersten Spielers
     * @param player2 die Karten des zweiten Spielers
     * @return 1 wenn player1 gewinnt, -1 wenn player2 gewinnt, 0 bei Gleichstand
     * 
     */
    private int compareStrightFlush(Card[] player1, Card[] player2) {

        if (player1[player1.length - 1].getRank().value() > player2[player2.length - 1].getRank().value()) {
            return 1;
        } else if (player1[player1.length - 1].getRank().value() < player2[player2.length - 1].getRank().value()) {
            return -1;
        }
        return 0;
    }

    /**
     * 
     * Findet eine Gruppe von Karten mit gleichen Raengen (Paar, Drilling,
     * Vierling). Sucht von hinten nach vorne (von hoechster zur niedrigster Karte).
     * 
     * @param cards     die zu durchsuchenden Karten
     * @param matches   die Anzahl der gesuchten uebereinstimmenden Ränge (2, 3 oder
     *                  4)
     * @param exception die Rang-Ausnahme (wird uebersprungen), null wenn keine
     *                  Ausnahme
     * @return die erste gefundene Karte der Gruppe, null wenn nicht gefunden
     * 
     */
    private Card getPaar(Card[] cards, int matches, Card exception) {

        for (int i = cards.length - 1; i >= 0; i--) {
            if (exception == null || !exception.getRank().equals(cards[i].getRank())) {
                Card card = cards[i];
                int counter = 1;
                for (int j = i - 1; j >= 0; j--) {
                    if (card.getRank().equals(cards[j].getRank())) {
                        counter++;
                    }
                    if (counter == matches) {
                        return card;
                    }
                }
            }
        }
        return null;
    }

    /**
     * 
     * Findet eine Gruppe von Karten mit gleichen Raengen ohne Ausnahmen. Delegiert
     * an getPaar mit null als Ausnahme.
     * 
     * @param cards   die zu durchsuchenden Karten
     * @param matches die Anzahl der gesuchten uebereinstimmenden Ränge (2, 3 oder
     *                4)
     * @return die erste gefundene Karte der Gruppe, null wenn nicht gefunden
     * 
     */
    private Card getPaar(Card[] cards, int matches) {
        return getPaar(cards, matches, null);
    }

    /**
     * 
     * Extrahiert die Kicker (Beikarten) aus einer Hand, indem zwei bestimmte Raenge
     * ausgeschlossen werden. Kicker werden aufsteigend sortiert.
     * 
     * Beispiel: Bei Zwei-Paar wird nach jedem Paar die verbleibende Karte als
     * Kicker genommen.
     * 
     * @param cards           die Karten der Hand
     * @param exception       der erste auszuschließende Rang
     * @param secondException der zweite auszuschließende Rang (null wenn nur eine
     *                        Ausnahme)
     * @return Array der Kicker, sortiert aufsteigend
     * 
     */
    private Card[] getKickers(Card[] cards, Card exception, Card secondException) {
        // Card[] kickers = new Card[cards.length];
        List<Card> kickers = new ArrayList<>();
        for (Card c : cards) {
            if (!c.getRank().equals(exception.getRank())
                    && (secondException == null || !c.getRank().equals(secondException.getRank()))) {
                kickers.add(c);
            }
        }
        Card[] toReturn = kickers.toArray(new Card[0]);
        Arrays.sort(toReturn);
        return toReturn;

    }

    private Card[] getKickers(Card[] cards, Card exception) {
        return getKickers(cards, exception, null);
    }

    /**
     * 
     * Extrahiert die Kicker (Beikarten) aus einer Hand, indem ein bestimmter Rang
     * ausgeschlossen wird.
     * 
     * Beispiel: Bei einem Paar werden die restlichen 3 Karten als Kicker genommen.
     * 
     * @param cards     die Karten der Hand
     * @param exception der auszuschließende Rang
     * @return Array der Kicker, sortiert aufsteigend
     * 
     */
    public int compareTwoHands(Card[] player1, Card[] player2, HandRanking type) {

        switch (type) {

            case HIGH_CARD:
                return compareHighCard(player1, player2);

            case ONE_PAIR:
                return compareOnePair(player1, player2);

            case TWO_PAIR:
                return compareTwoPair(player1, player2);

            case TRIPS:
                return compareTrips(player1, player2);

            case STRAIGHT:
                return compareStright(player1, player2);

            case FLUSH:
                return compareFlush(player1, player2);

            case FULL_HOUSE:
                return compareFullHouse(player1, player2);

            case QUADS:
                return compareQuads(player1, player2);

            case STRAIGHT_FLUSH:
                return compareStrightFlush(player1, player2);

            default:
                assert (false) : "Unexpected hand type in compareTwoHands()!";
        }
        return 0;
    }

}
