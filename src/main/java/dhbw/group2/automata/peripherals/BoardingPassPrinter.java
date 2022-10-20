package dhbw.group2.automata.peripherals;

import dhbw.group2.plane.boarding.BaggageTag;
import dhbw.group2.plane.boarding.BoardingPass;

import java.util.List;

public class BoardingPassPrinter implements IPrinter<BoardingPass> {


    private List<BaggageTag> baggageTags;

    public void setBaggageTags(List<BaggageTag> baggageTags) {
        this.baggageTags = baggageTags;
    }

    @Override
    public BoardingPass print() {
        return new BoardingPass(baggageTags);
    }
}
