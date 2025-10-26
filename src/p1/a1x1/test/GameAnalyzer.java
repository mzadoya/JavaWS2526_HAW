// This source code is UTF-8 coded - see https://stackoverflow.com/questions/9180981/how-to-support-utf-8-encoding-in-eclipse
package p1.a1x1.test;


import p1.a1x1.supportStuff.testSupport.ResultOfGame;
import p1.a1x1.supportStuff.testSupport.TestCaseActual;
import p1.a1x1.supportStuff.utility.Version;


public interface GameAnalyzer {
    //
    //--VERSION:-----------------------#---vvvvvvvvv---vvvv-vv-vv--vv
    //  ========                       #___~version~___YYYY_MM_DD__dd_
    final static long encodedVersion = 2___00002_001___2023_08_25__01L;
    //---------------------------------#---^^^^^-^^^---^^^^-^^-^^--^^
    final static Version MultiPurposeListInterfaceVersion = new Version( encodedVersion );
    static String getDecodedVersion(){ return MultiPurposeListInterfaceVersion.getDecodedVersion(); }
    // Obiges (ab VERSION) dient nur der Versionierung
    
    
    
    
    
    // default constructor resp. constructor without any parameter is requested
  //public GameAnalyzer();
    
    
    
    
    
     ResultOfGame compute( final TestCaseActual tca );
    
}//interface
