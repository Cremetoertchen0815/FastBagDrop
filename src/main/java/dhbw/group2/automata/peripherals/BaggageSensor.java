package dhbw.group2.automata.peripherals;

import java.util.ArrayList;

public class BaggageSensor {

    private final ArrayList<IBaggageDetectorListener> listeners = new ArrayList<>();
    public void trigger() {
        for (var listener : listeners) listener.baggagePlacedOnConveyor();
    }

    public void addListener(IBaggageDetectorListener listener) {
        listeners.add(listener);
    }

    public void removeListener(IBaggageDetectorListener listener) {
        listeners.remove(listener);
    }
}
