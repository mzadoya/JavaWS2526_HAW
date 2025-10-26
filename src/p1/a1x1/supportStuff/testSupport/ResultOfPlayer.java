// This source code is UTF-8 coded - see https://stackoverflow.com/questions/9180981/how-to-support-utf-8-encoding-in-eclipse
package p1.a1x1.supportStuff.testSupport;


import p1.a1x1.application.cards.Card;

import p1.a1x1.supportStuff.applicationSupport.HandRanking;
import p1.a1x1.supportStuff.utility.Version;


public class ResultOfPlayer {
    //
    //--VERSION:-------------------------------#---vvvvvvvvv---vvvv-vv-vv--vv
    //  ========                               #___~version~___YYYY_MM_DD__dd_
    final static private long encodedVersion = 2___00002_001___2023_08_25__01L;
    //-----------------------------------------#---^^^^^-^^^---^^^^-^^-^^--^^
    final static private Version version = new Version( encodedVersion );
    /**
     * The method {@link #getDecodedVersion()} delivers the code version as reground/readable String.
     * @return version as decoded/readable String.
     */
    static public String getDecodedVersion(){ return version.getDecodedVersion(); }
    // Obiges (ab VERSION) dient nur der Versionierung
    
    
    
    
    
    final HandRanking handRank;
    
    final Card[] card;
    
    int position;                                                               // 1=best, 5=worst(of 5)
    
    
    
    
    
    public ResultOfPlayer( final int pos,  final HandRanking hrk,  final Card[] card ){
        this.position = pos;
        this.handRank = hrk;
        this.card     = card.clone();
    }//constructor()
    //
    public ResultOfPlayer( final HandRanking hrk,  final Card[] card ){
        this( Integer.MAX_VALUE, hrk, card );
    }//constructor()
    
    
    
    
    
    public int setPosition( final int newPositionValue ){
        final int oldPositionValue = position;
        position = newPositionValue;
        return oldPositionValue;
    }//method()
    
    @Override
    public ResultOfPlayer clone(){ return new ResultOfPlayer( this.position, this.handRank, card.clone() ); }
    
}//class
