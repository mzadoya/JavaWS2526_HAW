package p1.a1x2.resistor;

public abstract class Resistor implements ResistanceNet {

   protected String name;
   protected double wiederstandwert;
   
   public Resistor (String name, double value) {
       this.name = name;
       this.wiederstandwert = value;
   }
}
