package p1.a1x2.resistor;

public class OrdinaryResistor extends Resistor{

    public OrdinaryResistor (String name, double value) {
        super(name, value);
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
