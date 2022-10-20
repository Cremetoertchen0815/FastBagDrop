package dhbw.group2.plane.boarding;

import dhbw.group2.plane.PlaneSeat;

import java.util.List;

public class BoardingPass {
    private final List<BaggageTag> baggageTags;

    private final PlaneSeat seat;

    public BoardingPass(List<BaggageTag> baggageTags, PlaneSeat seat) { this.baggageTags = baggageTags; this.seat = seat; }

    public List<BaggageTag> getBaggageTags() {
        return baggageTags;
    }

    public PlaneSeat getSeat() {
        return seat;
    }
}
