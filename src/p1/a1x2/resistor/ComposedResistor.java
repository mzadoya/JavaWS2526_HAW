package p1.a1x2.resistor;

import java.util.*;

/**
 * Abstrakte Basisklasse fuer zusammengesetze Widerstandnetze
 * 
 * @author Maksym Zadoya
 * @version 2025/10/29 #1
 *  
 */
public abstract class ComposedResistor implements ResistanceNet{

    private ResistanceNet[] net;
    private List<ResistanceNet> composed = new ArrayList<ResistanceNet>();
    public ComposedResistor (ResistanceNet... nets) {
        int i = 0;
        for (ResistanceNet net : nets) {
            this.net[i++] = net;
        }
    }
    
    /**
     * Liefert ein Array der Widerstandnetze aus denen das zusammengesetze 
     * Widerstandnetz zusammengesetzt ist in der Original-Reihenfolge, also genau
     * in der gleichen Reihenfolge in der diese an den Konstruktor uebergeben wurden
     * 
     * 
     * @return ein Array der Widerstandsnetze
     */
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
    
}
