package p1.a1x2.resistor;

/**
 * Eine abstrakte Elternklasse fuer alle einfachen Widerstaende
 * 
 * Speicher gemeinsame Eigenschaften wie Name und Widerstandswert und bietet 
 * eine gemeinsame Grundlage fuer OrdinaryResistor und Potentiometer.
 * 
 * @author Maksym Zadoya
 * @version 2025/10/28 #1
 *  
 */
public abstract class Resistor implements ResistanceNet {

   protected String name;
   protected double wiederstandwert;
   
   public Resistor (String name, double value) {
       this.name = name;
       this.wiederstandwert = value;
       
   }
}
