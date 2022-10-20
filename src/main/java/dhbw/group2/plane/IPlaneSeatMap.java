package dhbw.group2.plane;

import dhbw.group2.humans.Passenger;

public interface IPlaneSeatMap {
    PlaneSeat findSeat(PassangerClass passangerClass);
    boolean isSeatReserved(PlaneSeat seat);
    boolean isSeatValid(PlaneSeat seat, PassangerClass passangerClass);
    void reserveSeat(Passenger who, PlaneSeat seat);
}
