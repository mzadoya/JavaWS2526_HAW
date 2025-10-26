package p1.a1x2.resistor;

import java.util.*;

public class ParallelResistor extends ComposedResistor{

    private List<ResistanceNet> paralleleResistors = new ArrayList<ResistanceNet>();
    public ParallelResistor(ResistanceNet... r) {
        for (ResistanceNet rc : r) {
            paralleleResistors.add(rc);
        }
    }
    
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

    @Override
    public int getNumberOfResistors() {
        int resistors = 0;
        for (ResistanceNet rc : paralleleResistors) {
            resistors += rc.getNumberOfResistors();
        }
        return resistors;
    }

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
