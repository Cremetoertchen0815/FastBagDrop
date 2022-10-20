package dhbw.group2.plane;

import dhbw.group2.humans.Passenger;
import dhbw.group2.plane.ticket.BookingClass;

public interface IPlaneSeatMap {
    PlaneSeat findSeat(BookingClass passangerClass);
    boolean isSeatReserved(PlaneSeat seat);
    boolean isSeatValid(PlaneSeat seat, BookingClass passangerClass);
    void reserveSeat(Passenger who, PlaneSeat seat);
}
