package dhbw.group2.plane;

import dhbw.group2.humans.Passenger;
import dhbw.group2.plane.ticket.BookingClass;

public class AirbusA350_900SeatMap implements IPlaneSeatMap {

    private final Passenger[] seatMatrix = new Passenger[41 * ('K' - 'A')];

    @Override
    public PlaneSeat findSeat(BookingClass passangerClass) {
        int startRow;
        int endRow;
        switch (passangerClass) {

            case B -> {
                startRow = 1;
                endRow = 8;
            }
            case P -> {
                startRow = 12;
                endRow = 15;
            }
            default -> {
                startRow = 16;
                endRow = 42;
            }
        }

        for (int i = startRow; i < endRow; i++) {
            for (char j = 'A'; j < 'K'; j++) {
                var nuSeat = new PlaneSeat(i, j);
                if (isSeatValid(nuSeat, passangerClass) && !isSeatReserved(nuSeat)) return nuSeat;
            }
        }

        return null;
    }

    @Override
    public boolean isSeatReserved(PlaneSeat seat) {
        return seatMatrix[(seat.row() - 1) * ('K' - 'A') + seat.seat() - 'A'] != null;
    }

    @Override
    public boolean isSeatValid(PlaneSeat seat, BookingClass passangerClass) {
        switch (passangerClass) {

            case B -> {
                if (seat.row() > 8 || seat.row() < 1) return false;
                if ("ACDGKH".indexOf(seat.seat()) < 0) return false;
            }
            case P -> {
                if (seat.row() > 15 || seat.row() < 12) return false;
                if ("ACDEGHK".indexOf(seat.seat()) < 0) return false;
            }
            case E -> {
                if (seat.row() > 42 || seat.row() < 16 || seat.row() == 17) return false;
                var pattern = switch (seat.row()) {
                    case 16 -> "ACDEGKH";
                    case 27 -> "BCDEGHJ";
                    case 42 -> "DEG";
                    default -> "ABCDEGHJK";
                };
                if (pattern.indexOf(seat.seat()) < 0) return false;
            }
        }
        return true;
    }

    @Override
    public void reserveSeat(Passenger who, PlaneSeat seat) {
        if (isSeatReserved(seat)) throw new RuntimeException("Seat already reserved!");
        seatMatrix[(seat.row() - 1) * ('K' - 'A') + seat.seat() - 'A'] = who;
    }
}
