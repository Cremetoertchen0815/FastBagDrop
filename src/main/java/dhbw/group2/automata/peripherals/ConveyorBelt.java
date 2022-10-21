package dhbw.group2.automata.peripherals;

import dhbw.group2.plane.boarding.Baggage;

public class ConveyorBelt {
    private final BaggageSensor sensor;
    private final Display weighDisplay;
    private Baggage currentBaggage;
    private float measuredWeight;

    public ConveyorBelt() {
        weighDisplay = new Display();
        sensor = new BaggageSensor();
        sensor.addListener(this::weighBaggage);
    }

    public void weighBaggage() {
        measuredWeight = currentBaggage.getWeight();
        weighDisplay.printMessage("Weight: " + measuredWeight + " kg");
    }

    public float getMeasuredWeight() {
        return measuredWeight;
    }

    public Baggage getCurrentBaggage() {
        return currentBaggage;
    }

    public void setCurrentBaggage(Baggage currentBaggage) {
        this.currentBaggage = currentBaggage;
        if (currentBaggage != null) sensor.trigger();
    }
}
