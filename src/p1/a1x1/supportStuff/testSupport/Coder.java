// This source code is UTF-8 coded - see https://stackoverflow.com/questions/9180981/how-to-support-utf-8-encoding-in-eclipse
package p1.a1x1.supportStuff.testSupport;


import p1.a1x1.application.cards.Card;
import java.math.BigInteger;
import p1.a1x1.supportStuff.utility.Version;


public class Coder {
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
    
    
    
    
    
    static BigInteger encodeROP( final ResultOfPlayer rop,  final TestCaseActual tca ){  // package scope on purpose
        BigInteger resu = BigInteger.ONE;
        Long value = encodeCards( rop.card, tca );
        resu = resu.multiply( new BigInteger( value.toString() ) );
        value = Constant.PRIME[52] * (1+rop.handRank.ordinal());                         // 13*4 = 52 cards => 0, .., 51
        resu = resu.multiply( new BigInteger( value.toString() ) );
        value = Constant.PRIME[53] * rop.position;                                       //
        resu = resu.multiply( new BigInteger( value.toString() ) );
        return resu;
    }//method()
    
    static long encodeCards( final Card[] ca,  final TestCaseActual tca ){      // package scope on purpose
        assert ca!=null & 5<=ca.length && ca.length<=7 : "Invalid parameter - card array requires 5 to 7 cards";
        long resu = 1L;
        for ( final Card c : ca ){
            resu *= Constant.PRIME[ computeCardOrdinal( tca.decodeSuite( c ) ) ];
        }//for
        return resu;
    }//method()
    
    static int computeCardOrdinal( final Card card ){                           // package scope on purpose
        return 4*card.getRank().ordinal() + card.getSuit().ordinal();
    }//method()
    
}//Coder
