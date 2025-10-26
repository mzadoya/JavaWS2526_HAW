// This source code is UTF-8 coded - see https://stackoverflow.com/questions/9180981/how-to-support-utf-8-encoding-in-eclipse
package p1.a1x1.test;


import p1.a1x1.application.implementation.DummyForYourSolution;
import java.time.Clock;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import p1.a1x1.supportStuff.testSupport.FieldSimulator;
import p1.a1x1.supportStuff.utility.EnvironmentAnalyzer;
import p1.a1x1.supportStuff.utility.Version;
import p1.a1x1.version.GivenCodeVersion;


public class TestFrameAndStarter {
    //
    //--VERSION:-------------------------------#---vvvvvvvvv---vvvv-vv-vv--vv
    //  ========                               #___~version~___YYYY_MM_DD__dd_
    final static private long encodedVersion = 2___00002_010___2025_09_24__01L;
    //-----------------------------------------#---^^^^^-^^^---^^^^-^^-^^--^^
    final static private Version version = new Version( encodedVersion );
    /**
     * The method {@link #getDecodedVersion()} delivers the code version as reground/readable String.
     * @return version as decoded/readable String.
     */
    static public String getDecodedVersion(){ return version.getDecodedVersion(); }
    // Obiges (ab VERSION) dient nur der Versionierung
    
    
    
    
    
    static final private class AnyNestedClass {}
    static final private AnyNestedClass anc = new AnyNestedClass();
    
    
    
    
    
    public static void main( final String... unused ){
        
        System.out.printf( "\n\n\n" );
        System.out.printf( "###############################################################################\n" );
        System.out.printf( "###############################################################################\n" );
        System.out.printf( "###############################################################################\n" );
        System.out.printf( "### Informationen zu den Versionen und zum Environment:\n" );
        System.out.printf( "### ===================================================\n" );
        System.out.printf( "###\n" );
        System.out.printf( "### Release:\n" );
        System.out.printf( "###     GivenCode version:  %31s\n",  GivenCodeVersion.getDecodedVersion() );
        System.out.printf( "###     TestFrame version:  %31s\n",  version.getDecodedVersion() );
        System.out.printf( "###     TestCase  version:  see below\n" );
        System.out.printf( "###\n" );
        System.out.printf( "###\n" );
        System.out.printf( "### Environment:\n" );
        System.out.printf( "###     Java (via Sys):     %s bzw. %s\n",         System.getProperty( "java.specification.version" ), System.getProperty( "java.version" ) );
        System.out.printf( "###     Java (via EA):      %s\n",                 EnvironmentAnalyzer.getJavaVersion() );
        System.out.printf( "###     Object related pkg: %s\n",                 new Object().getClass().getPackage() );
        System.out.printf( "###     #Cores:             %d\n",                 EnvironmentAnalyzer.getAvailableCores() );
        System.out.printf( "###     assert enabled?:    %s\n",                 EnvironmentAnalyzer.isAssertEnabled() );
        System.out.printf( "###     UTF-8 configured?:  %s          <- check output\n",  "[ÄËÏÖÜẞäëïöüß␣🙂😉😕🙁😟😓😎☠]" );
        System.out.printf( "###\n" );
        System.out.printf( "### %s    <- is home of \".class\"-files\n",       TestFrameAndStarter.class.getProtectionDomain().getCodeSource().getLocation().getPath() );
        System.out.printf( "### %s    is running\n",                           anc.getClass().getEnclosingClass().getCanonicalName() );
        System.out.printf( "###\n" );
        System.out.printf( "###\n" );
        System.out.printf( "### STARTING @ %s\n",                              DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone( ZoneId.of( "Europe/Berlin") ).format( Clock.systemUTC().instant() ) );
        System.out.printf( "\n" );
        System.out.flush();
        
        final GameAnalyzer ga = new DummyForYourSolution();
        final TestExecutor te = new FieldSimulator( ga );
        te.execute();
        
        System.out.printf( "###############################################################################\n" );
        System.out.printf( "###############################################################################\n" );
        System.out.printf( "### THE END @ %s", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone( ZoneId.of( "Europe/Berlin") ).format( Clock.systemUTC().instant() ) );
        
    }//method()
    
}//class
