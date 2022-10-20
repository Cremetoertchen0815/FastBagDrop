package dhbw.group2.plane.boarding;

import java.util.List;

public class BoardingPass {
    private final List<BaggageTag> baggageTags;

    public BoardingPass(List<BaggageTag> baggageTags) { this.baggageTags = baggageTags; }

    public List<BaggageTag> getBaggageTags() {
        return baggageTags;
    }
}
