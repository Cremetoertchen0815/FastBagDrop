package dhbw.group2.plane.boarding;

import dhbw.group2.plane.PlaneSeat;

import java.util.List;

public class PaperBoardingPass implements IBoardingPass {
    private final List<BaggageTag> baggageTags;
    private final BoardingQRCode qrCode;
    private final PlaneSeat seat;

    public PaperBoardingPass(List<BaggageTag> baggageTags, BoardingQRCode qrCode, PlaneSeat seat) {
        this.baggageTags = baggageTags;
        this.qrCode = qrCode;
        this.seat = seat;
    }

    public List<BaggageTag> getBaggageTags() {
        return baggageTags;
    }

    public PlaneSeat getSeat() {
        return seat;
    }
}
