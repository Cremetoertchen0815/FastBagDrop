package dhbw.group2.automata.peripherals;

import dhbw.group2.plane.PlaneSeat;
import dhbw.group2.plane.boarding.BaggageTag;
import dhbw.group2.plane.boarding.BoardingQRCode;
import dhbw.group2.plane.boarding.IBoardingDataMedium;
import dhbw.group2.plane.boarding.PaperBoardingPass;

import java.util.List;

public class BoardingPassPrinter implements IPrinter<PaperBoardingPass>, IBoardingDataMedium {


    private String source;
    private String destination;
    private String flight;
    private List<BaggageTag> baggageTags;
    private PlaneSeat planeSeat;

    public void setBaggageTags(List<BaggageTag> baggageTags) {
        this.baggageTags = baggageTags;
    }

    public void setPlaneSeat(PlaneSeat planeSeat) {
        this.planeSeat = planeSeat;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setFlight(String flight) {
        this.flight = flight;
    }

    @Override
    public PaperBoardingPass print() {
        return new PaperBoardingPass(baggageTags, new BoardingQRCode(source, destination, flight), planeSeat);
    }
}
