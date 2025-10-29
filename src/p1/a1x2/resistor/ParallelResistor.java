package p1.a1x2.resistor;

import java.util.*;

/**
 * Eine Parallelschaltung von Widerstandsnetzen. Der Gesamtwiderstand wird nach der 
 * Formel 1/R_gesamt = 1/R1 + 1/R2 + ... + 1/Rn berechnet. Die Berechnung erfogl in 
 * aufsteigender Reihenfolge der Widerstandswerte, umRundungsfehler zu minimieren
 * 
 * @author Maksym Zadoya
 * @version 2025/10/28 #1
 *  
 */
public class ParallelResistor extends ComposedResistor{

    private List<ResistanceNet> paralleleResistors = new ArrayList<ResistanceNet>();
    public ParallelResistor(ResistanceNet... r) {
        for (ResistanceNet rc : r) {
            paralleleResistors.add(rc);
        }
    }
    
    /**
     * Berechnet den Gesamtwiderstand in einer Parallelschaltung nach der Formel:
     * 
     * 1/R_gesamt = 1/R1 + 1/R2 + ... + 1/Rn
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
        for (ResistanceNet rc : paralleleResistors) {
           copy.add(rc.getResistance());
    
          /* int i = 0;
           double value = rc.getResistance();
           while (value > copy.get(i) && i < copy.size()){
               i++;
           }
           if (i+1 == copy.size()) {
               copy.add(value);
           }
           else {
              double copyValue;
              while (i < copy.size()) {
                  copyValue = copy.get(i);
                  copy.set(i, value);
                  value = copyValue;
                  i++;
              }
              copy.add(value);
           }
           */
           
        }
        Collections.sort(copy);
        
        for (Double r : copy) {
            totalResistance = totalResistance + 1/r;
        }
        
        return 1/totalResistance;
    }
    
    /**
     * Ermittelt die Gesamtanzahl aller Widerstände in dieser Parallelschaltung
     * 
     * @return die Gesamtanzahl der Widerstände
     */
    @Override
    public int getNumberOfResistors() {
        int resistors = 0;
        for (ResistanceNet rc : paralleleResistors) {
            resistors += rc.getNumberOfResistors();
        }
        return resistors;
    }
    
    /**
     * Gibt die String-Darstellung dieser Parallelschaltung zurük
     * 
     * Die Reihenfolge der Teilnetze aus dem Konstruktor bleibt erhalten.
     * 
     * @return String-Darstellung im Format (Netz1|Netz2|...)
     */
    @Override
    public String getCircuit() {
        StringBuilder circuit = new StringBuilder("(");
        for (ResistanceNet rc : paralleleResistors) {
            circuit.append(rc.getCircuit() + "|");
        }
        circuit.setCharAt(circuit.length()-1, ')');
        return circuit.toString();
    }
}
