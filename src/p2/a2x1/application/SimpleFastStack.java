// This source code is UTF-8 coded - see https://stackoverflow.com/questions/9180981/how-to-support-utf-8-encoding-in-eclipse
package p2.a2x1.application;


import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

import p2.a2x1.supportStuff.utility.Version;


/**
 * Die Klasse {@link SimpleFastStack} implementiert einen schnellen und sehr einfachen
 * Stack als Alternative zu der Klasse java.util.Stack, die sich von Vector ableitet
 * und threadsafe und in der Konsequenz auch &quot;langsam&quot; ist.<br />
 * Die Klasse {@link SimpleFastStack} ist <strong><u>nicht</u> threadsafe</strong>.
 * 
 * @version {@value #encodedVersion}
 * @author  Michael Schäfers ;  P2@Hamburg-UAS.eu 
 */
public class SimpleFastStack<T> implements Serializable {
    //
    //--VERSION:-------------------------------#---vvvvvvvvv---vvvv-vv-vv--vv
    //  ========                               #___~version~___YYYY_MM_DD__dd_
    final static private long encodedVersion = 2___00001_002___2023_08_31__01L;
    //-----------------------------------------#---^^^^^-^^^---^^^^-^^-^^--^^
    final static private Version version = new Version( encodedVersion );
    /**
     * The method {@link #getDecodedVersion()} delivers the code version as reground/readable String.
     * @return version as decoded/readable String.
     */
    static public String getDecodedVersion(){ return version.getDecodedVersion(); }
    // Obiges (ab VERSION) dient nur der Versionierung
    //
    final static private long serialVersionUID = version.getEncodedVersion();
    
    
    
    
    
    final private Deque<T> stack;
    private int elemCnt;                                                        // ArrayList::size() is considered slower, especially in pop()
    
    
    
    /**
     * ...
     */
    public SimpleFastStack(){
        stack = new ArrayDeque<T>();                                            // <-> ConcurrentLinkedDeque
        elemCnt = 0;
    }//constructor()
    
    
    
    /**
     * ...
     * 
     * @param elem  pushes given element on top of stack
     */
    public void push( final T elem ){
        stack.addFirst( elem );                                                 // equals stack.push(elem)
        elemCnt++;
    }//method()
    
    /**
     * ...
     * 
     * @return  pops top element from stack
     */
    public T pop(){
        if( 0 >= elemCnt-- )  throw new RuntimeException( "Stack-Underflow" );
        return stack.removeFirst();                                             // equals stack.pop()
    }//method()
    
    /**
     * ...
     * 
     * @return  true if stack empty, false otherwise
     */
    public boolean isEmpty(){
        return 0 > elemCnt;
    }//method()
    
    /**
     * ...
     * 
     * @return  stack size
     */
    public int size(){
        return elemCnt;
    }//method()
    
    /**
     * ...
     * <br />
     * <br />
     * ATTENTION! : This method works the simple and insecure way.
     * <table>
     *  <tr> <td valign="top">&bull;</td> <td>
     *      Originals are given away e.g. by {@link #toList()}.
     *  <td> </tr>
     *  <tr> <td valign="top">&bull;</td> <td>
     *      Client has "full control" over internal matter. E.g. after {@link #toList()}
     *      Client hasall references to each single element stored inside stack.
     *  <td> </tr>
     * </table>
     * Hence this method has to used in well encapsulated internal scope.
     * 
     * @return  ...
     */
    @SuppressWarnings("unchecked")
    public List<T> toList(){
        return Arrays.asList( ((T[])( stack.toArray() )) );
    }//method()
    
}//class
