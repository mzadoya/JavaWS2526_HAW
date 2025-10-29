package p1.a1x2.resistor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Eine Reihenschaltung von Widerstandsnetzen. Der Gesamtwiderstand wird nach der 
 * Formel R_gesamt = R1 + R2 + ... + Rn berechnet. Die Berechnung erfogl in aufsteigender 
 * Reihenfolge der Widerstandswerte, umRundungsfehler zu minimieren
 * 
 * @author Maksym Zadoya
 * @version 2025/10/28 #1
 *  
 */
public class SeriesResistor extends ComposedResistor{
    
    private List<ResistanceNet> seriesResistors = new ArrayList<ResistanceNet>();
    
    public SeriesResistor(ResistanceNet... r) {
       for (ResistanceNet rc : r) {
           seriesResistors.add(rc);
       }
    }

    /**
     * Berechnet den Gesamtwiderstand in einer Reihenschaltung nach der Formel:
     * 
     * 1/R_gesamt = R1 + R2 + ... + Rn
     * 
     * Die Berechnung erfogl in aufsteigender Reihenfolge der Widerstandswerte, um
     * Rundungsfehler zu minimieren
     * 
     * @return den berechneten Widerstandswert 
     */
    @Override
    public double getResistance() {
        List<Double> copy = new ArrayList<Double>();
        double totalResistance = 0d;
        for (ResistanceNet rc : seriesResistors) {
            copy.add(rc.getResistance());
        }
        Collections.sort(copy);
        
        for (Double d : copy) {
            totalResistance = totalResistance + d;
        }
        return totalResistance;
    }

    /**
     * Ermittelt die Gesamtanzahl aller Widerstände in dieser Reihenschaltung
     * 
     * @return die Gesamtanzahl der Widerstände
     */
    @Override
    public int getNumberOfResistors() {
        int resistors = 0;
        for (ResistanceNet rc : seriesResistors) {
            resistors += rc.getNumberOfResistors();
        }
        return resistors;
    }

    /**
     * Gibt die String-Darstellung dieser Reihenschaltung zurük
     * 
     * Die Reihenfolge der Teilnetze aus dem Konstruktor bleibt erhalten.
     * 
     * @return String-Darstellung im Format (Netz1+Netz2+...)
     */
    @Override
    public String getCircuit() {
        StringBuilder circuit = new StringBuilder("(");
        for (ResistanceNet rc : seriesResistors) {
            circuit.append(rc.getCircuit() + "+");
        }
        circuit.setCharAt(circuit.length()-1, ')');
        return circuit.toString();
    }
    
}
