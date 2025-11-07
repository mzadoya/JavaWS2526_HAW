// This source code is UTF-8 coded - see https://stackoverflow.com/questions/9180981/how-to-support-utf-8-encoding-in-eclipse
package p1.a1x1.application.implementation;


import static p1.a1x1.application.cards.Card.*;                                         // just import it in case it might be needed
import static p1.a1x1.application.cards.Card.Constant.*;                                // just import it in case it might be needed
import static p1.a1x1.supportStuff.applicationSupport.HandRanking.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import p1.a1x1.application.cards.Card;
import p1.a1x1.application.implementation.PlayerHand;
import p1.a1x1.application.implementation.PokerHand;
import p1.a1x1.application.implementation.PokerHandEvaluator;
//
import p1.a1x1.application.cards.*;                                                     // just import it in case it might be needed
import p1.a1x1.application.cards.Card.*;                                                // just import it in case it might be needed
import p1.a1x1.supportStuff.applicationSupport.HandRanking;
import p1.a1x1.supportStuff.testSupport.*;
import p1.a1x1.supportStuff.utility.Version;
import p1.a1x1.test.GameAnalyzer;
import p1.a1x1.supportStuff.testSupport.ResultOfGame;
import p1.a1x1.supportStuff.testSupport.ResultOfPlayer;
import p1.a1x1.supportStuff.testSupport.TestCaseActual;



/**
 * Implementiert die Spiellogik für Texas Hold'em Poker.
 * 
 * Diese Klasse koordiniert die Evaluierung der Händen aller 5 Spieler, bestimmt
 * ihre Ränge durch Vergleich und gibt die finalen Spielresultate zurück. Sie
 * implementiert das GameAnalyzer Interface für Test-Unterstützung.
 * 
 * @author Maksym Zadoya
 * @version 2025/11/6 #1
 */



public class TexasHoldemGame implements GameAnalyzer {
    //
    // --VERSION:-------------------------------#---vvvvvvvvv---vvvv-vv-vv--vv
    // ======== #___~version~___YYYY_MM_DD__dd_
    final static private long encodedVersion = 2___00002_001___2023_10_04__01L;
    // -----------------------------------------#---^^^^^-^^^---^^^^-^^-^^--^^
    final static private Version version = new Version(encodedVersion);

