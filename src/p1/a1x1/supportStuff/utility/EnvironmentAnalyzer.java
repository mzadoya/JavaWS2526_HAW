// This source code is UTF-8 coded - see https://stackoverflow.com/questions/9180981/how-to-support-utf-8-encoding-in-eclipse
// "Home"-VCS: git@github.com:ElCodificadorFeliz/ZZZ_SupportStuff    and before cyber attack :  git@git.HAW-Hamburg.de:shf/Px/LabExercise/ZZZ_SupportStuff[.git]
package p1.a1x1.supportStuff.utility;


import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.lang.module.ModuleDescriptor;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Optional;
import java.util.jar.Attributes;
import java.util.jar.Manifest;


/**
 * Task: For information see ReadMe.txt resp. task
 * 
 * @author  Michael Schaefers  ([UTF-8]:"Michael Schäfers");
 *          P1@Hamburg-UAS.eu
 * @version {@value #encodedVersion}
 */
public class EnvironmentAnalyzer implements Serializable {
    //
    //--VERSION:-------------------------------#---vvvvvvvvv---vvvv-vv-vv--vv
    //  ========                               #___~version~___YYYY_MM_DD__dd_
    final static private long encodedVersion = 2___00001_014___2025_09_24__01L;
    //-----------------------------------------#---^^^^^-^^^---^^^^-^^-^^--^^
    final static private Version version = new Version( encodedVersion );
    static public String getDecodedVersion(){ return version.getDecodedVersion(); }
    // Obiges (ab VERSION) dient nur der Versionierung
    //
    final static private long  serialVersionUID = version.getEncodedVersion();
    
    
    
    
    
    /**
     * Determine number of available cores
     * 
     * @return number of available cores
     */
    public static int getAvailableCores() {
        return Runtime.getRuntime().availableProcessors();
    }//method()
    
    
    
    /**
     * Determine if assert is enabled for JVM.
     * 
     * @return <code>true</code> if assert is enabled,
     *         <code>false</code> otherwise.
     */
    public static boolean isAssertEnabled(){
        try {
            assert false : "ASSERT IS ENABLED";
            throw new RuntimeException( "ASSERT IS DISABLED" );
        }catch( final Throwable ex ){
            if( ex instanceof AssertionError ){
                return true;
            }else{
                return false;
            }//if
        }//try
    }//method()
    
    
    
