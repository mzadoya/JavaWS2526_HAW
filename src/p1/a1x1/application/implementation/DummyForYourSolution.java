// This source code is UTF-8 coded - see https://stackoverflow.com/questions/9180981/how-to-support-utf-8-encoding-in-eclipse
package p1.a1x1.application.implementation;


import static p1.a1x1.application.cards.Card.*;                                         // just import it in case it might be needed
import static p1.a1x1.application.cards.Card.Constant.*;                                // just import it in case it might be needed
import static p1.a1x1.supportStuff.applicationSupport.HandRanking.*;

import java.util.ArrayList;
import java.util.List;

import application.cards.Card;
import application.implementation.PokerHand;
import application.implementation.PokerHandEvaluator;
//
import p1.a1x1.application.cards.*;                                                     // just import it in case it might be needed
import p1.a1x1.application.cards.Card.*;                                                // just import it in case it might be needed
import p1.a1x1.supportStuff.applicationSupport.HandRanking;
import p1.a1x1.supportStuff.testSupport.*;
import p1.a1x1.supportStuff.utility.Version;
import p1.a1x1.test.GameAnalyzer;
import supportStuff.testSupport.ResultOfGame;
import supportStuff.testSupport.ResultOfPlayer;
import supportStuff.testSupport.TestCaseActual;



public class DummyForYourSolution implements GameAnalyzer {
    //
    //--VERSION:-------------------------------#---vvvvvvvvv---vvvv-vv-vv--vv
    //  ========                               #___~version~___YYYY_MM_DD__dd_
    final static private long encodedVersion = 2___00002_001___2023_10_04__01L;
    //-----------------------------------------#---^^^^^-^^^---^^^^-^^-^^--^^
    final static private Version version = new Version( encodedVersion );
    /**
     * The method {@link #getDecodedVersion()} delivers the code version as reground/readable String.
     * @return version as decoded/readable String.
     */
    static public String getDecodedVersion(){ return version.getDecodedVersion(); }
    // Obiges (ab VERSION) dient nur der Versionierung
    
    
    
    
    