    /**
     * The method {@link #getDecodedVersion()} delivers the code version as
     * reground/readable String.
     * 
     * @return version as decoded/readable String.
     */
    static public String getDecodedVersion() {
        return version.getDecodedVersion();
    }
    // Obiges (ab VERSION) dient nur der Versionierung

    
    
    
    @Override
    public ResultOfGame compute(final TestCaseActual tca) {

        // Zugriff auf die Karten
        // ======================

        List<Card[]> players = new ArrayList<Card[]>();

        Card[] player1_hole_cards = tca.getPlayerHoleCards(0); // player #1
        players.add(player1_hole_cards);
        Card[] player2_hole_cards = tca.getPlayerHoleCards(1); // player #2
        players.add(player2_hole_cards);
        Card[] player3_hole_cards = tca.getPlayerHoleCards(2); // player #3
        players.add(player3_hole_cards);
        Card[] player4_hole_cards = tca.getPlayerHoleCards(3); // player #4
        players.add(player4_hole_cards);
        Card[] player5_hole_cards = tca.getPlayerHoleCards(4); // player #5
        players.add(player5_hole_cards);

        Card[] community_cards = tca.getCommunityCards(); //
        PokerHandEvaluator evaluator = new PokerHandEvaluator();
        PokerHand[] resultOfTheGame = new PokerHand[players.size()];

        int playerNumber = 0;

        for (Card[] cards : players) {
            PokerHand hand = evaluator.evaluate(cards, community_cards, playerNumber);
            resultOfTheGame[playerNumber] = hand;
            playerNumber++;

        }

        int place = 1;
        int actualPlace = 1;

        HandRanking[] allRankings = HandRanking.values();

        for (int j = allRankings.length - 1; j >= 0; j--) {
            place = actualPlace;
            for (int i = 0; i < players.size(); i++) {
                HandRanking temp = resultOfTheGame[i].getRanking();
                if (allRankings[j].equals(temp)) {
                    resultOfTheGame[i].setPlayerRank(place);
                    actualPlace++;
                }

            }
        }

        PokerHand[] copyOfResult = Arrays.copyOf(resultOfTheGame, resultOfTheGame.length);

        for (int i = 0; i < players.size(); i++) {
            for (int j = i + 1; j < players.size(); j++) {
                if (copyOfResult[i].getRanking() == copyOfResult[j].getRanking()) {
                    int result = evaluator.compareTwoHands(copyOfResult[i].getPlayerCards(),
                            copyOfResult[j].getPlayerCards(), copyOfResult[j].getRanking());

                    if (result == 1) {
                        int currentPos = resultOfTheGame[j].getPlayerRank();
                        resultOfTheGame[j].setPlayerRank(currentPos + 1);
                    } else if (result == -1) {
                        int currentPos = resultOfTheGame[i].getPlayerRank();
                        resultOfTheGame[i].setPlayerRank(currentPos + 1);
                    }
                    else if (result == 0) {
                        resultOfTheGame[i].setPlayerRank(resultOfTheGame[i].getPlayerRank());
                    }
                }
            }
        }

        final Card[] pokerHandOfPlayer1 = resultOfTheGame[0].getPlayerCards();
        final Card[] pokerHandOfPlayer2 = resultOfTheGame[1].getPlayerCards();
        final Card[] pokerHandOfPlayer3 = resultOfTheGame[2].getPlayerCards();
        final Card[] pokerHandOfPlayer4 = resultOfTheGame[3].getPlayerCards();
        final Card[] pokerHandOfPlayer5 = resultOfTheGame[4].getPlayerCards();

        // Wie heißt die jeweilige (Poker-)Hand
        final HandRanking handRankingOfPlayer1 = resultOfTheGame[0].getRanking();
        final HandRanking handRankingOfPlayer2 = resultOfTheGame[1].getRanking();
        final HandRanking handRankingOfPlayer3 = resultOfTheGame[2].getRanking();
        final HandRanking handRankingOfPlayer4 = resultOfTheGame[3].getRanking();
        final HandRanking handRankingOfPlayer5 = resultOfTheGame[4].getRanking();

        //
        // Welche Position hat der Spieler - ist er Erster, Zweiter, Dritter , Vierter
        // oder Fuenfter ?
        final int positionOfPlayer1 = resultOfTheGame[0].getPlayerRank();
        final int positionOfPlayer2 = resultOfTheGame[1].getPlayerRank();
        final int positionOfPlayer3 = resultOfTheGame[2].getPlayerRank();
        final int positionOfPlayer4 = resultOfTheGame[3].getPlayerRank();
        final int positionOfPlayer5 = resultOfTheGame[4].getPlayerRank();

        //
        // Abliefern des Ergebnisses
        // =========================

        final ResultOfGame rog = new ResultOfGame(new ResultOfPlayer[] {
                //
                // /------------------------------------------------------ position of player:
                // | 1 is the best, .., 5 is the worst
                // |
                // | /---------------------------------- handranking of player - valid values
                // are:
                // | | STRAIGHT_FLUSH, QUADS, FULL_HOUSE, FLUSH, STRAIGHT,
                // | | TRIPS, TWO_PAIR, ONE_PAIR and HIGH_CARD.
                // | | These values are defined in the enum poker.testSupport.HandRanking
                // | |
                // | | /----------- cards of player that are played
                // | | |
                // V V V
                new ResultOfPlayer(positionOfPlayer1, handRankingOfPlayer1, pokerHandOfPlayer1), // results for player
                                                                                                 // #1
                new ResultOfPlayer(positionOfPlayer2, handRankingOfPlayer2, pokerHandOfPlayer2), // results for player
                                                                                                 // #2
                new ResultOfPlayer(positionOfPlayer3, handRankingOfPlayer3, pokerHandOfPlayer3), // results for player
                                                                                                 // #3
                new ResultOfPlayer(positionOfPlayer4, handRankingOfPlayer4, pokerHandOfPlayer4), // results for player
                                                                                                 // #4
                new ResultOfPlayer(positionOfPlayer5, handRankingOfPlayer5, pokerHandOfPlayer5) // results for player #5
        }// array-new
        );// new

        return rog;
    }// method()

}// class