    /**
     * Determine Java version
     * 
     * @return java version
     */
    public static String getJavaVersion(){
        final String rawVersion = System.getProperty( "java.version" );
        if( rawVersion.startsWith("1.") ){
            //\=> java version: "1.0" - "1.4"
            return String.format( "%s (%s)",  rawVersion.substring( 2 ), rawVersion );
        }else{
            //\=> java version: "5.x" - "X.x"
            final int firstPositionOfDot = rawVersion.indexOf( "." );
            if( 0<firstPositionOfDot ){
                final int mainJavaVersion = Integer.valueOf( rawVersion.substring( 0, firstPositionOfDot ));
                if( 9 > mainJavaVersion ){
                    //\=> java version: "5.x" - "8.x"
                    return rawVersion;
                }else{
                    //\=> java version: "9.x" - "X.x"
                    return Runtime.version().toString();                        // ".version()" since Java 9
                }//if
            }else{
                //??? new effect with Temurin JDK25.0.0+36
                //\=> 25+36 ::= 25.0.0+36 currently, but always ???
                assert rawVersion.startsWith("25") : "unexpected version format";
                return Runtime.version().toString();                            // ".version()" since Java 9
            }//if
        }//if
    }//method()
    
    
    
    
    
    
    
    
    
    
    //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
    //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
    //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
    //XXX
    //XXX   Last Topic:
    //XXX   Determine JUnit related versions
    //XXX 
    //XXX   JUnit is hell - how do you get its version easily and timelessly?
    //XXX   Note -> see https://stackoverflow.com/questions/59377304/accessing-junit-version-during-runtime
    //XXX   With JUnit 4, it took forever to support access to the corresponding version at runtime:
    //XXX   \-> [JUnit4]:   "junit.runner.Version.id()"
    //XXX
    //XXX   JUnit5, with its 3 modules, is still waiting for a counterpart.
    //XXX   The following workaround/code is written in "slow motion" for maximum understanding.
    //XXX   Furthermore, deprecated code must be used to have at least some way.
    //XXX
    //XXX
    //XXX   250716:
    //XXX   Excessive checks for null as result of mysterious effects (NullPointerException) in the past.
    //XXX   The code is almost performance-hostile but hopefully as easy to maintain as possible.
    
    
    /*#*
     * Determine (JUnit-) Jupiter version
     * 
     * @return Jupiter version
     *#/
    public static String getJUnitJupiterVersion(){
        String version;
        
        version = getJUnitJupiterVersionBasedOnManifest();
        if( null!=version )  return version;
        
        version = getJUnitJupiterVersionBasedOnModuleDescriptor();
        if( null!=version )  return version;
        
        version = getJUnitJupiterVersionBasedOnPackageDescriptor();
        if( null!=version )  return version;

        version = "??? - can not be determined";
        return version;
    }//method()
    //OK
    */
    
    
    //~~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
    
    
    // The "counterpart" to JUnit4 WON'T work (currently)
    /*
    private static String getJunitJupiterVersionTheEasyWay(){
        return org.junit.jupiter.api.Version.getVersion();
    }//method()
    */
    
    
    /**
     * Determine (JUnit-) Jupiter version based on manifest
     * 
     * @return Jupiter version ;  null if no meaningful answer is possible
     */
    public static String getJUnitJupiterVersionBasedOnManifest(){
        final String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        try{
            final Class<?> classObj = EnvironmentAnalyzer.class;
            final ClassLoader classLoader = classObj.getClassLoader();
            if( null==classLoader )  return null;//<<<=================just staying on save side====
            //
            Enumeration<URL> resources = null;
            try{
                resources = classLoader.getResources( "META-INF/MANIFEST.MF" );
                if( null==resources )  return null;//<<<===============just staying on save side====
            }catch( final IOException ex ){
                ex.printStackTrace();
            }//try
            final List<StringBuilder> sbList = new ArrayList<StringBuilder>();
            while( resources.hasMoreElements() ){
                Manifest manifest = null;
                try{
                    final URL url = resources.nextElement();
                    if( null==url )  return null;//<<<=================just staying on save side====
                    final InputStream inputStream = url.openStream();
                    if( null==inputStream )  return null;//<<<=========just staying on save side====
                    manifest = new Manifest( inputStream );
                  //if( null==manifest )  return null;//<<<============just staying on save side====
                }catch( final IOException ex ){
                    ex.printStackTrace();
                }//try
                final Attributes manifestMainAttributes = manifest.getMainAttributes();
                if( null==manifestMainAttributes )  return null;//<<<==just staying on save side====
                final String implementationTitle = manifestMainAttributes.getValue( "Implementation-Title" );
                if( null!=implementationTitle  &&  "junit-jupiter-api".equals( implementationTitle )){
                    final StringBuilder sb = new StringBuilder();
                    sb.append( manifestMainAttributes.getValue( "Implementation-Version" ));
                    sb.append( " @ " );
                    sb.append( manifestMainAttributes.getValue( "Build-Date" ));
                    sb.append( "  ( based on manifest )" );
                    sbList.add( sb );
                }//if
            }//while
            if( 1 == sbList.size() ){
                return sbList.get(0).toString();//<<<###############################################
            }else{
                final StringBuilder sb = new StringBuilder();
                sb.append( "??? - unclear/multiple result" );
                for( final StringBuilder currSB : sbList ){
                    sb.append( " ;  " );
                    sb.append( currSB );
                }//
                return sb.toString();//<<<##########################################################
            }//if
        }catch( final RuntimeException ex ){                    // for safety's sake
            handleUnexpectedRuntimeException( ex, methodName ); // too much uncertain knowledge has determined implementation
        }//try
        //\=> Exception has occurred, otherwise control flow would not pass this line
        return null;
    }//method()
    
    
    /*#*
     * Determine (JUnit-) Jupiter version based on module descriptor
     * 
     * @return Jupiter version ;  null if no meaningful answer is possible
     *#/
    public static String getJUnitJupiterVersionBasedOnModuleDescriptor(){
        final String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        try{
            final Class<Test> testClass = org.junit.jupiter.api.Test.class;
            final Module module = testClass.getModule();
            if( null==module )  return null;//<<<======================just staying on save side====
            final ModuleDescriptor moduleDescriptor = module.getDescriptor();
            if( null==moduleDescriptor )  return null;//<<<============just staying on save side====
            final Optional<ModuleDescriptor.Version> optionalVersion = moduleDescriptor.version();
            return optionalVersion.isPresent()
                ? optionalVersion.get().toString() + "  ( based on module descriptor )"//<<<########
                : null;
        }catch( final RuntimeException ex ){                    // for sagety's sake
            handleUnexpectedRuntimeException( ex, methodName ); // too much uncertain knowledge has determined implementation
        }//try
        //\=> Exception has occurred, otherwise control flow would not pass this line
        return null;
    }//method()
    
    
    /*#*
     * Determine (JUnit-) Jupiter version based on package descriptor
     * 
     * @return Jupiter version ;  null if no meaningful answer is possible
     *#/
    public static String getJUnitJupiterVersionBasedOnPackageDescriptor(){
        final String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        try{
            final Class<Test> testClass = org.junit.jupiter.api.Test.class;
            final Package pakage = testClass.getPackage();
            if( null==pakage )  return null;//<<<======================just staying on save side====
            final String version = pakage.getImplementationVersion();
            if( null==version )  return null;//<<<=====================just staying on save side====
            return version + "  ( based on package descriptor )";//<<<##############################
        }catch( final RuntimeException ex ){                    // for safety's sake
            handleUnexpectedRuntimeException( ex, methodName ); // too much uncertain knowledge has determined implementation
        }//try
        //\=> Exception has occurred, otherwise control flow would not pass this line
        return null;
    }//method()
    
    
    //~~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
    
    
    /*#*
     * Determine (JUnit-) Platform version<br />
     * Attention: Access to JUnitPlatform is deprecated!
     * 
     * @return (JUnit-) Platform version
     *#/
    public static String getJUnitPlatformVersion(){
        String version;
        
        version = getJUnitPlatformVersionBasedOnManifest();
        if( null!=version )  return version;
        
        version = getJUnitPlatformVersionBasedOnModuleDescriptor();     // deprecated
        if( null!=version )  return version;
        
        version = getJUnitPlatformVersionBasedOnPackageDescriptor();    // deprecated
        if( null!=version )  return version;
        
        version = "??? - can not be determined";
        return version;
    }//method()
    
    
    //~~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
    
    
    // The "counterpart" to JUnit4 WON'T work (currently)
    /#*
    private static String getJunitPlatformVersionTheEasyWay(){
        return org.junit.platform.api.Version.getVersion();
    }//method()
    *#/
    
    
    /*#*
     * Determine (JUnit-) Platform version<br />
     * Attention: Access to JUnitPlatform is deprecated!
     * 
     * @return (JUnit-) Platform version ;  null if no meaningful answer is possible
     *#/
    public static String getJUnitPlatformVersionBasedOnManifest(){
        final String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        try{
            final Class<?> classObj = EnvironmentAnalyzer.class;
            final ClassLoader classLoader = classObj.getClassLoader();
            Enumeration<URL> resources = null;
            try{
                resources = classLoader.getResources( "META-INF/MANIFEST.MF" );
                if( null==resources )  return null;//<<<===============just staying on save side====
           }catch( final IOException ex ){
                ex.printStackTrace();
            }//try
            final List<StringBuilder> sbList = new ArrayList<StringBuilder>();
            while( resources.hasMoreElements() ){
                Manifest manifest = null;
                try{
                    final URL url = resources.nextElement();
                    if( null==url )  return null;//<<<=================just staying on save side====
                    final InputStream inputStream = url.openStream();
                    if( null==inputStream )  return null;//<<<=========just staying on save side====
                    manifest = new Manifest( inputStream );
                  //if( null==manifest )  return null;//<<<============just staying on save side====
                }catch( final IOException ex ){
                    ex.printStackTrace();
                }//try
                final Attributes manifestMainAttributes = manifest.getMainAttributes();
                if( null==manifestMainAttributes )  return null;//<<<==just staying on save side====
                final String implementationTitle = manifestMainAttributes.getValue( "Implementation-Title" );
                if( null!=implementationTitle  &&  "junit-platform-commons".equals( implementationTitle )){
                    final StringBuilder sb = new StringBuilder();
                    sb.append( manifestMainAttributes.getValue( "Implementation-Version" ));
                    sb.append( " @ " );
                    sb.append( manifestMainAttributes.getValue( "Build-Date" ));
                    sb.append( "  ( based on manifest )" );
                    sbList.add( sb );
                }//if
            }//while
            if( 1 == sbList.size() ){
                return sbList.get(0).toString();//<<<###############################################
            }else{
                final StringBuilder sb = new StringBuilder();
                sb.append( "??? - unclear/multiple result" );
                for( final StringBuilder currSB : sbList ){
                    sb.append( " ;  " );
                    sb.append( currSB );
                }//
                return sb.toString();//<<<##########################################################
            }//if
        }catch( final RuntimeException ex ){                    // for safety's sake
            handleUnexpectedRuntimeException( ex, methodName ); // too much uncertain knowledge has determined implementation
        }//try
        //\=> Exception has occurred, otherwise control flow would not pass this line
        return null;
    }//method()
    
    
    /*#*
     * Determine (JUnit-) Platform version<br />
     * Attention: Access to JUnitPlatform is deprecated!
     * 
     * @return (JUnit-) Platform version ;  null if no meaningful answer is possible
     *#/
    public static String getJUnitPlatformVersionBasedOnModuleDescriptor(){
        final String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        try{
            @SuppressWarnings("deprecation")
            final Class<JUnitPlatform> jUnitPlatformClass = org.junit.platform.runner.JUnitPlatform.class;  // deprecated!
            final Module module = jUnitPlatformClass.getModule();
            if( null==module )  return null;//<<<======================just staying on save side====
            final ModuleDescriptor moduleDescriptor = module.getDescriptor();
            if( null==moduleDescriptor )  return null;//<<<============just staying on save side====
            final Optional<ModuleDescriptor.Version> optionalVersion = moduleDescriptor.version();
            return optionalVersion.isPresent()
                ? optionalVersion.get().toString() + "  ( based on module descriptor )"//<<<########
                : null;
        }catch( final RuntimeException ex ){                    // for safety's sake
            handleUnexpectedRuntimeException( ex, methodName ); // too much uncertain knowledge has determined implementation
        }//try
        return null;
    }//method()
    
    
    /*#*
     * Determine (JUnit-) Platform version<br />
     * Attention: Access to JUnitPlatform is deprecated!
     * 
     * @return (JUnit-) Platform version ;  null if no meaningful answer is possible
     *#/
    public static String getJUnitPlatformVersionBasedOnPackageDescriptor(){
        final String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        try{
            @SuppressWarnings("deprecation")
            final Class<JUnitPlatform> jUnitPlatformClass = org.junit.platform.runner.JUnitPlatform.class;  // deprecated!
            final Package pakage = jUnitPlatformClass.getPackage();
            if( null==pakage )  return null;//<<<======================just staying on save side====
            final String version = pakage.getImplementationVersion();
            if( null==version )  return null;//<<<=====================just staying on save side====
            return version + "  ( based on package descriptor )";//<<<##############################
        }catch( final RuntimeException ex ){                    // for safety's sake
            handleUnexpectedRuntimeException( ex, methodName ); // too much uncertain knowledge has determined implementation
        }//try
        return null;
    }//method()
    */
    
    
    //~~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
    
    
    private static void handleUnexpectedRuntimeException( final Exception ex, final String methodName ){
        final StringBuilder sb = new StringBuilder();
        sb.append( "\n\n" );
        sb.append( "UNEXPECTED probably JUnit5 related exception occurred in :  " );
        sb.append( methodName );
        sb.append( "\n" );
        sb.append( "message :  " );
        sb.append( ex.getMessage() );
        sb.append( "\n" );
        Herald.proclaimError( sb );
        ex.printStackTrace();
        Herald.proclaimError( "\nCall advisor.\n\n" );
    }//method()
    
}//class
