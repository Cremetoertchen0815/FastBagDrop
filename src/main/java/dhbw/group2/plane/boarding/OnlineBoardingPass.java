package dhbw.group2.plane.boarding;

import dhbw.group2.plane.PlaneSeat;

import java.util.List;

public class OnlineBoardingPass implements IBoardingDataMedium, IBoardingPass {
    private String source;
    private String destination;
    private String flight;
    private List<BaggageTag> baggageTags;
    private PlaneSeat planeSeat;

    public void setPlaneSeat(PlaneSeat planeSeat) {
        this.planeSeat = planeSeat;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getFlight() {
        return flight;
    }

    public void setFlight(String flight) {
        this.flight = flight;
    }

    @Override
    public List<BaggageTag> getBaggageTags() {
        return baggageTags;
    }

    public void setBaggageTags(List<BaggageTag> baggageTags) {
        this.baggageTags = baggageTags;
    }

    @Override
    public PlaneSeat getSeat() {
        return planeSeat;
    }
}
