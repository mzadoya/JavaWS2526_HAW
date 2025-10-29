package p1.a1x2.resistor;

/**
 * Reppräsentiert ein Potentiometer, dessen Widerstandwert verändert
 * werden kann.
 * 
 * @author Maksym Zadoya
 * @version 2025/10/28 #1
 *  
 */
public class Potentiometer extends Resistor{

    /**
     * Erstellt ein neues Potentiometer mit dem Anfangswiderstandswert.
     * 
     * @param name der Name des Widerstands
     * @param value der Anfangswiderstandswert in Ohm
     */
    public Potentiometer (String name, double value) {
        super(name, value);
    }
    
    /**
     * Erstellt ein neues Potentiometer ohne den Anfangswiderstandswert, 
     * bzw. mit dem Widerstandswert 0 Ohm.
     * 
     * @param name der Name des Widerstands
     */
    public Potentiometer (String name) {
        super(name, 0);
    }
    
    /**
     * Ermöglicht die Änderung des Widerstandswerts dieses 
     * Potentiometers
     * 
     * @param value der neue Widerstandswert in Ohm
     */
    public void setResistance(double value) {
        super.wiederstandwert = value;
    }

    /**
     * Gibt den aktuellen Widerstandswert dieses Potentiometers 
     * zurück
     * 
     * @return der Widerstandwert in Ohm
     */
    @Override
    public double getResistance() {
        return super.wiederstandwert;
    }
    
    /**
     * Gibt die Anzahl der Widerstände zurück
     * 
     * Da ein Potentiometer ein einzelner Widerstand ist, wird immer 
     * 1 zurueckgegeben
     * 
     * @return 1
     */
    @Override
    public int getNumberOfResistors() {
        return 1;
    }

    /**
     * Gibt den Namen dieses Potentiometers als String zurück
     * 
     * @return der Name des Potentiometers
     */
    @Override
    public String getCircuit() {
        return super.name;
    }
    
}
