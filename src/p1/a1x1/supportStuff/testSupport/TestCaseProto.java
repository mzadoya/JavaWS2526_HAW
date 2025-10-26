// This source code is UTF-8 coded - see https://stackoverflow.com/questions/9180981/how-to-support-utf-8-encoding-in-eclipse
package p1.a1x1.supportStuff.testSupport;


import p1.a1x1.application.cards.Card;
import p1.a1x1.supportStuff.utility.Version;


public class TestCaseProto {
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
    
    
    
    
    
    final Card[] card;
    //
    final long id;
    //
    final long prm;
    //
    final String[] rop;
    
    
    
    public TestCaseProto( final Card[] card,  final long id,  final long prm,  final String[] rop ){
        this.card = card;
        this.id = id;
        this.prm = prm;
        this.rop = rop;
    }//constructor()
    
}//class
