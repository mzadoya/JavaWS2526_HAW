// This source code is UTF-8 coded - see https://stackoverflow.com/questions/9180981/how-to-support-utf-8-encoding-in-eclipse
package p1.a1x1.supportStuff.applicationSupport;


import p1.a1x1.supportStuff.utility.Version;


public enum HandRanking {
    HIGH_CARD,
    ONE_PAIR,
    TWO_PAIR,
    TRIPS,
    STRAIGHT,
    FLUSH,      
    FULL_HOUSE,
    QUADS,
    STRAIGHT_FLUSH;
    
    
    
    
    
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
    
}//enum
