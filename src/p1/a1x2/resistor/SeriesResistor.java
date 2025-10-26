package p1.a1x2.resistor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SeriesResistor extends ComposedResistor{
    
    private List<ResistanceNet> seriesResistors = new ArrayList<ResistanceNet>();
    
    public SeriesResistor(ResistanceNet... r) {
       for (ResistanceNet rc : r) {
           seriesResistors.add(rc);
       }
    }

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

    @Override
    public int getNumberOfResistors() {
        int resistors = 0;
        for (ResistanceNet rc : seriesResistors) {
            resistors += rc.getNumberOfResistors();
        }
        return resistors;
    }

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
