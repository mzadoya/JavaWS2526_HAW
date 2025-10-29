package p1.a1x2.resistor;

import java.util.*;

/**
 * Stellt zusammengesetze Widerstandnetze dar
 * 
 * @author Maksym Zadoya
 * @version 2025/10/27 #1
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
     * in der gleichen Reihenfolge in der diese an den Konstruktor übergeben wurden
     * 
     * 
     * @return ein Array der Widerstandsnetze
     */
    public ResistanceNet[] getSubNets() {
        return net;
    }
    
  
   
}
