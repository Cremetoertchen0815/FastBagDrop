package dhbw.group2.automata.peripherals;

import dhbw.group2.humans.Baggage;

public class ConveyorBelt {
    private final BaggageSensor sensor;
    private Baggage currentBaggage;

    public ConveyorBelt() {
        sensor = new BaggageSensor();
        sensor.addListener(this::weighBaggage);
    }

    public void weighBaggage() {

    }
    public Baggage getCurrentBaggage() {
        return currentBaggage;
    }

    public void setCurrentBaggage(Baggage currentBaggage) {
        this.currentBaggage = currentBaggage;
        if (currentBaggage != null) sensor.trigger();
    }
}
