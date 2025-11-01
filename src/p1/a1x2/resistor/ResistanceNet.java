package p1.a1x2.resistor;

/**
 * Dieses Interface definiert den Vertrag, die alle Widerstandnetze 
 * erfuellen muessen
 * 
 * @author Maksym Zadoya
 * @version 2025/10/29 #1
 *  
 */
public interface ResistanceNet {

    double getResistance();
    
    int getNumberOfResistors();
    
    String getCircuit();
}
