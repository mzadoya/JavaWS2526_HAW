// This source code is UTF-8 coded - see https://stackoverflow.com/questions/9180981/how-to-support-utf-8-encoding-in-eclipse
package p1.a1x1.supportStuff.testSupport;


import java.util.Arrays;

import p1.a1x1.supportStuff.utility.Version;


public class ResultOfGame {
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
    
    
    
    
    
    //@ChunkPreamble ( lastModified="2014/05/30", lastModifiedBy="Michael Schaefers" )
    final ResultOfPlayer[] rop;                                                 // package scope on purpose
    
    
    
    
    
    public ResultOfGame( final ResultOfPlayer[] rop ){
        assert rop.length == Constant.NUM_OF_PLAYERS : String.format( "Usage Error: %d != %d",  rop.length, Constant.NUM_OF_PLAYERS ) ;
        this.rop = rop.clone();
    }//constructor()
    
    
    
    
    
    public void standardizedPrint(){
        
        // print cards of placers in a nice way
        final char RAQUO = '\u00bb';                                            // Guillemet: '>>'
        //
        for ( int i=0; i<Constant.NUM_OF_PLAYERS; i++ ){
            System.out.printf( "player#%d plays:  ", i+1 );
            switch ( rop[i].handRank ){
                case STRAIGHT_FLUSH: System.out.printf( "[%s%c%s%c%s%c%s%c%s]",  rop[i].card[0], RAQUO, rop[i].card[1], RAQUO, rop[i].card[2], RAQUO, rop[i].card[3], RAQUO, rop[i].card[4] ); break;
                case QUADS:          System.out.printf( "[%s-%s-%s-%s|%s]",      rop[i].card[0], rop[i].card[1], rop[i].card[2], rop[i].card[3], rop[i].card[4] ); break;
                case FULL_HOUSE:     System.out.printf( "[%s-%s-%s|%s-%s]",      rop[i].card[0], rop[i].card[1], rop[i].card[2], rop[i].card[3], rop[i].card[4] ); break;
                case FLUSH:          System.out.printf( "[%s~%s~%s~%s~%s]",      rop[i].card[0], rop[i].card[1], rop[i].card[2], rop[i].card[3], rop[i].card[4] ); break;
                case STRAIGHT:       System.out.printf( "[%s>%s>%s>%s>%s]",      rop[i].card[0], rop[i].card[1], rop[i].card[2], rop[i].card[3], rop[i].card[4] ); break;
                case TRIPS:          System.out.printf( "[%s-%s-%s|%s|%s]",      rop[i].card[0], rop[i].card[1], rop[i].card[2], rop[i].card[3], rop[i].card[4] ); break;
                case TWO_PAIR:       System.out.printf( "[%s-%s|%s-%s|%s]",      rop[i].card[0], rop[i].card[1], rop[i].card[2], rop[i].card[3], rop[i].card[4] ); break;
                case ONE_PAIR:       System.out.printf( "[%s-%s|%s|%s|%s]",      rop[i].card[0], rop[i].card[1], rop[i].card[2], rop[i].card[3], rop[i].card[4] ); break;
                case HIGH_CARD:      System.out.printf( "[%s|%s|%s|%s|%s]",      rop[i].card[0], rop[i].card[1], rop[i].card[2], rop[i].card[3], rop[i].card[4] ); break;
            }//switch
            System.out.printf( "  %s\n",  rop[i].handRank );
        }//for
        System.out.printf( "\n" );
        
        
        // print showdown rankings of players in a nice way
        class ShowDownRank implements Comparable<ShowDownRank>{
            ShowDownRank( final int showDownRank, final int playerId ){
                this.showDownRank = showDownRank;
                this.playerId = playerId;
            }//constructor()
            //
            @Override
            public int compareTo( ShowDownRank other ){
                final int tmp = showDownRank - other.showDownRank;
                return (tmp!=0) ? tmp : playerId - other.playerId;
            }//method()
            //
            final int showDownRank;
            //
            final int playerId;
        }//class
        //
        ShowDownRank[] showDownRanking = new ShowDownRank[Constant.NUM_OF_PLAYERS];
        for ( int i=0; i<Constant.NUM_OF_PLAYERS; )  showDownRanking[i] = new ShowDownRank( rop[i].position, ++i );
        Arrays.sort( showDownRanking );
        //
        StringBuilder sb = new StringBuilder( "~~>    { " );
        loop:
        for ( int i=0; true; ){
            sb.append( String.format( "player#%d_(%d)",  showDownRanking[i].playerId, showDownRanking[i].showDownRank ));
            if ( i>=Constant.NUM_OF_PLAYERS-1)  break loop;
            sb.append( (showDownRanking[i].showDownRank==showDownRanking[++i].showDownRank)  ?  " & "  :  " }   >   { " );
        }//for
        sb.append( " }" );
        //
        System.out.printf( "%s\n", sb );
        
    }//method()
    
}//class