    @Override
    public ResultOfGame compute( final TestCaseActual tca ){
        
        // Zugriff auf die Karten
        // ======================
         
         List<Card[]> players = new ArrayList<Card[]>();
         
         Card[]  player1_hole_cards =  tca.getPlayerHoleCards( 0 );         // player #1
         players.add(player1_hole_cards);
         Card[]  player2_hole_cards =  tca.getPlayerHoleCards( 1 );         // player #2
         players.add(player2_hole_cards);
         Card[]  player3_hole_cards =  tca.getPlayerHoleCards( 2 );         // player #3
         players.add(player3_hole_cards);
         Card[]  player4_hole_cards =  tca.getPlayerHoleCards( 3 );         // player #4
         players.add(player4_hole_cards);
         Card[]  player5_hole_cards =  tca.getPlayerHoleCards( 4 );         // player #5
         players.add(player5_hole_cards);
        
         Card[]  community_cards =     tca.getCommunityCards();             // 
         PokerHandEvaluator evaluator = new PokerHandEvaluator();
         PokerHand[] resultOfTheGame = new PokerHand[players.size()];
         HandRanking[] playersRanking = new HandRanking[players.size()];
                 
         int playerNumber = 0;
         
         
         for (Card[] cards : players) {
             
             resultOfTheGame[playerNumber] = evaluator.evaluate(cards, community_cards);
             playersRanking[playerNumber] = resultOfTheGame[playerNumber].getRanking();
             
             playerNumber++;
         }
         
         int place = 1;
         int actuallPlace = 1;
         boolean wasChanged = false;
         int[] playerPlace = new int[players.size()];
         
         HandRanking[] allRankings = HandRanking.values();

         for (int i = allRankings.length - 1; i >= 0; i--) { // TODO: vereinchen
             HandRanking hand = allRankings[i];
             if (wasChanged == true) {
                 place = actuallPlace;
                 wasChanged = false;
             }
             playerNumber = 0;
             for (HandRanking results : playersRanking) {
                 if (results == hand) {
                     playerPlace[playerNumber] = place;
                     wasChanged = true;
                     actuallPlace++;
                 }
                 playerNumber++;
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
         // Welche Position hat der Spieler - ist er Erster, Zweiter, Dritter , Vierter oder Fuenfter ?
         final int positionOfPlayer1 = playerPlace[0];   
         final int positionOfPlayer2 = playerPlace[1];   
         final int positionOfPlayer3 = playerPlace[2];   
         final int positionOfPlayer4 = playerPlace[3];      
         final int positionOfPlayer5 = playerPlace[4];     
        
         //
        
        
        // Ihre Berechnung
        // ===============
        //
        // ...
        // Hier kommt Code von Ihnen hin - (moegliche) Idee:
        // Moeglicherweise Konstruktion der 5 Player-Objekte/Spieler und Uebergabe der 7 zugehoerigen Karten an den jeweiligen Konstruktor.
        // Abfrage des Ergebnisses vom jeweiligen Spieler
        // Vergeleich der Ergenisse der jeweiligen Spieler und Bestimmung ihrer Position
        //
        // Weitere Annahme fuer das Beispiel - das (berechnete) Ergebnis sei "wie unten"
        
        
        
       /*
        
        // Im Vorfeld berechnen der Ergebnisse
        //
        // Welche (Poker-)Hand bzw. welche 5 Karten spielen die jeweiligen Spieler
        final Card[] pokerHandOfPlayer1 = new Card[]{ SQ, HQ, HJ, H9, D7 };                 // Dies ist nur ein Beispiel - Sie muessen dies berechnen
        final Card[] pokerHandOfPlayer2 = new Card[]{ SJ, HJ, CJ, H9, D7 };                 // Dies ist nur ein Beispiel - Sie muessen dies berechnen
        final Card[] pokerHandOfPlayer3 = new Card[]{ HJ, H9, D7, C5, D4 };                 // Dies ist nur ein Beispiel - Sie muessen dies berechnen
        final Card[] pokerHandOfPlayer4 = new Card[]{ HJ, H9, D7, C5, D4 };                 // Dies ist nur ein Beispiel - Sie muessen dies berechnen
        final Card[] pokerHandOfPlayer5 = new Card[]{ DQ, CQ, HJ, H9, D7 };                 // Dies ist nur ein Beispiel - Sie muessen dies berechnen
        //
        // Wie heißt die jeweilige (Poker-)Hand
        final HandRanking handRankingOfPlayer1 = ONE_PAIR;                                  // Dies ist nur ein Beispiel - Sie muessen dies berechnen
        final HandRanking handRankingOfPlayer2 = TRIPS;                                     // Dies ist nur ein Beispiel - Sie muessen dies berechnen
        final HandRanking handRankingOfPlayer3 = HIGH_CARD;                                 // Dies ist nur ein Beispiel - Sie muessen dies berechnen
        final HandRanking handRankingOfPlayer4 = HIGH_CARD;                                 // Dies ist nur ein Beispiel - Sie muessen dies berechnen
        final HandRanking handRankingOfPlayer5 = ONE_PAIR;                                  // Dies ist nur ein Beispiel - Sie muessen dies berechnen
        //
        // Welche Position hat der Spieler - ist er Erster, Zweiter, Dritter , Vierter oder Fuenfter ?
        final int positionOfPlayer1 = 2;    // Spieler1 ist Zweiter (zusammen mit Spieler5)  - dies ist nur ein Beispiel - Sie muessen dies berechnen
        final int positionOfPlayer2 = 1;    // Spieler2 ist Erster                           - dies ist nur ein Beispiel - Sie muessen dies berechnen
        final int positionOfPlayer3 = 4;    // Spieler3 ist Vierter (zusammen mit Spieler4)  - dies ist nur ein Beispiel - Sie muessen dies berechnen
        final int positionOfPlayer4 = 5;    // Spieler4 ist Vierter (zusammen mit Spieler3)  - dies ist nur ein Beispiel - Sie muessen dies berechnen
        final int positionOfPlayer5 = 3;    // Spieler5 ist Zweiter (zusammen mit Spieler1)  - dies ist nur ein Beispiel - Sie muessen dies berechnen
        //
        ///
         */
        // Nachdem Sie alles berechnet haben
        
        
        
        
        
        //
        // Abliefern des Ergebnisses
        // =========================
        
        final ResultOfGame rog = new ResultOfGame(
            new ResultOfPlayer[]{
                //
                //                  /------------------------------------------------------ position of player:
                //                  |                                                       1 is the best, .., 5 is the worst
                //                  |
                //                  |                   /---------------------------------- handranking of player - valid values are:
                //                  |                   |                                   STRAIGHT_FLUSH, QUADS, FULL_HOUSE, FLUSH, STRAIGHT, 
                //                  |                   |                                   TRIPS, TWO_PAIR, ONE_PAIR and HIGH_CARD.
                //                  |                   |                                   These values are defined in the enum poker.testSupport.HandRanking
                //                  |                   |
                //                  |                   |                      /----------- cards of player that are played
                //                  |                   |                      |
                //                  V                   V                      V
                new ResultOfPlayer( positionOfPlayer1,  handRankingOfPlayer1,  pokerHandOfPlayer1 ),   // results for player #1
                new ResultOfPlayer( positionOfPlayer2,  handRankingOfPlayer2,  pokerHandOfPlayer2 ),   // results for player #2
                new ResultOfPlayer( positionOfPlayer3,  handRankingOfPlayer3,  pokerHandOfPlayer3 ),   // results for player #3
                new ResultOfPlayer( positionOfPlayer4,  handRankingOfPlayer4,  pokerHandOfPlayer4 ),   // results for player #4
                new ResultOfPlayer( positionOfPlayer5,  handRankingOfPlayer5,  pokerHandOfPlayer5 )    // results for player #5
            }//array-new
        );//new
        
        return rog;
    }//method()
    
}//class
