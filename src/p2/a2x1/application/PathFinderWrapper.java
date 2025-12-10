// This source code is UTF-8 coded - see https://stackoverflow.com/questions/9180981/how-to-support-utf-8-encoding-in-eclipse
package p2.a2x1.application;


import java.util.List;

import p2.a2x1.supportStuff.applicationSupport.Coordinate;
import p2.a2x1.supportStuff.utility.Version;


/**
 * Task: For information see ReadMe.txt resp. task
 * 
 * @version {@value #encodedVersion}
 * @author  Michael Schaefers  ([UTF-8]:"Michael Schäfers");
 *          Px@Hamburg-UAS.eu 
 */
public class PathFinderWrapper implements PathFinder_I {
    //
    //--VERSION:-------------------------------#---vvvvvvvvv---vvvv-vv-vv--vv
    //  ========                               #___~version~___YYYY_MM_DD__dd_
    final static private long encodedVersion = 2___00001_004___2025_09_25__01L;
    //-----------------------------------------#---^^^^^-^^^---^^^^-^^-^^--^^
    final static private Version version = new Version( encodedVersion );
    /**
     * The method {@link #getDecodedVersion()} delivers the code version as reground/readable String.
     * @return version as decoded/readable String.
     */
    static public String getDecodedVersion(){ return version.getDecodedVersion(); }
    // Obiges (ab VERSION) dient nur der Versionierung
    
    
    
    
    
    final private p2.a2x1.application.PathFinder_I theActualPathFinder;
    
    
    
    /**
     * This is just a demo. It shows an easy way to support multiple alternatives.
     * 
     * @param maze  the maze
     */
    public PathFinderWrapper( final Maze maze ){
        final int alternative = 42;
        switch( alternative ){
            case 0:
                theActualPathFinder = new p2.a2x1.supportStuff.applicationSupport.PathFinderDummy( maze );
                break;
            //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
            default:
                theActualPathFinder = new p2.a2x1.supportStuff.applicationSupport.PathFinderDummy( maze );
                break;
        }//switch
    }//constructor()
    
    
    
    @Override
    public List<Coordinate> getShortestPath() {
        return theActualPathFinder.getShortestPath();
    }//method()
    
}//class
