package dhbw.group2.plane.boarding;

import dhbw.group2.plane.PlaneSeat;

import java.util.List;

public interface IBoardingDataMedium {

    void setBaggageTags(List<BaggageTag> baggageTags);

    void setPlaneSeat(PlaneSeat planeSeat);

    void setSource(String source);

    void setDestination(String destination);

    void setFlight(String flight);
}
