package p1.a1x2.resistor;

/**
 * Ein normaler einzelner Widerstand mit einem festen, unverändbaren
 * Widerstandwert
 * 
 * @author Maksym Zadoya
 * @version 2025/10/28 #1
 *  
 */
public class OrdinaryResistor extends Resistor{

    /**
     * Erstellt einen neeun Widerstand mit dem Anfangswiderstandswert.
     * 
     * @param name der Name des Widerstands
     * @param value der Anfangswiderstandswert in Ohm
     */
    public OrdinaryResistor (String name, double value) {
        super(name, value);
    }
    
    /**
     * Gibt den aktuellen Widerstandswert dieses Widerstands 
     * zurück
     * 
     * @return der Widerstandwert in Ohm
     */
    @Override
    public double getResistance() {
        return super.wiederstandwert;
    }
    
    /**
     * Gibt die Anzahl der Widerstaende zurück
     * 
     * Da ein Widerstand ein einzelner Widerstand ist, wird immer 
     * 1 zurueckgegeben
     * 
     * @return 1
     */
    @Override
    public int getNumberOfResistors() {
        return 1;
    }
    
    /**
     * Gibt den Namen dieses Widerstands als String zurück
     * 
     * @return der Name des Widerstands
     */
    @Override
    public String getCircuit() {
        return super.name;
    }
}
