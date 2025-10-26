package p1.a1x2.resistor;

public class Potentiometer extends Resistor{

    
    public Potentiometer (String name, double value) {
        super(name, value);
    }
    
    public Potentiometer (String name) {
        super(name, 0);
    }
    
    public void setResistance(double value) {
        super.wiederstandwert = value;
    }

    @Override
    public double getResistance() {
        return super.wiederstandwert;
    }

    @Override
    public int getNumberOfResistors() {
        return 1;
    }

    @Override
    public String getCircuit() {
        return super.name;
    }
    
}
