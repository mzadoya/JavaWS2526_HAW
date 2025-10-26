// This source code is UTF-8 coded - see https://stackoverflow.com/questions/9180981/how-to-support-utf-8-encoding-in-eclipse
package p1.a1x1.supportStuff.testSupport;


import p1.a1x1.supportStuff.utility.Version;


public class Permutationer {
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
    
    
    
    
    
    static int[] decodePermutation( long codedPermutation ){                    // package scope on purpose
        final int[] permutation = new int[5];
        
        for ( int i=5; --i>=0; ){
            int cnt=0;
            while ( codedPermutation % Constant.PRIME[i] == 0 ){
                codedPermutation /= Constant.PRIME[i];
                cnt++;
            }//while
            permutation[i] = cnt;
        }//for
        
        assert codedPermutation==1 : "INTERNAL PROGRAMMING ERROR - contact programmer resp. Shf";
        return permutation;
    }//method()
    
    
    
    static long encodePermutation( int[] permutation ){                         // package scope on purpose
        long codedPermutation;
        
        codedPermutation = -1;  // see c07_S12W_TI1_P1.aufgabenzettel4_A9.inputGeneration.PermutationManagerModified

        assert codedPermutation>0 : "INTERNAL PROGRAMMING ERROR - contact programmer resp. Shf";
        return codedPermutation;
    }//method()
    
    
    
    static int[][] generateRandomPermutation( final int size ){                 // package scope on purpose
        assert size>0 : "INTERNAL PROGRAMMING ERROR - contact programmer resp. Shf";
        final int sm1 = size-1;
        
        final int[][] permutation = new int[2][size];
        for ( int i=size; --i>=0; ){
            permutation[0][i] = -1;                                             // init
            permutation[1][i] = -1;                                             // init
        }//for
        
        int j = 0;
        for ( int i=size; i>0; ){
            int r=1+(int)( Math.random() * i-- );
            while ( r>0 ){
                do{
                    if ( j < sm1 )  j++;  else  j=0;
                }while ( permutation[0][j] != -1 );
                r--;
            }//while
            permutation[0][j] = i;
            permutation[1][i] = j;
        }//for
        
        for ( int i=size; --i>=0; )  assert permutation[0][i]!=-1 && permutation[1][i]!=-1 : "INTERNAL PROGRAMMING ERROR - contact programmer resp. Shf";
        return permutation;
    }//method()
    
}//class
