package p2.a2x1.test;


import java.util.List;
import java.util.Scanner;

import p2.a2x1.application.Maze;
import p2.a2x1.application.PathFinderWrapper;
import p2.a2x1.application.PathFinder_I;
import p2.a2x1.supportStuff.applicationSupport.Coordinate;
import p2.a2x1.supportStuff.testSupport.MazeBuilder;
import p2.a2x1.supportStuff.testSupport.MazeDefinition;
import p2.a2x1.supportStuff.testSupport.MazeDefinition.MazeId;
import p2.a2x1.supportStuff.testSupport.Tools;
import p2.a2x1.supportStuff.utility.Beautician;
import p2.a2x1.supportStuff.utility.Herald;
import p2.a2x1.supportStuff.utility.Version;


/**
 * (Manual)TestFrame for PathFinder / Maze : Sequential(P2) & Parallel(PPJ)
 * For further information see ReadMe.txt resp. task
 * 
 * @version {@value #encodedVersion}
 * @author  Michael Schaefers  ([UTF-8]:"Michael Schäfers");
 *          Px@Hamburg-UAS.eu 
 */
public class TestFrameAndStarterSingleManual {
    //
    //--VERSION:-------------------------------#---vvvvvvvvv---vvvv-vv-vv--vv
    //  ========                               #___~version~___YYYY_MM_DD__dd_
    final static private long encodedVersion = 2___00001_005___2021_12_10__01L;
    //-----------------------------------------#---^^^^^-^^^---^^^^-^^-^^--^^
    final static private Version version = new Version( encodedVersion );
    /**
     * Get decoded version of code {@link UnitTestFrameAndStarterAllCasesAutomated}
     * 
     * @return decoded version
     */
    static public String getDecodedVersion(){ return version.getDecodedVersion(); }
    // Obiges (ab VERSION) dient nur der Versionierung.
    
    
    
    
    
    public static void main( final String... unused ){
        
        // show info
        StringBuffer sb = new StringBuffer();
        for( final MazeId mazeId : MazeId.values() ){
            sb.append( String.format( "%s -> %d\n", mazeId, mazeId.ordinal()));
        }//for
        sb.append( "\n\n" );
        Herald.proclaimMessage( sb.toString() );
        
        
        // do dialog with user
        final Scanner keyboard = new Scanner( System.in );
        Herald.proclaimMessage( "Bitte Labyrinth ID eingeben  :  " );
        final int mazeId = keyboard.nextInt();
        Herald.proclaimMessage( "Bitte \"Alternative\" eingeben :  " );
        final int alternative = keyboard.nextInt();
        keyboard.close();
        
        
        // setup test
        final MazeBuilder testMaze = MazeBuilder.createMaze( MazeDefinition.definition.get( MazeId.values()[mazeId] ),  alternative );
        final Maze copyForCodeUnderTest = (Maze)( testMaze.clone() );
        Herald.proclaimMessage( "\n\n" );
        testMaze.prettyPrint();
        Herald.proclaimMessage( "\n\n" );
        
        
        // do compution
        final long time1 = System.nanoTime();
        final PathFinder_I pathfinder = new PathFinderWrapper( copyForCodeUnderTest );
        final List<Coordinate> path = pathfinder.getShortestPath();
        final long time2 = System.nanoTime();
        
        
        // show first results
        Herald.proclaimMessage( String.format( "After: %s\n\n",  Beautician.nanoSecondBasedTimeToString( time2-time1 )));
        copyForCodeUnderTest.prettyPrint();
        Herald.proclaimMessage( String.format( "\npath:  %s\n", path ));
        if( null != path ){
            Herald.proclaimMessage( String.format( "steps: %d\n", path.size()));
            
            // do some checks
            //
            boolean failureFound = false;
            //
            // check start
            if( ! testMaze.getStart().equals( path.get(0) )){
                failureFound = true;
                Herald.proclaimError( String.format( "INVALID start :  %s != %s\n",  testMaze.getStart(), path.get(0)));
            }//if
            //
            // check destination
            if( ! testMaze.getDestination().equals( path.get(path.size() -1 ) )){
                failureFound = true;
                Herald.proclaimError( String.format( "INVALID destination :  %s != %s\n",  testMaze.getDestination(), path.get(path.size() -1 )));
            }//if
            //
            // check path
            Coordinate cLast = path.get(0);
            for( int indx=1 ; indx<path.size(); indx++ ){
                final Coordinate cNew = path.get( indx );
                final int[][] testMazeField = testMaze.getMazeField();
                final int xMax=testMazeField.length;
                final int yMax=testMazeField[0].length;
                final int x = cNew.getX();
                final int y = cNew.getY();
                if( x<0 || xMax<=x || y<0 || yMax<=y ){
                    failureFound = true;
                    Herald.proclaimError( String.format( "INVALID field coordinate: %s\n",  cNew ));
                }//if
                final int fieldValue = testMazeField[x][y];
                if( fieldValue == Integer.MIN_VALUE ){
                    failureFound = true;
                    Herald.proclaimError( String.format( "INVALID step on coordinate: %s  @[%s;%d]",  cNew, mazeId, alternative ));
                }//if
                if( 1 != Tools.delta( cNew, cLast )){
                    failureFound = true;
                    Herald.proclaimError( String.format( "INVALID step on coordinate: %s  @[%s;%d]",  cNew, mazeId, alternative ));
                }//if
                cLast = cNew;
            }//for
            //
            if( ! failureFound ){
                Herald.proclaimMessage( "NO failures detected - but having checked only partly ;-)" );
            }//if
        }else{
            Herald.proclaimMessage( "NO path was found at all\n" );
        }//if
        //
        Herald.proclaimMessage( String.format( "\n\n%s after %s",  pathfinder.getClass().getName(), Beautician.nanoSecondBasedTimeToString( time2-time1 )));
    }//method()
    
}//class
