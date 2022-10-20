package dhbw.group2.automata.peripherals;

import dhbw.group2.plane.boarding.BaggageTag;
import dhbw.group2.plane.boarding.BoardingPass;

import java.util.List;

public class BaggageTagPrinter implements IPrinter<BaggageTag> {

    @Override
    public BaggageTag print() {
        return new BaggageTag();
    }
}