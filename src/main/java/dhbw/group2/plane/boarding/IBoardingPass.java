package dhbw.group2.plane.boarding;

import dhbw.group2.plane.PlaneSeat;

import java.util.List;

public interface IBoardingPass {

    List<BaggageTag> getBaggageTags();

    PlaneSeat getSeat();
}
