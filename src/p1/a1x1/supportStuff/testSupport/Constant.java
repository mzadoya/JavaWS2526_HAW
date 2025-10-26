// This source code is UTF-8 coded - see https://stackoverflow.com/questions/9180981/how-to-support-utf-8-encoding-in-eclipse
package p1.a1x1.supportStuff.testSupport;


import p1.a1x1.supportStuff.utility.Version;


public class Constant {
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
    
    
    
    
    
    static final public int NUM_OF_PLAYERS = 5;
    
    static final long[] PRIME = {
          2,     3,     5,     7,    11,    13,    17,    19,    23,    29,
         31,    37,    41,    43,    47,    53,    59,    61,    67,    71,
         73,    79,    83,    89,    97,   101,   103,   107,   109,   113,
        127,   131,   137,   139,   149,   151,   157,   163,   167,   173,
        179,   181,   191,   193,   197,   199,   211,   223,   227,   229,
        233,   239,   241,   251,   257,   263,   269,   271,   277,   281,
        283,   293,   307,   311,   313,   317,   331,   337,   347,   349,
        353,   359,   367,   373,   379,   383,   389,   397,   401,   409,
        419,   421,   431,   433,   439,   443,   449,   457,   461,   463,
        467,   479,   487,   491,   499,   503,   509,   521,   523,   541
    };//array
    
    static final String requestedPackagePathApSu = "supportStuff.applicationSupport";
    static final String requestedPackagePathTeSu = "supportStuff.testSupport";
    static final String requestedPackagePathTest = "test";
    
}//class
