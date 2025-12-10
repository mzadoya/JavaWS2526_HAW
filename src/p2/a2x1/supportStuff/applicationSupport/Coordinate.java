// This source code is UTF-8 coded - see https://stackoverflow.com/questions/9180981/how-to-support-utf-8-encoding-in-eclipse
package p2.a2x1.supportStuff.applicationSupport;


import java.io.Serializable;

import p2.a2x1.supportStuff.utility.Version;


/**
 * Task: For information see ReadMe.txt resp. task
 * 
 * @version {@value #encodedVersion}
 * @author  Michael Schaefers  ([UTF-8]:"Michael Schäfers");
 *          Px@Hamburg-UAS.eu 
 */
public class Coordinate implements Cloneable, Serializable {
    //
    //--VERSION:-----------------------#---vvvvvvvvv---vvvv-vv-vv--vv
    //  ========                       #___~version~___YYYY_MM_DD__dd_
    final static long encodedVersion = 2___00001_001___2023_08_31__01L;
    //---------------------------------#---^^^^^-^^^---^^^^-^^-^^--^^
    final static Version version = new Version( encodedVersion );
    /**
     * The method {@link #getDecodedVersion()} delivers the code version as reground/readable String.
     * @return version as decoded/readable String.
     */
    static String getDecodedVersion(){ return version.getDecodedVersion(); }
    // Obiges (ab VERSION) dient nur der Versionierung
    //
    final static private long serialVersionUID = version.getEncodedVersion();
    
    
    
    
    
    final private int x;    public int getX(){ return x; }
    final private int y;    public int getY(){ return y; }
    
    
    
    public Coordinate( final int x, final int y ){
        this.x = x;
        this.y = y;
    }//constructor()
    
    
    
    @Override
    public Object clone(){
        return new Coordinate( x, y );
    }//method()
    
    
    @Override
    public boolean equals( final Object otherObject ){
        if( this == otherObject )  return true;
        if( null == otherObject )  return false;
        if( getClass()!=otherObject.getClass() )  return false;
        final Coordinate other = (Coordinate)( otherObject );
        if ( x != other.x )  return false;
        if ( y != other.y )  return false;
        return true;
    }//method()
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime*result + x;
        result = prime*result + y;
        return result;
    }//method()
    
    
    @Override
    public String toString(){
        return String.format(
            "(x:%02d,y:%02d)",
            x,
            y
        );//String.format()
    }//method()
    
}//class
