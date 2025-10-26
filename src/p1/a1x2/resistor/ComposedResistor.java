package p1.a1x2.resistor;

import java.util.*;

public abstract class ComposedResistor implements ResistanceNet{

    private ResistanceNet[] net;
    private List<ResistanceNet> composed = new ArrayList<ResistanceNet>();
    public ComposedResistor (ResistanceNet... nets) {
        int i = 0;
        for (ResistanceNet net : nets) {
            this.net[i++] = net;
        }
    }
    
    public ResistanceNet[] getSubNets() {
        return net;
    }
    
    @Override
    public int getNumberOfResistors() {
        int resistorsCounter = 0;
        for (ResistanceNet net : this.net) {
            resistorsCounter += net.getNumberOfResistors();
        }
        return resistorsCounter;
    }
    
    @Override
    public double getResistance() {
        double resistanceCounter = 0;
        for (ResistanceNet net : this.net) {
            resistanceCounter += net.getResistance();
        }
        return resistanceCounter;
    }
   
}
